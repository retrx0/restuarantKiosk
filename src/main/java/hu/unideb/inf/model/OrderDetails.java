/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author ABDULRAHMAN ILLO
 */
public class OrderDetails {
    
    //float cartTotal;
    StringProperty cartTotal = new SimpleStringProperty();
    ObservableList<FoodItem> cartItemList = CartItemView.cartItemList;
    PendingOrder.OrderType orderType;

    public OrderDetails() {
        init();
    }
    
    private void init(){
        /*DecimalFormat df = new DecimalFormat("##.##");
        float ct = 0;
        for(int i=0; i<CartItemView.cartItemList.size(); i++){
            ct = ct + CartItemView.cartItemList.get(i).getItemOrderDetails().getItemCount()*CartItemView.cartItemList.get(i).getPrice();
        }
        cartTotal.setValue(ct);
        df.format(cartTotal);
        df.setRoundingMode(RoundingMode.UP);*/
    }

    public int getCartSize(){
        return cartItemList.size();
    }
    
    public void getCartSizeForButton(Button l){
        StringProperty csp = new SimpleStringProperty("Done("+cartItemList.size()+")");
        csp.set("Done("+cartItemList.size()+")");
        l.textProperty().bind(csp);
    }
    
    public StringProperty getCartTotal() {
        DecimalFormat df = new DecimalFormat("##.##");
        float ct = 0;
        for(int i=0; i<CartItemView.cartItemList.size(); i++){
            ct = ct + CartItemView.cartItemList.get(i).getItemOrderDetails().getItemCount()*CartItemView.cartItemList.get(i).getPrice();
        }
        df.format(ct);
        df.setRoundingMode(RoundingMode.UP);
        cartTotal.set("Total: "+ct);
        return cartTotal;
    }
    
    public void getCartTotal(Label l) {
        l.textProperty().bind(getCartTotal());
        System.out.println(getCartTotal().get());
        System.out.println(cartTotal);
    }
    
    public float getCartTotalInFloat() {
        //DecimalFormat df = new DecimalFormat("##.##");
        float ct = 0;
        for(int i=0; i<CartItemView.cartItemList.size(); i++){
            ct = ct + CartItemView.cartItemList.get(i).getItemOrderDetails().getItemCount()*CartItemView.cartItemList.get(i).getPrice();
        }
        //df.format(ct);
        //df.setRoundingMode(RoundingMode.UP);
        this.cartTotal.set("Total: "+ct);
        return Float.valueOf(cartTotal.get().substring(7));
    }

    public void setCartTotal(String s) {
        this.cartTotal.set("Total: "+s);
    }
    
    public void setCartTotalInFloat(float cartTotal) {
        this.cartTotal.set("Total: "+cartTotal);
    }

    public void setOrderType(PendingOrder.OrderType orderType) {
        this.orderType = orderType;
    }

    public PendingOrder.OrderType getOrderType() {
        return orderType;
    }
    
    
    
}
