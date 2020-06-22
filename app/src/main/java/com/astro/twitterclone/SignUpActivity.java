package com.astro.twitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextUsername;
    EditText editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextPassword = findViewById(R.id.editTextSpassword);
        editTextUsername = findViewById(R.id.editTextSusername);
    }

    public void signUpClicked(View view) {
        String username = String.valueOf(editTextUsername.getText());
        String password = String.valueOf(editTextPassword.getText());

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Fill input box", Toast.LENGTH_SHORT).show();
        } else {
            ParseUser user = new ParseUser();
            user.setUsername(username);
            user.setPassword(password);
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {

                        Toast.makeText(SignUpActivity.this, "Signed Up Successfully!", Toast.LENGTH_SHORT).show();
                        Intent goToUserList = new Intent(SignUpActivity.this,UserListActivity.class);
                        startActivity(goToUserList);
                    } else {
                        Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public void logInclicked(View view) {

        finish();

    }
}
