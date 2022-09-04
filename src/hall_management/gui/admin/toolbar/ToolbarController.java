/*
 * Life sucks....
 */
package hall_management.gui.admin.toolbar;

import hall_management.gui.extras.utilities;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Abhik_Blaze
 */
public class ToolbarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadAddStudent(ActionEvent event) {
        
        utilities.loadStage(getClass().getResource("/hall_management/gui/admin/addStudent/add_Student.fxml"), "Add New Student", null);
        
    }

    @FXML
    private void loadAddTeacher(ActionEvent event) {
        
         utilities.loadStage(getClass().getResource("/hall_management/gui/admin/addTeacher/add_teacher.fxml"), "Add New Teacher", null);
    }

    @FXML
    private void loadAddHall(ActionEvent event) {
        utilities.loadStage(getClass().getResource("/hall_management/gui/admin/addHall/add_hall.fxml"), "Add New Hall", null);
        
    
    }



    @FXML
    private void loadAddFee(ActionEvent event) {
        
        utilities.loadStage(getClass().getResource("/hall_management/gui/admin/addFees/add_fee.fxml"), "Add New Fee", null);
        
    }

    @FXML
    private void loadSetupFees(ActionEvent event) {
        
        utilities.loadStage(getClass().getResource("/hall_management/gui/admin/setupFees/setup_fee.fxml"), "Set Up Fee", null);
        
    }
    
    

    
}
