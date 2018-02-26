package com.example.project_walgreens.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse3{

	@SerializedName("msg")
	private String msg;

	@SerializedName("UserMobile")
	private String userMobile;

	@SerializedName("UserName")
	private String userName;

	@SerializedName("AppApiKey ")
	private String appApiKey;

	@SerializedName("UserID")
	private String userID;

	@SerializedName("UserEmail")
	private String userEmail;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setUserMobile(String userMobile){
		this.userMobile = userMobile;
	}

	public String getUserMobile(){
		return userMobile;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setAppApiKey(String appApiKey){
		this.appApiKey = appApiKey;
	}

	public String getAppApiKey(){
		return appApiKey;
	}

	public void setUserID(String userID){
		this.userID = userID;
	}

	public String getUserID(){
		return userID;
	}

	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}

	public String getUserEmail(){
		return userEmail;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse3{" + 
			"msg = '" + msg + '\'' + 
			",userMobile = '" + userMobile + '\'' + 
			",userName = '" + userName + '\'' + 
			",appApiKey  = '" + appApiKey + '\'' + 
			",userID = '" + userID + '\'' + 
			",userEmail = '" + userEmail + '\'' + 
			"}";
		}
}