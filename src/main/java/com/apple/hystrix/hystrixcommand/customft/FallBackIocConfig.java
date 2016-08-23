package com.apple.hystrix.hystrixcommand.customft;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;

@Configuration
@EnableAspectJAutoProxy
public class FallBackIocConfig {

	@Bean
	public HystrixCommandAspect hystrixCommandAspect() {
		System.out.println("Initializing Hystrix Annotation");
		return new HystrixCommandAspect();
	}

	@Bean
	public RemoteCallService remoteCallService() {
		System.out.println("Initializing Remote Service");
		return new FallBackAnnotation();
	}

}
