/*
 * Life sucks....
 */
package hall_management.gui.extras;

import hall_management.gui.admin.Admin_viewController;
import hall_management.gui.hallAdmin.HallAdmin_viewController;
import hall_management.gui.student.Student_viewController;
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
    
    public static Admin_viewController a_controller = null ;
    public static Student_viewController s_controller = null ;
    public static HallAdmin_viewController ha_controller = null ;
    
    public static int hid = 0 ;
    public static String sid = null ;
    
    
    public static void setHid(int id){
        utilities.hid = id ;
    }
    
    public static int getHid() {
        return hid ;
    }
    
    public static void setSid(String id) {
        utilities.sid = id ;
    }
    
    public static String getSid() {
        return sid ;
    }
    
    public static void setHA_controller(HallAdmin_viewController ha_controller) {
        utilities.ha_controller = ha_controller;
    }

    public static HallAdmin_viewController getHA_controller() {
        return ha_controller;
    }

    public static void setA_controller(Admin_viewController a_controller) {
        utilities.a_controller = a_controller;
    }

    public static Admin_viewController getA_controller() {
        return a_controller;
    }
    
    public static void setS_controller(Student_viewController s_controller) {
        utilities.s_controller = s_controller ;
    }
    
    public static Student_viewController getS_controller() {
        return s_controller ;
    }
    
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
