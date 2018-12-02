package com.example.a9onhud.listviewapplication;

public class Person {
    private String name, nickname, surname;

    public Person(String name, String nickname, String surname) {
        this.name = name;
        this.nickname = nickname;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
