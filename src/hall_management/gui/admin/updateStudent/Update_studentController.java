/*
 * Life sucks....
 */
package hall_management.gui.admin.updateStudent;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.admin.Admin_viewController;
import hall_management.gui.admin.tableViewClasses.Student_Info;
import hall_management.gui.admin.updateTeacher.Update_teacherController;
import hall_management.gui.alerts.AlertMaker;
import hall_management.gui.extras.utilities;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author Abhik_Blaze
 */
public class Update_studentController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private JFXTextField s_id;
    @FXML
    private JFXTextField s_name;
    @FXML
    private JFXComboBox<String> s_hall;
    @FXML
    private JFXComboBox<String> s_status;
    
    boolean inserting = true ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> hall_options = FXCollections.observableArrayList() ;
        String str = "SELECT H_NAME FROM HALL" ;
        ResultSet res = connector.execQuery(str) ;
        try {
            while(res.next()) {
                
                hall_options.add(res.getString(1)) ;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Update_studentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        s_hall.setItems(hall_options);
        
        ObservableList<String> status_options = FXCollections.observableArrayList(
                
                "Running" ,
                "Alumni"
        ) ;
        
        s_status.setItems(status_options);
        
        
        // TODO
    }    

    @FXML
    private void cancel(ActionEvent event) {
        
        Stage stage = (Stage) anchorpane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void save(ActionEvent event) {
        
        String hall = s_hall.getValue() ;
        String status = s_status.getValue() ;
        String id = s_id.getText() ;
        String sgender = null ;
        String hgender = null ;
       
        
        String query = "SELECT * FROM ASSIGNED_HALL WHERE H_ID = (SELECT H_ID FROM HALL WHERE H_NAME = '" + hall + "') AND S_ID = '" + id + "'" ;
        ResultSet res = connector.execQuery(query) ;
        try {
                while (res.next()) {
                    inserting = false ;
                    AlertMaker.showErrorMessage("Invalid Data Error", "This student has already been assigned to this hall once");
                    return ;
                }
                query = "SELECT S_GENDER FROM STUDENTS WHERE S_ID = '" + id +"'" ;
                res = connector.execQuery(query) ;
                while(res.next()) {
                    sgender = res.getString(1) ;
                }
                query = "SELECT H_TYPE FROM HALL WHERE H_ID = (SELECT H_ID FROM HALL WHERE H_NAME = '" + hall + "')" ;
                res = connector.execQuery(query) ;
                while(res.next()) {
                    hgender = res.getString(1) ;
                }
                if(!sgender.equals(hgender)){
                    AlertMaker.showErrorMessage("Invalid Data Error", "Student Gender and Hall Type mismatch");
                    return ;
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(Update_studentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(inserting) {
            
            query = "INSERT INTO ASSIGNED_HALL "
                    + "VALUES( (SELECT H_ID FROM HALL WHERE H_NAME = '" + hall + "') , '" + id + "' , 'Attached' ,'YES' )" ;
            connector.execAction(query) ;
            
        }
        
        query = "UPDATE STUDENTS "
                        + "SET S_STATUS = '" + status +"'"
                        + "WHERE S_ID = '" + id + "'" ;
                
        connector.execAction(query) ;
        
        AlertMaker.showSimpleAlert("Success", "Student Data updated");
        
        Admin_viewController controller = utilities.getA_controller() ;
        controller.loadStudentData();
        Stage stage = (Stage) anchorpane.getScene().getWindow();
        stage.close();
           
        
        
        
    }
    
    public void inflateUI(Student_Info student) {
        
        s_id.setText(student.getSid());
        s_name.setText(student.getSname());
        s_id.setEditable(false);
        s_name.setEditable(false);
        
        s_hall.setValue(student.getScurrhall());
        s_status.setValue(student.getSstatus());
       
    }
}
