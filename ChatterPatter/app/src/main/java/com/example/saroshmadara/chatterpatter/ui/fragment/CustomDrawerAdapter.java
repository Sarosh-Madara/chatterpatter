package com.example.saroshmadara.chatterpatter.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saroshmadara.chatterpatter.ChatterPatterApp;
import com.example.saroshmadara.chatterpatter.R;
import com.example.saroshmadara.chatterpatter.models.DrawerItem;
import com.example.saroshmadara.chatterpatter.models.SpinnerItem;

import java.util.ArrayList;
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
            itemHolder.spinner = (Spinner) view.findViewById(R.id.drawerSpinner);
            itemHolder.title = (TextView) view.findViewById(R.id.drawerTitle);
            itemHolder.userName = (TextView) view.findViewById(R.id.currentUserfullName);
            itemHolder.userEmail = (TextView) view.findViewById(R.id.currentUserEmail);
            itemHolder.userProfileImage = (ImageView) view.findViewById(R.id.userProfileIV);

            itemHolder.spinnerLayout = (LinearLayout) view.findViewById(R.id.spinnerLayout);
            itemHolder.itemLayout = (LinearLayout) view.findViewById(R.id.itemLayout);
            itemHolder.headerLayout = (LinearLayout) view.findViewById(R.id.headerLayout);
            itemHolder.userItemLayout = (LinearLayout) view.findViewById(R.id.userItemLayout);

            view.setTag(itemHolder);
        }else{
            itemHolder =   (DrawerItemHolder) view.getTag();
        }
        DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);

        if(dItem.isSpinner()){
            itemHolder.spinnerLayout.setVisibility(View.VISIBLE);
            itemHolder.headerLayout.setVisibility(View.INVISIBLE);
            itemHolder.itemLayout.setVisibility(View.INVISIBLE);
            itemHolder.userItemLayout.setVisibility(View.INVISIBLE);

            List<SpinnerItem> users = new ArrayList<>();
            users.add(new SpinnerItem(ChatterPatterApp.getApplicationUser().getFirstName() + ChatterPatterApp.getApplicationUser().getLastName(), R.drawable.ic_action_camera, ChatterPatterApp.getApplicationUser().getEmail()));
            CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(context,R.layout.spinner_item,users);
            itemHolder.spinner.setAdapter(adapter);

            itemHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "user changed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }else if(dItem.hasUser()){
            itemHolder.userItemLayout.setVisibility(View.VISIBLE);
            itemHolder.itemLayout.setVisibility(View.INVISIBLE);
            itemHolder.headerLayout.setVisibility(View.INVISIBLE);
            itemHolder.spinnerLayout.setVisibility(View.INVISIBLE);

            itemHolder.userEmail.setText(dItem.getUser().getEmail());
            itemHolder.userName.setText(dItem.getUser().getFirstName() + " " + dItem.getUser().getLastName());
            itemHolder.userProfileImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_action_camera));

        }else if(dItem.getTitle() != null){
            itemHolder.userItemLayout.setVisibility(View.INVISIBLE);
            itemHolder.itemLayout.setVisibility(View.INVISIBLE);
            itemHolder.spinnerLayout.setVisibility(View.INVISIBLE);
            itemHolder.headerLayout.setVisibility(View.VISIBLE);
            itemHolder.title.setText(dItem.getTitle());


        }else{
            itemHolder.userItemLayout.setVisibility(View.INVISIBLE);
            itemHolder.itemLayout.setVisibility(View.VISIBLE);
            itemHolder.headerLayout.setVisibility(View.INVISIBLE);
            itemHolder.spinnerLayout.setVisibility(View.INVISIBLE);

            itemHolder.icon.setImageDrawable(ContextCompat.getDrawable(context,dItem.getImgResID()));
            itemHolder.ItemName.setText(dItem.getItemName());
        }

        return view;
    }
    private static class DrawerItemHolder {
        TextView ItemName,title,userName,userEmail;
        ImageView icon,userProfileImage;
        LinearLayout itemLayout,spinnerLayout,headerLayout,userItemLayout;
        Spinner spinner;
    }
}
