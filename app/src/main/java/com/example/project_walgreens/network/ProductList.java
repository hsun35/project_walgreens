package com.example.project_walgreens.network;

import com.example.project_walgreens.model.CategoryItem;
import com.example.project_walgreens.model.ProductInfo;
import com.example.project_walgreens.model.ProductItem;
import com.example.project_walgreens.model.SubCategoryItem;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hefen on 2/25/2018.
 */

public class ProductList {
    public static List<CategoryItem> categoryItemList;
    public static List<ProductItem> productItemList;
    public static List<SubCategoryItem> subCategoryItemList;
    //public static Map<String, ProductInfo> cart_item_num;
    //public static List<ProductItem> cartItemList;
    //public static Map<String, ProductInfo> history_item_num;
    //public static List<ProductItem> historyItemList;
    public static Map<String, Integer> item_left;
    public static Map<String, Integer> item_obtained;
    public static List<ProductInfo> item_in_cart;
    public static List<ProductInfo> item_in_record;
}
