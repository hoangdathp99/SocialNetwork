package com.example.socialnetwork;

public class Posts {
    public String uid, time, date, postimage, fullname, email, description;
    public Posts(){

    }

    public String getUid() {
        return uid;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPostimage(String postimage) {
        this.postimage = postimage;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostimage() {
        return postimage;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public Posts(String uid, String time, String date, String postimage, String fullname, String email, String description) {
        this.uid = uid;
        this.time = time;
        this.date = date;
        this.postimage = postimage;
        this.fullname = fullname;
        this.email = email;
        this.description = description;
    }
}
