package com.example.fit;

import java.sql.Blob;
import java.util.Arrays;

public class Ing_Model {
                        // This is the Ingredient Model , this is what defines an Ingredient Object
                        // Variables being used
    private int id;
    private String Title;
    private int Calories;
    private int carb;
    private int  protein;
    private int fat;
    private byte[] image;
   /* public void setCalories(int calories) {
        Calories = calories;
    }*/

    @Override
    public String toString() {
        return "Ing_Model{" +
                "id=" + id +
                ", Title='" + Title + '\'' +
                ", Calories=" + Calories +
                ", carb=" + carb +
                ", protein=" + protein +
                ", fat=" + fat +
                ", image=" + Arrays.toString(image) +
                '}';
    }
    // Main Constructor being used to create a new Ingredient
    public Ing_Model(int id, String title, int carb, int protein, int fat, byte[] image) {
        this.id = id;
        Title = title;
        this.Calories = ((carb*4)+(protein*4)+(fat*9)); // This is a general calculation of the calories in the Ingredient
        this.carb = carb;
        this.protein = protein;
        this.fat = fat;
        this.image = image;
    }
                                                    // Getter + setter methods for every variable accordingly
    public int getCalories() {
        return Calories;
    }

    public void setCalories(int calories) {
        Calories = calories;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getCarb() {
        return carb;
    }

    public void setCarb(int carb) {
        this.carb = carb;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }



    public Ing_Model(){
    // default empty constructor
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }


}
