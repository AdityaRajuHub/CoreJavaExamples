package com.mthread.basic;

public class ProducerConsumer {

	public static void main(String[] args) {
		Q q = new Q();
		new Producer(q);
		new Consumer(q);
	}
}

class Q {

	int num=0;
	boolean produced= false;

	synchronized void produce() throws InterruptedException {
		if(!produced) {
			num++;
			System.out.println("Produced..."+num);
			produced= true;
			notify();
		} else {
			System.out.println("Waiting for consumer to consume...");
			wait();
		}
	}

	synchronized void consume() throws InterruptedException {
		if(produced) {
			num--;
			System.out.println("Consumed..."+num);
			produced= false;
			notify();
		} else {
			System.out.println("Waiting for producer to produce...");
			wait();
		}
	}
}

class Producer implements Runnable{

	Q q;

	Producer(Q q) {
		this.q= q;
		Thread t1= new Thread(this, "Producer");
		t1.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				q.produce();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}

}

class Consumer implements Runnable {

	Q q;

	Consumer(Q q) {
		this.q= q;
		Thread t1= new Thread(this, "Consumer");
		t1.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				q.consume();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
		}
	}

}
