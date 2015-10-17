package com.example.saroshmadara.chatterpatter.firebase;

import com.example.saroshmadara.chatterpatter.util.ConfigConstants;
import com.firebase.client.Firebase;

/**
 * Created by Sarosh Madara on 16-10-2015.
 */
public class FirebaseHandler {
    private static FirebaseHandler instance;
    private static Firebase firebaseRef;
    private static Firebase users;
    private static Firebase logged_in_users;
    private static Firebase user_group_chat;
    private static Firebase user_group_members;
    private static Firebase user_group_messages;



    public static Firebase getUser_group_chat() {
        return user_group_chat;
    }

    public static Firebase getUser_todos() {
        return user_todos;
    }

    private static Firebase user_todos;

    public static Firebase getLogged_in_users() {
        return logged_in_users;
    }

    private FirebaseHandler(){
        firebaseRef =           new Firebase(ConfigConstants.FIREBASE_URL);
        users =                 new Firebase(ConfigConstants.FIREBASE_URL).child("users");
        logged_in_users =       new Firebase(ConfigConstants.FIREBASE_URL).child("logged-in-users");
        user_todos =            new Firebase(ConfigConstants.FIREBASE_URL).child("user-todos");
        user_group_chat =       new Firebase(ConfigConstants.FIREBASE_URL).child("user-group-chat");
        user_group_members =    new Firebase(ConfigConstants.FIREBASE_URL).child("user-group-members");
        user_group_messages =   new Firebase(ConfigConstants.FIREBASE_URL).child("user-group-messages");
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

    public static Firebase getUser_group_members() {
        return user_group_members;
    }

    public static Firebase getUser_group_messages() {
        return user_group_messages;
    }

}
