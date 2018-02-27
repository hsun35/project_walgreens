package com.example.project_walgreens.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_walgreens.R;
import com.example.project_walgreens.model.ProductInfo;
import com.example.project_walgreens.model.ProductItem;
import com.example.project_walgreens.network.ProductList;
import com.example.project_walgreens.utils.SendMessage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.project_walgreens.network.ProductList.item_in_cart;

/**
 * Created by hefen on 2/25/2018.
 */

public class ProductDescriptionFragment extends Fragment {
    SendMessage sendMessage;
    View rootView;
    Context context;
    int item;
    TextView product;
    TextView price;
    TextView description;
    ImageView picture;
    Button buttonAdd;

    ProductItem productItem;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_description,container,false);
        //Log.i("mylog", "on create fragment");
        initDescription();

        return rootView;
    }

    private void initDescription(){
        product = rootView.findViewById(R.id.textViewProduct);
        price = rootView.findViewById(R.id.textViewPrice);
        description = rootView.findViewById(R.id.textViewDescription);
        picture = rootView.findViewById(R.id.imageView2);
        context = rootView.getContext();
        buttonAdd = rootView.findViewById(R.id.buttonAdd);
        productItem = ProductList.productItemList.get(item);

        String imageUrl = productItem.getImage();
        if (imageUrl == null || imageUrl.length() == 0) {
            picture.setImageResource(R.drawable.ic_shop);
        } else {
            Picasso.with(context).load(imageUrl).into(picture);
        }
        product.setText(productItem.getProductName());
        price.setText("$ " + productItem.getPrize());
        description.setText(productItem.getDiscription());

        /*
        * public static Map<String, Integer> item_left;
    public static Map<String, Integer> item_obtained;
    public static List<ProductItem> item_in_cart;
    public static List<ProductItem> item_in_record;*/

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            String Id = productItem.getId();
            @Override
            public void onClick(View view) {
                if (ProductList.item_left == null) {
                    ProductList.item_left = new HashMap<>();
                }
                if (item_in_cart == null) {
                    item_in_cart = new ArrayList<>();
                }
                if ( !ProductList.item_left.containsKey(Id) ) {
                    ProductList.item_left.put(Id, Integer.parseInt(productItem.getQuantity()) );
                }
                if (ProductList.item_left.get(Id) == 0) {
                    sendMessage.showMessage("No more items left in stock.");
                } else {
                    ProductList.item_left.put(Id, ProductList.item_left.get(Id) - 1);
                    sendMessage.showMessage("Put one into the cart, " + ProductList.item_left.get(Id) + " left.");
                    ProductList.item_in_cart.add(new ProductInfo(productItem.getProductName(),
                            productItem.getImage(),
                            productItem.getPrize(),
                            productItem.getId(), "0"));
                }

            }
        });
    }

    public void setSendMessage(SendMessage sendMessage){
        this.sendMessage = sendMessage;
    }
    public void setId(int item){
        this.item = item;
    }


}
