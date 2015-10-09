package com.example.saroshmadara.chatterpatter.services.core;

import android.content.Intent;
import android.util.Log;

import com.example.saroshmadara.chatterpatter.ChatterPatterApp;
import com.example.saroshmadara.chatterpatter.firebase.FirebaseHandler;
import com.example.saroshmadara.chatterpatter.models.User;
import com.example.saroshmadara.chatterpatter.services.listeners.ServiceListener;
import com.example.saroshmadara.chatterpatter.ui.activity.HomeActivity;
import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sarosh Madara on 24-09-2015.
 */
public class AuthenticationService {
    static Map obj;
    static int i = 0;
    static int no_of_users = 0;

    public static void signUp(String id,User user){

         Firebase  newUserRef = FirebaseHandler.getInstance().getUsersRef();
         obj = new HashMap();
         obj.put(id,user);
        Log.d("authSer.signUp()","called");
        newUserRef.setValue(obj);


         newUserRef.addChildEventListener(new ChildEventListener() {
             @Override
             public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                 Log.d("on child added test","getKEY: "+dataSnapshot.getKey()+" getValue: "+dataSnapshot.getValue()+" getChildrenCount: "+dataSnapshot.getChildrenCount() +" getChildren: "+dataSnapshot.getChildren() );
//                 obj.put(dataSnapshot.getKey(),dataSnapshot.getValue(User.class));
             }

             @Override
             public void onChildChanged(DataSnapshot dataSnapshot, String s) {

             }

             @Override
             public void onChildRemoved(DataSnapshot dataSnapshot) {

             }

             @Override
             public void onChildMoved(DataSnapshot dataSnapshot, String s) {

             }

             @Override
             public void onCancelled(FirebaseError firebaseError) {

             }
         });
//        newUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.d("listenerForSingleValue", "getKEY: " + dataSnapshot.getKey() + " getValue: " + dataSnapshot.getValue() + " getChildrenCount: " + dataSnapshot.getChildrenCount());
////                obj.put(dataSnapshot.getKey(),dataSnapshot.getValue(User.class));
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });

        newUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("test", dataSnapshot.getValue().toString());
                Log.d("test valueEventListener", "getKEY: " + dataSnapshot.getKey() + " getValue: " + dataSnapshot.getValue() + " getChildrenCount: " + dataSnapshot.getChildrenCount());

//                obj.put(dataSnapshot.getKey(),dataSnapshot.getValue(User.class));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("nothing changed", "nothing chnged");
            }
        });




    }

    public static void signIn(String email, String password){
//        final ProgressDialog signinProgress = ProgressDialog.show(context,"Signing in","Connecting....",true,false);
        Firebase loginRef = FirebaseHandler.getInstance().getFirebaseRoot();

        loginRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Log.d("authenticated", "success in authentication");

//                LoginFragment.isAuthenticated = true;
//                signinProgress.dismiss();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Log.d("authenticated failed","failure in authentication");
//                LoginFragment.isAuthenticated = false;
//                signinProgress.dismiss();
            }
        });
    }
}
