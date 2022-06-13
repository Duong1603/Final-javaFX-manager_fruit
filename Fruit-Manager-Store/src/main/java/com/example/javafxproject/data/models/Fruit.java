package com.example.javafxproject.data.models;

public class Fruit {
    public int id;
    public String name;
    public int quality;
    public float price;
    public String typefruit;
    public String image;

    public Fruit(int id, String name, int quality,float price, String typefruit, String image) {
        this.id = id;
        this.name = name;
        this.quality = quality;
        this.price = price;
        this.typefruit = typefruit;
        this.image = image;

    }

    public Fruit(String name, int quality, float price, String typefruit, String image) {
        this.name = name;
        this.price = price;
        this.quality = quality;
        this.typefruit = typefruit;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int name) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public float getPrice() {return price;}

    public void setPrice(float price) {
        this.price = price;
    }
    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getTypefruit() {
        return typefruit;
    }

    public void setTypefruit(String typefruit) {
        this.typefruit = typefruit;
    }

}

