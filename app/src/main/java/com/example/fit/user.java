package com.example.fit;

public class user {

                                            //User Class having id,username,password
                                            // empty constructor
                                            // Get / Set methods for each variable
                                            // Constructor used in the creation of a new user.

    private int id;
    private String Username;
    private String Password;

    public user(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public user(int id, String username, String password) {
        this.id = id;
        Username = username;
        Password = password;
    }
}
