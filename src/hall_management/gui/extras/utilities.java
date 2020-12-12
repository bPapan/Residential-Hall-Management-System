/*
 * Life sucks....
 */
package hall_management.gui.extras;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Abhik_Blaze
 */
public class utilities {
    
    public static void setStageIcon( Stage stage ) 
    {
        stage.getIcons().add(new Image("/items/icon.png")) ;
    }
    
    public static void loadStage( URL url , String title , Stage parentStage ) 
    {
        try {
            Parent parent = FXMLLoader.load(url) ;
            Stage stage = null ;
            if( parentStage != null)
                stage = parentStage ;
            else
                stage = new Stage(StageStyle.DECORATED) ;
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            setStageIcon(stage); 
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
