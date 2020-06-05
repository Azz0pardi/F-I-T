package com.example.fit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleItem extends AppCompatActivity {


     // Displaying a single ingredient

        ImageView Image;
        TextView  pro,carb,fat,cals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);
        // views are set by their id in the xml file accordingly
        Image = findViewById(R.id.ItemImage);
        pro = findViewById(R.id.Proteinnumber);
        carb =findViewById(R.id.carbsNumb);
        fat = findViewById(R.id.Fatnumber);
        cals = findViewById(R.id.caloriesnumberstring);

        // toolbar is initialized to be used later on as an activity
        Toolbar toolbar = findViewById(R.id.toolbar1);
        //  setSupportActionBar(toolbar);
        Intent intent = getIntent();
        //decodes and  Sets Image in Imageview from the Byte Array Extra being sent
        Bitmap bitmap = BitmapFactory.decodeByteArray(intent.getByteArrayExtra("Image"), 0, intent.getByteArrayExtra("Image").length);
        Image.setImageBitmap(bitmap);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Displays back arrow in toolbar


        setTitle(intent.getStringExtra("Title")); // Displays the ingredients title as the toolbar title

        // Sets/Displays  all information being received accordingly
        pro.setText(""+intent.getIntExtra("Protein",0)+"g");
        carb.setText(""+intent.getIntExtra("Carbs",0)+"g");
        fat.setText(""+ intent.getIntExtra("Fat",0)+"g");
        cals.setText(""+intent.getIntExtra("Calories",0) + "K/Cal");


    }
}
