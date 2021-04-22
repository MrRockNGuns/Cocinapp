package com.example.cocinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE); // esto Permite no mostrar la barra la primera vez
        setContentView(R.layout.activity_main);

    }

    public void ForgotPassword(View view){
        Intent myIntent = new Intent(MainActivity.this, ForgotPassword.class);
        startActivity(myIntent);

    }
}