/*
 * Life sucks....
 */
package hall_management.gui.student.hallApply;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import hall_management.database.connector;
import hall_management.gui.admin.addStudent.Add_StudentController;
import hall_management.gui.alerts.AlertMaker;
import hall_management.gui.extras.utilities;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abhik_Blaze
 */
public class Change_HallController implements Initializable {

    @FXML
    private JFXComboBox<String> s_hall;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ObservableList<String> hall_options = FXCollections.observableArrayList() ;
        String str = "SELECT H_NAME FROM HALL" ;
        ResultSet res = connector.execQuery(str) ;
        try {
            while(res.next()) {
                
                hall_options.add(res.getString(1)) ;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Change_HallController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        s_hall.setItems(hall_options);
    }    

    @FXML
    private void handleApply(ActionEvent event) {
        
        try {
            String hall = s_hall.getValue() ;
            if( hall == null ){
                AlertMaker.showErrorMessage("Incomplete Data Error", "Please enter values in all the fields.");
                return ;
            }
            
            String stmt = "SELECT H_TYPE FROM HALL WHERE H_ID = (SELECT H_ID FROM HALL WHERE H_NAME = '" + hall + "')" ;
            ResultSet res = connector.execQuery(stmt) ;
            String hgender= null ;
            while(res.next()) {
                hgender = res.getString(1) ;
            }
            String sgender = null ;
            stmt = "SELECT S_GENDER FROM STUDENTS WHERE S_ID = '" + utilities.getSid() + "'" ;
            res = connector.execQuery(stmt) ;
            while(res.next()){
                sgender = res.getString(1) ;
            }
            if(!sgender.equals(hgender)){
                AlertMaker.showErrorMessage("Invalid Data Error", "Student Gender and Hall Type mismatch");
                return ;
            }
            
            stmt = "INSERT INTO NOTICE(S_ID,ADMIN_ID,N_TYPE,N_HALL)\n" +
                        "VALUES('"+utilities.getSid()+"','0','Hall Change','" + hall + "')" ;
                
            if(connector.execAction(stmt)){
                 
                AlertMaker.showSimpleAlert("Success", "Applied to admin for hall change");
                Stage stage = (Stage) s_hall.getScene().getWindow();
                stage.close();
            }
            
            
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Change_HallController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
