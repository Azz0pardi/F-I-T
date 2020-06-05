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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.List;

public class Adaptor_Ingredients extends RecyclerView.Adapter<Adaptor_Ingredients.ItemViewHolder> {

    // Initializers

    private FragListener listener;
    private Context context;
    private LayoutInflater mInflater;
    private  List<Ing_Model> AllIngredients;
                                                    // This is the Adaptor being used for all the ingredients
    DbHelper myDB;

    public interface FragListener{
        void onSend(CharSequence inp);

    }

    // Constructor for the adaptor - allowing for the list of all Ingredients to be passed on to the Adapter
    public Adaptor_Ingredients(Context context, List<Ing_Model> All){
        mInflater = LayoutInflater.from(context);
        this.AllIngredients = All;
    }

    // defines the properties every view holder created will have
    class ItemViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        public final TextView MyItemView;
        //Image part
        public final ImageView MyImageView;
        final Adaptor_Ingredients mAdapter;


        public ItemViewHolder(View itemView, Adaptor_Ingredients adapter) {
            super(itemView);
            context = itemView.getContext();
            MyItemView = itemView.findViewById(R.id.myText1);
            MyImageView = itemView.findViewById(R.id.myImageView);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);

        }



        // On click of said view the Single Item Activity is opened
        // passing to it all the information related to the item which was pressed, The Activity
        // uses this data later on In order to display the  Ingredient and its properties

        @Override
        public void onClick(View v) {

                int mPosition = getLayoutPosition();

                if(mPosition != RecyclerView.NO_POSITION) {
                        Ing_Model Current = AllIngredients.get(mPosition);      // The related data is fetched  from the List of All ingredient by making use of the position
                        mAdapter.notifyDataSetChanged();

                        Intent intent = new Intent(context, SingleItem.class);     //The data is passed on to the activity by using putExtra
                        intent.putExtra("Title",Current.getTitle());
                        intent.putExtra("Protein",Current.getProtein());
                        intent.putExtra("Carbs",Current.getCarb());
                        intent.putExtra("Fat",Current.getFat());
                        intent.putExtra("Image",Current.getImage());
                        intent.putExtra("Calories",Current.getCalories());
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
    public Adaptor_Ingredients.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

            View mItemView = mInflater.inflate(R.layout.my_itemcontentrow,
                    parent, false);
            return new ItemViewHolder(mItemView, this);


    }

    // gives the created view holder the data to display, each view containing the data according to its position.
    @Override
    public void onBindViewHolder(@NonNull Adaptor_Ingredients.ItemViewHolder holder, int position) {
        Ing_Model Current = AllIngredients.get(position);
        String mCurrent  = Current.getTitle();
        byte[] mCurrentImage = Current.getImage();
        holder.MyItemView.setText(mCurrent);// <----------------------Here is the probnlem I think
        holder.MyImageView.setImageBitmap(BitmapFactory.decodeByteArray(mCurrentImage,0,mCurrentImage.length));

    }

    // Returns the size of the AllIngredients list
    @Override
    public int getItemCount() {
        return AllIngredients.size();
    }


    // Method used to convert the image in the imageview to byte array
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    // Method used together with the search bar in the fragment the old list is replaced with the new list ( holding the filtered data )
    // The adapter is notified and the data is refreshed accordingly.

    public void filter(List<Ing_Model> newlist){
        this.AllIngredients = newlist;
        notifyDataSetChanged();
    }


}
