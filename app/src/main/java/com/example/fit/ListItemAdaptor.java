package com.example.fit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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

public class ListItemAdaptor extends RecyclerView.Adapter<ListItemAdaptor.ItemViewHolder> {



    private FragListener listener;
    private Context context;
    private LayoutInflater mInflater;
    private final LinkedList<String> mWordList;
    private final LinkedList<Integer> mImageList;
    DbHelper myDB;

    public interface FragListener{
        void onSend(CharSequence inp);
    }
                                                                    // This Adaptor was being used initially in the app
                                                                    // As progress went through I decided to create different adapters for every situation making it much easir to handle

    public ListItemAdaptor(Context context, LinkedList<String> wordList, LinkedList<Integer> imageList){
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
        this.mImageList= imageList;
    }


        // defines the properties every view holder created will have
    class ItemViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        public final TextView MyItemView;
        //Image part
        public final ImageView MyImageView;
        final ListItemAdaptor mAdapter;



        // Initializers

        public ItemViewHolder(View itemView, ListItemAdaptor adapter) {
            super(itemView);
            context = itemView.getContext();
            MyItemView = itemView.findViewById(R.id.myText1);
            MyImageView = itemView.findViewById(R.id.myImageView);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);

        }




            // On click method for recycler view item/view
        @Override
        public void onClick(View v) {
           // if (listener != null){
                int mPosition = getLayoutPosition();
                if(mPosition != RecyclerView.NO_POSITION) {
                  //  AddInfo();
                        Intent intent = new Intent(context, SingleItem.class);// This is what I added Last
                        this.itemView.getContext().startActivity(intent);

        } //
        }

    }

        // Peeks at first Item in the list
    public String getFirstItem(LinkedList<String> e){
        String first = e.peekFirst();
        return first;
    }
    // Methods for the Recycler View adapter which create a new view holder efficiently  as it can automatically reuse the viewholders.


    @NonNull
    @Override
    public ListItemAdaptor.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
            View mItemView = mInflater.inflate(R.layout.my_itemcontentrow,
                    parent, false);
            return new ItemViewHolder(mItemView, this);
    }

    // gives the created view holder the data to display, each view containing the data according to its position.
    @Override
    public void onBindViewHolder(@NonNull ListItemAdaptor.ItemViewHolder holder, int position) {
        String mCurrent = mWordList.get(position);
        int mCurrentImage = mImageList.get(position);
        holder.MyItemView.setText(mCurrent);//
        holder.MyImageView.setImageResource(mCurrentImage);

    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }      // Used to return th size of list which serves as the item count



    // Method used to change image View into a byte[] array which can later on be stored as a blob
     public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


}
