/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import javafx.scene.image.ImageView;

/**
 *
 * @author ABDULRAHMAN ILLO
 */
public class FoodItem {
    
    String category;
    String name;
    String Description;
    ImageView image;
    float price;
    ItemOrderDetails itemOrderDetails = new ItemOrderDetails();

    public FoodItem() {
    }

    public FoodItem(String name, ImageView image, float price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public FoodItem(String category, String name, ImageView image, float price) {
        this.category = category;
        this.name = name;
        this.image = image;
        this.price = price;
        this.itemOrderDetails = new ItemOrderDetails();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public ItemOrderDetails getItemOrderDetails() {
        return itemOrderDetails;
    }
    
    public void setItemOrderDetails(ItemOrderDetails itemOrderDetails) {
        this.itemOrderDetails = itemOrderDetails;
    }
    
    public void setCartTotalLabel(){
        OrderDetails o = new OrderDetails();
        float ct = o.getCartTotalInFloat();
        String cts = o.getCartTotal().get();
        System.out.println(ct);
        System.out.println("s -"+cts);
    }
    
}
