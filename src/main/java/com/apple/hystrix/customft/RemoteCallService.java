package com.apple.hystrix.customft;

public interface RemoteCallService {
    String call(String request) throws Exception;
}