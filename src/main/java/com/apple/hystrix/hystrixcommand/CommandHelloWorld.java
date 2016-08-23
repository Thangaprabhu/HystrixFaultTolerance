package com.apple.hystrix.hystrixcommand;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandHelloWorld extends HystrixCommand<String> {

	private final String name;

	public CommandHelloWorld(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.name = name;
	}

	@Override
	protected String run() {
		// a real example would do work like a network call here
		return "Hello " + name + "!";
	}

	public static void main(String[] args) {
		System.out.println(callSynchronous());
		System.out.println(callASynchronous());
	}

	public static String callSynchronous() {
		return new CommandHelloWorld("World").execute();
	}

	public static String callASynchronous() {
		Future<String> fs = new CommandHelloWorld("World").queue();
		try {
			return fs.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
