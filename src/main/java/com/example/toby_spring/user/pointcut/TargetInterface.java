package com.example.toby_spring.user.pointcut;

public interface TargetInterface {
    void hello();
    void hello(String a);
    int minus(int a, int b) throws RuntimeException;
    int plus(int a, int b);
    void method();
}
