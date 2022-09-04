/*
 * Life sucks....
 */
package hall_management.gui.admin.setupFees;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import hall_management.database.connector;
import hall_management.gui.admin.Admin_viewController;
import hall_management.gui.admin.queries.query;
import hall_management.gui.admin.tableViewClasses.Fee_Info;
import hall_management.gui.alerts.AlertMaker;
import hall_management.gui.extras.utilities;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abhik_Blaze
 */

public class Setup_feeController implements Initializable {

    
    public ObservableList<Fee_Info> fee_list = FXCollections.observableArrayList();
    
    
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private JFXComboBox<String> f_month;
    @FXML
    private JFXComboBox<String> f_year;
    @FXML
    private TableView<Fee_Info> fee_table;
    @FXML
    private TableColumn<Fee_Info, String> fidCol;
    @FXML
    private TableColumn<Fee_Info, String> fnameCol;
    @FXML
    private TableColumn<Fee_Info, String> famountCol;
    @FXML
    private TableColumn<Fee_Info, String> fcategoryCol;
    @FXML
    private TableColumn<Fee_Info, String> ftimeCol;
    @FXML
    private JFXDatePicker f_ldate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initFeeColumns();
        loadFeeData();
        ObservableList<String> month_options = FXCollections.observableArrayList(
                
                "January",
                "February" ,
                "March" ,
                "April" ,
                "May" ,
                "June" ,
                "July" ,
                "August" ,
                "September" ,
                "October" ,
                "November" ,
                "December"
        ) ;
        f_month.setItems(month_options);
        
         Calendar now = Calendar.getInstance() ;
         int year = now.get(Calendar.YEAR) ;
          ObservableList<String> year_options = FXCollections.observableArrayList(
          
                  String.valueOf(year-2) ,
                  String.valueOf(year-1) ,
                  String.valueOf(year) 
                   
          
          ) ;
          
          f_year.setItems(year_options);
          fee_table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); ;
          
        
        // TODO
    }    

    @FXML
    private void save(ActionEvent event) {
        
        ObservableList<Fee_Info> fees = fee_table.getSelectionModel().getSelectedItems() ;
        String month = f_month.getValue() ;
        String year = f_year.getValue() ;
        LocalDate date = f_ldate.getValue() ;
      
        if(date == null || month == null || year == null) {
             AlertMaker.showErrorMessage("Incomplete Data Error", "Please enter values in all the fields.");
             return ;
        }
        
        else if(fees.size() == 0) {
            
             AlertMaker.showErrorMessage("Incomplete Data Error", "Please select the fees applicable for this month.");
             return ;
            
        }
        
        else if(!String.valueOf(date.getYear()).equals(year)  ) {
            
             AlertMaker.showErrorMessage("Invalid Data Error", "Fee year and payment date year mismatch");
             return ;
        }
        
        String sql = "SELECT * FROM STUDENTS_FEES WHERE S_F_MONTH = '" + month + "' AND S_F_YEAR = '" + year + "'" ;
        ResultSet res = connector.execQuery(sql) ;
        try {
            while(res.next()) {
                
                 AlertMaker.showErrorMessage("Invalid Data Error", "Fee Data for this month and year has already been added");
                  return ;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Setup_feeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         sql = "{call SET_UP_FEES(? , ? , ?, ? , ? )}" ;
         CallableStatement cst = connector.getCST(sql) ;
         
         for( Fee_Info fee : fees ){
            
            try {
                cst.setInt(1, Integer.parseInt(fee.getFid()));
                cst.setString(2, fee.getFcategory());
                cst.setString(3,month) ;
                cst.setString(4,year) ;
                cst.setDate(5, Date.valueOf(date));
                
                cst.execute() ;
                
            } catch (SQLException ex) {
                AlertMaker.showErrorMessage("Procedure error", "Error in set up fees procedure");
                Logger.getLogger(Setup_feeController.class.getName()).log(Level.SEVERE, null, ex);
                return ;
            }
         }
        AlertMaker.showSimpleAlert("Success", "Fees successfully set up");
        
        Admin_viewController controller = utilities.getA_controller() ;
        controller.loadPaymentData();
        Stage stage = (Stage) anchorpane.getScene().getWindow();
        stage.close();
        
        
        
    }

    @FXML
    private void cancel(ActionEvent event) {
        
         Stage stage = (Stage) anchorpane.getScene().getWindow();
        stage.close();
    }
    
     public void initFeeColumns() {
        
        fidCol.setCellValueFactory(new PropertyValueFactory<>("fid"));
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        famountCol.setCellValueFactory(new PropertyValueFactory<>("famount"));
        fcategoryCol.setCellValueFactory(new PropertyValueFactory<>("fcategory"));
        ftimeCol.setCellValueFactory(new PropertyValueFactory<>("ftime"));
        
    }
     
     public void loadFeeData() {
        
        fee_list.clear();
        
        ResultSet res = connector.execQuery(query.getFeeTableData()) ;
        
        try {
            while (res.next()) {
                String fid = res.getString(1);
                String fname = res.getString(2);
                String famount = res.getString(3);
                String fcategory = res.getString(4);
                String ftime = res.getString(5);
                
                fee_list.add(new Fee_Info(fid, fname, famount, fcategory, ftime)) ;

            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        
        
        fee_table.setItems(fee_list);
       // fRecordNo.setText("No.of Records : "+ fee_list.size());

        
    }
    
    
}
