package com.astro.twitterclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

public class starterApplication extends Application {
//class starterApplication extends AppCompatActivity {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("your_app_id")
                // if desired
                .clientKey("your_client_key")
                .server("your_server_url")
                .build()
        );

//        ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
 