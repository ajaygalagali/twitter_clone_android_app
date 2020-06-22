package com.astro.twitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    ListView listView;
//    static ArrayList<String> followedList;
    static ArrayList<String> arrayListUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        setTitle("Users");

        listView = findViewById(R.id.listViewUser);

        arrayListUser = new ArrayList<String>();
//        followedList = new ArrayList<String>();


        final ArrayAdapter<String> arrayAdapterUser = new ArrayAdapter<String>(UserListActivity.this,android.R.layout.simple_list_item_multiple_choice,arrayListUser);
//        arrayListUser.add("User 1");
//        arrayListUser.add("User 2");
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        query.addAscendingOrder("username");
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e==null){
                    if(objects.size()  > 0){
                        for(ParseUser user:objects){
                            arrayListUser.add(user.getUsername());
                        }
                        try{
                            for(String username : arrayListUser){
                                if(ParseUser.getCurrentUser().getList("following").contains(username)){
                                    listView.setItemChecked(arrayListUser.indexOf(username),true);
//                                    followedList.add(username);
                                }
                            }
                        }catch (Exception ee){
                            ee.printStackTrace();

                        }

                        arrayAdapterUser.notifyDataSetChanged();
                    }
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listView.isItemChecked(position)){
                    //CHECKED
//                    Log.i("Follow",String.valueOf(arrayListUser.get(position)));
                    ParseUser.getCurrentUser().add("following",arrayListUser.get(position));

                }else if(!listView.isItemChecked(position)){
                    //UN-CHECKED
                    ParseUser.getCurrentUser().getList("following").remove(arrayListUser.get(position));
                    List tempUser = ParseUser.getCurrentUser().getList("following");
                    ParseUser.getCurrentUser().remove("following");
                    ParseUser.getCurrentUser().put("following",tempUser);
                    }
                ParseUser.getCurrentUser().saveInBackground();
            }
        });



        listView.setAdapter(arrayAdapterUser);


    }

    public void tweetClicked(View view) {
        Intent goToTweet = new Intent(UserListActivity.this,TweetActivity.class);
        startActivity(goToTweet);
    }

    public void feedClicked(View view) {
        Intent goToFeed = new Intent(UserListActivity.this,FeedActivity.class);
        startActivity(goToFeed);
    }

    public void logoutClicked(View view) {
        ParseUser.logOut();
        Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
        Intent goToLogIn = new Intent(UserListActivity.this,MainActivity.class);
        startActivity(goToLogIn);
    }


}
