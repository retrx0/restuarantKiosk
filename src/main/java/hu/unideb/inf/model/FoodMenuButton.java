/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author ABDULRAHMAN ILLO
 */
public class FoodMenuButton extends HBox{
    
    FoodItem foodItem = new FoodItem();
    DropDownView dropDownView = new DropDownView(foodItem);
    Group group;
    VBox vbox = new VBox();
    HBox hbox;
    public Button rightAddBtn;
    ImageView icon1;
    Label label;
    Label label2;
    
    HBox iLeftHbox = new HBox();
    VBox iLeftVbox = new VBox();
    
    public FoodMenuButton(String imagepath, String name, float price) {
            this.foodItem.setImage(new ImageView(imagepath));
            this.foodItem.setPrice(price);
            this.foodItem.setName(name);
            initialize();
    }
    
    private void initialize() {
        hbox = new HBox();
        group = new Group();
        rightAddBtn = new Button("+ Add");
        //this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        label = new Label(foodItem.getName());
        label2 = new Label(""+foodItem.getPrice());
        
        this.foodItem.getImage().setPreserveRatio(true);
        this.foodItem.getImage().setFitHeight(120);
        this.foodItem.getImage().setFitWidth(120);
        //make the button grow if you want the right icon to always be on the right of the button :
        label.setMaxWidth(Long.MAX_VALUE);
        VBox.setVgrow(label, Priority.ALWAYS);
        //HBox.setHgrow(this, Priority.ALWAYS);
        vbox.getChildren().addAll(label, this.foodItem.getImage());
        vbox.setSpacing(20);
        iLeftVbox.getChildren().addAll(label2);
        iLeftHbox.getChildren().addAll(vbox,iLeftVbox);
        
        Region region = new Region();
        region.setPrefSize(100, 50);
        HBox.setHgrow(region, Priority.ALWAYS);
        hbox.getChildren().addAll(iLeftHbox,region,rightAddBtn);
        //vbox.getChildren().addAll(label, icon1, label2);
        //this.setGraphic(vbox);
        rightAddBtn.setPrefSize(100, 50);
        hbox.setAlignment(Pos.CENTER);
        hbox.setFillHeight(true);
        hbox.setSpacing(20);
        hbox.setMaxWidth(Double.MAX_VALUE);
        iLeftVbox.setAlignment(Pos.TOP_LEFT);
        iLeftHbox.setSpacing(20);
        iLeftVbox.setSpacing(20);
        
        this.getChildren().add(hbox);
        this.setPadding(new Insets(10));
        this.getStylesheets().clear();
        this.getStylesheets().add("/styles/style-normal.css");
        this.getStyleClass().clear();
        this.getStyleClass().add("food-button");
        //group.getChildren().add(this);
        
        /*rightAddBtn.setOnAction((ActionEvent t) -> {
            CartItemView.cartItemList.add(this.foodItem);
            System.out.println("You've added "+ foodItem.getName() + " to your cart");
        });*/
    }
    
    protected static final Interpolator WEB_EASE = Interpolator.SPLINE(0.25, 0.1, 0.25, 1);
    
    boolean pressed = false;
    public void dropDown(VBox fl){
        
    rightAddBtn.setOnAction((t) -> {
        List<Node> ls =new ArrayList<>();
        for(int i=0; i<fl.getChildren().size(); i++){
                ls.add(fl.getChildren().get(i));
            }
        int remIndex = 0;
        /*for(int j = 0; j<fl.getChildren().size(); j++){
        if(fl.getChildren().get(j).getClass().getName().equals(DropDownView.class.getName()))
        remIndex = j;
        fl.getChildren().remove(remIndex);
        }*/;
        int vboxSize = fl.getChildren().size();
        int index = fl.getChildren().indexOf(this);
            if(this.pressed == false){    
                   CartItemView.cartItemList.add(this.foodItem);
                   System.out.println("You've added "+ foodItem.getName() + " to your cart");
                    if(index == 0){
                             fl.getChildren().set(index+1, dropDownView = new DropDownView());
                             System.out.println(fl.getChildren());System.out.println(ls);
                             for(int i = 1; i<vboxSize; i++){
                                if( i+1 < vboxSize)
                                    fl.getChildren().set(i+1,ls.get(i));
                                else if( i+1 >= vboxSize)
                                    fl.getChildren().add(i+1,ls.get(i));
                                }
                             this.pressed = true;
                         }
                        else if(index == ls.size()-2){
                             fl.getChildren().set(index+1, new DropDownView(this.foodItem));
                             if(index+2 >= vboxSize){
                                 fl.getChildren().add(index+2,ls.get(index+1));
                             }
                             else{
                                 fl.getChildren().set(index+2,ls.get(index+1));
                             }
                             this.pressed = true;
                         }
                        else if(index == ls.size()-1){
                             fl.getChildren().add(index+1, new DropDownView(this.foodItem));
                             this.pressed = true;
                         }
                        else{
                             fl.getChildren().set(index+1, new DropDownView(this.foodItem));
                             System.out.println(fl.getChildren());System.out.println(ls);

                             fl.getChildren().set(index+1, new DropDownView(this.foodItem));
                             for(int i = index+1; i<vboxSize; i++){
                                if( i+1 < vboxSize)
                                    fl.getChildren().set( i+1,ls.get(i));
                                else if( i+1 >= vboxSize)
                                    fl.getChildren().add( i+1, ls.get(i));
                             }
                             this.pressed = true;
                        }
                    
                    this.pressed = true;
                }
            else if(this.pressed == true){
                dropDownView.doSlideFadeInUp(fl, index);
                    //fl.getChildren().remove(index+1);
                    this.pressed  = false;
                }
            
        });
    }
}
