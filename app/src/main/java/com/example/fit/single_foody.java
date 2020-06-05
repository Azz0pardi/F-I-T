package com.example.fit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class single_foody extends AppCompatActivity {

    // Initializers

    ImageView Image;
    TextView Ingredients;
    TextView Desc;
                                                    // Class used to display a single Food / Recipe

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_single);

        // Initializes Views by their Id's
        Image = findViewById(R.id.singleFoodImg);
        Ingredients = findViewById(R.id.singleIngredients);
        Desc = findViewById(R.id.actualMethod);

        Intent intent = getIntent();
        //decodes and  Sets Image in Imageview from the Byte Array Extra being sent
        Bitmap bitmap = BitmapFactory.decodeByteArray(intent.getByteArrayExtra("Image"), 0, intent.getByteArrayExtra("Image").length);
        Image.setImageBitmap(bitmap);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar); // sets toolbar as Actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // The Views are set accordingly according to the data being received

        setTitle(intent.getStringExtra("Title"));
        Desc.setText(intent.getStringExtra("Description"));
        Ingredients.setText(intent.getStringExtra("Type"));
        Desc.setMovementMethod(new ScrollingMovementMethod());
//        fat.setText(""+ intent.getIntExtra("Fat",0));
//        cals.setText(""+intent.getIntExtra("Calories",0));


    }


}
