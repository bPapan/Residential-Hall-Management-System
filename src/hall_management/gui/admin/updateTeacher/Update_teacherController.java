/*
 * Life sucks....
 */
package hall_management.gui.admin.updateTeacher;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.admin.Admin_viewController;
import hall_management.gui.admin.tableViewClasses.Teacher_Info;
import hall_management.gui.alerts.AlertMaker;
import hall_management.gui.extras.utilities;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.Types ;
import java.util.concurrent.Callable;

/**
 * FXML Controller class
 *
 * @author Abhik_Blaze
 */
public class Update_teacherController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private JFXTextField t_id;
    @FXML
    private JFXTextField t_name;
    @FXML
    private JFXComboBox<String> t_designation;
    @FXML
    private JFXComboBox<String> t_currentHall;
    @FXML
    private JFXDatePicker t_edate;
    @FXML
    private JFXDatePicker t_sdate;
    @FXML
    private JFXComboBox<String> t_currentRole;

    /**
     * Initializes the controller class.
     */
    
    public boolean assigning = true ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> options = FXCollections.observableArrayList(
                "Professor",
                "Assistant Professor",
                "Associate Professor",
                "Lecturer"
        );
        t_designation.setItems(options);
        
        ObservableList<String> role_options = FXCollections.observableArrayList(
                
                "Provost",
                "Asst. Provost"
        ) ;
        t_currentRole.setItems(role_options);
        
        
        ObservableList<String> hall_options = FXCollections.observableArrayList() ;
        String str = "SELECT H_NAME FROM HALL" ;
        ResultSet res = connector.execQuery(str) ;
        try {
            while(res.next()) {
                
                hall_options.add(res.getString(1)) ;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Update_teacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        t_currentHall.setItems(hall_options);
        
        
        
    }    

    @FXML
    private void save(ActionEvent event) {
        
     
       
            
            String desig = t_designation.getValue() ;
            String hall = t_currentHall.getValue() ;
            String role = t_currentRole.getValue() ;
      
            
            
            if( (desig == null) || (hall == null) || (role == null) || (t_sdate.getValue() == null) ) {
                
                AlertMaker.showErrorMessage("Incomplete Data Error", "All the fields except End Date must contain input");
                return ; 
            }
            
            else if( !assigning &&(t_edate.getValue() == null )) {
                AlertMaker.showErrorMessage("Incomplete Data Error", "End Date must contain input");
                return ;
            }
            
            Date sdate = Date.valueOf(t_sdate.getValue()) ;
           
            
            String sql = "{call UPDATE_TEACHER_CONTROLLER(? , ? , ?, ? , ? , ? , ?)}" ;
            
            CallableStatement cst = connector.getCST(sql) ;
            
            try {
                
                
                if(role.equals("Provost") && assigning )  {
                        String query = "SELECT * FROM DESIGNATED_HALL WHERE H_ID = (SELECT H_ID FROM HALL WHERE H_NAME = '" + hall + "') AND DES_ADMIN_ROLE = 'Provost' AND DES_ADMIN_RDATE IS NULL" ;
                        ResultSet res = connector.execQuery(query) ;
                        while(res.next())
                        {
                            AlertMaker.showErrorMessage("Invalid Data Error", "There is already a provost assigned for this hall");
                            return ;
                        }
                }
                else if( role.equals("Asst. Provost") && assigning ) {
                        
                        String query = "SELECT COUNT(*) FROM DESIGNATED_HALL WHERE H_ID = (SELECT H_ID FROM HALL WHERE H_NAME = '" + hall + "') AND DES_ADMIN_ROLE = 'Asst. Provost' AND DES_ADMIN_RDATE IS NULL" ;
                        ResultSet res = connector.execQuery(query) ;
                        while(res.next())
                        {
                            int count = res.getInt(1) ;
                            if(count == 4) {
                                AlertMaker.showErrorMessage("Invalid Data Error", "No.of Asst. Provosts for this hall exceeded limit");
                                return ;
                            }
                        }
                    
                }
                
                cst.setString(1, hall);
                cst.setString(2,t_id.getText()) ;
                cst.setString(3, desig);
                cst.setString(4, role);
                cst.setDate(5, sdate);
                if(t_edate.getValue() == null)
                    cst.setNull(6, Types.DATE);
                else
                {
                    Date edate = Date.valueOf(t_edate.getValue()) ;
                    if( sdate.compareTo(edate) > 0 )
                    {
                         AlertMaker.showErrorMessage("Invalid Data Error", "End date must be after join date");
                         return ;
                    }
                    cst.setDate(6,edate );
                }
                if(assigning)
                    cst.setInt(7, 1);
                else
                    cst.setInt(7, 0);
                
                
                cst.execute() ;
                AlertMaker.showSimpleAlert("Success", "Teacher Data successfully updated");
                
                Admin_viewController controller = utilities.getA_controller() ;
                controller.loadTeacherData();
                
                
                Stage stage = (Stage) anchorpane.getScene().getWindow();
                stage.close();
                
            } catch (SQLException ex) {
                
                AlertMaker.showErrorMessage("Error in Procedure", "This teacher was assigned in this hall in this same date before");
                Logger.getLogger(Update_teacherController.class.getName()).log(Level.SEVERE, null, ex);
                return ;
            }
                         
    }
    

    @FXML
    private void cancel(ActionEvent event) {
        
        Stage stage = (Stage) anchorpane.getScene().getWindow();
        stage.close();
    }
    
    
    public void inflateUI(Teacher_Info teacher) {
        
        t_id.setText(teacher.getTid());
        t_name.setText(teacher.getTname()) ;
        t_designation.setValue(teacher.getTdesig());
        
        
        
        t_id.setEditable(false);
        t_name.setEditable(false);
        
        
        if(!teacher.getTcurrhall().equals("N/A"))  {
           
            assigning = false ;
            t_currentHall.setValue(teacher.getTcurrhall());
            t_currentRole.setValue(teacher.getTcurrrole());
            
            t_currentHall.setDisable(true);
            t_currentRole.setDisable(true);
            
            t_currentHall.setPromptText("Current Assigned Hall");
            t_currentRole.setPromptText("Current Role");
            
            String str = "SELECT DES_ADMIN_SDATE FROM DESIGNATED_HALL WHERE T_ID = '" + teacher.getTid() + "' AND DES_ADMIN_RDATE IS NULL" ;
            ResultSet res = connector.execQuery(str) ;
            Date date = null ;
            try {
                while(res.next())
                {
                   date = res.getDate(1) ;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Update_teacherController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            t_sdate.setValue(date.toLocalDate());
            t_sdate.setDisable(true);
            
        }
        
    }
}
