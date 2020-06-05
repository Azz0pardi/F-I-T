package com.example.fit;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Home extends Fragment {

                                                                // This is the Home Fragment

    Button btn_food,btn_ing,btn_exer;
    Drawable draw,draw2;
    private RequestQueue MyQueue;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<String> vidIds = new ArrayList<>();
    List<String> DescList = new ArrayList<>();
    public Home() {
        // Required empty public constructor
    }


    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vidIds.add("A-hZQXZUYH4");
        vidIds.add("E8SLO3JgUbQ");



        View v = inflater.inflate(R.layout.fragment_home, container, false);
        btn_ing = v.findViewById(R.id.btn_addIngredient);

        btn_food = v.findViewById(R.id.btn_addfood);
        btn_exer = v.findViewById(R.id.btn_addExercise);


        MyQueue = Volley.newRequestQueue(getContext());
        draw = v.getResources().getDrawable(R.drawable.pancake);


        // On click listeners for every button, each button take the user to the desired page/fragment
        // these button are used for the user to be taken to the Creation of a new Item, be it an Ingredient,Food/Recipe or an Exercise.

        btn_ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new frag_AddIngredient();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,
                        fragment).commit();

            }
        });

        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new add_food();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,
                        fragment).commit();
            }
        });

        btn_exer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Add_Exercise();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,
                        fragment).commit();

             //   getDataJson();

            }
        });

    return v;
    }

    public static byte[] imageToByte(Drawable image) {
        Bitmap bitmap = ((BitmapDrawable)image).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }



}
