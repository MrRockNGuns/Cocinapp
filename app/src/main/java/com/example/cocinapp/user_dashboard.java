package com.example.cocinapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link user_dashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class user_dashboard extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Button btnUpreceta,btnEditData,btnLogOut;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public user_dashboard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment user_dashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static user_dashboard newInstance(String param1, String param2) {
        user_dashboard fragment = new user_dashboard();
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
        View view = inflater.inflate(R.layout.fragment_user_dashboard, container, false);

        btnUpreceta = view.findViewById(R.id.btnUpreceta);
        btnEditData = view.findViewById(R.id.btnEditData);
        btnLogOut   = view.findViewById(R.id.btnLogOut);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 getActivity().getSupportFragmentManager().beginTransaction()
                         .replace(R.id.Principal,new user()).addToBackStack(null).commit();
                 Snackbar notification = Snackbar.make(getView(),"Usuario Desconectado",Snackbar.LENGTH_SHORT);
                 notification.show();
             }
         }
        );
        return view;
    }
}