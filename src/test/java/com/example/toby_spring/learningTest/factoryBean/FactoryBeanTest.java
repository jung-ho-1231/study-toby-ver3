package com.example.toby_spring.learningTest.factoryBean;

import com.example.toby_spring.user.factoryBean.Message;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class FactoryBeanTest {

    @Autowired
    ApplicationContext context;

    @Test
    void getMessageFromFactoryBean() {
        Object message = context.getBean("message");
        Assertions.assertThat(message).isInstanceOf(Message.class);
        Assertions.assertThat(((Message) message).getText()).isEqualTo("Factory Bean");
    }
}
