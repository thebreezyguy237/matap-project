/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author NAOUSSI
 */
public class main extends Application {
    
     @Override
    public void start(Stage stage) {
        
          try {
            Locale.setDefault(Locale.ENGLISH);
            Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));           
            stage.getIcons().add(new Image(main.class.getResourceAsStream("/resources/media/images/favicon.png")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login Page");
            //stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
