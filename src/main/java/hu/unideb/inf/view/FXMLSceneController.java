/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.view;

import hu.unideb.inf.effects.FlashTransition;
import hu.unideb.inf.model.CartItemView;
import hu.unideb.inf.model.FIrstPageButton;
import hu.unideb.inf.model.OrderDetails;
import hu.unideb.inf.model.SideMenuButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox; 
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;

public class FXMLSceneController implements Initializable {
    
    //<editor-fold defaultstate="collapsed" desc="FXML Var">
    @FXML private AnchorPane firstPage;
    @FXML private AnchorPane menuPane;
    @FXML private AnchorPane topAncPane;
    @FXML private AnchorPane orderPane;
    @FXML Pane glassPane;
    @FXML AnchorPane homePane;
    @FXML private StackPane upperStack;
    @FXML private StackPane menuStack;
    @FXML public VBox menuVbox;
    @FXML public VBox cartVbox;
    @FXML private HBox homePageHbox;
    @FXML VBox sideMenuVBox;
    @FXML public Button cartButton;
    @FXML Button payButton;
    @FXML public Label menuPaneTotalLabel;
    @FXML public Label totalLabel;
    @FXML Button cancelButton;
//</editor-fold>
    
    public FXMLSceneController thisController = this;
    
    ImageView i = new ImageView("https://www.mcdonalds.com/is/image/content/dam/usa/nfl/nutrition/items/iconic/desktop/t-mcdonalds-McFlurry-with-OREO-Cookies-12floz-cup.jpg?$Category_Desktop$");
    
    FIrstPageButton btn1 = new FIrstPageButton("/img/tray.jpg", "Eat Here", "");
    FIrstPageButton btn2 = new FIrstPageButton("/img/icon2.jpg", "Take Away", "");
    List<SideMenuButton> ls =new ArrayList();
    
    
    //<editor-fold defaultstate="collapsed" desc="Effects">
    protected static final Interpolator WEB_EASE = Interpolator.SPLINE(0.25, 0.1, 0.25, 1);
    void flash(Label l){
            FlashTransition ft = new FlashTransition(l);
            ft.play();
        }
    void doSlideInFromTop(StackPane stackPane, Node paneToAdd) {
    Node paneToRemove = stackPane.getChildren().get(0);
    paneToAdd.translateYProperty().set(-1 * stackPane.getHeight());
    stackPane.getChildren().add(paneToAdd);
    KeyValue kv = new KeyValue(paneToAdd.translateYProperty(), 0, WEB_EASE);
    KeyFrame kf = new KeyFrame(Duration.millis(250), kv);
    Timeline tl = new Timeline(kf);
    tl.setOnFinished(evt -> {
        stackPane.getChildren().remove(paneToRemove);
    });
    tl.play();
}
    void doFadeInUpTransition(StackPane stackPane, Node paneToAdd){
        Node paneToRemove = stackPane.getChildren().get(0);
        paneToAdd.translateYProperty().set(-1 * stackPane.getHeight());
        stackPane.getChildren().add(paneToAdd);
        Timeline tl = new Timeline(
                new KeyFrame(Duration.millis(0),    
                new KeyValue(paneToAdd.opacityProperty(), 1, WEB_EASE),
                new KeyValue(paneToAdd.translateYProperty(), 20, WEB_EASE)
                ),
                new KeyFrame(Duration.millis(1000),    
                new KeyValue(paneToAdd.opacityProperty(), 1, WEB_EASE),
                new KeyValue(paneToAdd.translateYProperty(), 0, WEB_EASE)
                ));
        tl.setOnFinished(evt -> stackPane.getChildren().remove(paneToRemove));
        tl.play();
    }
    void doFadeIn(StackPane stackPane, Node paneToAdd) {
    Node paneToRemove = stackPane.getChildren().get(0);
    stackPane.getChildren().add(paneToAdd);
     FadeTransition ft = new FadeTransition(Duration.millis(300));
    ft.setOnFinished(evt -> {
        stackPane.getChildren().remove(paneToRemove);
    });
    ft.setNode(paneToAdd);
    ft.setFromValue(0);
    ft.setToValue(1);
    ft.play();
}
    void doFadeInDownTransition(StackPane stackPane, Node paneToAdd){
        Node paneToRemove = stackPane.getChildren().get(0);
        paneToAdd.translateYProperty().set(-1 * stackPane.getHeight());
        stackPane.getChildren().add(paneToAdd);
        Timeline tl = new Timeline(
               new KeyFrame(Duration.millis(0),    
                    new KeyValue(paneToAdd.opacityProperty(), 0, WEB_EASE),
                    new KeyValue(paneToAdd.translateYProperty(), -20, WEB_EASE)
                ),
                new KeyFrame(Duration.millis(1000),    
                    new KeyValue(paneToAdd.opacityProperty(), 1, WEB_EASE),
                    new KeyValue(paneToAdd.translateYProperty(), 0, WEB_EASE)
                ));
        tl.setOnFinished(evt -> stackPane.getChildren().remove(paneToRemove));
        tl.play();
    }
    void doSlideInFromLeft(StackPane stackPane, Node paneToAdd) {
    Node paneToRemove = stackPane.getChildren().get(0);
    paneToAdd.translateXProperty().set(-1 * stackPane.getWidth());
    stackPane.getChildren().add(paneToAdd);
    KeyValue kv = new KeyValue(paneToAdd.translateXProperty(), 0, Interpolator.SPLINE(0.25, 0.1, 0.25, 1));
    KeyFrame kf = new KeyFrame(Duration.millis(250), kv);
    Timeline timeline = new Timeline(kf);
    timeline.setOnFinished(evt -> {
        stackPane.getChildren().remove(paneToRemove);
    });
    timeline.play();
}
//</editor-fold>
     
