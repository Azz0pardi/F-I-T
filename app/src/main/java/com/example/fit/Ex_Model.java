package com.example.fit;

import java.sql.Blob;

public class Ex_Model {
                                                // Exercise Model , used to define an Exercise

    // Variables
    private int id;
    private String Type;
    private String Title;
    private String  Description;
    private String Calories;
    private byte[] image;
    public String id_exx;

    // constructor
    public Ex_Model(){

    }
    // constructor used to create a new Exercise
    public Ex_Model(int id, String type, String title, String description, String calories, byte[] image, String id_ex) {
        this.id = id;
        Type = type;
        Title = title;
        Description = description;
        Calories = calories;
        this.image = image;
        this.id_exx = id_ex;
    }

    // Getter and setter methods for all the variables used. Used to get, or set data accordingly.

    public String getId_exx() {
        return id_exx;
    }

    public void setId_exx(String id_exx) {
        this.id_exx = id_exx;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCalories() {
        return Calories;
    }

    public void setCalories(String calories) {
        Calories = calories;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
