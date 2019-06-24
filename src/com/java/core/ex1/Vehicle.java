package com.java.core.ex1;

public interface Vehicle {
	
	double position= 0.0; //global variables are public, static and final
	double accelaration= 2.5;
	double friction= 0.1;
	double braking= 0.5;
	
	public boolean moveForward() throws InterruptedException;
	
	public boolean moveBackward() throws InterruptedException;
	
}
