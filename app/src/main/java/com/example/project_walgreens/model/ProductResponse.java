package com.example.project_walgreens.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ProductResponse{

	@SerializedName("Product")
	private List<ProductItem> product;

	public void setProduct(List<ProductItem> product){
		this.product = product;
	}

	public List<ProductItem> getProduct(){
		return product;
	}

	@Override
 	public String toString(){
		return 
			"ProductResponse{" + 
			"product = '" + product + '\'' + 
			"}";
		}
}