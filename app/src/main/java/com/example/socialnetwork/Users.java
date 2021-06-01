package com.example.socialnetwork;

public class Users {
    private String name, company, email;
    private String uid;

    public Users(String name, String company, String email, String uid) {
        this.name = name;
        this.company = company;
        this.email = email;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Users(){

    }

    public Users(String name, String company, String email) {
        this.name = name;
        this.company = company;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
