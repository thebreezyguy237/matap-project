/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
public class LoginController_1 implements Initializable {
    
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
           loginCustomer();
        } else {
            loginRestaurantMenu(event);
        }
    }
    
    //Login a restaurant to the platform if its credentials are valid
    void loginRestaurantMenu(ActionEvent event){
            String sql = "Select * from restaurants where resto_username = ? and resto_password = ?"; 
            try{
                pst = conn.prepareStatement(sql);
                pst.setString(1, userNameTextField.getText());
                pst.setString(2, passwordTextField.getText());
                rs = pst.executeQuery();
                if(rs.next()){
                    restoId = rs.getInt("resto_id");
                    restoUsername = rs.getString("resto_username");
                    restoName = rs.getString("resto_name");
                    
                    Button btn = (Button) event.getSource();
                    btn.getScene().getWindow().hide();
                        
                    Parent root = FXMLLoader.load(getClass().getResource("/views/restaurants/menuRestaurants.fxml"));
                    Stage stage = new Stage();
                    stage.getIcons().add(new Image(main.class.getResourceAsStream("/resources/media/images/resto/favicon.png")));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Login Page");
                    //stage.setResizable(false);
                    stage.show();
                    
                    /* try {
                    Locale.setDefault(Locale.ENGLISH);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/transition.fxml"));
                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.setScene(new Scene((Pane) loader.load()));
                    stage.getIcons().add(new Image(main.class.getResourceAsStream("/resources/media/images/favicon.png")));
                    stage.show();
                    //stage.setResizable(false);
                    } catch (IOException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
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
    void loginCustomer() {
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
                    AnchorPane pane =  FXMLLoader.load(getClass().getResource("/views/transition.fxml"));
                    rootPane.getChildren().setAll(pane);
                    /* try {
                    Locale.setDefault(Locale.ENGLISH);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/transition.fxml"));
                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.setScene(new Scene((Pane) loader.load()));
                    stage.getIcons().add(new Image(main.class.getResourceAsStream("/resources/media/images/favicon.png")));
                    stage.show();
                    //stage.setResizable(false);
                    } catch (IOException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
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
