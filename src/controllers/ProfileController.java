/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ProfileController implements Initializable {
    
    @FXML
    private TextField usernameTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private JFXDatePicker dobTxt;

    @FXML
    private TextField addressTxt;

    @FXML
    private TextField countryTxt;

    @FXML
    private ComboBox<String> genderTxt;

    @FXML
    private TextField phoneTxt;
    
    

    @FXML
    void update(ActionEvent event) {
        setEditable();
    }
    
    void setEditable(){
        usernameTxt.setEditable(true);
        nameTxt.setEditable(true);
        emailTxt.setEditable(true);
        passwordTxt.setEditable(true);
        dobTxt.setEditable(true);
        addressTxt.setEditable(true);
        countryTxt.setEditable(true);
        genderTxt.setEditable(true);
        phoneTxt.setEditable(true);
    }
    
    void initializeProfile(){
        String sql = "Select * from customers where customer_id = ?";
        try{
            PreparedStatement pst = LoginController.pst;
            ResultSet rs = LoginController.rs;
            pst = LoginController.conn.prepareStatement(sql); 
            pst.setString(1, LoginController.id);
            rs = pst.executeQuery();
           
            if (rs.next()){
                usernameTxt.setText(rs.getString("customer_username"));
                nameTxt.setText(rs.getString("customer_firstName") + rs.getString("customer_lastName"));
                emailTxt.setText(rs.getString("customer_email"));
                passwordTxt.setText(rs.getString("customer_password"));
                addressTxt.setText(rs.getString("customer_address"));
                countryTxt.setText(rs.getString("customer_nationality"));
                genderTxt.getItems().add(rs.getString("customer_gender"));
                genderTxt.setValue("customer_gender");
                phoneTxt.setText(rs.getString("customer_phoneNumber")); 
                
           }
           
           
        } catch(Exception e){
            
        }
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeProfile();
    }    
    
}
