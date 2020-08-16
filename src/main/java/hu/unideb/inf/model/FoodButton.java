/*
 * To change this license headerText, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author ABDULRAHMAN ILLO
 */

public class FoodButton extends Button{
    
    String imagePath;
    FoodItem fooditem = new FoodItem();
    ItemOrderDetails itemorder = new ItemOrderDetails();
    Group group;
    ImageView image;
    
    JFXButton addButton;

    public FoodButton() {
    }
    
    public FoodButton(String cat,String imagepath, String name, float price) {
            this.imagePath = imagepath;
            this.fooditem.setPrice(price);
            this.fooditem.setName(name);
            this.fooditem.setCategory(cat);
            initialize();
            this.fooditem.setImage(image);
    }
    
    private void initialize() {
        //group = new Group();
        
       
        this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        image = new ImageView(imagePath);
        image.setPreserveRatio(true);
        //image.setFitHeight(300);
        image.setFitWidth(250);
        image.setSmooth(true);
        
        Label label = new Label(this.fooditem.getName());
        
        Label label2 = new Label(""+this.fooditem.getPrice());
        
         VBox vBox = new VBox();
        //label.setMaxWidth(Long.MAX_VALUE);
        //VBox.setVgrow(label, Priority.ALWAYS);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label, image, label2);
        
        this.setGraphic(vBox);
        this.getStylesheets().clear();
        this.getStylesheets().add("/styles/style-normal.css");
        this.getStyleClass().clear();
        this.getStyleClass().add("food-button");
        this.setPrefSize(130, 100);
        //group.getChildren().add(this);
        
        this.setOnAction((ActionEvent t) -> {
            
            createDialog();
            
//            Parent rootPane = this.getScene().getRoot();
//            Effect previousEffect = rootPane.getEffect();
//            final BoxBlur blur = new BoxBlur(0, 0, 5);
//            blur.setInput(previousEffect);
//            rootPane.setEffect(blur);
//            //rootPane.setEffect(new Blend(BlendMode.SRC_OVER, blur, ds));
//            Stage stage = createDialog(this.getScene().getWindow());
//            stage.getScene().setOnMouseClicked((MouseEvent t1) -> {stage.hide();});
//            stage.setOnHidden(e -> rootPane.setEffect(previousEffect));
//            
//            stage.getScene().getRoot().setOpacity(1);
//            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300), 
//                    new KeyValue(blur.widthProperty(), 10, DropDownView.WEB_EASE), 
//                    new KeyValue(blur.heightProperty(), 10,DropDownView.WEB_EASE),
//                    new KeyValue(stage.getScene().getRoot().opacityProperty(), 1)
//            ));
//            timeline.play();
//            stage.show();
//            
           });
    }
    
    private void createDialog() {
//        Stage stage = new Stage();
//        stage.initOwner(owner);
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initStyle(StageStyle.TRANSPARENT);
        
        JFXDialog d = new JFXDialog();

        addButton = new JFXButton("Add +");
        JFXButton plusBtn = new JFXButton("+");
        JFXButton minusBtn = new JFXButton("-");
        JFXButton cancelButton = new JFXButton("Cancel");
        Label itemCountlabel = new Label();
        Label headerText = new Label();
        
        addButton.setPrefSize(100, 50);
        cancelButton.setPrefSize(100, 50);
        plusBtn.setPrefSize(60, 60);
        minusBtn.setPrefSize(60, 60);
        
        itemCountlabel.setStyle("-fx-font-size: 25px");
        headerText.setStyle("-fx-font-size: 25px");
        headerText.setWrapText(true);
        headerText.setTextAlignment(TextAlignment.CENTER);
        
        plusBtn.setOnAction(evt -> {
            int c =this.fooditem.getItemOrderDetails().getItemCount();
            c++;
            this.fooditem.getItemOrderDetails().setItemCount(c);
            itemCountlabel.setText("Count x"+String.valueOf(this.fooditem.getItemOrderDetails().getItemCount()));
            setCartTotal();
        });
        minusBtn.setOnAction(evt -> {
            int c =this.fooditem.getItemOrderDetails().getItemCount();
            if(this.itemorder.getItemCount() > 0){
                c--;
                this.fooditem.getItemOrderDetails().setItemCount(c);
                itemCountlabel.setText("Count x"+String.valueOf(this.fooditem.getItemOrderDetails().getItemCount()));
                setCartTotal();
            }
        });
        cancelButton.setOnAction((ActionEvent t) -> {
            d.close();
        });
        
        itemCountlabel.setText("Count x"+itemorder.getItemCount());
        headerText.setText(fooditem.getCategory() +": " + fooditem.getName());
        
        addButton.setOnAction(evt -> {
            CartItemView.cartItemList.add(fooditem);
            System.out.println(itemCountlabel.getText()+" Food "+ fooditem.getName() +" was added to cart");
           d.close();
        });
        
        HBox topHbox = new HBox(10,itemCountlabel,minusBtn,plusBtn);
        topHbox.setAlignment(Pos.CENTER);
        
        HBox buttonHbox = new HBox(10, cancelButton,addButton);
        buttonHbox.setAlignment(Pos.CENTER);
        
        VBox dialogRootVbox = new VBox(20,headerText,topHbox, buttonHbox);

        dialogRootVbox.setAlignment(Pos.CENTER);
        dialogRootVbox.getStylesheets().clear();
        dialogRootVbox.getStylesheets().add("/styles/style-normal.css");
        dialogRootVbox.getStyleClass().clear();
        dialogRootVbox.getStyleClass().add("dialog-root");
        dialogRootVbox.setPrefSize(500, 400);
        
        d.setContent(dialogRootVbox);
        d.setDialogContainer((StackPane)this.getScene().getRoot().getChildrenUnmodifiable().get(0));
        d.setTransitionType(JFXDialog.DialogTransition.NONE);
        d.show();
//        final Scene scene = new Scene(dialogRootVbox, 500, 400);
//        
//        scene.setFill(Color.TRANSPARENT);
//        stage.setScene(scene);
//        return stage;
    }
    
    public void setCartTotal(){
        OrderDetails o = new OrderDetails();
        String ct = o.getCartTotal().get();
        o.setCartTotal(ct);
    }
    
}
