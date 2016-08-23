package com.apple.hystrix.hystrixcommand;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class FallBack extends HystrixCommand<String> {

    private final String name;

    public FallBack(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        throw new RuntimeException("this command always fails");
    }

    @Override
    protected String getFallback() {
        return "Hello Failure " + name + "!";
    }
    public static void main(String[] args) {
		String result = new FallBack("World").execute();
		System.out.println(result);
	}
}