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
import com.example.projectwalgreens.model.SubCategoryItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hefen on 2/25/2018.
 */

public class MySubCategoryAdapter extends RecyclerView.Adapter<MySubCategoryAdapter.MyViewHolder> {
    List<SubCategoryItem> itemsList;
    Context context;
    private MySubCategoryAdapter.ItemModifier itemModifier;
    //boolean loadDefaultImg;

    public interface ItemModifier{
        public void onItemSelected(int position);
    }

    public MySubCategoryAdapter(List<SubCategoryItem> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }

    public void setItemModifier(MySubCategoryAdapter.ItemModifier itemModifier){
        this.itemModifier = itemModifier;
    }

    @Override
    public MySubCategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_layout, parent, false);

        final MySubCategoryAdapter.MyViewHolder myViewHolder = new MySubCategoryAdapter.MyViewHolder(v);

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
    public void onBindViewHolder(MySubCategoryAdapter.MyViewHolder holder, int position) {
        final SubCategoryItem item = itemsList.get(position);
        String imageUrl = item.getCatagoryImage();


        //if (isGrid) {
        holder.titleTextView.setText(item.getSubCatagoryName());
        if (imageUrl == null || imageUrl.length() == 0) {
            holder.imageViewMyImage.setImageResource(R.drawable.ic_shop);
        } else {
            Picasso.with(context).load(imageUrl).into(holder.imageViewMyImage, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    //do smth when picture is loaded successfully
                    //loadDefaultImg = false;
                }

                @Override
                public void onError() {
                    //do smth when there is picture loading error
                    //item.setCatagoryImage("");
                    Log.i("mylog", "piccasso err");
                    //loadDefaultImg = true;
                }
            });
        }
        //if (loadDefaultImg) {
        //    holder.imageViewMyImage.setImageResource(R.drawable.ic_shop);
        //}
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
