package com.generics;

class GenericsDemo {
	
	public static void main(String[] args) {
		GenericsDemo demo= new GenericsDemo();
		//Container<Integer> container= demo.new Container<Integer>(10);
		Container<Integer> container= new Container<Integer>(10);
		System.out.println(container.getValue());
	}

	/*class Container<T extends Number>{
		
		private T value;
		public Container() {
		}
		public Container(T value) {
			this.setValue(value);
		}
		
		public T getValue() {
			return value;
		}
		public void setValue(T value) {
			this.value = value;
		}
	}*/
}

class Container<T extends Number>{
	
	private T value;
	public Container() {
	}
	public Container(T value) {
		this.setValue(value);
	}
	
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
}