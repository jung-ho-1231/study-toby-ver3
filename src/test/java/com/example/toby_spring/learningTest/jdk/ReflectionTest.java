package com.example.toby_spring.learningTest.jdk;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class ReflectionTest {

    @Test
    void invokeMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String name = "Spring";

        // length()
        assertThat(name.length()).isEqualTo(6);

        Method length = String.class.getMethod("length");
        assertThat((Integer) length.invoke(name)).isEqualTo(6);


        // charAt()
        assertThat(name.charAt(0)).isEqualTo('S');

        Method charAt = String.class.getMethod("charAt", int.class);
        assertThat((Character) charAt.invoke(name, 0)).isEqualTo('S');
    }



}
