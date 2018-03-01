package com.example.projectwalgreens.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectwalgreens.R;
import com.example.projectwalgreens.model.ProductItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hefen on 2/25/2018.
 */

public class MyProductAdapter extends RecyclerView.Adapter<MyProductAdapter.MyViewHolder>{
    List<ProductItem> itemsList;
    Context context;
    private MyProductAdapter.ItemModifier itemModifier;

    public interface ItemModifier{
        public void onItemSelected(int position);
    }

    public MyProductAdapter(List<ProductItem> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }

    public void setItemModifier(MyProductAdapter.ItemModifier itemModifier){
        this.itemModifier = itemModifier;
    }

    @Override
    public MyProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_layout, parent, false);

        final MyProductAdapter.MyViewHolder myViewHolder = new MyProductAdapter.MyViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemModifier!=null){
                    itemModifier.onItemSelected(myViewHolder.getAdapterPosition());
                }
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyProductAdapter.MyViewHolder holder, int position) {
        final ProductItem item = itemsList.get(position);
        String imageUrl = item.getImage();


        //if (isGrid) {
        holder.titleTextView.setText(item.getProductName());
        if (imageUrl == null || imageUrl.length() == 0) {
            holder.imageViewMyImage.setImageResource(R.drawable.ic_shop);
        } else {
            Picasso.with(context).load(imageUrl).into(holder.imageViewMyImage, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    //do smth when picture is loaded successfully
                }

                @Override
                public void onError() {
                    //do smth when there is picture loading error
                    item.setImage("");
                    Log.i("mylog", "piccasso err");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView;// descriptionTextView, countryTextView;
        ImageView imageViewMyImage;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageViewMyImage = itemView.findViewById(R.id.imageViewItem);
            titleTextView = itemView.findViewById(R.id.textViewItem);

        }
    }
}
