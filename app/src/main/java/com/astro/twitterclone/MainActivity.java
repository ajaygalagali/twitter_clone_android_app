package com.astro.twitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {
    EditText editTextLusername;
    EditText editTextLpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ParseUser.getCurrentUser() != null){
            Intent goToUserList = new Intent(MainActivity.this,UserListActivity.class);
            startActivity(goToUserList);
        }

        editTextLpassword = findViewById(R.id.editTextLpassword);
        editTextLusername = findViewById(R.id.editTextLusername);



    }

    public void logInClicked(View view) {
        String username = String.valueOf(editTextLusername.getText());
        String password = String.valueOf(editTextLpassword.getText());

        if (username.isEmpty() ||  password.isEmpty()){
            Toast.makeText(this, "Enter Details", Toast.LENGTH_SHORT).show();
        }else{
            ParseUser.logInInBackground(username, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(e==null){
                        Toast.makeText(MainActivity.this, "Logged In Succesfully!", Toast.LENGTH_SHORT).show();
                        Intent goToUserList = new Intent(MainActivity.this,UserListActivity.class);
                        startActivity(goToUserList);
                    }else{
                        Toast.makeText(MainActivity.this, "Either username or password is wrong!", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public void goToSignUpClicked(View view) {

     Intent goToSignUp = new Intent(MainActivity.this,SignUpActivity.class);
     startActivity(goToSignUp);
    }


}
