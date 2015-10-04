package com.example.saroshmadara.chatterpatter.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saroshmadara.chatterpatter.R;
import com.example.saroshmadara.chatterpatter.models.SpinnerItem;

import java.util.List;

/**
 * Created by Sarosh Madara on 03-10-2015.
 */
public class CustomSpinnerAdapter extends ArrayAdapter<SpinnerItem> {

    private List<SpinnerItem> dataList;
    private int drawableResID;
    private Context context;

    public CustomSpinnerAdapter(Context context, int resource, List<SpinnerItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.drawableResID = resource;
        this.dataList = (List<SpinnerItem>) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        SpinnerHolder holder;

        if(view == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view = inflater.inflate(drawableResID,parent,false);
            holder = new SpinnerHolder();

            holder.email = (TextView) view.findViewById(R.id.drawEmail);
            holder.name = (TextView) view.findViewById(R.id.drawName);
            holder.imageView = (ImageView) view.findViewById(R.id.drawImage);
            view.setTag(holder);
        }else{
            holder = (SpinnerHolder) view.getTag();
        }


        SpinnerItem item = dataList.get(position);
        holder.email.setText(item.getEmail());
        holder.name.setText(item.getName());
        holder.imageView.setImageDrawable(ContextCompat.getDrawable(context,item.getDrawableResID()));



        return view;
    }



    public class SpinnerHolder{
        TextView email,name;
        ImageView imageView;
    }
}
