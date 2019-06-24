package com.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

public class LamdaDemo {
	
	public static void main(String[] args) {
		/*Phone phone= new Phone(){
			@Override
			public void showVersion(String type, double version) {
				System.out.println("The phone is "+type +" of version: "+version);
			}
		};*/
		
		Phone phone= (t, v) -> out.println("The phone is "+t +" of version: "+v);
		phone.showDevice();
		phone.showVersion("Android", 2.1);
		
		List<Integer> numList= new LinkedList<Integer>();
		
		for(int i=0;i<10;i++)
			numList.add(i);
		
		Stream<Integer> numStream= numList.isEmpty() ? Stream.empty() : numList.parallelStream();
		numStream.filter(t -> t%2 == 0 ? true : false)
						//.forEach(System.out::println);
							.forEach(t -> out.println("The even value is: "+t));
		System.out.println("It is printed bfore stream");
		/*
		Cannot use numStream once again
		an attempt to reuse the same reference after calling the terminal operation will trigger the IllegalStateException:
		As the IllegalStateException is a RuntimeException, a compiler will not signalize about a problem. So, it is very important to remember that Java 8 streams can’t be reused.

		This kind of behavior is logical because streams were designed to provide an ability to apply a 
		finite sequence of operations to the source of elements in a functional style, but not to store elements.
*/		
		//identity reduce
		System.out.println("normal   stream reduced::::"+numList.stream().reduce((a,b) -> a+b)); //45
		
		//Aggregator
		System.out.println("normal   stream reduced::::"+numList.stream().reduce(10, (a,b) -> a+b));			//10+0+1+2+3+...+9 = 55
		System.out.println("parallel stream reduced::::"+numList.parallelStream().reduce(10, (a,b) -> a+b));	//(10+0) + (10+1) + (10+2) + ... + (10+9) = 155
		
		//Combiner
		System.out.println(numList.parallelStream().reduce(10, (a,b) -> a+b, (a,b) -> {
			out.println("combiner called:::::::a: "+a+" b: "+b);
			return a+b;
		}));
		
		List<Product> prodList= new ArrayList<Product>();
		for(int i=1;i<=10;i++) {
			prodList.add(new Product("Prod"+i, (float)(i+10.00), Arrays.asList("groceries"+i, "general"+i)));
		}
		
		//flatMap works by applying map on every element of the list and then flattens the result
		//Stream<String> typesStream= prodList.stream().flatMap(t -> Stream.of(t.getTypes())).flatMap(t-> t.stream()); //Stream<List<Product>> -> Stream<List<String>> 
		Stream<String> typesStream= prodList.stream().flatMap(t -> t.getTypes().stream()); //map(Stream<List<Product>>) -> Stream<List<String>> -> flat(Stream<List<String>>) -> List<String>
		System.out.println(typesStream.collect(Collectors.toList()));
		
		
		
		
		
		
		
				/*
				 * 
				 * 
		numStream.sorted((t1, t2) -> t1>t2?-1:1)
		.forEach(out::println);
		
		
		 * reduce to a single value
		 * Here ans variable is assigned 0 as the initial value and i is added to it .
		 
		System.out.println(numStream.reduce(0, (ans, i)-> ans+i).intValue());
		
		Stream<Integer> val= numStream.filter(t -> t/5==1);
		val.collect(Collectors.toList());*/
		//val.orElse(0);
						
		
	}
}

interface Phone {
	default void showDevice() {
		System.out.println("This is Phone device...");
	}
	
	void showVersion(String type, double version);
}