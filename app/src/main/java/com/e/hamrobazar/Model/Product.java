package com.e.hamrobazar.Model;

public class Product {
    private String name;
    private String price;
    private String image;
    private String condition;

    public Product(String name, String price, String image, String condition) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
