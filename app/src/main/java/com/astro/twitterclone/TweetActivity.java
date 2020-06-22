package com.astro.twitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class TweetActivity extends AppCompatActivity {

    EditText editTextTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);
        editTextTweet = findViewById(R.id.editTextTweet);
        setTitle("Tweet");
    }

    public void postClicked(View view) {
        String tweet = String.valueOf(editTextTweet.getText());

        ParseObject object = new ParseObject("Tweet");
        object.put("tweet",tweet);
        object.put("username", ParseUser.getCurrentUser().getUsername());
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Toast.makeText(TweetActivity.this, "Posted!", Toast.LENGTH_SHORT).show();
                    Intent gotoList = new Intent(TweetActivity.this,UserListActivity.class);
                    startActivity(gotoList);

                }else {
                    Toast.makeText(TweetActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

    }
}
