package com.service.user.Model;

public class UserLoginRegisterDTO {
    private String email;
    private String password;

    public UserLoginRegisterDTO() {
    }

    public UserLoginRegisterDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
