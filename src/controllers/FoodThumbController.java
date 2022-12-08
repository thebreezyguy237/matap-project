/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import models.Food;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FoodThumbController implements Initializable {
    
    @FXML
    private ImageView foodImage;
    
    @FXML
    private Label foodName;

    @FXML
    private Label foodDescription;

    @FXML
    private Label foodPrice;
    
    @FXML
    private Button payBtn;
    
    PreparedStatement pst = LoginController.pst;
    ResultSet rs = LoginController.rs;
    
    
    int foodId(){
        String sql = "Select food_id from food_item where food_type_id = ? and resto_id=?";
        try{
            pst = LoginController.conn.prepareStatement(sql);
            pst.setInt(1, FoodAndDrinksController.type_id);
            pst.setInt(2, 1);
            rs = pst.executeQuery(); 
            
            if (rs.next()){
                return rs.getInt(1);
            }
        } catch(Exception e){
            System.out.println("An error occured!");
        }
        return 0;
    }

    @FXML
    void addToCart(ActionEvent event) {
        
        String sql = "Insert into order_details(food_id, amount, no_of_serving, total_amount) values (?,?,?,?)";
        try{
            pst = LoginController.conn.prepareStatement(sql);
            pst.setInt(1, foodId());
            pst.setFloat(2, (float) Double.parseDouble(foodPrice.getText()));
            pst.setInt(3, 1);
            pst.setFloat(4, (float) (Double.parseDouble(foodPrice.getText()) * 1));
            rs = pst.executeQuery(); 
            
            if(rs.next()){
                int orderDetailsId = rs.getInt("order_details_id");
                //int orderId = getOrderId();
                String sql2 = "Update order_details set order_id = ? where order_details_id = ?";
                
                pst = LoginController.conn.prepareStatement(sql2);
                pst.setInt(1, 1);
                pst.setInt(2, orderDetailsId);
                //rs = pst.executeUpdate();
            }
        } catch(Exception e){
            System.out.println("An error occured!");
        }
        
        String sql2 = "Insert into orders(customer_id, order_date, order_status) values (?,?,?) on duplicate key update order_date = ?";
        try{
            pst = LoginController.conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(LoginController.id));
            pst.setDate(2, new java.sql.Date(new Date().getTime()));
            pst.setInt(3, 1); //meaning that the order is pending
            pst.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
            rs = pst.executeQuery();
            
            if(rs.next()){
                int orderId = rs.getInt("order_id");
                
                String sql3 = "Update orders set total_amount = ? where order_id = ?";
                pst = LoginController.conn.prepareStatement(sql2);
                pst.setFloat(1, (float) Double.parseDouble(foodPrice.getText()));
                pst.setInt(2, orderId);
                rs = pst.executeQuery();
            }
        } catch(Exception e){
            System.out.println("An error occured!");
        }
    }
    
   /* int getOrderId(){
        
    }*/

    @FXML
    void addToFavorite(ActionEvent event) {
       /* if (likeBtn.getText().equals("Like")){
            likeBtn.setText("Unlike");
            String sql = "Insert into favorites(favorite_"
        }*/
    }

    @FXML
    void pay(ActionEvent event) {
        
        String sql = "Insert into order_details(food_id, amount, no_of_serving, total_amount) values (?,?,?,?)";
        try{
            pst = LoginController.conn.prepareStatement(sql);
            pst.setInt(1, foodId());
            pst.setFloat(2, (float) Double.parseDouble(foodPrice.getText()));
            pst.setInt(3, 1);
            pst.setFloat(4, (float) (Double.parseDouble(foodPrice.getText()) * 1));
            boolean insert = pst.execute();
            
            if(insert){
                int orderDetailsId = rs.getInt("order_details_id");
                //int orderId = getOrderId();
                String sql2 = "Insert into orders(customer_id, order_date, total_amount, order_status) values (?,?,?,?)";
                
                pst = LoginController.conn.prepareStatement(sql2);
                pst.setInt(1, Integer.parseInt(LoginController.id));
                pst.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
                pst.setFloat(3, (float)(Double.parseDouble(foodPrice.getText()) * 1));
                pst.setInt(4, 1); //pending
                insert = pst.execute();
                
                if(insert){
                   AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/payment-method.fxml"));
                   payBtn.getScene().setRoot(pane);
                }
                //rs = pst.executeUpdate();
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    /**
     *
     * @param food
     */
    public void setData(Food food){
        foodImage.setImage(new Image(getClass().getResourceAsStream("/resources/media/images/logo.png")));
        foodName.setText(food.getName());
        foodDescription.setText(food.getDescription());
        foodPrice.setText(""+food.getPrice());
    }
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
