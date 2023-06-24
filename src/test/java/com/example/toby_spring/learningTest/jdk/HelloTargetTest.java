package com.example.toby_spring.learningTest.jdk;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class HelloTargetTest {

    @Test
    void simpleProxy() {
        Hello hello = new HelloTarget();

        assertEquals("Hello Toby", hello.sayHello("Toby"));
        assertEquals("Hi Toby", hello.sayHi("Toby"));
        assertEquals("Thank you Toby", hello.sayThankYou("Toby"));


        Hello proxiedHello = new HelloUppercase(new HelloTarget());
        Assertions.assertThat(proxiedHello.sayHello("Toby")).isEqualTo("HELLO TOBY");
        Assertions.assertThat(proxiedHello.sayHi("Toby")).isEqualTo("HI TOBY");
        Assertions.assertThat(proxiedHello.sayThankYou("Toby")).isEqualTo("THANK YOU TOBY");
    }

    @Test
    void createHelloProxy() {
        Hello proxiedHello = (Hello) Proxy.newProxyInstance(
                getClass().getClassLoader(), // 동적으로 생성되는 다이내믹 프록시 클래스의 로딩에 사용할 클래스 로더
                new Class[]{Hello.class}, // 구현할 인터페이스
                new UppercaseHandler(new HelloTarget()) // 부가기능과 위임 코드를 담은 InvocationHandler
        );

        Assertions.assertThat(proxiedHello).isNotNull();
    }


}