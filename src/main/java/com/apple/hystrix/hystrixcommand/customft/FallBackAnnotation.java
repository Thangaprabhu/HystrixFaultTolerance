package com.apple.hystrix.hystrixcommand.customft;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

public class FallBackAnnotation implements RemoteCallService {

	@Override
	@HystrixCommand(fallbackMethod = "getFallback")
	public String call(String name) {
		throw new RuntimeException("this command always fails");
	}

	protected String getFallback(String name) {
		return "Inside Fallback ";
	}

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(FallBackIocConfig.class);
		RemoteCallService rt = (RemoteCallService) ctx.getBean(RemoteCallService.class);
		String result;
		try {
			result = rt.call("Prabhu");
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
