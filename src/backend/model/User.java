package backend.model;

import java.util.UUID;

public class User {
    private String name;
    private String username;
    private String id;
    private Integer phoneNumber;
    private String password;

    public User() {
    }

    public User(String name, String username, Integer phoneNumber, String password) {
        this.name = name;
        this.username = username;
        this.id = UUID.randomUUID().toString();
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Your name: " + name + '\n' + "@" + username + '\n' + "+998 " + phoneNumber;
    }
}
