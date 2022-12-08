/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import models.mySqlConnect;
import views.main;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SignUpController implements Initializable {
    
    @FXML
    private AnchorPane signUpPane;
    
    @FXML
    private AnchorPane loginBlock;
      
    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private VBox vbox;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField verifyPassword;

    @FXML
    private JFXDatePicker dateOfBirthTextField;
    
    @FXML
    private TextField placeOfBirthTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private ComboBox<String> genderField;

    @FXML
    private CheckBox checkbox;
     
    @FXML
    private Button submit_btn;

    Connection conn = null;   
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    @FXML
    void submit(ActionEvent event) {
       
            /*submit_btn.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/views/menu.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();*/
            conn = mySqlConnect.connectDb();
            String sql = "insert into customers (customer_firstName, customer_lastName, customer_email, customer_userName, customer_password, "
            + "customer_dateOfBirth, customer_address, customer_phoneNumber, customer_gender, customer_profilePicture) values (?,?,?,?,?,?,?,?,?,?)";
            try{
            
            if (checkbox.isSelected()){
                if (passwordTextField.getText().equals(verifyPassword.getText())) {
                    if (firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("") ||
                            emailTextField.getText().equals("") || userNameTextField.getText().equals("") ||
                            passwordTextField.getText().equals("") || dateOfBirthTextField.getValue().toString().equals("") ||
                            addressTextField.getText().equals("") || phoneNumberTextField.getText().equals("") ||
                            genderField.getValue().equals("")
                            ) {
                        JOptionPane.showMessageDialog(null, "Some Fields are empty!");
                        
                    } else {
                        long dOB = dateOfBirthTextField.getValue().toEpochDay(); 
                        long milliseconds = dOB * 24 * 60 * 60 * 1000;
                        java.sql.Date date = new java.sql.Date(milliseconds);

                        pst = conn.prepareStatement(sql);
                        pst.setString(1, firstNameTextField.getText());
                        pst.setString(2, lastNameTextField.getText());
                        pst.setString(3, emailTextField.getText());
                        pst.setString(4, userNameTextField.getText());
                        pst.setString(5, passwordTextField.getText());
                        pst.setDate(6, date);
                        pst.setString(7, addressTextField.getText());
                        pst.setString(8, phoneNumberTextField.getText());
                        pst.setString(9, genderField.getValue());
                        pst.setString(10, "NULL");



                        pst.execute();


                        JOptionPane.showMessageDialog(null, "Your account has been created successfully!");
                    }
                    
                    
                } else
                    JOptionPane.showMessageDialog(null, "Your passwords are not matching!");
            
            } else {
            JOptionPane.showMessageDialog(null, "You didnot read the terms and contracts!");
            }
            
            } catch(Exception ex) {
                
            } 
         
    }
   
  
    @FXML
    void login(ActionEvent event) {
        try{  
            AnchorPane loginPane = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
            signUpPane.getChildren().setAll(loginPane);
        } catch(Exception ex){
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] gender = {"M", "F"};
        ObservableList<String> items = FXCollections.observableArrayList(gender);
        genderField.getItems().addAll(items);
        genderField.setValue(gender[0]);
    }    
    
}
