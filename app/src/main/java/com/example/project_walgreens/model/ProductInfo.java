package com.example.project_walgreens.model;

/**
 * Created by hefen on 2/25/2018.
 */

public class ProductInfo {
    String productName;
    String image;
    String prize;
    String id;
    String status;
    //int max_num;
    //int num;
    boolean selection;

    public ProductInfo(String productName, String image, String prize, String id, String status) {
        this.productName = productName;
        this.image = image;
        this.prize = prize;
        this.id = id;
        this.selection = false;
        this.status = status;
        //this.max_num = max_num;
        //this.num = num;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSelection() {
        return selection;
    }

    public void setSelection(boolean selection) {
        this.selection = selection;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
