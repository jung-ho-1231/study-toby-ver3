package com.example.toby_spring.user.dao;

import lombok.Getter;

import java.sql.Connection;

public class CountingConnectionMaker implements ConnectionMaker {
    @Getter
    int counter = 0;
    private final ConnectionMaker realConnectionMaker;

    public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
        this.realConnectionMaker = realConnectionMaker;
    }

    @Override
    public Connection makeNewConnection() {
        counter++;
        return realConnectionMaker.makeNewConnection();
    }
}
