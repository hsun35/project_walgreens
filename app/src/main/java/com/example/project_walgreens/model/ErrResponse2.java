package com.example.project_walgreens.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ErrResponse2{

	@SerializedName("msg")
	private List<Integer> msg;

	public void setMsg(List<Integer> msg){
		this.msg = msg;
	}

	public List<Integer> getMsg(){
		return msg;
	}

	@Override
 	public String toString(){
		return 
			"ErrResponse2{" + 
			"msg = '" + msg + '\'' + 
			"}";
		}
}