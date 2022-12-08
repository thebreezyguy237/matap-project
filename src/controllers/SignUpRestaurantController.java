/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import models.mySqlConnect;
import views.main;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SignUpRestaurantController implements Initializable {
    
    
    @FXML
    private AnchorPane signUpResto;
    
    @FXML
    private BorderPane signUpPane;

    @FXML
    private TextField restaurantNameTextField;

    @FXML
    private TextField sloganTextField;

    @FXML
    private VBox vbox;

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField verifyPasswordTextField;

    @FXML
    private JFXDatePicker dateOfCreationTextField;
    
     @FXML
    private TextField countryTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField streetTextField;

    @FXML
    private TextField phone1TextField;

    @FXML
    private TextField phone2TextField;
    
    @FXML
    private CheckBox checkbox;
    
    @FXML
    private TextField emailTextField;
    
    Connection conn = null;   
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    @FXML
    void submit(ActionEvent event) {
        conn = mySqlConnect.connectDb();
            String sql = "insert into restaurants (resto_name, resto_slogan, resto_email, resto_username, resto_password, "
            + "resto_dateOfCreation, resto_country, resto_city, resto_street, resto_phoneNumber1, resto_phoneNumber2, resto_profilePicture)"
                    + " values (?,?,?,?,?,?,?,?,?,?,?,?)";
            try{
            
            if (checkbox.isSelected()){
                if (passwordTextField.getText().equals(verifyPasswordTextField.getText())) {
                    if (restaurantNameTextField.getText().equals("") ||
                            emailTextField.getText().equals("") || userNameTextField.getText().equals("") ||
                            passwordTextField.getText().equals("") || dateOfCreationTextField.getValue().toString().equals("") ||
                            phone1TextField.getText().equals("") || 
                            countryTextField.getText().equals("") || streetTextField.getText().equals("") ||
                            cityTextField.getText().equals("")
                            ) {
                        JOptionPane.showMessageDialog(null, "Some Fields are empty!");
                        
                    } else {
                        long dOB = dateOfCreationTextField.getValue().toEpochDay(); 
                        long milliseconds = dOB * 24 * 60 * 60 * 1000;
                        java.sql.Date date = new java.sql.Date(milliseconds);

                        pst = conn.prepareStatement(sql);
                        pst.setString(1, restaurantNameTextField.getText());
                        pst.setString(2, sloganTextField.getText());
                        pst.setString(3, emailTextField.getText());
                        pst.setString(4, userNameTextField.getText());
                        pst.setString(5, passwordTextField.getText());
                        pst.setDate(6, date);
                        pst.setString(7, countryTextField.getText());
                        pst.setString(8, cityTextField.getText());
                        pst.setString(9, streetTextField.getText());
                        pst.setString(10, phone1TextField.getText());
                        pst.setString(11, phone2TextField.getText());
                        pst.setString(12, "NULL");
                        pst.execute();


                        JOptionPane.showMessageDialog(null, "Your account has been created successfully!");
                        
                        Button btn = (Button)event.getSource();
                        btn.getScene().getWindow().hide();
                        
                        Parent root = FXMLLoader.load(getClass().getResource("/views/restaurants/menuRestaurants.fxml"));
                        Stage stage = new Stage();
                        stage.getIcons().add(new Image(main.class.getResourceAsStream("/resources/media/images/resto/favicon.png")));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("Login Page");
                        //stage.setResizable(false);
                        stage.show();
                        
                        
                        Locale.setDefault(Locale.ENGLISH);        
                        
                        
                    } 
                    
                } else
                    JOptionPane.showMessageDialog(null, "Your passwords are not matching!");
            
            } else {
            JOptionPane.showMessageDialog(null, "You didnot read the terms and contracts!");
            }
            
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            } 
    }
    
    
    @FXML
    void login(ActionEvent event) {
        try{  
            AnchorPane loginPane = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
            signUpResto.getChildren().setAll(loginPane);
        } catch(Exception ex){
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
