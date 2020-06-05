package com.example.fit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Ingredient extends Fragment {

//
    // vars                                                 // Main Ingredient Fragment being used to display all the Ingredients

    private final LinkedList<String> mWordList = new LinkedList<>();
    private final LinkedList<Integer> mImageList  = new LinkedList<>();

    List<Ing_Model> AllIngredients;

    //  List which will store all the ingredients retrieved from db


    private RecyclerView mRecyclerView;
    private Adaptor_Ingredients mAdapter;
    EditText edittext;
    public Ingredient() {
        // Required empty public constructor
    }

    // default methods created when creating a fragment

    public static Ingredient newInstance(String param1, String param2) {
        Ingredient fragment = new Ingredient();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }


    // OnCreateView Method Override
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DbHelper Dbhelper = new DbHelper(this.getContext());            // using a Dbhelper obj the getAllIngredients method is used to fetch all the ingredients in the db
         AllIngredients =  Dbhelper.getAllIngredients();
        View v = inflater.inflate(R.layout.fragment_ingredient, container, false); // Inflation of layouts accordingly
        mRecyclerView = v.findViewById(R.id.recyclerview); // The Recycler View is being used to display the items views
       // mRecyclerView.setHasFixedSize(true);
        mAdapter = new Adaptor_Ingredients(this.getActivity(),AllIngredients);  // Passing the list of ingredients to the Custom Adapter class Adaptor_ingredients
        //
        mRecyclerView.setAdapter(mAdapter);
        //
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        // This view and methods are used in order to let the user filter through the list of items according to the items title
        // by making use of a Text Changed Listener
        edittext = v.findViewById(R.id.edittext);
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                check(s.toString());
            }
        });
        return v;

        // Inflate the layout for this fragment

    }
    // The check method is being used with the text changed listener
    // By checking if the string entered in the search bar is present in any of the strings in the Ingredients Titles it makes
    // If conditions are met then the item is added to the new List 'filter'. filter is then used in the adapters method filter.
    private void check(String item){
        List<Ing_Model> filter = new ArrayList<>();
        for(Ing_Model ing : AllIngredients){
            if(ing.getTitle().toLowerCase().contains(item.toLowerCase())){
                filter.add(ing);
            }
        }
        mAdapter.filter(filter);
    }


}
