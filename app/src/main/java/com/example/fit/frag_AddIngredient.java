package com.example.fit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
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
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class frag_AddIngredient extends Fragment {

    private static final int  GALLERY_REQUEST_CODE = 100;
  //d
    int calscalc;
    TextView Caloriesnum;
    EditText Protein,Carbohydrates,Fat,Title;
    Button add;
    String carbs,protein,fats,title;
    int carbs_int,protein_int,fats_int;
    ImageButton Image;
    boolean checker = true;
                                                                // Fragment used to allow user to add a new Ingredient to the Db.
    public frag_AddIngredient() {
    }


    // Method used to get the text from the views and storing it in each variable accordingly
    public void setVars() {
        carbs = Carbohydrates.getText().toString();
        protein = Protein.getText().toString();
        fats = Fat.getText().toString();
        if(carbs.contains(".") ||protein.contains(".")||fats.contains(".")){
            carbs_int = 0; protein_int = 0; fats_int=0;
        }else {

            carbs_int = Integer.parseInt(carbs);
            protein_int = Integer.parseInt(protein);
            fats_int = Integer.parseInt(fats);
        }}

    // onCreate method
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // onCreate View
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_frag__add_ingredient, container, false);
        // Setting views according to their id
        Title = v.findViewById(R.id.userenter_IngredientName);
        Carbohydrates =v.findViewById(R.id.userenter_Carbs);
        Fat = v.findViewById(R.id.userenter_Fat);
        Protein = v.findViewById(R.id.userenter_Protein);
        Image = v.findViewById(R.id.ingredient_image);
        add = v.findViewById(R.id.addd_ing);
        Caloriesnum = v.findViewById(R.id.display_cals);

        //TEXT CHANGE LISTENERS ------------------------------------------------------

        Title.addTextChangedListener(checkpls);
        Carbohydrates.addTextChangedListener(checkpls);
        Fat.addTextChangedListener(checkpls);
        Protein.addTextChangedListener(checkpls);


        //----------------------------------------------------------------------------
        // Image on click listener , on click the openGallery Method is called
        Image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openGallery();
            }
        });

        // Add Button on click Listener
        // When pressed the variables are set using the setVars() Method
        // A new Ingredient Is created  using the data in the variables accordingly
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVars();
                Ing_Model Ingredient = new Ing_Model();
                try {
                    if(imageToByte(Image)!= null) {
                        if(carbs.contains(".") ||protein.contains(".")||fats.contains(".")){ // Most recent update - only integers are allowed
                            Toast.makeText(getContext(),"Integers only allowed",Toast.LENGTH_SHORT).show();
                            checker = false;
                        }else {
                            Ingredient = new Ing_Model(-1, Title.getText().toString(), carbs_int, protein_int, fats_int, imageToByte(Image));
                        }
                    }else{
                        Toast.makeText(getContext(),"Image too large",Toast.LENGTH_SHORT).show();
                        checker = false;
                    }
                }catch (Exception e){
                    checker = false;
                    Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT);
                }
                // If no errors are met the new Ingredient is added to the database
            if(checker == true){
                DbHelper Dbhelper = new DbHelper(getContext());
                Dbhelper.InsertDataIngredients(Ingredient);
                Toast.makeText(getContext(), " Ingredient created successfully ", Toast.LENGTH_SHORT).show();
            }
            }
        });
        return v;
    }
        // Method used to change the Image from Imageview to byte array
    public static byte[] imageToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        byte[] byteArray = stream.toByteArray();
        if((byteArray.length)/1024 >= 1000){
            return byteArray = null;            //Most recent update - added to check the size of the image, if
        }else{                                  // greater than 1mb then user is prompted that the image is too large
            return byteArray;
        }

    }

    // Method used to allow user to choose an image from their gallery.
    private void openGallery(){
        Intent intent = new Intent (Intent.ACTION_PICK);
        intent.setType("image/*");
        String[]  acceptedformats = {"image/jpeg","image/jpg","image/png"}; // Sets the type of images to be accepted
        intent.putExtra(Intent.EXTRA_MIME_TYPES,acceptedformats);
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
    }

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

    // Text Watcher being used to check that all the entries have been entered and  if so allows the user to click the Add button

    private TextWatcher checkpls = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        // Method called everytime theres a change in text
        // If the conditions are met, that is if neither of the options are empty then the add button is enabled allowing user to proceed.
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            title =  Title.getText().toString();
            carbs = Carbohydrates.getText().toString();
            protein = Protein.getText().toString();
            fats = Fat.getText().toString();
            if(!carbs.isEmpty() && !protein.isEmpty() && !fats.isEmpty()){
                if(carbs.contains(".") ||protein.contains(".")||fats.contains(".")){
                    Caloriesnum.setText("INTEGERS ONLY");
//                    Carbohydrates.setText("0");
//                    Protein.setText("0");
//                    Fat.setText("0");
                }else {
                    calscalc = ((Integer.parseInt(carbs) * 4) + (Integer.parseInt(protein) * 4) + (Integer.parseInt(fats) * 9));   // This is done to display a live representation of the
                    Caloriesnum.setText(calscalc + "  Calories");                                                          // amount of cals in the ingredient- In general
                }
            }else{
                         Caloriesnum.setText("Calories");                                                        // Carbs and Protein have approx 4 cals per gram and fats 9 cals per gram
            }
            add.setEnabled(!carbs.isEmpty() && !protein.isEmpty() && !fats.isEmpty() && !title.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
    
}
