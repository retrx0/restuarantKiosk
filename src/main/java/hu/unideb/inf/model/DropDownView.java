/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author ABDULRAHMAN ILLO
 */
public class DropDownView extends HBox{
    
    FoodItem foodItem;

    DropDownView() {
        }
    
    public DropDownView(FoodItem foodItem) {
        this.foodItem = foodItem;
        this.addButton = new Button("Add");
        this.cancelButton = new Button("Cancel");
        init();
    }
    
    protected static final Interpolator WEB_EASE = Interpolator.SPLINE(0.25, 0.1, 0.25, 1);
    
    public Button addButton = new Button();
    public Button cancelButton = new Button();
    
    private void init(){
        Button plusButton = new Button("+");
        Button minusButton = new Button("-");
        Label countLabel = new Label("x"+foodItem.getItemOrderDetails().getItemCount());
        
        plusButton.setPrefSize(40, 40);
        minusButton.setPrefSize(40, 40);
        cancelButton.setPrefSize(80, 40);
        addButton.setPrefSize(80, 40);
        
        HBox h1 =new HBox(20, minusButton,plusButton);
        VBox v1 = new VBox(20, countLabel,h1);
        
        v1.setAlignment(Pos.CENTER);
        
        this.getChildren().addAll(v1,addButton,cancelButton);
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
        
        plusButton.setOnAction((t) -> {
            int c = this.foodItem.getItemOrderDetails().getItemCount();
            c++;
            this.foodItem.getItemOrderDetails().setItemCount(c);
            countLabel.setText("x"+c);
        });
        
        minusButton.setOnAction((t) -> {
            int c = this.foodItem.getItemOrderDetails().getItemCount();
            if(c >0){
                c--;
                this.foodItem.getItemOrderDetails().setItemCount(c);
                countLabel.setText("x"+c);
            }
        });
        
        cancelButton.setOnAction((t) -> {
            System.out.println("cancel");
            Timeline tl = new Timeline(
            new KeyFrame(Duration.millis(0),    
                new KeyValue(this.opacityProperty(), 1, WEB_EASE),
                new KeyValue(this.translateYProperty(), 20, WEB_EASE)
            ),
            new KeyFrame(Duration.millis(300),    
                new KeyValue(this.opacityProperty(), 0.7, WEB_EASE),
                new KeyValue(this.translateYProperty(), -20, WEB_EASE)
                    )
                );
            tl.play();
        });
        
        addButton.setOnAction((t) -> {
            System.out.println("Add Button");
            Timeline tl = new Timeline(
            new KeyFrame(Duration.millis(0),    
                new KeyValue(this.opacityProperty(), 1, WEB_EASE),
                new KeyValue(this.translateYProperty(), 20, WEB_EASE)
            ),
            new KeyFrame(Duration.millis(300),    
                new KeyValue(this.opacityProperty(), 0.7, WEB_EASE),
                new KeyValue(this.translateYProperty(), -20, WEB_EASE)
                    )
                );
            tl.play();
        });
        
        Timeline tl = new Timeline(
            new KeyFrame(Duration.millis(0),    
                new KeyValue(this.opacityProperty(), 0.7, WEB_EASE),
                new KeyValue(this.translateYProperty(), -20, WEB_EASE)
            ),
            new KeyFrame(Duration.millis(300),    
                new KeyValue(this.opacityProperty(), 1, WEB_EASE),
                new KeyValue(this.translateYProperty(), 0, WEB_EASE)
                    )
                );
            tl.play();
            
        this.getStylesheets().clear();
        this.getStylesheets().add("/styles/style-normal.css");
        this.getStyleClass().clear();
        this.getStyleClass().add("drop-down-view");
    }
    
    public void doSlideFadeInUp(VBox vb, int in){
        Timeline tl = new Timeline(
            new KeyFrame(Duration.millis(0),    
                new KeyValue(vb.getChildren().get(in+1).opacityProperty(), 1, WEB_EASE),
                new KeyValue(vb.getChildren().get(in+1).translateYProperty(), 20, WEB_EASE)
            ),
            new KeyFrame(Duration.millis(200),    
                new KeyValue(vb.getChildren().get(in+1).opacityProperty(), 0.7, WEB_EASE),
                new KeyValue(vb.getChildren().get(in+1).translateYProperty(), -20, WEB_EASE)
                    )
                );
            tl.setOnFinished((t) -> {
                vb.getChildren().remove(in+1);
            });
            tl.play();
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }
    
}
