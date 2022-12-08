/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import views.main;

/**
 *
 * @author NAOUSSI
 */
public class Toolkit {
    
    public static PreparedStatement pst;
    public static Connection conn;
    public static ResultSet rs;
    
    public static void loginRestaurant(ActionEvent event, TextField username, TextField password){
        String sql = "Select * from restaurants where resto_username = ? and resto_password = ?"; 
            try{
                pst = conn.prepareStatement(sql);
                pst.setString(1, username.getText());
                pst.setString(2, password.getText());
                rs = pst.executeQuery();
                if(rs.next()){
                    LoginController.restoId = rs.getInt("resto_id");
                    LoginController.restoUsername = rs.getString("resto_username");
                    LoginController.restoName = rs.getString("resto_name");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                    username.clear();
                    password.clear();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }
    
}
