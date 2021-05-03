package com.example.cocinapp;

import android.app.DownloadManager;
import android.app.FragmentManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link user#newInstance} factory method to
 * create an instance of this fragment.
 */
public class user extends Fragment {

    //RequestQueue myRequest;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    EditText email, pass;
    Button btnLogin, btnRegister;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public user() {
        // Required empty public constructor
    }

    private void jsonArrayPost(String email,String pass){

        RequestQueue postRequest = Volley.newRequestQueue(getContext());
        String url= "http://gestion.universof.com/back_cocinapp/login_api.php";
        //String url= "http://10.0.2.2/test/login_api.php";
        final JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("email", email);
            jsonBody.put("pass", pass);
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
                        Snackbar notification = Snackbar.make(getView(),"Usuario y/o Contraseña incorrectos",Snackbar.LENGTH_SHORT);
                        notification.show();
                    }
                });
        postRequest.add(jsonObjectRequest);
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment user.
     */

    // TODO: Rename and change types and number of parameters
    public static user newInstance(String param1, String param2) {
        user fragment = new user();
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

        View view = inflater.inflate(R.layout.fragment_user, container, false);

        email = (EditText)view.findViewById(R.id.etUser);
        pass  = (EditText)view.findViewById(R.id.etPass);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnRegister = view.findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString();
                String userPass = pass.getText().toString();
                jsonArrayPost(userEmail,userPass);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Principal,new create_user()).addToBackStack(null).commit();
            }
        });
        return view;
    }
}