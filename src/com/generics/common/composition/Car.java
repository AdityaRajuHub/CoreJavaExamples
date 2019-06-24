package com.generics.common.composition;

public class Car {

	private final Engine engine;
	
	Car(String engineType) {
		this.engine= new Engine();
		this.engine.setEngineType(engineType);
	}
	
}
