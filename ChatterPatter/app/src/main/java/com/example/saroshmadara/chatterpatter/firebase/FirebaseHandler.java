package com.example.saroshmadara.chatterpatter.firebase;

import com.example.saroshmadara.chatterpatter.utils.ConfigConstants;
import com.firebase.client.Firebase;

/**
 * Created by Sarosh Madara on 24-09-2015.
 */
public class FirebaseHandler {
    private static FirebaseHandler instance;
    private static Firebase firebaseRef;
    private static Firebase users;
    private static Firebase logged_in_users;

    public static Firebase getUser_todos() {
        return user_todos;
    }

    private static Firebase user_todos;

    public static Firebase getLogged_in_users() {
        return logged_in_users;
    }

    private FirebaseHandler(){
        firebaseRef = new Firebase(ConfigConstants.FIREBASE_URL);
        users = new Firebase(ConfigConstants.FIREBASE_URL).child("users");
        logged_in_users = new Firebase(ConfigConstants.FIREBASE_URL).child("logged-in-users");
        user_todos = new Firebase(ConfigConstants.FIREBASE_URL).child("user-todos");
    }

    public static FirebaseHandler getInstance(){
        if(instance == null){
            instance =  new FirebaseHandler();
        }
        return instance;
    }

    public static Firebase getUsersRef(){
        return users;
    }
    public static Firebase getFirebaseRoot(){
        return firebaseRef;
    }
}
