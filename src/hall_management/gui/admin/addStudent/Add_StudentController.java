/*
 * Life sucks....
 */
package hall_management.gui.admin.addStudent;

import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.admin.queries.query;
import hall_management.gui.alerts.AlertMaker;
import java.net.URL;
import java.util.ResourceBundle;
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
public class Add_StudentController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private JFXTextField s_id;
    @FXML
    private JFXTextField s_name;
    @FXML
    private JFXTextField s_f_name;
    @FXML
    private JFXTextField s_m_name;
    @FXML
    private JFXTextField s_gender;
    @FXML
    private JFXTextField s_religion;
    @FXML
    private JFXTextField s_mobile;
    @FXML
    private JFXTextField s_bld_grp;
    @FXML
    private JFXTextField s_prm_addr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connector.getConnection() ;
        // TODO
    }    

    @FXML
    private void save(ActionEvent event) {
        
        String id = s_id.getText() ;
        String name = s_name.getText() ;
        String father = s_f_name.getText() ;
        String mother = s_m_name.getText() ;
        String gender = s_gender.getText() ;
        String religion = s_religion.getText() ;
        String mobile = s_mobile.getText() ;
        String bld_grp = s_bld_grp.getText() ;
        String prm_addr = s_prm_addr.getText() ;
        
        if(id.isEmpty() || name.isEmpty() || father.isEmpty() || mother.isEmpty() || gender.isEmpty() || religion.isEmpty() || mobile.isEmpty() || bld_grp.isEmpty() || prm_addr.isEmpty() )
        {
            AlertMaker.showErrorMessage("Incomplete Data Error", "Please enter values in all the fields.");
            return ; 
        }    
        
        String stmt = query.addStudent(id, name, father, mother, gender, religion, mobile, bld_grp, prm_addr) ;
        
        if (connector.execAction(stmt)) {
            reset() ;
            AlertMaker.showSimpleAlert("Success", "Student added to Database");
            
        } 
        else //Error
        {
            //taken care of in connector part...
            //AlertMaker.showErrorMessage("Failed", "Student Id already in use.");
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
        s_id.setText(null);
        s_name.setText(null);
        s_f_name.setText(null);
        s_m_name.setText(null);
        s_gender.setText(null);
        s_religion.setText(null);
        s_mobile.setText(null);
        s_bld_grp.setText(null);
        s_prm_addr.setText(null);
    }
  
    
}
