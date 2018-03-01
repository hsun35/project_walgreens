package com.example.projectwalgreens.model;

import com.google.gson.annotations.SerializedName;

public class SubCategoryItem{

	@SerializedName("SubCatagoryName")
	private String subCatagoryName;

	@SerializedName("CatagoryImage")
	private String catagoryImage;

	@SerializedName("SubCatagoryDiscription")
	private String subCatagoryDiscription;

	@SerializedName("Id")
	private String id;

	public void setSubCatagoryName(String subCatagoryName){
		this.subCatagoryName = subCatagoryName;
	}

	public String getSubCatagoryName(){
		return subCatagoryName;
	}

	public void setCatagoryImage(String catagoryImage){
		this.catagoryImage = catagoryImage;
	}

	public String getCatagoryImage(){
		return catagoryImage;
	}

	public void setSubCatagoryDiscription(String subCatagoryDiscription){
		this.subCatagoryDiscription = subCatagoryDiscription;
	}

	public String getSubCatagoryDiscription(){
		return subCatagoryDiscription;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"SubCategoryItem{" + 
			"subCatagoryName = '" + subCatagoryName + '\'' + 
			",catagoryImage = '" + catagoryImage + '\'' + 
			",subCatagoryDiscription = '" + subCatagoryDiscription + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}