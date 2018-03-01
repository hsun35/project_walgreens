package com.example.projectwalgreens.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectwalgreens.R;

/**
 * Created by hefen on 2/24/2018.
 */

public class MyListAdapter extends ArrayAdapter<String>{
    String[] names;
    int[] pics;
    Context context;

    public MyListAdapter(@NonNull Context context, @NonNull String[] itemName, int[] itemPic) {
        super(context, R.layout.frontpage_item_layout);
        this.names = itemName;
        this.pics = itemPic;
        this.context = context;
    }

    @Override
    public int getCount() {
        //return super.getCount();
        return names.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.frontpage_item_layout, parent, false);
            //convertView = getLayoutInflater().inflate(R.layout.item_layout, parent, false);
            viewHolder.name = convertView.findViewById(R.id.textView5);
            viewHolder.pic = convertView.findViewById(R.id.imageView4);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(names[position]);
        viewHolder.pic.setImageResource(pics[position]);
        return convertView;
    }

    static class ViewHolder {
        ImageView pic;
        TextView name;
    }
}
