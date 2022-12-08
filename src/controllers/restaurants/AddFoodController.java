/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.restaurants;

import com.jfoenix.controls.JFXComboBox;
import controllers.LoginController;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AddFoodController implements Initializable {
    
    @FXML
    private TextField foodName;

    @FXML
    private TextField foodPrice;

    @FXML
    private TextArea foodDescription;

    @FXML
    private JFXComboBox<String> foodType;

    @FXML
    void add(ActionEvent event) {
        if(foodName.getText().equals("") || foodPrice.getText().equals("") || foodDescription.getText().equals("") || foodType.getValue().equals("")){
            JOptionPane.showMessageDialog(null, "Some Fields are empty!");
        } else {
            String sql = "Insert into food_item(food_name, type_id, resto_id, food_ingredients, food_price, food_status) values"
                + "(?,?,?,?,?,?)";
            try{
                PreparedStatement pst = LoginController.conn.prepareStatement(sql);
                pst.setString(1, foodName.getText());
                pst.setString(2, foodType.getValue());
                pst.setInt(3, LoginController.restoId);
                pst.setFloat(4, (float) Double.parseDouble(foodDescription.getText()));
                pst.setString(5, foodPrice.getText());
                pst.setInt(6, 1);
                int intRs = pst.executeUpdate();

                if(intRs == 1){
                    JOptionPane.showMessageDialog(null, "Your recipe has been added succesfully to the platform");
                    foodName.clear();
                    foodDescription.clear();
                    foodPrice.clear();
                    foodType.setValue("");
                }
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
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
