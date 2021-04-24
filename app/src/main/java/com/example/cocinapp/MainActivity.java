package com.example.cocinapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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



                return true;
            }
        });
    }

    public void ForgotPassword(View view){
        Intent myIntent = new Intent(MainActivity.this, ForgotPassword.class);
        startActivity(myIntent);

    }


}