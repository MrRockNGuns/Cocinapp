package com.example.cocinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchRecipe extends AppCompatActivity {

    private EditText ingredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipe);

        ingredient = findViewById(R.id.ingrediente1);

        Button btnBusca = (Button) findViewById(R.id.buscarReceta);

        btnBusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRecipeOptions();
                Toast.makeText(getApplicationContext(), "Ingrediente: "  + ingredient.getText().toString(), Toast.LENGTH_LONG).show();
            }

        });

    }

    public void goRecipeOptions() {
        //       Toast.makeText(getApplicationContext(), userId, Toast.LENGTH_LONG).show();
        Intent select = new Intent(this, RecipeOptions.class);
        // select.putExtra("userId", userId);
        startActivity(select);

    }
}