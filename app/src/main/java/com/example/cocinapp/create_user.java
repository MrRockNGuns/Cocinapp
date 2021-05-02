package com.example.cocinapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link create_user#newInstance} factory method to
 * create an instance of this fragment.
 */
public class create_user extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Spinner gender;
    TextView tvName,tvEmail,tvPass,tvBirthdate;
    Button btnRegister;

    String gendervalue="", gener ="";


    private void jsonregisterUser(String name,String email,String pass,String birthdate,String gendervalue){
        RequestQueue postRequest = Volley.newRequestQueue(getContext());
        String url= "http://cocinapp.infinityfreeapp.com/registro_api.php";
        //String url= "http://10.0.2.2/test/registro_api.php";

        final JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("nombre", name);
            jsonBody.put("email", email);
            jsonBody.put("password", pass);
            jsonBody.put("fecha", birthdate);
            jsonBody.put("genero", gendervalue);

            Log.e("JSON",jsonBody.toString());
        }
        catch (JSONException e){
            Log.d("ERROR","no pudo hacer PUT");
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
                                        .replace(R.id.Principal,new user()).addToBackStack(null).commit();
                            }
                            catch (JSONException e){
                                e.printStackTrace();
                                Snackbar notification = Snackbar.make(getView(),"Hubo un error al intentarlo",Snackbar.LENGTH_SHORT);
                                notification.show();
                            }
                        }
                    }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //int error_code = error.networkResponse.statusCode;
                            /*
                            if (error_code != 401){
                                Snackbar notification = Snackbar.make(getView(),"Hubo un problema al intentarlo",Snackbar.LENGTH_SHORT);
                                notification.show();
                            }
                             */
                            Snackbar notification = Snackbar.make(getView(),error.toString(),Snackbar.LENGTH_SHORT);
                            notification.show();
                            Log.e("ERROR",error.toString());
                        }

                    });
        postRequest.add(jsonObjectRequest);
    }

    public create_user() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment create_user.
     */
    // TODO: Rename and change types and number of parameters
    public static create_user newInstance(String param1, String param2) {
        create_user fragment = new create_user();
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
        View view = inflater.inflate(R.layout.fragment_create_user, container, false);

        tvBirthdate = view.findViewById(R.id.etBirthDay);
        tvEmail     = view.findViewById(R.id.etEmail);
        tvName      = view.findViewById(R.id.etName);
        tvPass      = view.findViewById(R.id.etPasswd);
        btnRegister = view.findViewById(R.id.btnRegister_User);
        gender      = view.findViewById(R.id.spnGender);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.gender_option,
                android.R.layout.simple_spinner_item);

        gender.setAdapter(adapter);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, name, pass, birthdate;
                //asigno valores
                name = tvName.getText().toString();
                email = tvEmail.getText().toString();
                pass = tvPass.getText().toString();
                birthdate = tvBirthdate.getText().toString();
                gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        gendervalue= parent.getItemAtPosition(position).toString();
                        if (gendervalue != "Seleccione.."){
                            gener =  determinateGender(gendervalue);
                            Log.d("DEBUG",gener);
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // TODO Auto-generated method stub
                    }
                });
                Log.d("Genero",gendervalue);
                if (gendervalue != "Seleccione.."){
                    jsonregisterUser(name,email,pass,birthdate,gener);
                }
                else {
                    Snackbar notification = Snackbar.make(getView(),"Debe Indicar un Genero",Snackbar.LENGTH_SHORT);
                    notification.show();
                }
            }
        });
        return view;
    }
    public String determinateGender(String genders){
        if (genders == "Masculino"){
            genders = "M";
        }
        if (genders == "Femenino"){
            genders = "F";
        }
        if (genders == "Otro"){
            genders = "O";
        }
        return genders;
    }
}

