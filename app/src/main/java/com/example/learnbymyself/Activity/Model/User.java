package com.example.learnbymyself.Activity.Model;

import java.io.Serializable;

public class User implements Serializable {
    private String id, userName, pass, URLavatar, fullName, myTelephone, address;

    public User(String id, String userName, String pass, String URLavatar, String fullName, String myTelephone, String address) {
        this.id = id;
        this.userName = userName;
        this.pass = pass;
        this.URLavatar = URLavatar;
        this.fullName = fullName;
        this.myTelephone = myTelephone;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getURLavatar() {
        return URLavatar;
    }

    public void setURLavatar(String URLavatar) {
        this.URLavatar = URLavatar;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMyTelephone() {
        return myTelephone;
    }

    public void setMyTelephone(String myTelephone) {
        this.myTelephone = myTelephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
