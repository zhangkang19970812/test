package com.nju.account_service.restController.vo;

public class UserVO {
    String username;
    int id;
    String password;
    String email;

    public UserVO(String username, int id, String password, String email) {
        this.username = username;
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {

        return username;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
