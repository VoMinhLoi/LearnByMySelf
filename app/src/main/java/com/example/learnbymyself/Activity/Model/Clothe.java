package com.example.learnbymyself.Activity.Model;

import java.io.Serializable;

public class Clothe implements Serializable {
    private String id, clothesName, price, description, quantity;
    private String image;
    public Clothe(){

    }
    public Clothe(String id, String clothesName, String price, String description, String image) {
        this.id = id;
        this.clothesName = clothesName;
        this.price = price;
        this.description = description;
        this.image = image;
    }
    public Clothe(String id, String clothesName, String price, String description, String quantity, String image) {
        this.id = id;
        this.clothesName = clothesName;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClothesName() {
        return clothesName;
    }

    public void setClothesName(String clothesName) {
        this.clothesName = clothesName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String img) {
        this.image = img;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
