/*
 * Life sucks....
 */
package hall_management.gui.admin.addTeacher;

import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.admin.queries.query;
import hall_management.gui.alerts.AlertMaker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abhik_Blaze
 */
public class Add_teacherController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private JFXTextField t_id;
    @FXML
    private JFXTextField t_name;
    @FXML
    private JFXTextField t_desig;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connector.getConnection() ;
    }    

    @FXML
    private void save(ActionEvent event) {
        String id = t_id.getText() ;
        String name = t_name.getText() ;
        String desig = t_desig.getText() ;
        
        if( id.isEmpty() || name.isEmpty() || desig.isEmpty() )
        {
            AlertMaker.showErrorMessage("Incomplete Data Error", "Please enter values in all the fields.");
            return ; 
        }
        
        String stmt = query.addTeacher(id, name, desig) ;
        System.out.println(stmt);
        
        if (connector.execAction(stmt)) {
            reset() ;
            AlertMaker.showSimpleAlert("Success", "Teacher added to Database");
            
        } 
        else //Error
        {
            //AlertMaker.showErrorMessage("Failed", "Teacher Id already in use.");
        }
        
        
    }

    @FXML
    private void cancel(ActionEvent event) {
        
        connector.closeConnection();
        Stage stage = (Stage) anchorpane.getScene().getWindow();
        stage.close();
    
    }
    
    public void reset()
    {
        t_id.setText(null);
        t_name.setText(null);
        t_desig.setText(null);
    }
    
}


