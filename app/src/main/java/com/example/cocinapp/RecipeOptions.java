package com.example.cocinapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class RecipeOptions extends AppCompatActivity {

    List<Recipe> elements;
    private RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    //    ProgressDialog progress;
    RecyclerView recyclerRecipe;
    ArrayList<Recipe> listRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_options);

        String url = "http://gymup.zonahosting.net/gymphp/getGimnasiosWS.php";
        request = Volley.newRequestQueue(this);
        loadWebService(url);

        elements = new ArrayList<>();

        elements.add(new Recipe("1", "Spagetti", "30 min"));
        elements.add(new Recipe("2", "Pastel de papas", "45 min"));
        elements.add(new Recipe("3", "Milanesa", "15 min"));
        elements.add(new Recipe("4", "Empanadas", "120 min"));
        elements.add(new Recipe("5", "Spagetti", "30 min"));
        elements.add(new Recipe("6", "Pastel de papas", "45 min"));
        elements.add(new Recipe("7", "Milanesa", "15 min"));
        elements.add(new Recipe("8", "Empanadas", "120 min"));
        elements.add(new Recipe("9", "Spagetti", "30 min"));
        elements.add(new Recipe("10", "Pastel de papas", "45 min"));
        elements.add(new Recipe("11", "Milanesa", "15 min"));
        elements.add(new Recipe("12", "Empanadas", "120 min"));

        RecipeAdapter listAdapter = new RecipeAdapter(elements, getApplicationContext(), new RecipeAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(Recipe item) {
                Intent goToRecipe = new Intent(getApplicationContext(), FullRecipe.class);
                startActivity(goToRecipe);

            }
        });
        RecyclerView recyclerView = findViewById(R.id.recicler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(listAdapter);
    }

    public void loadWebService(String url){


    }

}