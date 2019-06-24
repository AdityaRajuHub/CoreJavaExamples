package com.mthread.advanced;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.generics.ThreadsDemo;

public class ThreadPoolDemo {
	
	public static void main(String[] args) {
		/*for(int i=0; i<10;i++)
			new Thread(new Task()).start();*/
		System.out.println("Avalbl cores: "+Runtime.getRuntime().availableProcessors());
		
		ExecutorService service= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		
		for(int i=0; i<10;i++)
			service.execute(new Task());
		
		service.shutdown();
		
		System.out.println("isServiceShutdown(): "+service.isShutdown());
	}

}

class Task implements Runnable
{
	@Override
	public void run() {
		try {
			long sleepFor= new Double((Math.random()*100000)%10000).longValue();
			System.out.println("Sleep for: "+ sleepFor);
			Thread.sleep(sleepFor);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Hello from "+Thread.currentThread().getName());
	}
}
