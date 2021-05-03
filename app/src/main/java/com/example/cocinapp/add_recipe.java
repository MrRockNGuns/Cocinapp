package com.example.cocinapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        etName = (EditText)view.findViewById(R.id.etRecipeName);
        etIngredients = (EditText)view.findViewById(R.id.etRecipeIngredients);
        etRecipePreparation = (EditText)view.findViewById(R.id.etRecipePreparation);
        etRecipeTime = (EditText)view.findViewById(R.id.etRecipeTime);
        /*
        cbCeliac = (CheckBox)view.findViewById(R.id.cbRecipeCeliac);
        cbVegan = (CheckBox)view.findViewById(R.id.cbRecipeVegan);
        cbVegetarian = (CheckBox)view.findViewById(R.id.cbRecipeVegetarian);
        cbDiabetic = (CheckBox)view.findViewById(R.id.cbRecipeDiabetic);
        */
        btAddRecipe =(Button)view.findViewById(R.id.btnAdd_Recipe);


        btAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer recipetime;

                String name = etName.getText().toString();
                String ingredients = etIngredients.getText().toString();
                String preparation = etRecipePreparation.getText().toString();
                String time  = etRecipeTime.getText().toString();

                //recipetime = new Integer(time).intValue();

                // Llamo a la funcion
                jsonCreateRecipe(name,ingredients,preparation,time);
            }
        });

        return view;
    }

    private void jsonCreateRecipe(String name, String ingredients, String preparation, String time){
        RequestQueue postRequest = Volley.newRequestQueue(getContext());
        String url = "http://gestion.universof.com/back_cocinapp/IngresoReceta_api.php";
        //String url = "http://10.0.2.2/test/IngresoReceta_api.php";

        final JSONObject jsonIngredient = new JSONObject();
        try {
            jsonIngredient.put("ingredient",ingredients);
        }
        catch (JSONException e){
            Log.d("Error", "Error al realizar put");
        }
        final JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("name", name);
            jsonBody.put("preparation", preparation);
            jsonBody.put("time", time);
            jsonBody.put("ingredients",jsonIngredient);
        }
        catch (JSONException e){
            Log.d("Error", "Error al realizar put");
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int size = response.length();
                        for(int i=0; i<size; i++){
                            try {
                                JSONObject jsonObject = new JSONObject(response.toString());
                                String titulo = jsonObject.getString("message");
                                Snackbar notification = Snackbar.make(getView(),titulo,Snackbar.LENGTH_SHORT);
                                notification.show();
                                getActivity().getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.Principal,new user_dashboard()).addToBackStack(null).commit();
                            }
                            catch (JSONException e){
                                e.printStackTrace();
                                Snackbar notification = Snackbar.make(getView(),"Hubo un error al intentarlo",Snackbar.LENGTH_SHORT);
                                notification.show();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        int error_code = error.networkResponse.statusCode;
                        if (error_code != 401){
                            Snackbar notification = Snackbar.make(getView(),"Hubo un problema al intentarlo",Snackbar.LENGTH_SHORT);
                            notification.show();
                        }
                        Snackbar notification = Snackbar.make(getView(),"Hubo un error que no pudimos resolver",Snackbar.LENGTH_SHORT);
                        notification.show();
                    }
                });
        postRequest.add(jsonObjectRequest);
    }
}