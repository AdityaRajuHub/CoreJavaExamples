package com.mthread.basic;

public class ThreadDemo {
	
	public static void main(String[] args) {
		for(int i=0; i<10;i++)
			new Thread(new Task()).start();
	}

}

class Task implements Runnable
{
	@Override
	public void run() {
		try {
			//wait(new Double(Math.random()%10).longValue());
			System.out.println(new Double((Math.random()*31)%10000).longValue());
			Thread.sleep(new Double((Math.random()*319999)%10000).longValue());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Hello from "+Thread.currentThread().getName());
	}
}
