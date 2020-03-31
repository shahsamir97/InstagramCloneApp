package com.example.instagramcloneapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    Button loginButton, signupButton;
    EditText usernameEditText, passwordEditText;
    ImageView imageView;
    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Instagram");

        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText2);
        imageView = findViewById(R.id.imageView);
        constraintLayout = findViewById(R.id.layout);
        imageView.setOnClickListener(this);
        constraintLayout.setOnClickListener(this);
        signupButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            Intent intent = new Intent(MainActivity.this, listOfUsers.class);
            startActivity(intent);
        }

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }


    public void loginUser() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            if (ParseUser.getCurrentUser() == null) {
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e == null) {
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, listOfUsers.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } else {
                if (ParseUser.getCurrentUser() != null) {
                    Toast.makeText(this, "You are already logged in.", Toast.LENGTH_LONG).show();
                    new AlertDialog.Builder(this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Warning")
                            .setMessage("You are already loggen in.Would you like to log out?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ParseUser.logOut();
                                }
                            })
                            .setNegativeButton("No",null)
                            .show();
                }
            }
        } else {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void signupUser() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            if (ParseUser.getCurrentUser() == null) {
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(MainActivity.this, "Sign up successful", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, listOfUsers.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        } else {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageView || v.getId() == R.id.layout ||
                v.getId() == R.id.signupButton || v.getId() == R.id.layout || v.getId() == R.id.loginButton) {

            if (v.getId() == R.id.loginButton) {
                loginUser();
            } else if (v.getId() == R.id.signupButton) {
                signupUser();
            }
            InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.ACTION_DOWN || keyCode == KeyEvent.KEYCODE_ENTER) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            return true;
        } else {
            return false;
        }
    }
}
