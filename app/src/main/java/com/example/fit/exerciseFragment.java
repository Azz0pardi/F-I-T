package com.example.fit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * A simple {@link Fragment} subclass.
 */
public class exerciseFragment extends Fragment {

    DbHelper myDB;
    private final LinkedList<String> mWordList = new LinkedList<>();                        // creation of lists storing data for navigation rows
    private final LinkedList<Integer> mImageList  = new LinkedList<>();


    private RecyclerView mRecyclerView;
    private ListAdaptor mAdapter;

    public exerciseFragment() {
        // Required empty public constructor
    }                                                   // This is the main Exercise Fragment , this is used to display a selection of items
                                                        // Each item representing a different category of exercise allowing the user to view
                                                        // the exercise according to the category chosen
                                                        // Current categories include Powerlifting,Calisthenics,Cardio and Yoga.


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Lists Storing the Titles and Images to be used by the adapter In order to display the Views accordingly

        mImageList.clear();
        mImageList.addLast(R.drawable.weights);
        mImageList.addLast(R.drawable.thenx);
        mImageList.addLast(R.drawable.cardio);
        mImageList.addLast(R.drawable.yoga);
        // Currently the lists are split, so order of addition is important

        mWordList.clear();
        mWordList.addLast("PowerLifting");
        mWordList.addLast("Calisthenics");
        mWordList.addLast("Cardio");
        mWordList.addLast("Yoga");


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_exercise, container, false);
        mRecyclerView = v.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ListAdaptor(this.getActivity(),mWordList,mImageList); // The lists are passed on the the adapter
        //
        mRecyclerView.setAdapter(mAdapter);
        //
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB = new DbHelper(this.getActivity());  // Currently not being used, kept here as said before , for reference and to  give an of the app dev journey
        myDB.getDatabaseName();
         //    AddInfo();

    }


}
