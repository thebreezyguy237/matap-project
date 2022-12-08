/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FoodAndDrinksController implements Initializable {
    
    @FXML
    private AnchorPane typeFoodPane;
    
    @FXML
    private Button africanFood;

    @FXML
    private Button bakeries;

    @FXML
    private Button americanFood;

    @FXML
    private Button drinks;
                
    ArrayList<String> list = new ArrayList<>();
    
    static int type_id;
    
    
    
    void buttonName(){
        String sql = "Select distinct type_name from food_type";
        try {
            LoginController.pst = LoginController.conn.prepareStatement(sql);
            LoginController.rs = LoginController.pst.executeQuery();
            while (LoginController.rs.next()){
                list.add(LoginController.rs.getString(1));
            }
        }catch (SQLException ex) {
              
        }
        
    }

    @FXML
    void loadFoodType(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String btnName = btn.getText();
        
        String sql = "Select food_type_id from food_type where type_name=?";
        try {
            LoginController.pst = LoginController.conn.prepareStatement(sql);
            LoginController.pst.setString(1, btnName);
            LoginController.rs = LoginController.pst.executeQuery();
            if (LoginController.rs.next()){
                type_id = LoginController.rs.getInt("food_type_id");
                System.out.println(type_id); 
           }
        }catch (SQLException ex) {
            System.out.println("An error occured!");  
        }
        
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/type-of-food.fxml"));
            typeFoodPane.getChildren().add(pane);
            
        } catch (IOException ex) {
            Logger.getLogger(FoodAndDrinksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonName();
        africanFood.setText(list.get(0));
        bakeries.setText(list.get(1));
        americanFood.setText(list.get(2));
        drinks.setText(list.get(3));
    }    
    
}
