package com.example.projectwalgreens.network;

import com.example.projectwalgreens.model.CategoryItem;
import com.example.projectwalgreens.model.OrderHistoryItem;
import com.example.projectwalgreens.model.ProductInfo;
import com.example.projectwalgreens.model.ProductItem;
import com.example.projectwalgreens.model.SubCategoryItem;

import java.util.List;
import java.util.Map;

/**
 * Created by hefen on 2/25/2018.
 */

public class ProductList {
    public static List<CategoryItem> categoryItemList;
    public static List<ProductItem> productItemList;
    public static List<SubCategoryItem> subCategoryItemList;
    public static List<OrderHistoryItem> historyItemList;
    //public static Map<String, ProductInfo> cart_item_num;
    //public static List<ProductItem> cartItemList;
    //public static Map<String, ProductInfo> history_item_num;
    //public static List<ProductItem> historyItemList;
    public static Map<String, Integer> item_left;
    public static Map<String, Integer> item_obtained;
    public static List<ProductInfo> item_in_cart;
    public static List<ProductInfo> item_in_record;
    public static List<ProductInfo> item_in_track;
    public static void clearRecord() {
        item_left = null;
        item_in_record = null;
        item_obtained = null;
        item_in_cart = null;
        item_in_track = null;
    }
}
