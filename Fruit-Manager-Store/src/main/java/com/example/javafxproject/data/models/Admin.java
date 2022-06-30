package com.example.javafxproject.data.models;

public class Admin {
    int id;
    public String name;
    public String password;

    public Admin(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}