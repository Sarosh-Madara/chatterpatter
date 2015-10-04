package com.example.saroshmadara.chatterpatter;

import android.app.Application;
import android.content.Context;

import com.example.saroshmadara.chatterpatter.firebase.FirebaseHandler;
import com.example.saroshmadara.chatterpatter.models.User;
import com.firebase.client.Firebase;

/**
 * Created by Sarosh Madara on 02-10-2015.
 */
public class ChatterPatterApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        Firebase.setAndroidContext(context);
    }

    public static Context getContext() {
        return context;
    }

    private static Context context;

    public static User getApplicationUser() {
        return applicationUser;
    }

    public static void setApplicationUser(User applicationUser) {
        ChatterPatterApp.applicationUser = applicationUser;
    }

    private static User applicationUser;
}
