package com.java.core.ex1;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Car car= new Car();

		Vehicle v= (Vehicle) car;
		FourWheeler four= (FourWheeler) car;

		Thread t1= new Thread(() -> {
			try {
				v.moveForward();
			} catch (InterruptedException e) {}});

		Thread t2= new Thread(() -> {
			for(int i=0;i<10;i++) {
				System.out.println("Steering left...");
				try {
					if(!four.steerLeft())
						break;
				} catch (InterruptedException e) {}
			}
		});
		
		Thread t3= new Thread(() -> {
			try {
				car.applyBrakes();
			} catch (InterruptedException e) {}
		});

		t1.start();
		t2.start();
		t3.start();
		
		t1.join();
		t2.join();
		t3.join();
		
		//System.out.println(car.toString());
		System.out.println("-----------------------");
		if(car.position>10)
			System.out.println("Car crashed !!!");
		else
			System.out.println("Car parked safely...");
	}
}
