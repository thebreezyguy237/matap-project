/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import models.Food;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RestaurantFoodController implements Initializable {
    
    @FXML
    private TextField searchBar;

    @FXML
    private Label foodType;

    @FXML
    private GridPane foodGridPane;
    
    ArrayList<Food> list = new ArrayList<>(); 
    
    PreparedStatement pst = LoginController.pst;
    ResultSet rs = LoginController.rs;
    
    private List<Food> foods(){
        String sql = "Select * from food_item where resto_id=?";
        
        try{
            pst = LoginController.conn.prepareStatement(sql);
           // System.out.println(RestaurantProfileController.restoId);
           // pst.setInt(1, RestaurantProfileController.restoId);
            rs = pst.executeQuery();
            
            while(rs.next()){
                list.add(new Food(rs.getFloat("food_price"), rs.getString("food_name"), rs.getString("food_ingredients")));
            }
        }catch(Exception e){
            System.out.println("An error has occured!");
        }
        
        return list;
    }

    @FXML
    void loadFoodAndDrinks(ActionEvent event) {
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        foods();
        
        int column = 0;
        int row = 0;
        
        try {
            for (int i = 0; i<list.size(); i++){
                
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/food-thumb.fxml"));
                BorderPane pane = fxmlLoader.load();
                FoodThumbController thumbController = fxmlLoader.getController();
                thumbController.setData(list.get(i));
                    
                if (column == 2){
                    column = 0;
                    ++row;
                }
                
                foodGridPane.add(pane, column, row);
                GridPane.setMargin(pane, new Insets(10));
                column++;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
       
    
}
