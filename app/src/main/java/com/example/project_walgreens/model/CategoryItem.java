package com.example.project_walgreens.model;

import com.google.gson.annotations.SerializedName;

public class CategoryItem{

	@SerializedName("CatagoryImage")
	private String catagoryImage;

	@SerializedName("Id")
	private String id;

	@SerializedName("CatagoryName")
	private String catagoryName;

	@SerializedName("CatagoryDiscription")
	private String catagoryDiscription;

	public void setCatagoryImage(String catagoryImage){
		this.catagoryImage = catagoryImage;
	}

	public String getCatagoryImage(){
		return catagoryImage;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCatagoryName(String catagoryName){
		this.catagoryName = catagoryName;
	}

	public String getCatagoryName(){
		return catagoryName;
	}

	public void setCatagoryDiscription(String catagoryDiscription){
		this.catagoryDiscription = catagoryDiscription;
	}

	public String getCatagoryDiscription(){
		return catagoryDiscription;
	}

	@Override
 	public String toString(){
		return 
			"CategoryItem{" + 
			"catagoryImage = '" + catagoryImage + '\'' + 
			",id = '" + id + '\'' + 
			",catagoryName = '" + catagoryName + '\'' + 
			",catagoryDiscription = '" + catagoryDiscription + '\'' + 
			"}";
		}
}