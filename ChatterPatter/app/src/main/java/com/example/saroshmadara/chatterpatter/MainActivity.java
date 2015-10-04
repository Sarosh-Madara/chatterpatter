package com.example.saroshmadara.chatterpatter;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.example.saroshmadara.chatterpatter.ui.fragment.LoginFragment;
import com.example.saroshmadara.chatterpatter.ui.fragment.SignupFragment;
import com.firebase.client.Firebase;


public class MainActivity extends ActionBarActivity implements LoginFragment.OnFragmentInteractionListener,SignupFragment.OnSignUpFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            changeFragment(0);
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
