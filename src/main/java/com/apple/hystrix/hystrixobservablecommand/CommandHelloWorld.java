package com.apple.hystrix.hystrixobservablecommand;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

public class CommandHelloWorld extends HystrixObservableCommand<String> {

	private final String name;

	public CommandHelloWorld(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.name = name;
	}

	@Override
	protected Observable<String> construct() {
		return Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> observer) {
				try {
					if (!observer.isUnsubscribed()) {
						// a real example would do work like a network call here
						observer.onNext("Hello");
						observer.onNext(name + "!");
						observer.onCompleted();
					}
				} catch (Exception e) {
					observer.onError(e);
				}
			}
		});
	}

	public static void main(String[] args) {
		Observable<String> fWorld = new CommandHelloWorld("World").observe();
		Observable<String> fBob = new CommandHelloWorld("Bob").observe();

		// // blocking
		// System.out.println(fWorld.toBlocking().single());
		// System.out.println(fBob.toBlocking().single());

		// non-blocking
		// - this is a verbose anonymous inner-class approach and doesn't do
		// assertions
		 fWorld.subscribe((v) -> {
		        System.out.println("onNext: " + v);
		    }, (exception) -> {
		        exception.printStackTrace();
		    },() -> {}
		    
				 );

		// non-blocking
		// - also verbose anonymous inner-class
		// - ignore errors and onCompleted signal
		fBob.subscribe(new Action1<String>() {

			@Override
			public void call(String v) {
				System.out.println("onNext: " + v);
			}

		});

	}
}