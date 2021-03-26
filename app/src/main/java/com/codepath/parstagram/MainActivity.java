package com.codepath.parstagram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;

import fragments.ComposeFragment;
import fragments.PostsFragment;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private final int REQUEST_CODE = 20;
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = new Fragment();
                switch (item.getItemId()) {
                    case R.id.action_home:
                        //TDODO: UPDATE FRAGMENT
                        fragment=new PostsFragment();
                        break;
                    case R.id.action_compose:
                        fragment=new ComposeFragment();
                        break;
                    case R.id.action_profile:

                    default:
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_xml, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            // Logout icon has been selected
            ParseUser.logOut();
            ParseUser currentUser = ParseUser.getCurrentUser();
            // Navigate back to the login activity
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
            Toast.makeText(MainActivity.this, "Logout successful!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}