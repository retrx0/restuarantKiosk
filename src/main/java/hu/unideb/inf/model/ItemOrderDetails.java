/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.text.DecimalFormat;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ABDULRAHMAN ILLO
 */
public class ItemOrderDetails {
    
    int itemCount = 1;
    StringProperty itemTotal = new SimpleStringProperty();

    
    private void initFormat(){
        DecimalFormat df = new DecimalFormat("##.##");
        float f = Float.valueOf(itemTotal.get());
        df.format(f);
    }
    
    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    
    public void setItemTotalProperty(String itemTotal) {
        this.itemTotal.set(itemTotal);
    }

    public StringProperty getItemTotalProperty() {
        //itemTotal.set(""+(float)this.getItemCount()*this.getPrice());
        return itemTotal;
    }
    
    public StringProperty getItemTotalProperty(FoodItem f) {
        itemTotal.set(""+f.getItemOrderDetails().getItemCount()*f.getPrice());
        return itemTotal;
    }  
    
    public void setItemTotal(float itemTotal) {
        this.itemTotal.set(""+itemTotal);
    }

    public float getItemTotal() {
        //float f =(float)this.getItemCount()*this.getPrice();
        float f = Float.valueOf(this.itemTotal.get());
        return f;
    }    
    
}
