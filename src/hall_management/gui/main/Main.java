/*
 * Life sucks....
 */
package hall_management.gui.main;

import hall_management.gui.extras.utilities;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Abhik_Blaze
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/hall_management/gui/login/login.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Hall Management System");
        stage.setScene(scene);
        utilities.setStageIcon(stage);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
