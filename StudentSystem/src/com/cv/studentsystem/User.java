package com.cv.studentsystem;

public class User {
    private String userName;
    private String password;
    private String phoneNum;
    private String idNum;

    public User() {
    }

    public User(String userName, String password, String phoneNum, String idNum) {
        this.userName = userName;
        this.password = password;
        this.phoneNum = phoneNum;
        this.idNum = idNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }
}
