/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class PaymentController implements Initializable {
    
    @FXML
    private TextField phoneNumber;

    @FXML
    private PasswordField pinCode;

    @FXML
    void pay(ActionEvent event) {
        if (phoneNumber.getText().length() == 9 && Integer.parseInt(phoneNumber.getText()) > 690000000 && Integer.parseInt(phoneNumber.getText()) < 700000000) {
            if (pinCode.getText().length() == 4){
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Validate Payment");
                a.setHeaderText("Payment");
                a.setContentText("You are going to pay " +ShoppingCartController.getTotalAmount()+"FCFA using your OM account number "
                        + phoneNumber.getText() + ". Do you confirm the transaction?");
                a.showAndWait();      
                
                
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Pin Code!");
            }
        } else{
            JOptionPane.showMessageDialog(null, "Invalid Phone Number Account!");
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
