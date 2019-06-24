package com.java.core.ex1;

public interface FourWheeler {

	double steerPosition= 0.0; //global variables are public, static and final
	
	public boolean steerLeft() throws InterruptedException;
	
	public boolean steerRight() throws InterruptedException;
	
}
