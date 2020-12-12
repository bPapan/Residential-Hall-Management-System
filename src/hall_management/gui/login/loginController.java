/*
 * Life sucks....
 */
package hall_management.gui.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import hall_management.gui.extras.utilities;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Abhik_Blaze
 */
public class loginController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void handleMenuFullScreen(ActionEvent event) {
        Stage stage = ((Stage) username.getScene().getWindow());
        stage.setFullScreen(!stage.isFullScreen());
    }
     private void closeStage() {
        ((Stage) username.getScene().getWindow()).close();
    }

    @FXML
    private void handleloginbutton(ActionEvent event) {
        
        String uname = username.getText();
        String pass = password.getText();
       
        
        if( uname.equals("admin") && pass.equals("admin"))
        {
            closeStage();
            loadAdminView();
            pushnotify(uname);
        }
        else
        {
            username.getStyleClass().add("wrong-credentials");
            password.getStyleClass().add("wrong-credentials");
        }
        
        
    }
    
    public void pushnotify(String uname)
    {
        Image img = new Image("/items/check_mark.jpg") ;
        Notifications notification = Notifications.create()
                                     .text("Welcome " + uname)
                                     .graphic(new ImageView(img))
                                     .hideAfter(Duration.seconds(4))
                                     .position(Pos.TOP_RIGHT) ;
        notification.darkStyle().show();
    }
    
    void loadAdminView() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/hall_management/gui/admin/admin_view.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Hall Management System");
            stage.setScene(new Scene(parent));
            stage.show();
            utilities.setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void loadHallAdminView(int x){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hall_management/gui/hallAdmin/hallAdmin_view.fxml"));
            Parent parent = loader.load() ;
            HallAdmin_viewController controller = (HallAdmin_viewController) loader.getController() ;
            utilities.setHA_controller(controller);
            
            controller.setHid(x);
            
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Hall Management System");
            stage.setScene(new Scene(parent));
            stage.setMaximized(true);
            
            
            stage.show();
            utilities.setStageIcon(stage);
        }catch(IOException ex){
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
