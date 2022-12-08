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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import views.main;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class UserchoiceController implements Initializable {
    
    @FXML
    private BorderPane rootPane;
    
    @FXML
    void clientSignUp(ActionEvent event) throws Exception {
        try {
            BorderPane pane =  FXMLLoader.load(getClass().getResource("/views/signUp.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception ex) {
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void restaurantSignUp(ActionEvent event) {
         try {
            BorderPane pane =  FXMLLoader.load(getClass().getResource("/views/restaurants/signUpRestaurant.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception ex) {
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
