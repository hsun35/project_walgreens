package com.example.project_walgreens.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_walgreens.R;
import com.example.project_walgreens.model.ProductInfo;
import com.example.project_walgreens.model.ProductItem;
import com.example.project_walgreens.network.ProductList;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hefen on 2/26/2018.
 */

public class MyRecordAdapter extends RecyclerView.Adapter<MyRecordAdapter.MyViewHolder>{
    List<ProductInfo> itemsList;
    Context context;
    //private MyRecordAdapter.ItemModifier itemModifier;

/*    public interface ItemModifier{
        public void onItemSelected(int position);
    }*/

    public MyRecordAdapter(List<ProductInfo> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }

/*    public void setItemModifier(MyRecordAdapter.ItemModifier itemModifier){
        this.itemModifier = itemModifier;
    }*/

    @Override
    public MyRecordAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item_layout, parent, false);//!!!incorrect layout

        final MyRecordAdapter.MyViewHolder myViewHolder = new MyRecordAdapter.MyViewHolder(v);

        /*v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemModifier!=null){
                    itemModifier.onItemSelected(myViewHolder.getAdapterPosition());
                }
            }
        });*/

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecordAdapter.MyViewHolder holder, int position) {
        final ProductInfo item = itemsList.get(position);
        String imageUrl = item.getImage();
        String Id = item.getId();

        holder.titleTextView.setText(item.getProductName());
        holder.priceTextView.setText(item.getPrize());
        holder.numberTextView.setText("Num: " + String.valueOf(ProductList.item_obtained.get(Id)));//
        //if (isGrid) {
        //holder.titleTextView.setText(item.getProductName());
        if (imageUrl == null || imageUrl.length() == 0) {
            holder.imageViewMyImage.setImageResource(R.drawable.ic_shop);
        } else {
            Picasso.with(context).load(imageUrl).into(holder.imageViewMyImage);
        }
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView;// descriptionTextView, countryTextView;
        TextView priceTextView;
        TextView numberTextView;
        ImageView imageViewMyImage;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageViewMyImage = itemView.findViewById(R.id.imageView12);
            titleTextView = itemView.findViewById(R.id.textView6);
            priceTextView = itemView.findViewById(R.id.textView10);
            numberTextView = itemView.findViewById(R.id.textView11);
        }
    }
}
