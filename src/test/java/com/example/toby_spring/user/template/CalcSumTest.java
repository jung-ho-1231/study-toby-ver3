package com.example.toby_spring.user.template;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalcSumTest {
    @Test
    void sumOfNumbers() throws Exception {
        Calculator calculator = new Calculator();
        int sum = calculator.calcSum(getClass().getResource("/numbers.txt").getPath());
        Assertions.assertThat(sum).isEqualTo(10);
    }
}
