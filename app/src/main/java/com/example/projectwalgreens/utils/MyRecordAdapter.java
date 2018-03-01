package com.example.projectwalgreens.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectwalgreens.R;
import com.example.projectwalgreens.model.OrderHistoryItem;

import java.util.List;

/**
 * Created by hefen on 2/26/2018.
 */

public class MyRecordAdapter extends RecyclerView.Adapter<MyRecordAdapter.MyViewHolder>{
    List<OrderHistoryItem> itemsList;
    Context context;


    public MyRecordAdapter(List<OrderHistoryItem> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }



    @Override
    public MyRecordAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item_layout, parent, false);//!!!incorrect layout

        final MyRecordAdapter.MyViewHolder myViewHolder = new MyRecordAdapter.MyViewHolder(v);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecordAdapter.MyViewHolder holder, int position) {
        final OrderHistoryItem item = itemsList.get(position);

        String status = item.getOrderStatus();

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

        holder.titleTextView.setText(item.getItemName());
        holder.priceTextView.setText("$ " + item.getFinalPrice());
        holder.idTextView.setText("Order Id: " + item.getOrderID());//
        holder.statusTextView.setText("Order " + status);

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView;// descriptionTextView, countryTextView;
        TextView priceTextView;
        TextView statusTextView;
        TextView idTextView;

        public MyViewHolder(View itemView) {
            super(itemView);

            statusTextView = itemView.findViewById(R.id.textView16);
            titleTextView = itemView.findViewById(R.id.textView14);
            priceTextView = itemView.findViewById(R.id.textView15);
            idTextView = itemView.findViewById(R.id.textView13);
        }
    }
}
