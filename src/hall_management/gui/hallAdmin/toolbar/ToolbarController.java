/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.gui.hallAdmin.toolbar;

import hall_management.gui.extras.utilities;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author papan
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
    private void loadAddStaff(ActionEvent event) {
        
        utilities.loadStage(getClass().getResource("/hall_management/gui/hallAdmin/addStaff/add_Staff.fxml"), "Add New Staff", null);
        
    }
    
    @FXML
    private void loadAddRoom(ActionEvent event){
        utilities.loadStage(getClass().getResource("/hall_management/gui/hallAdmin/addRoom/add_Room.fxml"), "Add New Room", null);
    }
    
    @FXML
    private void loadAddSponsor(ActionEvent event){
        utilities.loadStage(getClass().getResource("/hall_management/gui/hallAdmin/addSponsor/add_Sponsor.fxml"), "Add New Sponsor", null);
    }

    @FXML
    private void loadAssignRoom(ActionEvent event) {
        utilities.loadStage(getClass().getResource("/hall_management/gui/hallAdmin/assignRoom/assign_Room.fxml"), "Assign Room to Student.", null);
    }

    @FXML
    private void loadAddEvent(ActionEvent event) {
        utilities.loadStage(getClass().getResource("/hall_management/gui/hallAdmin/addEvent/add_Event.fxml"), "Add New Event.", null);
    }
    
    
}
