/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.gui.hallAdmin.addRoom;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.alerts.AlertMaker;
import hall_management.gui.extras.utilities;
import hall_management.gui.hallAdmin.HallAdmin_viewController;
import hall_management.gui.hallAdmin.queries.query;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author papan
 */
public class Add_RoomController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXTextField roomID;
    @FXML
    private JFXTextField capacity;
    @FXML
    private JFXComboBox<String> wingList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connector.getConnection();
        
        ObservableList<String> wingOptions = FXCollections.observableArrayList(
                "Not Applicable",
                "North",
                "West"
        );
        wingList.setItems(wingOptions);
        
        if(utilities.getHid()!=5)
        {
            wingList.setDisable(true);
            wingList.setValue("Not Applicable");
        }
    }    
    
    @FXML
    private void save(){
        String id = roomID.getText() ;
        int rCap = Integer.parseInt(capacity.getText());
        String wing = wingList.getValue();
        
        if(id==null || rCap==0 || wing ==null)
        {
            AlertMaker.showErrorMessage("Incomplete or Invalid Data Error!", "Please fill up all the fields. Capacity can't be Zero.");
            return;
        }
        
        int rid = Integer.parseInt(id);
        if(searchRoomInHall(rid, utilities.getHid()).equals("Yes"))
        {
            AlertMaker.showErrorMessage("Invalid Data Error!", "The Room is already added to the database.");
            return;
        }
        
        String stmt = query.addRoom(utilities.getHid(), id, wing, rCap);
        System.out.println(stmt);

        if (connector.execAction(stmt)) {
            reset();
            AlertMaker.showSimpleAlert("Success", "Room added to Database");
            
            HallAdmin_viewController controller = utilities.getHA_controller() ;
            //controller.loadStaffData();

        } else {
            //AlertMaker.showErrorMessage("Failed", "Teacher Id already in use.");
        }
    }
    
    @FXML
    private void reset(){
        roomID.setText(null);
        capacity.setText(null);
        wingList.getSelectionModel().clearSelection();
    }
    
    public String searchRoomInHall(int rid, int hid)
    {
        String decision = null;
        ResultSet res2 = connector.execQuery(query.searchRoom(hid, rid));
        try{
            if(res2.next())
            {
                decision = "Yes";
            }
            else
                decision = "No";
        }catch(SQLException ex)
        {
            //Logger.getLogger(Add_RoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return decision;
    }
}
