/*
 * Life sucks....
 */
package hall_management.gui.admin.updatePayment;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.admin.Admin_viewController;
import hall_management.gui.admin.tableViewClasses.Payment_Info;
import hall_management.gui.alerts.AlertMaker;
import hall_management.gui.extras.utilities;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abhik_Blaze
 */
public class Update_paymentController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private JFXTextField p_sid;
    @FXML
    private JFXTextField p_month;
    @FXML
    private JFXTextField p_year;
    @FXML
    private JFXTextField p_feeamount;
    @FXML
    private JFXDatePicker p_pdate;
    @FXML
    private JFXTextField p_fine;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void calculate_fine_from_date(ActionEvent event) {
        
        try {
            LocalDate pdate = p_pdate.getValue() ;
            if( pdate == null ){
                AlertMaker.showErrorMessage("Incomplete Data Error", "Please select a valid date");       
                return;
            }
            
            String query = "SELECT GET_FINE_DATE(?,?,?,?) FROM STUDENTS_FEES" ;
            PreparedStatement pst = connector.getPST(query) ;
            pst.setString(1, p_sid.getText());
            pst.setString(2, p_month.getText());
            pst.setString(3, p_year.getText());
            pst.setDate(4, Date.valueOf(pdate));
            
            ResultSet res = pst.executeQuery() ;
            while(res.next()) {
                p_fine.setText(res.getString(1));
                return ;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Update_paymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
         Stage stage = (Stage) anchorpane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void save(ActionEvent event) {
        
        try {
            LocalDate pdate = p_pdate.getValue() ;
            if( pdate == null ){       
                AlertMaker.showErrorMessage("Incomplete Data Error", "Please select a valid date");
                return;
            }
            
            String sql = "{call CLEAR_DUES(? , ? , ?, ? )}";
            CallableStatement cst = connector.getCST(sql) ;
            
            cst.setString(1, p_sid.getText());
            cst.setString(2, p_month.getText());
            cst.setString(3, p_year.getText());
            cst.setDate(4, Date.valueOf(pdate));
            
            cst.execute() ;
            
            Admin_viewController controller = utilities.getA_controller() ;
          
            controller.loadPaymentData();
            
            AlertMaker.showSimpleAlert("Success", "Student Fee Cleared");
            Stage stage = (Stage) anchorpane.getScene().getWindow();
            stage.close();
            

            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Update_paymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }
    
    public void inflateUI(Payment_Info payment) {
        
        p_sid.setText(payment.getPsid());
        p_sid.setPromptText("Student ID");
        p_sid.setEditable(false);
        
        p_month.setText(payment.getPmonth());
        p_month.setPromptText("Month");
        p_month.setEditable(false);
                
        p_year.setText(payment.getPyear());
        p_year.setPromptText("Year");
        p_year.setEditable(false);
        
        p_feeamount.setText(payment.getPfeeamount());
        p_feeamount.setPromptText("Fee Amount");
        p_feeamount.setEditable(false);
        
        p_fine.setText(payment.getPfine() + " ( If paid today )");
        p_fine.setEditable(false);
        p_fine.setPromptText("Fine Amount");
        
       
        
        
    }
}
