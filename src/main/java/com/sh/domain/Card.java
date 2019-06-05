package com.sh.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Card {
    private int id;
    private String name;
    private String sex;
    private String department;
    private String mobile;
    private String phone;
    private String email;
    private String address;
    private String flag;

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", department='" + department + '\'' +
                ", mobie='" + mobile + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
