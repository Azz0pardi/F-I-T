package com.example.fit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;


public class add_food extends Fragment {

                                                                        // Add_food fragment - used to display and get the information required in order
                                                                        // To create a new Food/Recipe
    private static final int  GALLERY_REQUEST_CODE = 100;

    // Initializing variables/views
    EditText ingredient,Method,Title;
    Button add;
    String ing,Methodu,title;
    ImageButton Image;
    boolean checker = true;



    // Set Vars method used to set the value to string variables accordingly
    // info is obtained through the EditText's
    public void setVars() {
        title = Title.getText().toString();
        ing = ingredient.getText().toString();
        Methodu = Method.getText().toString();
    }

    public add_food() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_food, container, false);



        Title = v.findViewById(R.id.userenter_foodname);
        ingredient =v.findViewById(R.id.userenter_Ingredients);
        Method = v.findViewById(R.id.userenter_Method);
        Image = v.findViewById(R.id.food_image);
        add = v.findViewById(R.id.addd_food);

        //TEXT CHANGE LISTENERS ------------------------------------------------------
        // Text changed listener being used to enable the add button when data is filled in
        Title.addTextChangedListener(checkpls);
        ingredient.addTextChangedListener(checkpls);
        Method.addTextChangedListener(checkpls);

        //----------------------------------------------------------------------------

        // On image click the Open Gallery Method is called
        Image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openGallery();
            }
        });


        // When enabled and clicked , the add button allows the user to create a new Food/Recipe by using the information they've just entered.

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVars();
                Food_Model Food = new Food_Model();
                try {
                    if(imageToByte(Image)!= null) {
                        Food = new Food_Model(-1, title, Methodu, imageToByte(Image), ing);
                    }else{
                        Toast.makeText(getContext()," ! Image too large !",Toast.LENGTH_SHORT).show();
                        checker = false;
                    }
                }catch (Exception e){
                    checker = false;
                    Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT);
                }

                if(checker == true){
                    DbHelper Dbhelper = new DbHelper(getContext());   // If no errors occur the new Food object is added to the Database using the InsertDataFood Method
                    Dbhelper.InsertDataFood(Food);
                    Toast.makeText(getContext(), Food.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    // Method used to convert Images into byte array
    public static byte[] imageToByte(ImageView image) {

        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        byte[] byteArray = stream.toByteArray();
        if((byteArray.length)/1024 >= 1000){
            return byteArray = null;                // Most recent update - checks image size making sure that it is not too large
        }else{                                      // as it might crash the app
        return byteArray;
    }}

    // Method allows user to select an image from their phones gallery

    private void openGallery(){
        Intent intent = new Intent (Intent.ACTION_PICK);
        intent.setType("image/*");
        String[]  acceptedformats = {"image/jpeg","image/jpg","image/png"};  // accepted image formats/types
        intent.putExtra(Intent.EXTRA_MIME_TYPES,acceptedformats);
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
    }


    // Method used when an image is chosen. The newly selected image is displayed in the view.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST_CODE){
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    //data.getData returns the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    Image.setImageURI(selectedImage);
                    break;
            }}

    }

    // As explained previously this textwatcher is being used in conjunction with the add button
    // When the text is entered and conditions are met the add button is enabled
    private TextWatcher checkpls = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            title = Title.getText().toString();
            ing = ingredient.getText().toString();
            Methodu = Method.getText().toString();
            add.setEnabled(!title.isEmpty() && !ing.isEmpty() && !Methodu.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

    };








}
