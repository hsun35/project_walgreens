package com.example.project_walgreens.model;

import com.google.gson.annotations.SerializedName;

public class OrderConfirmedItem{

	@SerializedName("OrderId")
	private int orderId;

	public void setOrderId(int orderId){
		this.orderId = orderId;
	}

	public int getOrderId(){
		return orderId;
	}

	@Override
 	public String toString(){
		return 
			"OrderConfirmedItem{" + 
			"orderId = '" + orderId + '\'' + 
			"}";
		}
}