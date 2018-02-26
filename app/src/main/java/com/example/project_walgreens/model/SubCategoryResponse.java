package com.example.project_walgreens.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class SubCategoryResponse{

	@SerializedName("SubCategory")
	private List<SubCategoryItem> subCategory;

	public void setSubCategory(List<SubCategoryItem> subCategory){
		this.subCategory = subCategory;
	}

	public List<SubCategoryItem> getSubCategory(){
		return subCategory;
	}

	@Override
 	public String toString(){
		return 
			"SubCategoryResponse{" + 
			"subCategory = '" + subCategory + '\'' + 
			"}";
		}
}