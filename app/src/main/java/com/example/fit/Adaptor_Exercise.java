package com.example.fit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.List;

public class Adaptor_Exercise extends RecyclerView.Adapter<Adaptor_Exercise.ItemViewHolder> {

                                                // Adapter used for Recycler view
    // The main idea for using a recycler view is that it efficiently allows for the views to be displayed - since
    // This app might make use of large amounts of data ( numerous items in the db etc ) then Efficiency is essential.
    //Adapter used for exercises

    //    Initializers
    private FragListener listener;
    private Context context;
    private LayoutInflater mInflater;
    private final List<Ex_Model> AllExercises;

    DbHelper myDB;

    public interface FragListener{
        void onSend(CharSequence inp);
    }

    // Constructor for the adaptor - allowing for the list of all Exercises to be passed on to the Adapter

    public Adaptor_Exercise(Context context, List<Ex_Model> All){
        mInflater = LayoutInflater.from(context);
        this.AllExercises = All;
    }


    // defines the properties every view holder created will have
    class ItemViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        public final TextView MyItemView;
        //Image part
        public final ImageView MyImageView;
        final Adaptor_Exercise mAdapter;



        public ItemViewHolder(View itemView, Adaptor_Exercise adapter) {
            super(itemView);
            context = itemView.getContext();
            MyItemView = itemView.findViewById(R.id.myText1); // aw fejn iddahhal laffarjiet
            MyImageView = itemView.findViewById(R.id.myImageView);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);

        }

        // On click of said view the Single Exercise Activity is opened ( Activity responsible to display the information for one Exercise )
        // passing to it all the information related to the item which was pressed, The Activity
        // uses this data later on In order to display the  Exercise and It's Properties
        @Override
        public void onClick(View v) {

           // if (listener != null){
                int mPosition = getLayoutPosition();

                if(mPosition != RecyclerView.NO_POSITION) {
                        Ex_Model Current = AllExercises.get(mPosition);  // The related data is fetched  from the List of All Exercises by making use of the position
                        mAdapter.notifyDataSetChanged();


                        Intent intent = new Intent(context, singleExercise.class);  //The data is passed on to the activity by using putExtra
                        intent.putExtra("Title",Current.getTitle());
                        intent.putExtra("Calories",Current.getCalories());
                        intent.putExtra("Type",Current.getType());
                        intent.putExtra("Description",Current.getDescription());
                    //    intent.putExtra("Image",Current.getImage());
                        intent.putExtra("vidId",Current.getId_exx());
                        this.itemView.getContext().startActivity(intent);


        } //
        }
    }



    // Gets first Item in the list
    public String getFirstItem(LinkedList<String> e){
        String first = e.peekFirst();
        return first;
    }
    // Methods for the Recycler View adapter which create a new view holder efficiently  as it can automatically reuse the viewholders.
    @NonNull
    @Override
    public Adaptor_Exercise.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

            View mItemView = mInflater.inflate(R.layout.my_itemcontentrow,
                    parent, false);
            return new ItemViewHolder(mItemView, this);


    }


    // gives the created view holder the data to display, each view containing the data according to its position.
    @Override
    public void onBindViewHolder(@NonNull Adaptor_Exercise.ItemViewHolder holder, int position) {
        Ex_Model Current = AllExercises.get(position);
        String mCurrent  = Current.getTitle();
        byte[] mCurrentImage = Current.getImage();
        holder.MyItemView.setText(mCurrent);//
        holder.MyImageView.setImageBitmap(BitmapFactory.decodeByteArray(mCurrentImage,0,mCurrentImage.length));

    }

    // Returns the size of the All Exercises list
    @Override
    public int getItemCount() {return AllExercises.size(); }


    // Changes an imageView to bytes
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }



}
