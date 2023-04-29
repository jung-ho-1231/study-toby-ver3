package com.example.toby_spring.user.dao;

import java.sql.Connection;

public interface ConnectionMaker {
    Connection makeNewConnection();
}
