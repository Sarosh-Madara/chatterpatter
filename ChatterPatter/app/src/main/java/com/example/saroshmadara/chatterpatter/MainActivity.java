package com.example.saroshmadara.chatterpatter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.saroshmadara.chatterpatter.models.User;
import com.example.saroshmadara.chatterpatter.ui.activity.HomeActivity;
import com.example.saroshmadara.chatterpatter.ui.fragment.LoginFragment;
import com.example.saroshmadara.chatterpatter.ui.fragment.SignupFragment;
import com.firebase.client.Firebase;
import com.google.gson.Gson;


public class MainActivity extends ActionBarActivity implements LoginFragment.OnFragmentInteractionListener,SignupFragment.OnSignUpFragmentInteractionListener {

    private static boolean hasUser = false;
    public static SharedPreferences chatPrefs;
    private String CHATPREFERENCE = "chatpreference";

    public static SharedPreferences getChatPrefs() {
        return chatPrefs;
    }

    public static void setChatPrefs(SharedPreferences chatPrefs) {
        MainActivity.chatPrefs = chatPrefs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);

       hasUser();

        if(savedInstanceState == null){
            if(hasUser) {
                Intent i = new Intent(this, HomeActivity.class);
                startActivity(i);
            }else{
                changeFragment(0);
            }
        }

    }

    private void hasUser() {
        chatPrefs = getSharedPreferences(CHATPREFERENCE,Context.MODE_PRIVATE);
        SharedPreferences.Editor chatEdit = chatPrefs.edit();
        if(chatEdit == null){
            Toast.makeText(this,"found null chatPref",Toast.LENGTH_SHORT).show();
        }
        else if(chatEdit != null) {

            Gson gson = new Gson();
            String json = chatPrefs.getString(ChatterPatterApp.appuser, "");

            if (json == null || json.equals("")) {
            }else{
                User user = gson.fromJson(json, User.class);
                ChatterPatterApp.setApplicationUser(user);
                hasUser = true;

            }
        }
    }

    public void changeFragment(int index){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.container,createFragment(index))
                .addToBackStack("Main")
                .commit();
    }

    public Fragment createFragment(int index){
        switch(index){
            case 0:
                return new LoginFragment();
            case 1:
                return new SignupFragment();
        }
        return new LoginFragment();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onSignupFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStack("Main",0);
    }
}
