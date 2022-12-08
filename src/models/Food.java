/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author HP
 */
public class Food {
    private float price;
    private String name;
    private String description;  
    private String foodImage;

    public Food() {
    }

    public Food(float price, String name, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }
    
    
}
