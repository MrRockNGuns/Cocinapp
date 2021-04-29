package com.example.cocinapp;

import android.app.DownloadManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

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
    Button btnLogin;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    // Esto hace la solicitud
    private void postRequest(String data){
        final String sendData = data;
        String url= "http://192.168.1.6/test/login_api.php";
        Log.d("ENTRE","Al Proceso");
        RequestQueue postRequest = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject objres = new JSONObject(response);
                    Snackbar notification = Snackbar.make(getView(),objres.toString(),Snackbar.LENGTH_SHORT);
                    notification.show();
                    Log.d("MENSAJES",objres.toString());
                }
                catch (JSONException e){
                    Snackbar notification = Snackbar.make(getView(),e.toString(),Snackbar.LENGTH_SHORT);
                    notification.show();
                    Log.d("MENSAJES",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar notification = Snackbar.make(getView(),error.toString(),Snackbar.LENGTH_SHORT);
                notification.show();
            }
        });
        postRequest.add(stringRequest);
    }

    public user() {
        // Required empty public constructor
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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString();
                String userPass = pass.getText().toString();
                String data = "{ " +
                        "\"email\"" + "\"" + userEmail + "\","+
                        "\"pass\"" + "\"" + userPass + "\""+
                        "}";
                Log.d("ANTES",data);
                postRequest(data);

            }
        });


        return view;
    }

}
