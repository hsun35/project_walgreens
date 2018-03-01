package com.example.projectwalgreens.model;

import com.google.gson.annotations.SerializedName;

public class RetrieveResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("UserMobile")
	private String userMobile;

	@SerializedName("UserPassword")
	private String userPassword;

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

	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}

	public String getUserPassword(){
		return userPassword;
	}

	@Override
 	public String toString(){
		return 
			"RetrieveResponse{" + 
			"msg = '" + msg + '\'' + 
			",userMobile = '" + userMobile + '\'' + 
			",userPassword = '" + userPassword + '\'' + 
			"}";
		}
}