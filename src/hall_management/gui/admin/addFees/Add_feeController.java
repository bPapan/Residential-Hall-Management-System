/*
 * Life sucks....
 */
package hall_management.gui.admin.addFees;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.admin.Admin_viewController;
import hall_management.gui.admin.addTeacher.Add_teacherController;
import hall_management.gui.admin.queries.query;
import hall_management.gui.admin.tableViewClasses.Fee_Info;
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
public class Add_feeController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private JFXTextField f_id;
    @FXML
    private JFXTextField f_name;
    @FXML
    private JFXTextField f_amount;
    @FXML
    private JFXComboBox<String> f_category;
    @FXML
    private JFXComboBox<String> f_time;

    public boolean updating = false ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        f_id.setEditable(false);
        f_id.setText(getFid());
        
        ObservableList<String> category_options = FXCollections.observableArrayList(
                
                "Resident",
                "All"
        ) ;
        f_category.setItems(category_options);
        ObservableList<String> time_options = FXCollections.observableArrayList(
                
                "Monthly",
                "Per Level",
                "Per Term" ,
                "One Time"
        ) ;
        f_time.setItems(time_options);
    }    

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) anchorpane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void save(ActionEvent event) {
        
        String id = f_id.getText();
        String name = f_name.getText()  ;
        String amount = f_amount.getText();
        String category = f_category.getValue();
        String time = f_time.getValue();
        String stmt = null ;
        
        if( name.isEmpty() || amount.isEmpty() || category == null || time == null ) {
            AlertMaker.showErrorMessage("Incomplete Data Error", "Please enter values in all the fields.");
            return;
        }
        else if(name.length()>15) {
            AlertMaker.showErrorMessage("Invalid Data Error", "Name must be within 15 characters");
            return;
        }
        else if(!isInteger(amount)) {
            AlertMaker.showErrorMessage("Invalid Data Error", "Please enter numeric value in the amount field.");
            return;
        }
        if(!updating) {
            stmt = "SELECT * FROM FEES WHERE upper(F_NAME) = '" + name.toUpperCase() + "' ORDER BY F_ID" ;
            ResultSet res = connector.execQuery(stmt) ;

            try {
                while(res.next())
                {
                    AlertMaker.showErrorMessage("Invalid Data Error", "Another fee by this name already exists.");
                    return ;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Add_feeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(!updating)
            stmt = query.addFee(id, name, amount, category, time) ;
        else
           stmt = "UPDATE FEES\n"
                  + "SET F_AMNT = " + amount + " , F_CATEGORY = '" + category + "', F_TIME = '" + time + "'\n"
                  + "WHERE F_ID = " + f_id.getText() ;
        
         if (connector.execAction(stmt)) {
                reset();
                if(!updating)
                    AlertMaker.showSimpleAlert("Success", "Fee added to Database");
                else
                    AlertMaker.showSimpleAlert("Success", "Fee data updated");
                
                
                
                
                Admin_viewController controller = utilities.getA_controller() ;
                controller.loadFeeData();
                if(updating) {
                    Stage stage = (Stage) anchorpane.getScene().getWindow();
                    stage.close();
                }
         }
        
        
       
            
        
    }
    
    public String getFid() {
        String id = null;
        ResultSet res = connector.execQuery(query.getFeeCount());
        try {
            while (res.next()) {
                id = res.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Add_teacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id ;

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
     
      public void reset() {
        f_id.setText(getFid());
        f_name.setText(null);
        f_amount.setText(null);
        f_category.getSelectionModel().clearSelection();
        f_time.getSelectionModel().clearSelection();
    }
      
      public void inflateUI(Fee_Info fee) {
          
          updating = true ;
          f_id.setText(fee.getFid());
          f_id.setEditable(false);
          f_name.setText(fee.getFname());
          f_amount.setText(fee.getFamount());
          f_category.setValue(fee.getFcategory());
          f_time.setValue(fee.getFtime()) ;
          
      }

    
}
