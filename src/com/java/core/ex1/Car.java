package com.java.core.ex1;

public class Car implements Vehicle, FourWheeler {

	double position;
	double steerPosition;
	double accelaration;
	double friction;
	double braking;
	boolean motion;
	boolean brakeAble;

	Car() {
		this.steerPosition= FourWheeler.steerPosition;
		this.position= Vehicle.position;
		this.accelaration= Vehicle.accelaration;
		this.friction = Vehicle.friction;
		this.braking = Vehicle.braking;
		this.brakeAble= false;
	}

	@Override
	public synchronized boolean steerLeft() throws InterruptedException {
		double pos= this.steerPosition;
		pos -= 0.5;
		if(pos < -3.0)
			return false;

		this.steerPosition= pos;
		wait(20);
		
		return true;
	}

	@Override
	public synchronized boolean steerRight() throws InterruptedException {
		double pos= this.steerPosition;
		pos += 0.5;
		if(pos > 3.0)
			return false;

		this.steerPosition= pos;
		wait(100);

		return true;
	}

	@Override
	public synchronized boolean moveForward() throws InterruptedException {

		while(this.accelaration > 0) {
			
			this.motion= true;
			System.out.println("In motion..."+this.position);
			this.position += this.accelaration;
			this.accelaration -= this.friction;

			if(this.position > 10.0) {
				//System.out.println("Car Crashed !!!");
				this.motion= false;
				return false;
			} 
			else if(this.motion && this.position + 2 * this.accelaration > 10.0) { //Check for future state and notify app.
				System.out.println("Sensing danger ahead, apply brakes...");
				this.brakeAble= true;
				notifyAll();
				//wait(100);
			}

			wait(50); //release lock and wait for 100 msec
		}
		this.motion= false;
		notifyAll();
		
		return true;
	}

	@Override
	public synchronized boolean moveBackward() throws InterruptedException {

		while(this.accelaration > 0) {
			
			this.motion= true;
			System.out.println("In motion..."+this.position);
			this.position -= this.accelaration;
			this.accelaration -= this.friction;

			if(this.position < -10.0) {
				//System.out.println("Car Crashed !!!");
				this.motion= false;
				return false;
			} 
			else if(this.position - 3 * this.accelaration < -10.0) { //Check for future state and notify app.
				System.out.println("Sensing danger ahead, apply brakes...");
				this.brakeAble= true;
				notifyAll();
				//wait(100);
			}

			wait(100);	//release lock and wait for 100 msec
		}
		this.motion= false;
		notifyAll();
		
		return true;
	}

	public synchronized boolean applyBrakes() throws InterruptedException {
		while(this.motion) 
		{
			if(this.brakeAble) {
				this.accelaration -= this.braking;
				System.out.println("Applying brakes...");
				wait(50);
				
				//System.out.println("Car safely stopped...");
				//return true;	//Brake applied
			}
			else {
				wait();
			}
		}
		this.motion= false;
		return false;	//Brake not used
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car [steerPosition=").append(steerPosition).append(", position=").append(position).append("]");
		return builder.toString();
	}



}
