/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import models.dashboardThumb;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DashboardThumbController implements Initializable {
    
    @FXML
    private VBox thumb;

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
    
    public void setData(dashboardThumb thumb){
        Image image = new Image(getClass().getResourceAsStream(thumb.getThumbImgSrc()));
        thumb_img.setImage(image);
        
        thumb_stat.setText(""+thumb.getThumbStat());
        thumb_text.setText(thumb.getThumbText());
        thumb_question.setText(thumb.getThumbQuestion());
        thumb_button.setText(thumb.getThumbButtonText());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
