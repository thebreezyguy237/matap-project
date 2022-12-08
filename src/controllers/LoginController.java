/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.Toolkit.conn;
import static controllers.Toolkit.pst;
import static controllers.Toolkit.rs;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import models.mySqlConnect;
import views.main;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class LoginController implements Initializable {
    
    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordTextField;
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private Label paragraph;
    
    @FXML
    private Label userChoice;
    
    public static String id, username, firstName, lastName;
    public static String restoUsername, restoName;
    public static int restoId;
    
    static public Connection conn = null;
    static public ResultSet rs = null;
    static public PreparedStatement pst = null;
    
    
    
    //Login a user to the platflorm if its credentials are valid
    @FXML
    void login(ActionEvent event) throws Exception {
        conn = mySqlConnect.connectDb();
        if (userChoice.getText().contains("restaurant")){
           loginCustomer(event);
        } else {
            loginRestaurantMenu(event);
        }
    }
    
    public void loadRestaurant(ActionEvent event) throws IOException{
            Button btn = (Button) event.getSource();
            btn.getScene().getWindow().hide();
                        
            Parent root = FXMLLoader.load(getClass().getResource("/views/restaurants/menuRestaurants.fxml"));
            Stage stage = new Stage();
            stage.getIcons().add(new Image(main.class.getResourceAsStream("/resources/media/images/resto/favicon.png")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login Page");
            stage.show();
    }
    
    public void loadClient(ActionEvent event) throws IOException{
            Button btn = (Button) event.getSource();
            btn.getScene().getWindow().hide();
                        
            Parent root = FXMLLoader.load(getClass().getResource("/views/menu.fxml"));
            Stage stage = new Stage();
            stage.getIcons().add(new Image(main.class.getResourceAsStream("/resources/media/images/favicon.png")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login Page");
            stage.show();
    }
    
    //Login a restaurant to the platform if its credentials are valid
    void loginRestaurantMenu(ActionEvent event) throws IOException{
        String sql = "Select * from restaurants where resto_username = ? and resto_password = ?"; 
            try{
                pst = conn.prepareStatement(sql);
                pst.setString(1, userNameTextField.getText());
                pst.setString(2, passwordTextField.getText());
                rs = pst.executeQuery();
                if(rs.next()){
                    LoginController.restoId = rs.getInt("resto_id");
                    LoginController.restoUsername = rs.getString("resto_username");
                    LoginController.restoName = rs.getString("resto_name");
                    loadRestaurant(event);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                    userNameTextField.clear();
                    passwordTextField.clear();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }
    
    //Login a customer to the platform if its credentials are valid
    void loginCustomer(ActionEvent event) {
        String sql = "Select * from customers where customer_username = ? and customer_password = ?";
            try{
                pst = conn.prepareStatement(sql);
                pst.setString(1, userNameTextField.getText());
                pst.setString(2, passwordTextField.getText());
                rs = pst.executeQuery();
         
                if(rs.next()){
                    id = rs.getString("customer_id");
                    username = rs.getString("customer_username");
                    firstName = rs.getString("customer_firstName");
                    lastName = rs.getString("customer_lastName");
                    loadClient(event);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                    userNameTextField.clear();
                    passwordTextField.clear();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }
    
    
    //Load the sign-up page to add a new client or restaurant to the database
    @FXML
    void signUp(ActionEvent event) throws Exception {      
        if (userChoice.getText().contains("restaurant")){
            loadSignUpClient();
        } else {
            loadSignUpRestaurant();
        }  
    }
    
    
    //Switch to the restaurant login page and vice-versa
    @FXML
    void loginRestaurant(ActionEvent event) {
        if (userChoice.getText().contains("restaurant")) {          
            paragraph.setText("Increase your market value by selling your products on the best food ordering system");
            userChoice.setText("Are you a client?");
        } else {
            paragraph.setText("Get delivered the best food from the best restaurants in the world without having to leave you sofa");
            userChoice.setText("Are you a restaurant?");
        }
    }

    //Load the restaurant's sign-up page
    void loadSignUpRestaurant() {
        try {
                AnchorPane pane =  FXMLLoader.load(getClass().getResource("/views/restaurants/signUpRestaurant.fxml"));
                rootPane.getChildren().setAll(pane);
            } catch (Exception ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    //Load the customers' sign-up page
    void loadSignUpClient() {
         try {
                AnchorPane pane =  FXMLLoader.load(getClass().getResource("/views/signUp.fxml"));
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
