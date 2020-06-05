package com.example.fit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
                                                                // DbHelper - being used to handle all methods / events which have to do with the database.
public class DbHelper  extends SQLiteOpenHelper {


    // Variables used to store the information of the Tables needed accordingly
    // This makes it easier to create the other methods, also makes it safer as spaces are important
    // in SQL Queries
    public static final String EXERCISE_TABLE = "exercise_table";
    public static final String COLUMN_EXE_ID = "ID";
    public static final String COL_EXE_CALS = "CaloriesBurned";
    public static final String COL_EXE_TYPE = "Type";
    public static final String COL_EXE_DESC = "Description";
    public static final String COL_EXE_IMG = "Image";
    public static final String  COL_EXE_TITLE = "Title";
    public static final String COL_EXE_VIDID="vidid";

    public static final String INGREDIENT_TABLE = "INGREDIENT_table";
    public static final String COLUMN_ING_ID = "ID";
    public static final String COL_ING_TITLE = "Title";
    public static final String COL_ING_CALS = "Calories";
    public static final String COL_ING_TYPE = "Type";
    public static final String COL_ING_PROTEIN= "Protein";
    public static final String COL_ING_CARBS= "Carbohydrates";
    public static final String COL_ING_FAT= "Fats";
    public static final String COL_ING_IMG = "Image";




    public static final String FOOD_TABLE = "FOOD_table";
    public static final String COLUMN_FOOD_ID = "ID";
    public static final String COL_FOOD_CALS = "Total Calories";
    public static final String COL_FOOD_TYPE = "Type";
    public static final String COL_FOOD_DESC = "Description";
    public static final String COL_FOOD_IMG = "Image";
    public static final String COL_FOOD_Title = "Title";



    public static final String USER_TABLE = "USER_table";
    public static final String COLUMN_USER_ID = "ID";
    public static final String COL_USER_Username = "Username";
    public static final String COL_USER_Password = "Password";




    public DbHelper(@Nullable Context context) {
        super(context, "Fit-DB", null, 1);
    }

    // onCreate Method override - being used to create the required tables
    // Strings are created accordingly each storing the related SQL Query required to create/ define a new table

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Exercise = " CREATE TABLE " +EXERCISE_TABLE+" ( "+COLUMN_EXE_ID +" INTEGER PRIMARY KEY AUTOINCREMENT , "+ COL_EXE_CALS + " INT , "+COL_EXE_TYPE+" TEXT , "+COL_EXE_DESC+" TEXT, "+COL_EXE_TITLE+ " TEXT, "+COL_EXE_IMG+" BLOB, "+COL_EXE_VIDID+" TEXT ) ";

        String Ingredients = " CREATE TABLE " +INGREDIENT_TABLE+" ( "+COLUMN_ING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "+COL_ING_TITLE+" TEXT ,"+COL_ING_CALS +
                " INT , "+ COL_ING_PROTEIN+ " INT, "+COL_ING_CARBS +" INT, "+ COL_ING_FAT+ " INT, "+COL_ING_IMG+" BLOB , " +COL_ING_TYPE+ " TEXT )";

