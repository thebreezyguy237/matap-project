/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
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
public class PaymentMethodController implements Initializable {
    
    @FXML
    private AnchorPane paymentMethodPane;
    
    @FXML
    private Button OM;

    @FXML
    private Button MoMo;

    @FXML
    private Button Visa;
    
    static String paymentMethod;
    
    @FXML
    void OMPaymentMethod(ActionEvent event) {
        
        paymentMethod = OM.getText();
        
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/payment.fxml"));
            paymentMethodPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(PaymentMethodController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    void MoMoPaymentMethod(ActionEvent event){
        paymentMethod = MoMo.getText();
        
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/payment.fxml"));
            paymentMethodPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(PaymentMethodController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    void visaPaymentMethod(ActionEvent event){
        paymentMethod = Visa.getText();
        
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/payment.fxml"));
            paymentMethodPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(PaymentMethodController.class.getName()).log(Level.SEVERE, null, ex);
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
