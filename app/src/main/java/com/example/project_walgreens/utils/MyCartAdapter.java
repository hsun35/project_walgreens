package com.example.project_walgreens.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_walgreens.R;
import com.example.project_walgreens.model.ProductInfo;
import com.example.project_walgreens.model.ProductItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hefen on 2/26/2018.
 */

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyViewHolder> {
    List<ProductInfo> itemsList;
    Context context;
    private MyCartAdapter.ItemModifier itemModifier;

    public interface ItemModifier{
        public void onItemSelected(int position);
    }

    public MyCartAdapter(List<ProductInfo> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }

    public void setItemModifier(MyCartAdapter.ItemModifier itemModifier){
        this.itemModifier = itemModifier;
    }



    @Override
    public MyCartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);

        final MyCartAdapter.MyViewHolder myViewHolder = new MyCartAdapter.MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyCartAdapter.MyViewHolder holder, int position) {
        final ProductInfo item = itemsList.get(position);
        String imageUrl = item.getImage();

        if (imageUrl == null || imageUrl.length() == 0) {
            holder.imageViewMyImage.setImageResource(R.drawable.ic_shop);
        } else {
            Picasso.with(context).load(imageUrl).into(holder.imageViewMyImage);
        }
        holder.titleTextView.setText(item.getProductName());
        holder.priceTextView.setText("$ " + item.getPrize());
        if (item.isSelection()) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (holder.checkBox.isChecked()) {

                }*/
                if(itemModifier!=null){
                    itemModifier.onItemSelected(holder.getAdapterPosition());
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView;// descriptionTextView, countryTextView;
        TextView priceTextView;
        ImageView imageViewMyImage;
        CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageViewMyImage = itemView.findViewById(R.id.imageView12);
            titleTextView = itemView.findViewById(R.id.textView6);
            priceTextView = itemView.findViewById(R.id.textView10);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
