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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Food extends Fragment {

    // Food Fragment being used to display all the Foods / Recipes available

    private final LinkedList<String> mWordList = new LinkedList<>();
    private final LinkedList<Integer> mImageList  = new LinkedList<>();
    EditText edittext;
    List<Food_Model> AllFoods;
    private RecyclerView mRecyclerView;
    private Adaptor_Foods mAdapter;

    DbHelper myDB;
    public Food() {
        // Required empty public constructor
    }

    //Defualt fragment methods created upon creation of a new fragment
    public static Food newInstance(String param1, String param2) {
        Food fragment = new Food();
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

    // onCreate View Method
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // A new Dbhelper obj is initialized and the AllFoods List is populated with all the available food/Recipes
        // by making use of the getAllFoods method implemented in the Dbhelper
        DbHelper Dbhelper = new DbHelper(this.getContext());
        AllFoods =  Dbhelper.getAllFoods();
        // The layout is inflated
        View v = inflater.inflate(R.layout.fragment_ingredient, container, false);
        //-----------------------------------------------------

        // Same Method as seen in the Ingredient Fragment, this view and Textchanged Listener are being used
        // In order to allow the user to filter through the list of items as they wish

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

        // Recycler view being used to display the items views
        //------------------------------------------------------
        mRecyclerView = v.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);  // This is said to increase the recycler views performance
        mAdapter = new Adaptor_Foods(this.getActivity(),AllFoods);
        //
        mRecyclerView.setAdapter(mAdapter);
        //
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        return v;


    }

        // As in Ingredient class this method is used together with the text changed listener
        // By checking if the string entered in the search bar is present in any of the strings in the Recipes Titles it makes
        // If conditions are met then the item is added to the new List 'filter'. filter is then used in the adapters method 'filter'.
    private void check(String item){
        List<Food_Model> filter = new ArrayList<>();
        for(Food_Model ing : AllFoods){
            if(ing.getTitle().toLowerCase().contains(item.toLowerCase())){
                filter.add(ing);
            }
        }
        mAdapter.filter(filter);
    }

}
