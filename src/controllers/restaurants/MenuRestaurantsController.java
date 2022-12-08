/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.restaurants;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import views.main;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MenuRestaurantsController implements Initializable {
    
    @FXML
    private AnchorPane menuPane;

    @FXML
    private Button listFoodButton;

    @FXML
    private Button notificationButton;

    @FXML
    private Circle imageProfile;

    @FXML
    private Label name;

    @FXML
    private Button profileButton;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button foodButton;

    @FXML
    private ImageView settingsButton;

    @FXML
    private AnchorPane loadingMenuViews;

    @FXML
    void loadingDashboard(ActionEvent event) {

    }

    @FXML
    void loadingFoodAndDrinks(ActionEvent event) {

    }

    @FXML
    void loadingListFood(ActionEvent event) {

    }

    @FXML
    void loadingNotifications(ActionEvent event) {

    }

    @FXML
    void loadingProfile(ActionEvent event) {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