        String Food = " CREATE TABLE "+FOOD_TABLE+" ( "+COLUMN_FOOD_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "+COL_FOOD_Title+ " TEXT  , "+ COL_FOOD_DESC +" TEXT, "+COL_FOOD_IMG +" BLOB, "+COL_FOOD_TYPE+ " TEXT) ";

        String User = " CREATE TABLE "+ USER_TABLE +" ( "+COLUMN_USER_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT , "+COL_USER_Username+" TEXT  , "+COL_USER_Password + " TEXT) ";
        // SQL is then executed
        db.execSQL(Exercise);
        db.execSQL(Ingredients);
        db.execSQL(Food);
        db.execSQL(User);

    }

    // Method used in order to insert a new user into the database

    public boolean InsertUser( user User){

        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_USER_Username,User.getUsername());
        cv.put(COL_USER_Password,User.getPassword());

        if(db.insert(USER_TABLE,null,cv) == -1){
            return  false;
        }else{
            return true;
        }
    }


    // Method used to Insert a new Ingredient into the Database


    public boolean InsertDataIngredients(Ing_Model Ingredient){

        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_ING_CALS,Ingredient.getCalories());
        cv.put(COL_ING_CARBS,Ingredient.getCarb());
        cv.put(COL_ING_FAT,Ingredient.getFat());
        cv.put(COL_ING_PROTEIN,Ingredient.getProtein());
        cv.put(COL_ING_TITLE,Ingredient.getTitle());
        cv.put(COL_ING_IMG,Ingredient.getImage());

        if(db.insert(INGREDIENT_TABLE,null,cv) == -1){
            return  false;
        }else{
            return true;
        }
    }

    //Method used to insert a new Food/ Recipe into the Database
    public boolean InsertDataFood( Food_Model Food){

        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_FOOD_Title,Food.getTitle());
        cv.put(COL_FOOD_DESC,Food.getDesc());
        cv.put(COL_FOOD_IMG,Food.getImage());
        cv.put(COL_FOOD_TYPE,Food.getType());


        if(db.insert(FOOD_TABLE,null,cv) == -1){
            return  false;
        }else{
            return true;
        }
    }
    //Method used to insert a new Exercise into the Datanase
    public boolean InsertDataExe( Ex_Model Exe){

        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_EXE_TITLE,Exe.getTitle());
        cv.put(COL_EXE_CALS,Exe.getCalories());
        cv.put(COL_EXE_DESC,Exe.getDescription());
        cv.put(COL_EXE_IMG,Exe.getImage());
        cv.put(COL_EXE_TYPE,Exe.getType());
        cv.put(COL_EXE_VIDID,Exe.getId_exx());

        if(db.insert(EXERCISE_TABLE,null,cv) == -1){
            return  false;
        }else{
            return true;
        }
    }

    // Method used to retrieve/ get A list of all the users available in the DB

    public List<user> getAllUsers(){

        SQLiteDatabase  db = this.getReadableDatabase();
        List<user> user_list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+USER_TABLE,null);
        if (cursor.moveToFirst()){
            do{
                int ID = cursor.getInt(0);
                String Username = cursor.getString(1);
                String Password = cursor.getString(2);

                user newuser = new user(ID,Username,Password);
                user_list.add(newuser);
            }while (cursor.moveToNext());
        } else  {} cursor.close();db.close();

        return  user_list;
    }



    // Method  to retrieve a List of all the Ingredients in the DB


    public List<Ing_Model> getAllIngredients(){

        SQLiteDatabase  db = this.getReadableDatabase();
        List<Ing_Model> Ing_List = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+INGREDIENT_TABLE,null);
        if (cursor.moveToFirst()){
           do{
               int ID = cursor.getInt(0);
               String Title = cursor.getString(1);
          //     int Calories = cursor.getInt(2);
               int Protein = cursor.getInt(3);
               int Carbs = cursor.getInt(4);
               int Fats = cursor.getInt(5);
               byte[] Image = cursor.getBlob(6);
               // Creation of new Ing obj

               Ing_Model Ingredient = new Ing_Model(ID,Title,Carbs,Protein,Fats,Image);
               Ing_List.add(Ingredient);
           }while (cursor.moveToNext());
        } else  {} cursor.close();db.close();

    return  Ing_List;
    }

    // Method  to retrieve a List of all the Food/Recipes in the DB
    public List<Food_Model> getAllFoods(){

        SQLiteDatabase  db = this.getReadableDatabase();
        List<Food_Model> Food_List = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+FOOD_TABLE,null);
        if (cursor.moveToFirst()){
            do{
                int ID = cursor.getInt(0);
                String Title = cursor.getString(1);
                String Description = cursor.getString(2);
                byte[] Image = cursor.getBlob(3);
                String Type = cursor.getString(4);
                Food_Model food = new Food_Model(ID,Title,Description,Image,Type);
                Food_List.add(food);
            }while (cursor.moveToNext());
        } else  {} cursor.close();db.close();

        return  Food_List;
    }

    // Method  to retrieve a List of all the Exercises in the DB
    public List<Ex_Model> getAllExercises(){

        SQLiteDatabase  db = this.getReadableDatabase();
        List<Ex_Model> Ex_List = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+EXERCISE_TABLE,null);
        if (cursor.moveToFirst()){
            do{
                int ID = cursor.getInt(0);
                int Calories = cursor.getInt(1);
                String Type = cursor.getString(2);
                String Description = cursor.getString(3);
                String Title = cursor.getString(4);
                byte[] Image = cursor.getBlob(5);
                String vidId = cursor.getString(6);
                Ex_Model Ex = new Ex_Model(ID,Type,Title,Description,""+Calories,Image,vidId);
                Ex_List.add(Ex);
            }while (cursor.moveToNext());
        } else  {} cursor.close();db.close();

        return  Ex_List;
    }

    // OnUpgrade method used in order to drop the old tables and create new ones

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+EXERCISE_TABLE);
        db.execSQL(" DROP TABLE IF EXISTS "+FOOD_TABLE);
        db.execSQL(" DROP TABLE IF EXISTS "+INGREDIENT_TABLE);
        db.execSQL(" DROP TABLE IF EXISTS "+USER_TABLE);
        onCreate(db);
    }



}
