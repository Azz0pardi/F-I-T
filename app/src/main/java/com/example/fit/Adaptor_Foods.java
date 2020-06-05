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

public class Adaptor_Foods extends RecyclerView.Adapter<Adaptor_Foods.ItemViewHolder> {

    // This is the Adaptor being used for all the Foods/Recipes

    private FragListener listener;
    private Context context;
    private LayoutInflater mInflater;
    private List<Food_Model> AllFoods;

    DbHelper myDB;

    public interface FragListener{
        void onSend(CharSequence inp);

    }
// Constructor for the adaptor - allowing for the list of all Foods/Recipes to be passed on to the Adapter

    public Adaptor_Foods(Context context, List<Food_Model> All){
        mInflater = LayoutInflater.from(context);
        this.AllFoods = All;
    }


    // defines the properties every view holder created will have
    class ItemViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        public final TextView MyItemView;
        //Image part
        public final ImageView MyImageView;
        final Adaptor_Foods mAdapter;



        public ItemViewHolder(View itemView, Adaptor_Foods adapter) {
            super(itemView);
            context = itemView.getContext();
            MyItemView = itemView.findViewById(R.id.myText1);
            MyImageView = itemView.findViewById(R.id.myImageView);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);

        }

        // On click of said view the Single foody Activity is opened ( Activity responsible to display the information for one Recipe )
        // passing to it all the information related to the item which was pressed, The Activity
        // uses this data later on In order to display the  Recipe and It's Properties

        @Override
        public void onClick(View v) {

           // if (listener != null){
                int mPosition = getLayoutPosition();

                if(mPosition != RecyclerView.NO_POSITION) {
                        Food_Model Current = AllFoods.get(mPosition);  // The related data is fetched  from the List of All ingredient by making use of the position
                        mAdapter.notifyDataSetChanged();

                        Intent intent = new Intent(context, single_foody.class);//The data is passed on to the activity by using putExtra
                        intent.putExtra("Title",Current.getTitle());
                        intent.putExtra("Description",Current.getDesc());
                        intent.putExtra("Type",Current.getType());
                        intent.putExtra("Image",Current.getImage());

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
    public Adaptor_Foods.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

            View mItemView = mInflater.inflate(R.layout.my_itemcontentrow,
                    parent, false);
            return new ItemViewHolder(mItemView, this);


    }
    // gives the created view holder the data to display, each view containing the data according to its position.

    @Override
    public void onBindViewHolder(@NonNull Adaptor_Foods.ItemViewHolder holder, int position) {
        Food_Model Current = AllFoods.get(position);
        String mCurrent  = Current.getTitle();
        byte[] mCurrentImage = Current.getImage();
        holder.MyItemView.setText(mCurrent);// <----------------------Here is the probnlem I think
        holder.MyImageView.setImageBitmap(BitmapFactory.decodeByteArray(mCurrentImage,0,mCurrentImage.length));

    }
    // Returns the size of the All Foods list
    @Override
    public int getItemCount() {
        return AllFoods.size();
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
    public void filter(List<Food_Model> newlist){
        this.AllFoods = newlist;
        notifyDataSetChanged();
    }

}
