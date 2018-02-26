package com.example.project_walgreens.model;

import com.google.gson.annotations.SerializedName;

public class ProductItem{

	@SerializedName("ProductName")
	private String productName;

	@SerializedName("Quantity")
	private String quantity;

	@SerializedName("Id")
	private String id;

	@SerializedName("Discription")
	private String discription;

	@SerializedName("Image")
	private String image;

	@SerializedName("Prize")
	private String prize;

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setQuantity(String quantity){
		this.quantity = quantity;
	}

	public String getQuantity(){
		return quantity;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDiscription(String discription){
		this.discription = discription;
	}

	public String getDiscription(){
		return discription;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setPrize(String prize){
		this.prize = prize;
	}

	public String getPrize(){
		return prize;
	}

	@Override
 	public String toString(){
		return 
			"ProductItem{" + 
			"productName = '" + productName + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",id = '" + id + '\'' + 
			",discription = '" + discription + '\'' + 
			",image = '" + image + '\'' + 
			",prize = '" + prize + '\'' + 
			"}";
		}
}