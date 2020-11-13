package com.argo.gateway.User.Application.dto;

import com.argo.gateway.User.Roles.domain.en.roles_enum;

public class createAccount {
    public String username;
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
