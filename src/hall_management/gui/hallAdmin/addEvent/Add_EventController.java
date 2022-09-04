/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.gui.hallAdmin.addEvent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.alerts.AlertMaker;
import hall_management.gui.extras.utilities;
import hall_management.gui.hallAdmin.HallAdmin_viewController;
import hall_management.gui.hallAdmin.addStaff.Add_StaffController;
import hall_management.gui.hallAdmin.queries.query;
import hall_management.gui.hallAdmin.tableViewClasses.Events_Info;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author papan
 */
public class Add_EventController implements Initializable {

    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private VBox rootVbox;
    @FXML
    private JFXTextField eventIDtxt;
    @FXML
    private JFXTextField evNametxt;
    @FXML
    private Label stDateLabel;
    @FXML
    private JFXDatePicker stDatePicker;
    @FXML
    private Label eDateLabel;
    @FXML
    private JFXDatePicker eDatePicker;
    @FXML
    private JFXButton saveButon;
    @FXML
    private JFXButton cancButon;
    
    private boolean inEditMode = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        eventIDtxt.setText(getEventId());
        eventIDtxt.setEditable(false);
        stDatePicker.setValue(LocalDate.now());
    }

    public boolean isInteger(String str)
    {
        try{
            Integer.parseInt(str);
            return true ;
        }catch(NumberFormatException e){
            return false ;      
        }
                
    }

    @FXML
    private void save(ActionEvent event) {
        String id =null;
        if(inEditMode==true)
            id = eventIDtxt.getText();
        else
            id = getEventId();
        
        String evName = evNametxt.getText();
        
        LocalDate hDate = stDatePicker.getValue();
        LocalDate eDate = eDatePicker.getValue();
        
        if(evName == null || hDate==null || eDate ==null)
        {
            AlertMaker.showErrorMessage("Incomplete Data Error","Please Enter values in Event Name and Start Date");
            return;
        }
        if(eDate.isBefore(hDate))
        {
            AlertMaker.showErrorMessage("Invalid Data Error","End Date Can't be After Start Date!");
            return;
        }
        String eDateStr = eDate.toString();
        String hDateStr = hDate.toString();
        int eid = Integer.parseInt(id);
        if(inEditMode==false)
        {
            String stmt1 = query.addEvent(eid, evName);
            String stmt2 = query.addEventInfo(eid, utilities.getHid(), hDateStr, eDateStr);
            if(connector.execAction(stmt1) && connector.execAction(stmt2))
            {
                reset();
                AlertMaker.showSimpleAlert("Success", "Event added to Database");
            
                HallAdmin_viewController controller = utilities.getHA_controller() ;
                controller.loadEventsData();
            }
            else{
                
            }
        }
//        if(inEditMode==true)
//        {
//            String stmt3 = query.updateEventInfo(eid, utilities.getHid(), eDateStr);
//            if(connector.execAction(stmt3))
//            {
//                reset();
//                AlertMaker.showSimpleAlert("Success", "Event updated in Database");
//            
//                HallAdmin_viewController controller = utilities.getHA_controller() ;
//                controller.loadEventsData();
//            }
//            else{
//                
//            }
//        }
    }

    @FXML
    private void reset() {
        eventIDtxt.setText(getEventId());
        evNametxt.setText(null);
        stDatePicker.setValue(LocalDate.now());
        eDatePicker.setValue(null);
    }
    
    public String getEventId()
    {
        String id = null;
        //HallAdmin_viewController controller2 = utilities.getHA_controller();
        int hId = utilities.getHid();
        //String hIdInput = String.valueOf(hId);
        ResultSet res = connector.execQuery(query.loadEventsCount());
        try {
            while (res.next()) {
                id = res.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Add_StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id ;
    }
    
//    public void inflateUI(Events_Info event)
//    {
//        inEditMode = true;
//        eventIDtxt.setText(event.getEvId());
//        evNametxt.setText(event.getEvName());
//        stDatePicker.setValue(LocalDate.parse(event.getEvStDate()));
//        
//        eventIDtxt.setEditable(false);
//        evNametxt.setEditable(false);
//        stDatePicker.setEditable(false);
//        
//        eventIDtxt.setDisable(true);
//        evNametxt.setDisable(true);
//        stDatePicker.setDisable(true);
//    }
}
