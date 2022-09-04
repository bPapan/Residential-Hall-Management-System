/*
 * Life sucks....
 */
package hall_management.gui.admin.addHall;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.admin.Admin_viewController;
import hall_management.gui.admin.addTeacher.Add_teacherController;
import hall_management.gui.admin.queries.query;
import hall_management.gui.admin.tableViewClasses.Hall_Info;
import hall_management.gui.alerts.AlertMaker;
import hall_management.gui.extras.utilities;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abhik_Blaze
 */
public class Add_hallController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private JFXTextField h_id;
    @FXML
    private JFXTextField h_name;
    @FXML
    private JFXComboBox<String> h_type;
    
    
    private boolean inEditMode = false ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //connector.getConnection();
        
        h_id.setEditable(false);
        h_id.setText(getHid());
        ObservableList<String> options = FXCollections.observableArrayList(
                "Male",
                "Female"
        );
        h_type.setItems(options);
        
    }    

    @FXML
    private void save(ActionEvent event) {
        
        try {
            String id = h_id.getText() ;
            String name = h_name.getText();
            String type = h_type.getValue();
            
            
            
            if ( name.isEmpty() || (type == null) || type.isEmpty() ) {
                AlertMaker.showErrorMessage("Incomplete Data Error", "Please enter values in all the fields.");
                return;
            }
            
            if( name.length() > 25 ) {
                
                AlertMaker.showErrorMessage("Name length exceeded", "Hall name should be within 25 characters.");
                return;    
            }
            
            
            String stmt = null ;
            stmt = "SELECT * FROM HALL WHERE upper(H_NAME) = '" + name.toUpperCase() + "' ORDER BY H_ID" ;
            ResultSet res = connector.execQuery(stmt) ;
            
            while(res.next())
            {
                AlertMaker.showErrorMessage("Invalid Data Error", "Another hall by this name already exists.");
                return ;
            }
            
            String stmt1 = null ;
            if( !inEditMode ){
                stmt = query.addHall(id, name, type);
                stmt1 = "INSERT INTO LOGIN VALUES('" + id + "','" + name + "','hall','1234')"  ;
                
            }
                
            else
                stmt = "UPDATE HALL\n"
                        + "SET H_NAME = '" + name + "'\n"
                        + "WHERE H_ID = " + h_id.getText() ;
            System.out.println(stmt);
            
            if (connector.execAction(stmt)) {
                reset();
                if(!inEditMode){
                    connector.execAction(stmt1) ;
                    AlertMaker.showSimpleAlert("Success", "Hall added to Database");
                }
                    
                else
                    AlertMaker.showSimpleAlert("Success", "Hall data updated");
                
                
                
                
                Admin_viewController controller = utilities.getA_controller() ;
                controller.loadHallData();
                if(inEditMode) {
                    Stage stage = (Stage) anchorpane.getScene().getWindow();
                    stage.close();
                }
                
            } else {//Error
                //AlertMaker.showErrorMessage("Failed", "Teacher Id already in use.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Add_hallController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        
        //connector.closeConnection();
        Stage stage = (Stage) anchorpane.getScene().getWindow();
        stage.close();
    }
    
    public void reset() {
        h_id.setText(getHid());
        h_name.setText(null);
        h_type.getSelectionModel().clearSelection();
       
    }

    
    public String getHid() {
        String id = null;
        ResultSet res = connector.execQuery(query.getHallCount());
        try {
            while (res.next()) {
                id = res.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Add_teacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id ;

    }
    
    public void inflateUI(Hall_Info hall)
    {
        inEditMode = true ;
        h_id.setText(hall.getHid());
        h_name.setText(hall.getHname());
        h_type.setValue(hall.getHtype());
       
        
        h_id.setEditable(false);
        h_type.setDisable(true);
        
        
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
    
}


