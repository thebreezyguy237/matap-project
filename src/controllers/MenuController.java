/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import models.Customer;
import views.main;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MenuController implements Initializable {
    
    
    @FXML
    private Button dashboardButton;

    @FXML
    private Button foodButton;

    @FXML
    private Button restoButton;

    @FXML
    private Button cartButton;

    @FXML
    private Button notificationButton;

    @FXML
    private Button favoritesButton;
       
    @FXML
    private Button profileButton;
    
    @FXML
    private Circle imageProfile;

    @FXML
    private Label name;

    @FXML
    private AnchorPane loadingMenuViews;
    
    void initializeName(){
        Customer customer = new Customer(LoginController.firstName, LoginController.lastName);
        String myName = (customer.getFirstName() + " " + customer.getLastName()).toUpperCase();
        name.setText(myName);
    }

    @FXML
    void loadingDashboard(ActionEvent event) throws Exception {
        try{
            AnchorPane dashboardPane = FXMLLoader.load(getClass().getResource("/views/dashboard.fxml"));
            loadingMenuViews.getChildren().setAll(dashboardPane);
        } catch(Exception ex){
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void setButtonActive() {
        
    }
    
    
    @FXML
    void loadingFavorites(ActionEvent event) {
        
    }

    @FXML
    void loadingFoodAndDrinks(ActionEvent event) {
        try{
            AnchorPane foodPane = FXMLLoader.load(getClass().getResource("/views/foodAndDrinks.fxml"));
            loadingMenuViews.getChildren().setAll(foodPane);
        } catch(Exception ex){
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void loadingNotifications(ActionEvent event) {
        try{
            BorderPane notificationsPane = FXMLLoader.load(getClass().getResource("/views/notifications.fxml"));
            loadingMenuViews.getChildren().setAll(notificationsPane);
        } catch(Exception ex){
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void loadingProfile(ActionEvent event) {
        try{
            BorderPane profilePane = FXMLLoader.load(getClass().getResource("/views/profile.fxml"));
            loadingMenuViews.getChildren().setAll(profilePane);
        } catch(Exception ex){
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void loadingRestaurants(ActionEvent event) {
        try{
            AnchorPane restaurantsPane = FXMLLoader.load(getClass().getResource("/views/restaurants.fxml"));
            loadingMenuViews.getChildren().setAll(restaurantsPane);
        } catch(Exception ex){
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void loadingSettings(ActionEvent event) {
        try{
            BorderPane settingsPane = FXMLLoader.load(getClass().getResource("/views/settings.fxml"));
            loadingMenuViews.getChildren().setAll(settingsPane);
        } catch(Exception ex){
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void loadingShoppingCart(ActionEvent event) {
        try{
            AnchorPane cartPane = FXMLLoader.load(getClass().getResource("/views/shoppingCart.fxml"));
            loadingMenuViews.getChildren().setAll(cartPane);
        } catch(Exception ex){
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        initializeName();
        
        try{
            AnchorPane dashboardPane = FXMLLoader.load(getClass().getResource("/views/dashboard.fxml"));
            loadingMenuViews.getChildren().setAll(dashboardPane);
        } catch(Exception ex){
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
