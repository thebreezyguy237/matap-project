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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RestaurantsController implements Initializable {
    
    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;
    
    @FXML
    private AnchorPane restoPane;
    
    ArrayList<String> list = new ArrayList<>();
    
    static String btnName;
    
    void buttonName(){
        String sql = "Select distinct resto_name from restaurants";
        try {
            LoginController.pst = LoginController.conn.prepareStatement(sql);
            LoginController.rs = LoginController.pst.executeQuery();
            while (LoginController.rs.next()){
                list.add(LoginController.rs.getString(1));
            }
        }catch (SQLException ex) {
              System.out.println("An error occured");
        }
        
    }

    
    @FXML
    void loadButton(ActionEvent event) {
        Button btn = (Button) event.getSource();
        btnName = btn.getText();
        System.out.println(btnName);
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/restaurantProfile.fxml"));
            restoPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonName();
        button1.setText(list.get(0));
        button2.setText(list.get(1));
    }    
    
}
