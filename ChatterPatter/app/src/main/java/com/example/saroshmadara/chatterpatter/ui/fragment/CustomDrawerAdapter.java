package com.example.saroshmadara.chatterpatter.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saroshmadara.chatterpatter.R;
import com.example.saroshmadara.chatterpatter.models.DrawerItem;

import java.util.List;

/**
 * Created by Sarosh Madara on 28-09-2015.
 */
public class CustomDrawerAdapter extends ArrayAdapter<DrawerItem>{

    Context context;
    List<DrawerItem> drawerItemList;
    int layoutResId;


    public CustomDrawerAdapter(Context context, int resource, List<DrawerItem> listItems) {
        super(context, resource, listItems );
        this.context = context;
        this.layoutResId = resource;
        this.drawerItemList = listItems;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        DrawerItemHolder itemHolder;
        if(convertView == null){

            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view = inflater.inflate(layoutResId,parent,false);
            itemHolder = new DrawerItemHolder();

            itemHolder.ItemName = (TextView) view.findViewById(R.id.drawer_itemName);
            itemHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);

            view.setTag(itemHolder);
        }else{
            itemHolder =   (DrawerItemHolder) view.getTag();
        }
        DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);

        itemHolder.icon.setImageDrawable(view.getResources().getDrawable(
                dItem.getImgResID()));
        itemHolder.ItemName.setText(dItem.getItemName());

        return view;
    }
    private static class DrawerItemHolder {
        TextView ItemName;
        ImageView icon;
    }
}
