package com.example.saroshmadara.chatterpatter.ui.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.saroshmadara.chatterpatter.ChatterPatterApp;
import com.example.saroshmadara.chatterpatter.R;
import com.example.saroshmadara.chatterpatter.models.User;

import org.w3c.dom.Text;

public class UserProfileFragment extends Fragment {


    private TextView email,fullname,followers,following;
    private View view;
    public UserProfileFragment() {
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
        view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        initView(view);
        setUserData(ChatterPatterApp.getApplicationUser());
        return view;
    }

    private void setUserData(User applicationUser) {
        email.setText(applicationUser.getEmail());
        fullname.setText(applicationUser.getFirstName()+" "+applicationUser.getLastName());
        followers.setText("8 Followers");
        following.setText("12 Following");
    }

    private void initView(View view) {
        email = (TextView) view.findViewById(R.id.userEmailTv);
        fullname = (TextView) view.findViewById(R.id.userNameTv);
        followers = (TextView) view.findViewById(R.id.userFollowersTv);
        following = (TextView) view.findViewById(R.id.userFollowingTv);
    }



}