    void showStack(StackPane sp, Node nta){
        sp.getChildren().clear();
        sp.getChildren().add(nta);
    }
    
    void addSideMenuItems(VBox vb){
        //vb.getChildren().clear();
        SideMenuButton sd1 = new SideMenuButton("/img/icon1c.jpg", "Burgers",menuVbox);
        SideMenuButton sd2 = new SideMenuButton("/img/drinkc.png", "Drinks",menuVbox);
        SideMenuButton sd3 = new SideMenuButton("/img/meals.png", "Meals",menuVbox);
        vb.getChildren().addAll(sd1,sd2,sd3);
        
            for(int i =0; i<vb.getChildren().size();i++){
                ls.add((SideMenuButton)vb.getChildren().get(i));
            }
            ls.get(0).getOnAction();
            ls.get(0).fire();
    }
    void cancelOrder(){
        CartItemView.cartItemList.clear();
    }
   
    @FXML void backButtonPressed(){
        doSlideInFromLeft(upperStack, menuPane);
        ls.get(0).fire();
        //showStack(menuStack, homePane);
    }
    
    @FXML void doneButtonOnAction(){
        doSlideInFromLeft(upperStack, orderPane);
        CartItemView civ1 = null;
        OrderDetails o = new OrderDetails();
        cartVbox.getChildren().clear();
        for(int i=0; i<CartItemView.cartItemList.size(); i++){
            civ1 = new CartItemView(CartItemView.cartItemList.get(i));
            cartVbox.getChildren().add(civ1);
            civ1.deleteCartItem(thisController);
        }
        totalLabel.textProperty().bind(o.getCartTotal());
    }
    
