package com.example.cocinapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link add_recipe#newInstance} factory method to
 * create an instance of this fragment.
 */
public class add_recipe extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText etName, etIngredients,etRecipePreparation,etRecipeTime;

    CheckBox cbCeliac,cbVegan,cbVegetarian,cbDiabetic;

    Button btAddRecipe;

    Integer sCeliac,sVegan,sVegetarian,sDiabetic;

    public add_recipe() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_recipe.
     */
    // TODO: Rename and change types and number of parameters
    public static add_recipe newInstance(String param1, String param2) {
        add_recipe fragment = new add_recipe();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_recipe, container, false);

        etName = (EditText)view.findViewById(R.id.etName);
        etIngredients = (EditText)view.findViewById(R.id.etRecipeIngredients);
        etRecipePreparation = (EditText)view.findViewById(R.id.etRecipePreparation);
        etRecipeTime = (EditText)view.findViewById(R.id.etRecipeTime);

        cbCeliac = (CheckBox)view.findViewById(R.id.cbRecipeCeliac);
        cbVegan = (CheckBox)view.findViewById(R.id.cbRecipeVegan);
        cbVegetarian = (CheckBox)view.findViewById(R.id.cbRecipeVegetarian);
        cbDiabetic = (CheckBox)view.findViewById(R.id.cbRecipeDiabetic);

        btAddRecipe =(Button)view.findViewById(R.id.btnAdd_Recipe);


        btAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,ingredients,preparation,time;
                Integer recipetime;

                // Mostrar Coso

            }
        });

        return view;
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.cbRecipeVegan:
                if (checked){
                    sVegan = 1;
                }
                else {
                    sVegan = 0;
                }
            case R.id.cbRecipeCeliac:
                if (checked){
                    sCeliac = 1;
                }
                else {
                    sCeliac = 0;
                }
            case R.id.cbRecipeDiabetic:
                if (checked){
                    sDiabetic = 1;
                }
                else {
                    sDiabetic = 0;
                }
            case R.id.cbRecipeVegetarian:
                if (checked){
                    sVegetarian = 1;
                }
                else {
                    sVegetarian = 0;
                }
        }
    }
}