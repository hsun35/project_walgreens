package com.example.project_walgreens.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CategoryResponse{

	@SerializedName("Category")
	private List<CategoryItem> category;

	public void setCategory(List<CategoryItem> category){
		this.category = category;
	}

	public List<CategoryItem> getCategory(){
		return category;
	}

	@Override
 	public String toString(){
		return 
			"CategoryResponse{" + 
			"category = '" + category + '\'' + 
			"}";
		}
}