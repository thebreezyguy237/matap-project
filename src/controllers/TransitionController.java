/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import views.main;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class TransitionController implements Initializable {
    
    @FXML
    private AnchorPane transitionPane;
    
    @FXML
    private Circle circle1;

    @FXML
    private Circle circle2;

    @FXML
    private Circle circle3;
    
    private PathTransition pt;
    
   
    Line line1 = new Line(-100, -20, -100, 20);
    Line line2 = new Line(-100, -20, -100, 20);
    Line line3 = new Line(-100, -20, -100, 20);
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        line1.setLayoutX(95);
        line1.setLayoutY(0);
        line2.setLayoutX (100);
        line2.setLayoutY(0);
        line3.setLayoutX (105);
        line3.setLayoutY(0);
        transition(circle1, true, line1);
        reverseTransition(circle2, true, line2);
        transition(circle3, true, line3);
        
        if(pt.getCycleCount() == 5){
            try {
                //circle1.getScene().getWindow().hide();
                AnchorPane pane =  FXMLLoader.load(getClass().getResource("/views/menu.fxml"));
                Parent root = pane;
                Scene scene = new Scene(root);
                Stage menuStage = new Stage();
                menuStage.setScene(scene);
                menuStage.show();
            
        } catch (Exception ex) {
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        

    }    
    
    void transition(Circle c, boolean reverse, Line l){
        pt = new PathTransition(javafx.util.Duration.millis(3000), l, c);
        pt.setAutoReverse(reverse);
        pt.setRate(2);
        pt.setCycleCount(5);
        pt.play();
           
    }
    
    void reverseTransition(Circle c, boolean reverse, Line l){
        pt = new PathTransition(javafx.util.Duration.millis(3000), l, c);
        pt.setAutoReverse(reverse);
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.setRate(2);
        pt.setDelay(javafx.util.Duration.millis(1500));
        pt.setCycleCount(5);
        pt.play();
    }
    
}
