package com.example.toby_spring.user.factoryBean;

import org.springframework.beans.factory.FactoryBean;


public class MessageFactoryBean implements FactoryBean<Message> {
    final String text;

    public MessageFactoryBean(String text) {
        this.text = text;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public Message getObject() throws Exception {
        return Message.newMessage(text);  // 팩토리 빈이 생성하는 오브젝트를 직접 생성
    }

    @Override
    public Class<?> getObjectType() {
        return Message.class;
    }
}
