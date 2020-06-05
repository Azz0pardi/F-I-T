package com.example.fit;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListAdaptor extends RecyclerView.Adapter<ListAdaptor.ItemViewHolder> {

                                                            // Adapter being used for the different types of exercises page
                                                            // Takes the user to that specific category of exercises depending on what the user chooses
    private FragListener listener;
    private Context context;
    private LayoutInflater mInflater;
    private final LinkedList<String> mWordList;
    private final LinkedList<Integer> mImageList;


    public interface FragListener{
        void onSend(CharSequence inp);
    }

        // Adapter constructor

    public ListAdaptor(Context context,LinkedList<String> wordList,LinkedList<Integer> imageList){
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
        this.mImageList= imageList;
    }

    // defines the properties every view holder created will have
    class ItemViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        // declarations and initializers

        public final TextView MyItemView;
        //Image part
        public final ImageView MyImageView;
        final ListAdaptor mAdapter;


        public ItemViewHolder(View itemView, ListAdaptor adapter) {
            super(itemView);
            context = itemView.getContext();
            MyItemView = itemView.findViewById(R.id.myText1);
            MyImageView = itemView.findViewById(R.id.myImageView);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);

        }



        // On click of said view the fragment is changed with the Exercise type fragment
        // passing to it a string with the type of exercise which was chosen , The fragment
        // uses this data later on In order to display the related exercises
        @Override
        public void onClick(View v) {

                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                int mPosition = getLayoutPosition();

                if(mPosition != RecyclerView.NO_POSITION) {
                    String element = mWordList.get(mPosition);

                    Bundle bundle = new Bundle();
                    Fragment selectedFragment = null;
                    selectedFragment = new Exercise_Type_Frag();

                    if (mPosition == 0) { //If type Cardio

                        bundle.putString("Type","PowerLifting");

                    } else if (mPosition == 1){ // If Type Yoga

                        bundle.putString("Type","Calisthenics");

                    }else if (mPosition==2){  //If type PowerLifting

                        bundle.putString("Type","Cardio");

                    }else{
                        bundle.putString("Type","Yoga");
                    }
                        selectedFragment.setArguments(bundle);
                        manager.beginTransaction().replace(R.id.fragment_cont,selectedFragment).addToBackStack(null).commit();

                    }

             //   }
        } //
        }




// gets first item in the list
    public String getFirstItem(LinkedList<String> e){
        String first = e.peekFirst();
        return first;
    }
    // Methods for the Recycler View adapter which create a new view holder efficiently  as it can automatically reuse the viewholders.
    @NonNull
    @Override
    public ListAdaptor.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        String a = getFirstItem(mWordList);
        if(a == "Excercise : 1 ") {
            View mItemView = mInflater.inflate(R.layout.my_itemcontentrow,    // This was initially being used like so, currently the first exercise
                    parent, false);                               // will never be Exercise 1 but I kept it here just for reference so as
            return new ItemViewHolder(mItemView, this);                // remember the inefficiency it might bring to maintain
        }else{
            View mItemView = mInflater.inflate(R.layout.my_row,                // The else statement is the route that is taken
                    parent, false);
            return new ItemViewHolder(mItemView, this);
        }

    }
    // gives the created view holder the data to display, each view containing the data according to its position.
    @Override
    public void onBindViewHolder(@NonNull ListAdaptor.ItemViewHolder holder, int position) {
        String mCurrent = mWordList.get(position);
        int mCurrentImage = mImageList.get(position);
        holder.MyItemView.setText(mCurrent);// <----------------------Here is the probnlem I think
        holder.MyImageView.setImageResource(mCurrentImage);

    }
    // returns number of Items in list
    @Override
    public int getItemCount() {
        return mWordList.size();
    }
}
