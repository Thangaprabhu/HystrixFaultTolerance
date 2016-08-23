package com.apple.hystrix.customft;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestRemoteCallServiceHystrix {
	@Autowired
	private RemoteCallService remoteCallService;

	@Test
	public void testRemoteCall() throws Exception {
		assertThat(this.remoteCallService.call("test"), is("FALLBACK Success: test"));
		assertThat(this.remoteCallService.call("test"), is("FALLBACK Success: test"));
		assertThat(this.remoteCallService.call("test"), is("test"));
	}

	@Configuration
	@EnableAspectJAutoProxy
	public static class SpringConfig {
		@Bean
		public HystrixCommandAspect hystrixCommandAspect() {
			System.out.println("Initializing Hystrix Annotation");
			return new HystrixCommandAspect();
		}

		@Bean
		public RemoteCallService remoteCallService() {
			System.out.println("Initializing Remote Service");
			return new DummyRemoteCallService();
		}
	}
}