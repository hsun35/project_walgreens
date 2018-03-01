package com.example.projectwalgreens.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class HistoryResponse{

	@SerializedName("Order History")
	private List<OrderHistoryItem> orderHistory;

	public void setOrderHistory(List<OrderHistoryItem> orderHistory){
		this.orderHistory = orderHistory;
	}

	public List<OrderHistoryItem> getOrderHistory(){
		return orderHistory;
	}

	@Override
 	public String toString(){
		return 
			"HistoryResponse{" + 
			"order History = '" + orderHistory + '\'' + 
			"}";
		}
}