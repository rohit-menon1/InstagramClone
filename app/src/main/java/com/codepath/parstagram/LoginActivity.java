package com.codepath.parstagram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (ParseUser.getCurrentUser() != null) {
            goMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick signup button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                signupUser(username, password);
            }
        });
    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user " + username);
        //TODO:NAVIGATE TO MAIN ACTVITY IN USERNAME AND PASSWORD CORRECT
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e!=null){
                    Log.e(TAG,"Issue With login",e);
                    Toast.makeText(LoginActivity.this,"Issue with login!",Toast.LENGTH_SHORT);

                    return;
                    
                }
                goMainActivity();
                Toast.makeText(LoginActivity.this,"Success!",Toast.LENGTH_SHORT);

            }
        });
    }
    private void signupUser(String username, String password) {
        Log.i(TAG, "Attempting to create new user " + username);
        //TODO:NAVIGATE TO MAIN ACTVITY IN USERNAME AND PASSWORD CORRECT
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e!=null){
                    Log.e(TAG,"Issue With signup",e);
                    Toast.makeText(LoginActivity.this,"Issue with signup!",Toast.LENGTH_SHORT);
                    return;

                }
                loginUser(username, password);
            }
        });
    }
    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
