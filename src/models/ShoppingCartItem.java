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
public class ShoppingCartItem {
    private String itemName;
    private int quantity;
    private float price;
    private float total;
    

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(int quantity, float price, float total) {
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }
    
    public ShoppingCartItem(String itemName, int quantity, float price, float total) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public float getTotal() {
        return total;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    
}
