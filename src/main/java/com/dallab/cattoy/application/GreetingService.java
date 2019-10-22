package com.dallab.cattoy.application;

// service는 실제 기능은 appication 부분이다.
public class GreetingService {

    public String getMessage(String name) {
        if(name == null) {
            return "Hello";
        }
        return "Hello, " + name;
    }
}
