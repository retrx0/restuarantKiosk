/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXScrollPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author ABDULRAHMAN ILLO
 */
public class SideMenuButton extends Button{

    String imagePath;
    ImageView image;
    String buttonName;
    boolean fromNet = false;
    JFXMasonryPane menuMasonPane;
    
    VBox vBox;
    ImageView icon1;
    
    public SideMenuButton(String imagepath, String lowertext,VBox vb,boolean fromNet) {
        this.fromNet = fromNet;
        this.imagePath = imagepath;
        this.buttonName = lowertext;
        this.vBox = vb;
        init();
    }
    
    public SideMenuButton(String imagepath, String lowertext,JFXMasonryPane vb,boolean fromNet) {
        this.fromNet = fromNet;
        this.imagePath = imagepath;
        this.buttonName = lowertext;
        this.menuMasonPane = vb;
        init();
    }
    
    public SideMenuButton(String imagePath,String name, boolean fromNet) {    
        this.fromNet = fromNet;
        this.imagePath = imagePath;
        this.buttonName = name;
        init();
    }

    private void init(){
        //Group group = new Group();
        VBox vbx = new VBox();
        this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        if(!fromNet)
            image = new ImageView(getClass().getResource(this.imagePath).toExternalForm());
        else
            image = new ImageView("https://"+this.imagePath.substring(0, this.imagePath.indexOf("$")));
        
        image.setPreserveRatio(true);
        image.setSmooth(true);
        //image.setFitHeight(90);
        image.setFitWidth(120);
        
        Label label = new Label(this.buttonName);
        label.setAlignment(Pos.CENTER);
        label.setWrapText(true);
        //make the button grow if you want the right icon to always be on the right of the button :
        //label.setMaxWidth(Long.MAX_VALUE);
        //VBox.setVgrow(label, Priority.ALWAYS);
        vbx.getChildren().addAll(image,label);
        
        vbx.setSpacing(10);
        vbx.setAlignment(Pos.CENTER);
        vbx.setPadding(new Insets(10, 0, 10, 0));
        
        this.setGraphic(vbx);
        this.getStylesheets().clear();
        this.getStylesheets().add("/styles/style-normal.css");
        this.getStyleClass().clear();
        this.getStyleClass().add("side-menu-btn");
        this.setPrefSize(50, 50);
        //group.getChildren().add(this);
        
        DAO d  = new JPA();
        
        this.setOnAction((t) -> {
            
            List<FoodButton> lst = new ArrayList<>();
            lst = d.listFoodButtonsFor(this);
            menuMasonPane.getChildren().clear();
            for (int i = 0; i < lst.size(); i++) {
                menuMasonPane.getChildren().add(lst.get(i));
            }
            
//            switch(this.getButtonName()){
//                case "Burgers":
//                    FoodButton f =new FoodButton("Burger","/img/dbz-bur.png", "Double Zinger", (float)19.99);
//                    FoodButton f2 =new FoodButton("Burger","/img/dg-bur.png", "Double Grander", (float)19.99);
//                    FoodButton f1 =new FoodButton("Burger","/img/dbz-bur.png", "Double Zinger", (float)19.99);
//                    FoodButton f22 =new FoodButton("Burger","/img/dg-bur.png", "Double Grander", (float)19.99);
//                    List<FoodButton> fl = new ArrayList<>();
//                    fl = d.listFoodButtonsFor(this);
//                    fl.add(f);fl.add(f2);fl.add(f1);fl.add(f22);
//                    int ln2 = fl.size()%2;
//                    HBox hbx;
//                    vBox.getChildren().clear();
//                     for(int i=0; i<fl.size(); i=i+2){
//                        if(ln2==0){
//                            hbx = new HBox();
//                            hbx.setAlignment(Pos.CENTER);
//                            hbx.setSpacing(10);
//                            hbx.getChildren().addAll(fl.get(i),fl.get(i+1));
//                            vBox.getChildren().addAll(hbx);
//                        }else if(ln2 == 1) {
//                                if( i != fl.size()-1){
//                                    hbx = new HBox();
//                                    hbx.setAlignment(Pos.CENTER);
//                                    hbx.setSpacing(10);
//                                    hbx.getChildren().addAll(fl.get(i), fl.get(i+1));
//                                    vBox.getChildren().addAll(hbx);
//                                }
//                                else{
//                                    hbx = new HBox(fl.get(i));
//                                    hbx.setAlignment(Pos.CENTER);
//                                    hbx.setSpacing(10);
//                                    vBox.getChildren().addAll(hbx);
//                                }
//                        }
//                    }
//                    
//                    break;
//                case "Drinks":
//                    FoodButton f3 =new FoodButton("Drink","/img/pepsi.png", "Pepsi", (float)5.99);
//                    FoodButton f4 =new FoodButton("Drink","/img/Juice1.png", "Juice", (float)5.99);
//                    FoodButton f5 =new FoodButton("Drink","/img/water.png", "Water", (float)5.99);
//                    FoodButton ff4 =new FoodButton("Drink","/img/Juice1.png", "Juice", (float)5.99);
//                    FoodButton ff3 =new FoodButton("Drink","/img/pepsi.png", "Pepsi", (float)5.99);
//                    List<FoodButton> l = new ArrayList();
//                    l.add(f3);l.add(f4);l.add(f5);l.add(ff4);l.add(ff3);
//                    HBox hb;
//                    vBox.getChildren().clear();
//                    
//                    int ln = l.size()%2;
//                    for(int i=0; i<l.size(); i=i+2){
//                        if(ln==0){
//                            hb = new HBox();
//                            hb.setAlignment(Pos.CENTER);
//                            hb.setSpacing(10);
//                            hb.getChildren().addAll(l.get(i),l.get(i+1));
//                            vBox.getChildren().addAll(hb);
//                        }else if(ln == 1) {
//                            if( i != l.size()-1){
//                                hb = new HBox();
//                                hb.setAlignment(Pos.CENTER);
//                                hb.setSpacing(10);
//                                hb.getChildren().addAll(l.get(i), l.get(i+1));
//                                vBox.getChildren().addAll(hb);
//                            }
//                            else{
//                                hb = new HBox(l.get(i));
//                                hb.setAlignment(Pos.CENTER);
//                                hb.setSpacing(10);
//                                vBox.getChildren().addAll(hb);
//                            }
//                        }
//                    }
//                    break;
//                case "Meals":
//                    FoodMenuButton f6 =new FoodMenuButton("/img/meal1.png", "Double Grander Menu", (float)29.99);
//                    FoodMenuButton f7 =new FoodMenuButton("/img/meal2.png", "Menu", (float)29.99);
//                    FoodMenuButton ff6 =new FoodMenuButton("/img/meal1.png", "Double Grander Menu", (float)29.99);
//                    FoodMenuButton ff7 =new FoodMenuButton("/img/meal2.png", "Menu", (float)29.99);
//                    FoodMenuButton fff7 =new FoodMenuButton("/img/meal2.png", "Double Meal", (float)29.99);
//                    vBox.getChildren().clear();
//                    List<FoodMenuButton> ls = new ArrayList<>();
//                    ls.add(f6);ls.add(f7);ls.add(ff6);ls.add(ff7);ls.add(fff7);
//                    for(int i=0; i<ls.size(); i++){
//                        vBox.getChildren().add(ls.get(i));
//                        ls.get(i).dropDown(vBox);
//                    }
//                    break;
//            }
        });
    }
    
    public void addItemsToVB(VBox smvb, VBox mvb){
        //this.getOnAction();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public void setMenuMasonPane(JFXMasonryPane menuMasonPane) {
        this.menuMasonPane = menuMasonPane;
    }

    
    
    @Override
    public String toString() {
        return "SideMenuButton{" + "imagePath=" + imagePath + ", buttonName=" + buttonName + ", fromNet=" + fromNet + '}';
    }
    
}
    
   
