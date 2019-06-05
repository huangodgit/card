package com.sh.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class User {
    private int id;
    private String userName;
    private String userPassword;
    private String userRealName;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRealName='" + userRealName + '\'' +
                '}';
    }
}
