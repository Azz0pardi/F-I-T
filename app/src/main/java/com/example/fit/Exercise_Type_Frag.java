package com.example.fit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Exercise_Type_Frag extends Fragment {
                                                                                // Main Fragment being used to display A list of exercises according to their type

    // Initializers

    private final LinkedList<String> mWordList = new LinkedList<>();
    private final LinkedList<Integer> mImageList  = new LinkedList<>();
    private final  List<Ex_Model> FilteredEx = new ArrayList<>();  // List to be used to store the items/ exercises which meet the condition of type later on

    private RecyclerView mRecyclerView;
    private Adaptor_Exercise mAdapter;

    DbHelper myDB;
    public Exercise_Type_Frag() {
        // Required empty public constructor
    }


    // default methods created upon creation of new fragment
    public static Exercise_Type_Frag newInstance(String param1, String param2) {
        Exercise_Type_Frag fragment = new Exercise_Type_Frag();
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

        // OnCreate View Method
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Ex_Model temp;
        String typestr = getArguments().getString("Type");                  // The Arguments being sent by the exercise fragment ( when the user selects a
      //  Log.i("hello",typestr);                                                // Category of exercises ) are retrieved. using the dbhelper obj the method getallexercises
        DbHelper Dbhelper = new DbHelper(this.getContext());                     // is used to fetch all the exercises available. The list is then filtered by the type of
        List<Ex_Model> AllEx =  Dbhelper.getAllExercises();                      // exercise chosen. all the exercises who's  type match are added to the new list FilteredEx
        for(int i = 0;i < AllEx.size();i++){
            temp = AllEx.get(i);
            if(temp.getType().equals(typestr)){                             //Filtering exercises
               FilteredEx.add(temp);
            }
            else{
                    // Should the type not match, do nothing
            }

        }
        View v = inflater.inflate(R.layout.fragment_exercise, container, false);
        mRecyclerView = v.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new Adaptor_Exercise(this.getActivity(),FilteredEx);     //Passing on Filtered List so as to show appropriate Items According to Type
        //
        mRecyclerView.setAdapter(mAdapter);
        //
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        return v;


    }
}
