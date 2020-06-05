package com.example.fit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.LinkedList;

public class exerciseType extends AppCompatActivity {


                                                // This Activity was the activity I was using in the first steps of this apps creating
                                                // Please note that it has no use in the app and Is only kept here as reference of my
                                                // steps taken + shows the progress done from these steps. I like to keep such files so
                                                // so as to refer to them if needed and not repeat the same mistakes.
                                                // Ideally this is removed should the app ever go to live
    // 4 types of exercises


    private final LinkedList<String> mWordList = new LinkedList<>();
    private final LinkedList<Integer> mImageList  = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private ListItemAdaptor mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWordList.addLast("Excercise : 1 ");
        mWordList.addLast("Excercise : 2 ");
        mWordList.addLast("Excercise : 3 ");
        mWordList.addLast("Excercise : 4 ");
        mImageList.addLast(R.drawable.run);
        mImageList.addLast(R.drawable.ic_launcher_background);
        mImageList.addLast(R.drawable.list);
        mImageList.addLast(R.drawable.food);



        // Inflate the layout for this fragment
        setContentView(R.layout.activity_exercise_type);
        mRecyclerView = findViewById(R.id.recyclerview);

        //
        mAdapter = new ListItemAdaptor(this,mWordList,mImageList);
        //
        mRecyclerView.setAdapter(mAdapter);
        //
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    //    Toolbar toolbar = findViewById(R.id.toolbar);
    //      setSupportActionBar(toolbar);

    }


}
