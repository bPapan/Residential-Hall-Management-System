/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.gui.hallAdmin.updateStaff;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.alerts.AlertMaker;
import hall_management.gui.extras.utilities;
import hall_management.gui.hallAdmin.HallAdmin_viewController;
import hall_management.gui.hallAdmin.queries.query;
import hall_management.gui.hallAdmin.tableViewClasses.Staff_Info;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author papan
 */
public class UpdateStaffController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private JFXTextField st_id;
    @FXML
    private JFXTextField st_name;
    @FXML
    private JFXComboBox<String> jobList;
    @FXML
    private Label stJobLabel;
    @FXML
    private JFXTextField stSalary;
    @FXML
    private Label stDateLabel;
    @FXML
    private JFXDatePicker stDate;
    @FXML
    private Label endDateLabel;
    @FXML
    private JFXDatePicker endDate;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancButton;
    @FXML
    private Label stWingLabel;
    @FXML
    private JFXComboBox<String> stWing;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connector.getConnection();
        
        st_id.setEditable(false);
        //st_id.setText(getStid());
        ObservableList<String> options = FXCollections.observableArrayList(
                "Cook",
                "Office Employee",
                "Sickboy",
                "Sweeper"
        );
        jobList.setItems(options);
        
        ObservableList<String> wingOptions = FXCollections.observableArrayList(
                "NORTH",
                "WEST",
                "BOTH"
        );
        stWing.setItems(wingOptions);
        
        //HallAdmin_viewController controller = utilities.getHA_controller() ;
        
        if(utilities.getHid() != 5)
            stWing.setDisable(true);
    }    

    @FXML
    private void save(ActionEvent event) {
        String stID = st_id.getText();
        String stName = st_name.getText();
        int hid = utilities.getHid();
        String sal = stSalary.getText();
        LocalDate eDate = endDate.getValue();
        LocalDate hdate = stDate.getValue();
        String jobId = jobList.getValue();
        int wage = Integer.parseInt(sal);
        
        if(sal==null || jobId ==null || jobId.isEmpty())
        {
            AlertMaker.showErrorMessage("Incomplete Data Error","All the fields must be filled up except end date.");
            return;
        }
        
        if(eDate!=null && eDate.isBefore(hdate))
        {
            AlertMaker.showErrorMessage("Wrong Information Error","End Date must be after Start Date.");
            return;
        }
        String enDate = null;
        if(eDate == null)
        {
            enDate = null;
        }
        else
        {
            enDate = eDate.toString();
        }
        String update = query.updateStaffQuery(stID, hid, jobId, wage, enDate);
        
        if(connector.execAction(update))
        {
            //reset();
            AlertMaker.showSimpleAlert("Success", "Staff updated in Database");
            
            HallAdmin_viewController controller = utilities.getHA_controller() ;
            controller.loadStaffData();
        }else{
            
        }
    }

    @FXML
    private void reset() {
        Stage stage = (Stage) AnchorPane.getScene().getWindow();
        stage.close();
    }
    
    public void inflateUI(Staff_Info staff)
    {
        st_id.setText(staff.getStId());
        st_name.setText(staff.getStName());
        stWing.setValue(staff.getStWing());
        jobList.setValue(staff.getStJob());
        
        st_id.setEditable(false);
        st_name.setEditable(false);
        stWing.setEditable(false);
        
        String str = "SELECT ST_H_DATE FROM STAFFS WHERE ST_ID = '" + staff.getStId() + "' " ;
        ResultSet res = connector.execQuery(str) ;
        Date date = null ;
        try {
            while(res.next())
            {
               date = res.getDate(1) ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateStaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String str2 = "SELECT ST_SALARY FROM STAFFS WHERE ST_ID = '" + staff.getStId() + "' " ;
        ResultSet res2 = connector.execQuery(str2) ;
        String salary = null ;
        try {
            while(res2.next())
            {
               salary = res2.getString(1) ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateStaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        stSalary.setText(salary);
       
        stDate.setValue(date.toLocalDate());
        stDate.setDisable(true);
    }
}
