package com.example.toby_spring.learningTest;

import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

public class PointCutTest {

    @Test
    void methodsSignaturePointCut() {
        AspectJExpressionPointcut pointCut = new AspectJExpressionPointcut();

        // TargetInterface의 모든 메소드를 대상으로 한다.
        pointCut.setExpression("execution(public int com.example.toby_spring.learningTest.Target.minus(int, int) throws java.lang.RuntimeException)");

        // Target.minus()
        // Target.plus()
        // Target.method()
        // TargetInterface.hello()

    }
}
