package com.example.toby_spring.user.service;

import com.example.toby_spring.user.domain.User;

public interface UserService {
    void add(User user);
    void upgradeLevels();
}
