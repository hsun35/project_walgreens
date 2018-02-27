package com.example.project_walgreens.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_walgreens.R;
import com.example.project_walgreens.model.ProductInfo;
import com.example.project_walgreens.network.ProductList;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

/**
 * Created by hefen on 2/27/2018.
 */

public class MyTrackAdapter extends RecyclerView.Adapter<MyTrackAdapter.MyViewHolder>{
    List<ProductInfo> itemsList;
    Context context;


    public MyTrackAdapter(List<ProductInfo> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }


    @Override
    public MyTrackAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item_layout, parent, false);//!!!incorrect layout

        final MyTrackAdapter.MyViewHolder myViewHolder = new MyTrackAdapter.MyViewHolder(v);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyTrackAdapter.MyViewHolder holder, int position) {
        final ProductInfo item = itemsList.get(position);
        String imageUrl = item.getImage();
        String Id = item.getId();
        String status = item.getStatus();

        if (status.equals("0")) {
            status = "unconfirmed";
        } else if (status.equals("1")) {
            status = "confirmed";
        } else if (status.equals("2")) {
            status ="dispatched";
        } else if (status.equals("3")) {
            status ="on the way";
        } else if (status.equals("4")) {
            status ="delivered";
        }


        holder.titleTextView.setText(item.getProductName());
        holder.priceTextView.setText(item.getPrize());
        holder.statusTextView.setText("Order " + String.valueOf(status));//
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
        TextView statusTextView;
        ImageView imageViewMyImage;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageViewMyImage = itemView.findViewById(R.id.imageView12);
            titleTextView = itemView.findViewById(R.id.textView6);
            priceTextView = itemView.findViewById(R.id.textView10);
            statusTextView = itemView.findViewById(R.id.textView11);
        }
    }
}
