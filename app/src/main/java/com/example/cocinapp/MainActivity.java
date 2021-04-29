package com.example.cocinapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView miNavegacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE); // esto Permite no mostrar la barra la primera vez
        setContentView(R.layout.activity_main);

        miNavegacion = (BottomNavigationView) findViewById(R.id.bottom_menu);

        miNavegacion.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.ratings){
                    showFragment(new ratings());
                }
                if(item.getItemId() == R.id.home){
                    showFragment(new search());
                }
                if(item.getItemId() == R.id.user){
                    showFragment(new user());
                }
                return true;
            }
        });
    }

    public void ForgotPassword(View view){
        Intent myIntent = new Intent(MainActivity.this, ForgotPassword.class);
        startActivity(myIntent);

    }

    private void showFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.Principal,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}