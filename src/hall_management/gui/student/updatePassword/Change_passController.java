/*
 * Life sucks....
 */
package hall_management.gui.student.updatePassword;

import com.jfoenix.controls.JFXPasswordField;
import hall_management.database.connector;
import hall_management.gui.admin.Admin_viewController;
import hall_management.gui.alerts.AlertMaker;
import hall_management.gui.extras.utilities;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abhik_Blaze
 */
public class Change_passController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXPasswordField new_pass;
    @FXML
    private JFXPasswordField curr_pass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleUpdateButton(ActionEvent event) {
        try {
            String oldPass = curr_pass.getText() ;
            String newPass = new_pass.getText() ;
            
            if(oldPass.isEmpty() || newPass.isEmpty()) {
                AlertMaker.showErrorMessage("Incomplete Data Error", "Password Fields can not be empty");
                return;
            }
            
            String sid = utilities.getSid() ;
            String stmt = "SELECT USER_PASS FROM LOGIN WHERE USER_ID = '" + sid +"'" ;
            String dbold = null ;
            ResultSet res = connector.execQuery(stmt) ;
            while(res.next()) {
                dbold = res.getString(1) ;
            }
            if(!dbold.equals(oldPass)) {
                AlertMaker.showErrorMessage("Invalid Password Error", "Wrong current Password");
                return ;
            }
            else if(newPass.length() > 20) {
                AlertMaker.showErrorMessage("Invalid Password Error", "New Password length exceeded");
                return;
            }
            
            stmt = "UPDATE LOGIN SET USER_PASS = '" + newPass + "' WHERE USER_ID = '" + sid + "'" ;
            
        if (connector.execAction(stmt)) {
            AlertMaker.showSimpleAlert("Success", "Password updated");
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.close();
                  
        }
            
        } catch (SQLException ex) {
            Logger.getLogger(Change_passController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void handleCancelButton(ActionEvent event) {
        
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
}
