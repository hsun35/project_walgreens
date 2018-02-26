package com.example.project_walgreens.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResetResponse{

	@SerializedName("msg")
	private List<String> msg;

	public void setMsg(List<String> msg){
		this.msg = msg;
	}

	public List<String> getMsg(){
		return msg;
	}

	@Override
 	public String toString(){
		return 
			"ResetResponse{" + 
			"msg = '" + msg + '\'' + 
			"}";
		}
}