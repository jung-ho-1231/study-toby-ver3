package com.example.toby_spring.learningTest.factoryBean;

import com.example.toby_spring.learningTest.jdk.Hello;
import com.example.toby_spring.learningTest.jdk.HelloTarget;
import com.example.toby_spring.learningTest.jdk.UppercaseHandler;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import java.lang.reflect.Proxy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DynamicProxyTest {

    @Test
    void simpleProxy() {
        Hello proxiedHello = (Hello) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{Hello.class},
                new UppercaseHandler(new HelloTarget())
        );
    }


    @Test
    void proxyFactoryBean() {
        ProxyFactoryBean pdBean = new ProxyFactoryBean();
        pdBean.setTarget(new HelloTarget()); // 타깃 설정
        pdBean.addAdvice(new UppercaseAdvice()); // 부가기능을 담은 어드바이스를 추가한다. 여러 개를 추가할 수도 있다.[

        Hello proxiedHello = (Hello) pdBean.getObject(); // FactoryBean이므로 getObject()로 생성된 프록시를 가져온다.

        Assertions.assertThat(proxiedHello.sayHello("Toby")).isEqualTo("HELLO TOBY");
        Assertions.assertThat(proxiedHello.sayHi("Toby")).isEqualTo("HI TOBY");
        Assertions.assertThat(proxiedHello.sayThankYou("Toby")).isEqualTo("THANK YOU TOBY");
    }

    @Test
    void pointCutAdvisor() {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());

        // 메소드 이름을 비교해서 대상을 선정하는 알고리즘을 제공하는 포인트컷 생성
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();

        // 이름 비교조건 설정. sayH로 시작하는 모든 메소드를 선택하게 한다.
        pointcut.setMappedName("sayH*");

        // 포인트컷과 어드바이스를 Advisor로 묶어서 한 번에 추가
        pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));

        Hello proxiedHello = (Hello) pfBean.getObject();

        Assertions.assertThat(proxiedHello.sayHello("Toby")).isEqualTo("HELLO TOBY");
        Assertions.assertThat(proxiedHello.sayHi("Toby")).isEqualTo("HI TOBY");
        Assertions.assertThat(proxiedHello.sayThankYou("Toby")).isEqualTo("Thank You Toby");

    }


    @Test
    void classNamePointCutAdvisor() {
        // 포인트 컷 준비
        NameMatchMethodPointcut classMethodPointCut = new NameMatchMethodPointcut() {
            @Override
            public ClassFilter getClassFilter() { // 익명 내부 클래스 방식으로 클래스 정의
                // 클래스 이름이 HelloT로 시작하는 것만 선정한다.
                return clazz -> clazz.getSimpleName().startsWith("HelloT");
            }
        };


        classMethodPointCut.setMappedName("sayH*"); // sayH로 시작하는 메소드 이름을 가진 메소드만 선정한다.

        // 테스트
        checkAdviced(new HelloTarget(), classMethodPointCut, true); // 적용 클래스
        class HelloWorld extends HelloTarget { };
        checkAdviced(new HelloWorld(), classMethodPointCut, false); // 적용 클래스가 아닌 경우
        class HelloToby extends HelloTarget { };
        checkAdviced(new HelloToby(), classMethodPointCut, true); // 적용 클래스


    }

    private void checkAdviced(Object target, Pointcut pointCut, boolean adviced) {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(target);
        pfBean.addAdvisor(new DefaultPointcutAdvisor(pointCut, new UppercaseAdvice()));
        Hello proxiedHello = (Hello) pfBean.getObject();

        if (adviced) {
            Assertions.assertThat(proxiedHello.sayHello("Toby")).isEqualTo("HELLO TOBY");
            Assertions.assertThat(proxiedHello.sayHi("Toby")).isEqualTo("HI TOBY");
            Assertions.assertThat(proxiedHello.sayThankYou("Toby")).isEqualTo("Thank You Toby");
        }
        else {
            Assertions.assertThat(proxiedHello.sayHello("Toby")).isEqualTo("Hello Toby");
            Assertions.assertThat(proxiedHello.sayHi("Toby")).isEqualTo("Hi Toby");
            Assertions.assertThat(proxiedHello.sayThankYou("Toby")).isEqualTo("Thank You Toby");
        }
    }
}
