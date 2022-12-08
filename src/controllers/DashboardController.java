/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import models.dashboardThumb;
import models.mySqlConnect;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DashboardController implements Initializable {

    
    @FXML
    private Label username;

    @FXML
    private GridPane dashboardGrid;
    
    @FXML
    private ImageView thumb_img;

    @FXML
    private Label thumb_stat;

    @FXML
    private Label thumb_text;

    @FXML
    private Label thumb_question;

    @FXML
    private Button thumb_button;
    
    private List<dashboardThumb> thumbs = new ArrayList<>();
    
    
    
    Connection conn = LoginController.conn;
    PreparedStatement pst = LoginController.pst;
    ResultSet rs = LoginController.rs;
    
    
     
    //This method initialises a dashboard thumb based on orders and returns it.
    private dashboardThumb orderThumb(){
        
        int numberOfOrders = getNumberOfOrders();
        
        dashboardThumb thumb = new dashboardThumb();
        thumb.setThumbImgSrc("/resources/media/images/orders-icon.png");
        thumb.setThumbButtonText("Order Now!");
        thumb.setThumbQuestion("Do you want to order more?");
        
        if (numberOfOrders > 1)
            thumb.setThumbText("You made " +numberOfOrders+ " orders inside our platform");
        else
            thumb.setThumbText("You made " +numberOfOrders+ " order inside our platform");
        
        thumb.setThumbStat(numberOfOrders);
        thumbs.add(thumb);
        
        return thumb;
    }
    
    private dashboardThumb favoriteFoodThumb(){
        
        String favoriteFood = favoriteFood();
        
        dashboardThumb thumb = new dashboardThumb();
        thumb.setThumbImgSrc("/resources/media/images/orders-icon.png");
        thumb.setThumbButtonText("Order Now!");
        thumb.setThumbQuestion("Do you want to order more?");
       
        thumb.setThumbText("Your favorite food is made " +favoriteFood+ " order inside our platform");
        
        thumb.setThumbStat(Integer.parseInt(favoriteFood));
        thumbs.add(thumb);
        
        return thumb;
    }
    
    private dashboardThumb totalSpendThumb(){
        
        float totalSpend = totalSpend();
        
        dashboardThumb thumb = new dashboardThumb();
        thumb.setThumbImgSrc("/resources/media/images/orders-icon.png");
        thumb.setThumbButtonText("Order Now!");
        thumb.setThumbQuestion("Do you want to order more?");
       
        thumb.setThumbText("You spend " +totalSpend+ "FCFA inside our platform");
        
        thumb.setThumbStat((int) totalSpend);
        thumbs.add(thumb);
        
        return thumb;
    }
    
    
    float totalSpend(){
        float totalAmount = 0;
        float amount;
        String sql = "select amount from payment where customer_id = ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(LoginController.id));
            rs = pst.executeQuery();
            
            while(rs.next()){
                amount = rs.getFloat(1);
                totalAmount += amount;
            }
            
            return totalAmount;
            
        } catch(Exception ex){
           
        }
        
        return 0;
    }
    
    
    
    int getNumberOfOrdersPerMonth(){
        int size;
        conn = mySqlConnect.connectDb();
        String sql = "Select * from orders where customer_id = ? and order_date = ?";
        try {
            
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, LoginController.id);
            pst.setDate(2, (Date) new java.util.Date());
            rs = pst.executeQuery();
            
            if(rs.last()){ 
                size = rs.getRow();  
                return size;
            }
        } catch (Exception ex){
            
        }
        return 0;
    }
    
    
    String favoriteFood(){
        ArrayList<Integer> orderIds = new ArrayList<>();
        ArrayList<Integer> foodIds = new ArrayList<>();
        String food = null;
        int i = 0;
        conn = mySqlConnect.connectDb();
        String sql1 = "Select order_id from orders where customer_id = ?";
        try {
            pst = conn.prepareStatement(sql1);
            pst.setString(1, LoginController.id);
            rs = pst.executeQuery();
            
            while(rs.next()){
                orderIds.add(Integer.parseInt(rs.getString(1)));
            }
            
            
            String sql2 = "Select food_id from order_details where order_id = ?";
            pst = conn.prepareStatement(sql2);
            while(i < orderIds.size()){
                 pst.setString(1, ""+orderIds.get(i));
                 rs = pst.executeQuery();
                 
                 if(rs.next()){
                    foodIds.add(Integer.parseInt(rs.getString(1)));
                 }
                 
                 i++;
            }
            
            int favoritefoodID = countFrequencies(foodIds);
            
            String sql3 = "Select food_name from food_item where food_id = ?";
            pst = conn.prepareStatement(sql3);
            rs = pst.executeQuery();
            if(rs.next()){
                food = rs.getString(1);
            }
            
             if (food == null){
                 food = "No Favorite Meal Yet!";
             }
             
             return food;
        } catch (Exception ex){
            
        }
        
        return null;
    }
    
    
    public static <T> T countFrequencies(ArrayList<T> list) {
        Map<T, Integer> map = new HashMap<>();
        
        for (T t : list){
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }
        
        Entry<T, Integer> max = null;
        
        for (Entry<T, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }
        
        return max.getKey();
    }
            
    
    
    //This method returns the number of orders made by the user.
    int getNumberOfOrders(){
        int size;
        String sql = "Select * from orders where customer_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, LoginController.id);
            rs = pst.executeQuery();
            
            if(rs.last()){ //if rs.last() exists, the method getRow() returns the current row number of the last row which is equivalent to the number of orders.
                size = rs.getRow();  
                return size;
            }
        } catch (Exception ex){
            
        }
        return 0;
    }
    
    /*private void initializeOrderDashboardThumb(int row, int column) throws IOException{    
            VBox thumb = FXMLLoader.load(getClass().getResource("/views/dashboard-thumb.fxml"));
            dashboardGrid.add(thumb, row, column);
    }*/
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        username.setText(LoginController.username);
        orderThumb();
        totalSpendThumb();
        
        int column = 0;
        int row = 0;
        
        try {
            for (int i = 0; i<thumbs.size(); i++){
                
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/dashboard-thumb.fxml"));
                VBox pane = fxmlLoader.load();
                DashboardThumbController thumbController = fxmlLoader.getController();
                thumbController.setData(thumbs.get(i));
                    
                if (column == 2){
                    column = 0;
                    ++row;
                }
                
                dashboardGrid.add(pane, column, row);
                GridPane.setMargin(pane, new Insets(10));
                column++;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
        
        
