package com.liuhg.entity;


public class Admin {

    private String id;
    private String username;
    private String password;
    private String status;
    private String salt;
    private String level;

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", salt='" + salt + '\'' +
                ", level='" + level + '\'' +
                '}';
    }

    public Admin(String id, String username, String password, String status, String salt, String level) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.salt = salt;
        this.level = level;
    }

    public Admin() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
