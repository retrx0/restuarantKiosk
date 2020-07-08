/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import hu.unideb.inf.view.FXMLSceneController;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author ABDULRAHMAN ILLO
 */
public class CartItemView extends HBox{
    
    FoodItem foodItem;
    ItemOrderDetails itemOrderDetails;
    float totalPrice;
    static public List<FoodItem> cartItemList = new ArrayList();
    OrderDetails orderDetails = new OrderDetails();
    
    Button deleteButton;
    
    public CartItemView() {
        init();
    }

    public CartItemView(FoodItem foodItem) {
        this.foodItem = foodItem;
        init();
    }
    
    private void init(){
        DecimalFormat df = new DecimalFormat("##.##");
        itemOrderDetails = new ItemOrderDetails();
        totalPrice = (float) (this.foodItem.getPrice() * this.foodItem.getItemOrderDetails().getItemCount());
        df.format(totalPrice);
        Label totalPriceLabel = new Label(""+totalPrice);
        Label nameLabel = new Label(this.foodItem.getName());
        Label itemCount = new Label("x"+this.foodItem.getItemOrderDetails().getItemCount());
        deleteButton = new Button("Remove");
        Button plusBtn = new Button("+");
        Button minusBtn =  new Button("-");
        
        deleteButton.setPrefSize(80, 40);
        plusBtn.setPrefSize(40, 40);
        minusBtn.setPrefSize(40, 40);
        totalPriceLabel.setStyle("-fx-font-size: 20px;");
        
        foodItem.getImage().setPreserveRatio(true);
        foodItem.getImage().setFitHeight(100);
        foodItem.getImage().setFitWidth(100);
        foodItem.getImage().setImage(foodItem.getImage().getImage());
        
        HBox lvH1 = new HBox(20,itemCount,minusBtn,plusBtn);
        VBox lvV1 = new VBox(20,nameLabel,foodItem.getImage());
        lvV1.setPadding(new Insets(10));
        VBox leftVbox = new VBox(20,lvH1,deleteButton,totalPriceLabel);
        lvV1.setAlignment(Pos.CENTER);
        lvH1.setAlignment(Pos.CENTER);
        leftVbox.setAlignment(Pos.CENTER);
        leftVbox.setPadding(new Insets(10));
        
        this.getChildren().addAll(lvV1,leftVbox);
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER_LEFT);
        this.getStylesheets().clear();
        this.getStylesheets().add("/styles/style-normal.css");
        this.getStyleClass().clear();
        this.getStyleClass().add("cart-item-view");
        
        df.setRoundingMode(RoundingMode.UP);
        
        totalPriceLabel.textProperty().bind(itemOrderDetails.getItemTotalProperty(this.foodItem));
        plusBtn.setOnAction((t) -> {
            int c = this.foodItem.getItemOrderDetails().getItemCount();
            c++;
            this.foodItem.getItemOrderDetails().setItemCount(c);
            itemCount.setText("x"+c);
            itemOrderDetails.getItemTotalProperty(this.foodItem);
        });
        
        minusBtn.setOnAction((t) -> {
            int c = this.foodItem.getItemOrderDetails().getItemCount();
            if(c >0){
                c--;
                this.foodItem.getItemOrderDetails().setItemCount(c);
                itemCount.setText("x"+c);
                itemOrderDetails.getItemTotalProperty(this.foodItem);
            }
            
        });
        
    }

    public void deleteCartItem(FXMLSceneController f){
        deleteButton.setOnAction((t) -> {
            f.menuVbox.getChildren().remove(this);
            f.cartVbox.getChildren().remove(this);
            CartItemView.cartItemList.remove(this.foodItem);
            this.foodItem.setCartTotalLabel();
            OrderDetails o = new OrderDetails();
            System.out.println(o.getCartTotal().get());
        });
    }
    
    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public ItemOrderDetails getItemOrderDetails() {
        return itemOrderDetails;
    }

    public void setItemOrderDetails(ItemOrderDetails itemOrderDetails) {
        this.itemOrderDetails = itemOrderDetails;
    }
        
}
