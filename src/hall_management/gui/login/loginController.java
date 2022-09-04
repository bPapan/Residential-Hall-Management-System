/*
 * Life sucks....
 */
package hall_management.gui.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.admin.Admin_viewController;
import hall_management.gui.extras.utilities;
import hall_management.gui.hallAdmin.HallAdmin_viewController;
import hall_management.gui.student.Student_viewController;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private AnchorPane rootPane;

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
        
        boolean found = false ;
        connector.getConnection() ;
        
        String uname = username.getText();
        String pass = password.getText();
        String type = null;
        String id = null ;
        ResultSet res = connector.execQuery(getUser(uname, pass));
        try{
            while(res.next())
            {
                found = true ;
                id = res.getString(1) ;
                type = res.getString(3);
                //System.out.println(id+type);
            }
        }catch(SQLException ex){
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!found) {
            JFXSnackbar snack = new JFXSnackbar(rootPane) ;
            snack.show("Wrong Username or Password", 1500);
            username.getStyleClass().add("wrong-credentials");
            password.getStyleClass().add("wrong-credentials");
            return ;
        }
        
        if(type.equals("admin"))
        {
            closeStage();
            loadAdminView();
            pushnotify(uname);
        }
        else if(type.equals("student"))
        {
            
            closeStage();
            loadStudentView(id);
            pushnotify(uname);
            
        }
        else
        {
            closeStage();
            loadHallAdminView(Integer.parseInt(id)) ;
            pushnotify(uname + " Hall Admin");
            //for hall admin
        }
        
        
    }
    
    public void pushnotify(String uname)
    {
        Image img = new Image("/items/check_mark.jpg") ;
        Notifications notification = Notifications.create()
                                     .text("  Welcome " + uname)
                                     .graphic(new ImageView(img))
                                     .hideAfter(Duration.seconds(2.7))
                                     .position(Pos.TOP_RIGHT) ;
        notification.darkStyle().show();
    }
    
    void loadAdminView() {
        try {
            //Parent parent = FXMLLoader.load(getClass().getResource("/hall_management/gui/admin/admin_view.fxml"));
            
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hall_management/gui/admin/admin_view.fxml"));
            Parent parent = loader.load() ;
            Admin_viewController controller = (Admin_viewController) loader.getController() ;
            utilities.setA_controller(controller);
            
            
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Hall Management System");
            stage.setScene(new Scene(parent));
            stage.setMaximized(true);
            
            
            stage.show();
            utilities.setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void loadStudentView(String id) {
        
        utilities.setSid(id);
        
        try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/hall_management/gui/student/student_view.fxml"));
                Parent parent = loader.load() ;
                Student_viewController controller = (Student_viewController) loader.getController() ;
                utilities.setS_controller(controller);



                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Hall Management System");
                stage.setScene(new Scene(parent));
                stage.setMaximized(true);


                stage.show();
                utilities.setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
     void loadHallAdminView(int x){
        try{
            utilities.setHid(x);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hall_management/gui/hallAdmin/hallAdmin_view.fxml"));
            Parent parent = loader.load() ;
            HallAdmin_viewController controller = (HallAdmin_viewController) loader.getController() ;
            utilities.setHA_controller(controller);
            
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
   
    
    public String getUser(String userName, String passWord)
    {
        String str = "SELECT * FROM LOGIN"
                + " WHERE USER_NAME = "
                + "'" +userName+"'"
               + " AND USER_PASS = "
                + "'" +passWord+"'";
        return str;
    }
}
