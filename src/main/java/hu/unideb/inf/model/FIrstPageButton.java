/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author ABDULRAHMAN ILLO
 */
public class FIrstPageButton extends Button{
    
    String imagePath;
    String upperText;
    String lowerText;
    
    Group group;
    VBox vBox;
    ImageView icon1;
    Label label;
    Label label2;
    
    public FIrstPageButton(String imagepath, String uppertext, String lowertext) {
            this.imagePath = imagepath;
            this.lowerText = lowertext;
            this.upperText = uppertext;
            initialize(imagepath, uppertext, lowertext);
    }

    
    private void initialize(String imagepath, String uppertext, String lowertext) {
        group = new Group();
        vBox = new VBox();
        this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        icon1 = new ImageView(getClass().getResource(imagepath).toExternalForm());
        label = new Label(uppertext);
        label2 = new Label(lowertext);
        
        icon1.setFitHeight(250);
        icon1.setFitWidth(250);
        //make the button grow if you want the right icon to always be on the right of the button :
        label.setMaxWidth(Long.MAX_VALUE);
        VBox.setVgrow(label, Priority.ALWAYS);
        vBox.getChildren().addAll(label, icon1);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.CENTER);
        this.setGraphic(vBox);
        this.getStylesheets().clear();
        this.getStylesheets().add("/styles/style-normal.css");
        this.getStyleClass().clear();
        this.getStyleClass().add("first-page-button");
        this.setPrefSize(130, 100);
        group.getChildren().add(this);
    }
    
}
