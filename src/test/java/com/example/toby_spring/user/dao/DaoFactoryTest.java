package com.example.toby_spring.user.dao;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DaoFactoryTest {

    @Test
    void context_test() throws Exception {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(CountingDaoFactory.class);

        CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("ccm.getCounter() = " + ccm.getCounter());
    }

}