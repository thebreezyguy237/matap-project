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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import models.ShoppingCartItem;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ShoppingCartController implements Initializable {
    
    @FXML
    private TableView<ShoppingCartItem> tableView;
    
    @FXML
    private TableColumn<ShoppingCartItem, String> foodName;

    @FXML
    private TableColumn<ShoppingCartItem, Integer> quantity;

    @FXML
    private TableColumn<ShoppingCartItem, Float> price;

    @FXML
    private TableColumn<ShoppingCartItem, Float> total;

    @FXML
    private Label totalAmount;
    
    @FXML
    private AnchorPane shoppingPane;
    
    static ObservableList<ShoppingCartItem> list = FXCollections.observableArrayList();
    static ObservableList<ShoppingCartItem> tableList = FXCollections.observableArrayList();
    static int orderId;
    
    PreparedStatement pst = LoginController.pst;
    ResultSet rs = LoginController.rs;
    
    
    void initializeCart() {
        String sql = "select order_id from orders where customer_id = ? and order_status = 1";
        try{
            pst = LoginController.conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(LoginController.id));
            rs = pst.executeQuery();
            
            if(rs.next()){
                orderId = rs.getInt(1);
                
                String sql2 = "Select * from order_details where order_id = ?";
                pst = LoginController.conn.prepareStatement(sql2);
                pst.setInt(1, orderId);
                rs = pst.executeQuery();
                
                while(rs.next()){
                    int foodId = rs.getInt("food_id");
                    String food_name = getFoodName(foodId);
                    int numberOfServing = rs.getInt("no_of_serving");
                    float amount = rs.getFloat("amount");
                    float totAmt = rs.getFloat("total_amount");
                    ShoppingCartItem item = new ShoppingCartItem(food_name, numberOfServing, amount, totAmt);
                    list.add(item);
                    tableList = removeDuplicates(list);
                    System.out.println(food_name);
                    System.out.println(rs.getInt("no_of_serving")); 
                }
            }
        
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
      
    
    
    public static <E> ObservableList<E> removeDuplicates(ObservableList<E> list) {

        ObservableList<E> newList = FXCollections.observableArrayList();
        for (E aList : list) {
            if (!newList.contains(aList)) {
                newList.add(aList);
            }
        }
        return newList;
    }
    
    String getFoodName(int foodId){
        String sql = "select food_name from food_item where food_id = ?";
        try{
            PreparedStatement pst = LoginController.conn.prepareStatement(sql);
            pst.setInt(1, foodId);
            ResultSet rs = pst.executeQuery();
                      
            if(rs.next()){
                return rs.getString(1);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
         return ""; 
    }
    
    static Float getTotalAmount(){
        float totalAmount = 0;
        for (int i = 0; i<list.size(); i++){
            float amount = list.get(i).getTotal();
            totalAmount += amount;
        }
        
        return totalAmount;
    }
    

    @FXML
    void pay(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/payment-method.fxml"));
            shoppingPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(ShoppingCartController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableView.refresh();
        initializeCart();
        totalAmount.setText(""+getTotalAmount()+"$");
        foodName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        tableView.setItems(tableList);
        
    }    
    
}
