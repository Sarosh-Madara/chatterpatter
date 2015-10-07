package com.example.saroshmadara.chatterpatter.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saroshmadara.chatterpatter.R;
import com.example.saroshmadara.chatterpatter.ui.activity.HomeActivity;


public class HomeFragment extends Fragment {


    public static String ITEM_NAME;
    public static String IMAGE_RESOURCE_ID;
    private ImageView ivIcon;
    private TextView tvItemName;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        ivIcon=(ImageView)view.findViewById(R.id.frag1_icon);
        tvItemName=(TextView)view.findViewById(R.id.frag1_text);

        tvItemName.setText(getArguments().getString(HomeFragment.ITEM_NAME));

        ivIcon.setImageDrawable(ContextCompat.getDrawable(getActivity(),
                getArguments().getInt(IMAGE_RESOURCE_ID)));

        return view;
    }
}
