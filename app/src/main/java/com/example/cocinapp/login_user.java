package com.example.cocinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class login_user extends AppCompatActivity {
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE); // esto Permite no mostrar la barra la primera vez
        setContentView(R.layout.activity_login_user);

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //                 Toast.makeText(getApplicationContext(), "PRUEBA BOTON" , Toast.LENGTH_LONG).show();
                //Intent newActivity = new Intent(getApplicationContext(), SearchRecipe.class);
                //startActivity(newActivity);
            }
        });
    }

    public void ForgotPassword(View view){
        Intent myIntent = new Intent(login_user.this, ForgotPassword.class);
        startActivity(myIntent);

    }
}