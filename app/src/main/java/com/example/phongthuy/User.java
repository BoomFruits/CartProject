package com.example.phongthuy;

public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String username;
    private String password;
    private String registerDate;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRegisterDate() {
        return registerDate;
    }
    public User(){}
    public User(String name,String username, String password, String registerDate) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.registerDate = registerDate;
    }
}
