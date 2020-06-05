package com.example.fit;

import java.lang.reflect.Type;

public class Food_Model {
                                            // Food Model - used to define a Food / Recipe

    // Variables which the Recipe has
    private int id;
    private String Title;
   // private int Calories;
    private String Desc;
    private byte[] image;
    private String Type;
    // Empty Constructor
    public Food_Model(){}

    // Getters and setter methods used to set and get data from/to the object accordingly

    public String getType() {
        return Type;
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

    public void setType(String type) {
        Type = type;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    // Constructor used to set/ create a new Food/ Recipe
    public Food_Model(int id, String title, String desc, byte[] image, String type) {
        this.id = id;
        Title = title;
        Desc = desc;
        this.image = image;
        Type = type;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }





}
