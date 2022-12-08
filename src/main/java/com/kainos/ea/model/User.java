package com.kainos.ea.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private int role;
    private String email, password, salt;
    public User(){

    }

    public User (@JsonProperty("email") String email,
                 @JsonProperty("password") String password,
                 @JsonProperty("role") int role,
                 @JsonProperty("salt") String salt)

    {
        this.email = email;
        this.password = password;
        this. role = role;
        this.salt = salt;
    }
    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