    private Stage createPayDialog(Window owner){
        Stage stage = new Stage();
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        
        ImageView im = new ImageView("/img/creditcard.png");
        im.setFitHeight(100);
        im.setFitWidth(100);
        Label l1 = new Label("Credit Card");
        VBox v = new VBox(10, l1,im);
        v.setAlignment(Pos.CENTER);
        
        ImageView im2 = new ImageView("/img/cash.jpg");
        im2.setFitHeight(100);
        im2.setFitWidth(100);
        Label l2 = new Label("Cash");
        VBox v2 = new VBox(10, l2,im2);
        v2.setAlignment(Pos.CENTER);
        
        Button creditcard = new Button("",v);
        creditcard.setStyle("-fx-background-color: white");
        Button cash = new Button("",v2);
        cash.setStyle("-fx-background-color: white");
        Button cancel = new Button("Cancel");
        
        Label headerText = new Label("Select Payment Method");
        
        cancel.setPrefSize(100, 50);
        creditcard.setPrefSize(130, 100);
        cash.setPrefSize(130, 100);
        headerText.setStyle("-fx-font-size: 25px");
        
        creditcard.setOnAction(evt -> {
            stage.hide();
            //doFadeIn(upperStack, firstPage);
        });
        cash.setOnAction(evt -> {
            stage.hide();
        });
        
        cancel.setOnAction((t) -> {
           stage.hide();
        });
        
        HBox topHbox = new HBox(20,creditcard,cash);
        topHbox.setAlignment(Pos.CENTER);
        
        VBox dialogRootVbox = new VBox(20,headerText,topHbox,cancel);
        
        dialogRootVbox.setAlignment(Pos.CENTER);
        dialogRootVbox.getStylesheets().clear();
        dialogRootVbox.getStylesheets().add("/styles/style-normal.css");
        dialogRootVbox.getStyleClass().clear();
        dialogRootVbox.getStyleClass().add("dialog-root");
        final Scene scene = new Scene(dialogRootVbox, 550, 400);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        return stage;
    }
    @FXML void payButtonOnAction(){
        Parent rootPane = topAncPane.getScene().getRoot();
        Effect previousEffect = rootPane.getEffect();
        final BoxBlur blur = new BoxBlur(0, 0, 5);
        blur.setInput(previousEffect);
        rootPane.setEffect(blur);

        Stage stage = createPayDialog(topAncPane.getScene().getWindow());
        rootPane.setOnMouseClicked((MouseEvent t1) -> {stage.hide();});
        stage.setOnHidden(e -> rootPane.setEffect(previousEffect));

        stage.getScene().getRoot().setOpacity(1);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300), 
                new KeyValue(blur.widthProperty(), 10), 
                new KeyValue(blur.heightProperty(), 10),
                new KeyValue(stage.getScene().getRoot().opacityProperty(), 1)
        ));
        timeline.play();
        stage.show();
    }
    
    private Stage createCancelDialog(Window owner) {
        Stage stage = new Stage();
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        
        Button cancel = new Button("Cancel");
        Button confirm = new Button("Confirm");
        Label headerText = new Label("Confirm Order Cancelation");
        
        cancel.setPrefSize(100, 50);
        confirm.setPrefSize(100, 50);
        headerText.setStyle("-fx-font-size: 25px");
        
        confirm.setOnAction(evt -> {
            cancelOrder();
            stage.hide();
            doFadeIn(upperStack, firstPage);
        });
        cancel.setOnAction(evt -> {
            stage.hide();
        });
        
        HBox topHbox = new HBox(20,cancel,confirm);
        topHbox.setAlignment(Pos.CENTER);
        
        VBox dialogRootVbox = new VBox(20,headerText,topHbox);
        
        dialogRootVbox.setAlignment(Pos.CENTER);
        dialogRootVbox.getStylesheets().clear();
        dialogRootVbox.getStylesheets().add("/styles/style-normal.css");
        dialogRootVbox.getStyleClass().clear();
        dialogRootVbox.getStyleClass().add("dialog-root");
        final Scene scene = new Scene(dialogRootVbox, 550, 400);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        return stage;
    }
    @FXML void cancelOrderButton(){
            Parent rootPane = topAncPane.getScene().getRoot();
            Effect previousEffect = rootPane.getEffect();
            final BoxBlur blur = new BoxBlur(0, 0, 5);
            blur.setInput(previousEffect);
            rootPane.setEffect(blur);
            
            Stage stage = createCancelDialog(topAncPane.getScene().getWindow());
            rootPane.setOnMouseClicked((MouseEvent t1) -> {stage.hide();});
            stage.setOnHidden(e -> rootPane.setEffect(previousEffect));

            stage.getScene().getRoot().setOpacity(1);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300), 
                    new KeyValue(blur.widthProperty(), 10), 
                    new KeyValue(blur.heightProperty(), 10),
                    new KeyValue(stage.getScene().getRoot().opacityProperty(), 1)
            ));
            timeline.play();
            stage.show();
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            thisController = this;
            showStack(upperStack, firstPage);
            showStack(menuStack, homePane);
            
            btn1.setOnAction((ActionEvent t) -> {
                doFadeIn(upperStack, menuPane);
                addSideMenuItems(sideMenuVBox);
            });
            
            homePageHbox.getChildren().add(btn1);
            homePageHbox.getChildren().add(btn2);
    }    
    
}
