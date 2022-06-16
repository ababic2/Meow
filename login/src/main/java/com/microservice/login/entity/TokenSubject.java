package com.microservice.login.entity;

public class TokenSubject {
    int userRole;
    Long userId;
    String createdAt;
    int validity;

    public TokenSubject(){}

    public TokenSubject(int userRole, Long userId, String createdAt, int validity) {
        this.userRole = userRole;
        this.userId = userId;
        this.createdAt = createdAt;
        this.validity = validity;
    }
    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getValidity() {
        return validity;
    }

    public void setValidity(int validity) {
        this.validity = validity;
    }
}

