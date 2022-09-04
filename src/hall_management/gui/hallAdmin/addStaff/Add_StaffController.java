/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.gui.hallAdmin.addStaff;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.alerts.AlertMaker;
import hall_management.gui.extras.utilities;
import hall_management.gui.hallAdmin.HallAdmin_viewController;
import hall_management.gui.hallAdmin.queries.query;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author papan
 */
public class Add_StaffController implements Initializable {
    
    @FXML
    private JFXTextField st_id;
    @FXML
    private JFXTextField st_name;
    @FXML
    private JFXDatePicker birthDate;
    @FXML
    private Label bDateLabel;
    @FXML
    private Label stJobLabel;
    @FXML
    private JFXComboBox<String> jobList;
    @FXML
    private JFXComboBox<String> stWing;
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
    private Button saveButton;
    @FXML
    private Button cancButton;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Label stWingLabel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connector.getConnection();
        
        st_id.setEditable(false);
        st_id.setText(getStid());
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
                "BOTH",
                "Not Applicable"
        );
        stWing.setItems(wingOptions);
        
        //HallAdmin_viewController controller = utilities.getHA_controller() ;
        
        if(utilities.getHid() != 5)
        {
            stWing.setValue("Not Applicable");
            stWing.setDisable(true);
        }    
        
    }    
    
    @FXML
    private void save(ActionEvent event) {
        String id = getStid() ;
        String name = st_name.getText();
        String job = jobList.getValue();
        LocalDate bDate = birthDate.getValue();
        LocalDate hDate = stDate.getValue();
        LocalDate eDate = endDate.getValue();
        String wing = stWing.getValue();
        String salary = stSalary.getText();
        
        //HallAdmin_viewController controller2 = utilities.getHA_controller() ;
        
        if(utilities.getHid()!=5)
        {
            if (name.isEmpty() || (job == null) || job.isEmpty() || bDate.toString().isEmpty() || hDate.toString().isEmpty() || salary.isEmpty()) {
                AlertMaker.showErrorMessage("Incomplete Data Error", "Please enter values in Staff Name, Staff Job, Staff Birth Date and Job Start Date.");
                return;
            }
        
            if ((eDate!= null) && (eDate.isBefore(hDate)) || ((eDate!=null) && (eDate.isBefore(bDate))) || hDate.isBefore(bDate)) {
                AlertMaker.showErrorMessage("Wrong Data Error", "Please check the values in Birth Date, Job Start Date and Job End Date.");
                return;
            }
        }
        if(utilities.getHid()==5)
        {
            if (name.isEmpty() || (job == null) || job.isEmpty() || bDate.toString().isEmpty() || hDate.toString().isEmpty() || salary.isEmpty() || wing.isEmpty() || (wing==null)) {
                AlertMaker.showErrorMessage("Incomplete Data Error", "Please enter values in Staff Name, Staff Job, Wing, Staff Birth Date, Salary and Job Start Date.");
                return;
            }
            if ((eDate!= null) && (eDate.isBefore(hDate)) || ((eDate!=null) && (eDate.isBefore(bDate))) || hDate.isBefore(bDate)) {
                AlertMaker.showErrorMessage("Wrong Data Error", "Please check the values in Birth Date, Job Start Date and Job End Date.");
                return;
            }
        }
        int age = LocalDate.now().getYear()-bDate.getYear();
        String Age = Integer.toString(age);
        String Hid = Integer.toString(utilities.getHid());
        String hireDate = hDate.toString();
        String enDate = null;
        if(eDate!=null)
        {
            enDate = eDate.toString();
        }
        if(eDate==null)
        {
            enDate=null;
        }
        String stmt = query.addStaff(id, name, Hid, job, Age, salary, wing, hireDate, enDate);
        System.out.println(stmt);

        if (connector.execAction(stmt)) {
            reset();
            AlertMaker.showSimpleAlert("Success", "Staff added to Database");
            
            HallAdmin_viewController controller = utilities.getHA_controller() ;
            controller.loadStaffData();

        } else {
            //AlertMaker.showErrorMessage("Failed", "Teacher Id already in use.");
        }
        

    }
    
    @FXML
    public void reset() {
        st_id.setText(getStid());
        st_name.setText(null);
        jobList.getSelectionModel().clearSelection();
        birthDate.setValue(null);
        stSalary.setText(null);
        stDate.setValue(null);
        endDate.setValue(null);
        stWing.getSelectionModel().clearSelection();
    }
    
    public String getStid() {
        String id = null;
        //HallAdmin_viewController controller2 = utilities.getHA_controller();
        int hId = utilities.getHid();
        //String hIdInput = String.valueOf(hId);
        ResultSet res = connector.execQuery(query.getStaffCount(hId));
        try {
            while (res.next()) {
                id = res.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Add_StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id ;

    }
    
}
