package com.astro.twitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {
    ListView listViewFeed;
    ArrayList<String> arrayListTweets;
    ArrayList<String> arrayListUsers;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        setTitle("Your Feed");

        inflater = LayoutInflater.from(getApplicationContext());

        arrayListTweets = new ArrayList<String>();
        arrayListUsers = new ArrayList<String>();

        listViewFeed = findViewById(R.id.listViewFeed);



        ParseQuery<ParseObject> queryTweet = ParseQuery.getQuery("Tweet");
        queryTweet.whereContainedIn("username",ParseUser.getCurrentUser().getList("following"));

//      Log.i("Followed List",followedList.toString());
        queryTweet.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    if(objects.size() > 0){
                        for(ParseObject tweet:objects){
//                            Log.i("Tweet",tweet.get("tweet").toString());
                            arrayListTweets.add(tweet.get("tweet").toString());
                            arrayListUsers.add(tweet.get("username").toString());

                        }
                        listViewFeed.setAdapter(new myAdapter());


                    }
                }
            }
        });





//        listViewFeed.setAdapter(new myAdapter());





    }

    class myAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayListTweets.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = inflater.inflate(R.layout.tweet_list,null);
            TextView textViewUsername = view.findViewById(R.id.textViewTusername);
            textViewUsername.setText(arrayListUsers.get(position));

            TextView tweet = view.findViewById(R.id.textViewTtweet);
            tweet.setText(arrayListTweets.get(position));
            return  view;
        }
    }


}



