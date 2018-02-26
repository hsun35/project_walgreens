package com.example.project_walgreens.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_walgreens.R;
import com.example.project_walgreens.model.CategoryItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hefen on 2/25/2018.
 */

public class MyCategoryAdapter extends  RecyclerView.Adapter<MyCategoryAdapter.MyViewHolder> {
    List<CategoryItem> itemsList;
    Context context;
    private ItemModifier itemModifier;

    public interface ItemModifier{
        public void onItemSelected(int position);
    }

    public MyCategoryAdapter(List<CategoryItem> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }

    public void setItemModifier(ItemModifier itemModifier){
        this.itemModifier = itemModifier;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_layout, parent, false);
        /*if (isGrid) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout, parent, false);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        }*/
        final MyViewHolder myViewHolder = new MyViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemModifier!=null){
                    itemModifier.onItemSelected(myViewHolder.getAdapterPosition());
                }
            }
        });

        return myViewHolder;
        //return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CategoryItem item = itemsList.get(position);
        String imageUrl = item.getCatagoryImage();

        //if (isGrid) {
        holder.titleTextView.setText(item.getCatagoryName());
        if (imageUrl == null || imageUrl.length() == 0) {
            holder.imageViewMyImage.setImageResource(R.drawable.ic_shop);
        } else {
            Picasso.with(context).load(imageUrl).into(holder.imageViewMyImage);
        }
        //} else {
        //    holder.nameTextView.setText(simpson.getName());
        //}
        //holder.nameTextView.setText(simpson.getName());
        //holder.descriptionTextView.setText(simpson.getDescription());
        //holder.countryTextView.setText(actor.getCountry());



        //Picasso.with(context).load(actor.getImageUrl()).into(holder.imageViewMyImage);//take image url put on image view
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

            /*if (isGrid) {
                imageViewMyImage = itemView.findViewById(R.id.imageView2);
            } else {
                nameTextView = itemView.findViewById(R.id.textViewName);
            }*/
            imageViewMyImage = itemView.findViewById(R.id.imageViewItem);
            titleTextView = itemView.findViewById(R.id.textViewItem);
            //nameTextView = itemView.findViewById(R.id.textViewName);
            //descriptionTextView = itemView.findViewById(R.id.textViewDescription);
            //countryTextView = itemView.findViewById(R.id.textViewCountry);
            //imageViewMyImage = itemView.findViewById(R.id.imageView);
        }
    }
}
