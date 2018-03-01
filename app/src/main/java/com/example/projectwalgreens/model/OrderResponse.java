package com.example.projectwalgreens.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class OrderResponse{

	@SerializedName("Order Confirmed")
	private List<OrderConfirmedItem> orderConfirmed;

	public void setOrderConfirmed(List<OrderConfirmedItem> orderConfirmed){
		this.orderConfirmed = orderConfirmed;
	}

	public List<OrderConfirmedItem> getOrderConfirmed(){
		return orderConfirmed;
	}

	@Override
 	public String toString(){
		return 
			"OrderResponse{" + 
			"order Confirmed = '" + orderConfirmed + '\'' + 
			"}";
		}
}