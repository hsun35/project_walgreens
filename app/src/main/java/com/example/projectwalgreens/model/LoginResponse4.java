package com.example.projectwalgreens.model;

/**
 * Created by hefen on 2/24/2018.
 */

public class LoginResponse4 {
    String msg;
    String UserID;
    String UserName;
    String UserEmail;
    String UserMobile;
    String AppApiKey;

    public LoginResponse4(String msg, String userID, String userName, String userEmail, String userMobile, String appApiKey) {
        this.msg = msg;
        UserID = userID;
        UserName = userName;
        UserEmail = userEmail;
        UserMobile = userMobile;
        AppApiKey = appApiKey;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserMobile() {
        return UserMobile;
    }

    public void setUserMobile(String userMobile) {
        UserMobile = userMobile;
    }

    public String getAppApiKey() {
        return AppApiKey;
    }

    public void setAppApiKey(String appApiKey) {
        AppApiKey = appApiKey;
    }
}
