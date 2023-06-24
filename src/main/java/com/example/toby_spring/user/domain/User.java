package com.example.toby_spring.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class User {
    private String id;
    private String name;
    private String password;

    private Level level;
    private int login;
    private int recommend;


    public void upgradeLevel() {
        Level nextLevel = this.level.nextLevel();
        if (nextLevel == null) throw new IllegalStateException(this.level + "은 업그레이드가 불가능합니다.");
        else this.level = nextLevel;
    }

    public String getEmail() {
        return this.id + "@test.com";
    }
}
