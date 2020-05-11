package me.dnorris.server.user.data;

public class User {

    private final long id;
    private final String username;
    private final String email;
    private final String name;
    private final String password;


    public User(long id, String username, String email, String name, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }
}
