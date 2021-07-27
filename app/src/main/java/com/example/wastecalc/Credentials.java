package com.example.wastecalc;

public class Credentials {

    private String Username;
    private String Password;
    private String Email;
    private byte[] Salt;

    Credentials(String username, String password, byte[] salt){
        this.Username = username;
        this.Password = password;
        this.Salt = salt;
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

    public byte[] getSalt() { return Salt; }

    public void setSalt(byte[] salt) { Salt = salt; }

    public String getEmail() {
        return Password;
    }

    public void setEmail(String password) {
        Password = password;
    }
}
