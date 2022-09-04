/*
 * Life sucks....
 */
package hall_management.gui.admin.notifications;

import com.jfoenix.controls.JFXButton;
import hall_management.database.connector;
import hall_management.gui.admin.tableViewClasses.Teacher_History_Info;
import hall_management.gui.admin.teacherHistory.Teacher_historyController;
import hall_management.gui.alerts.AlertMaker;
import hall_management.gui.extras.utilities;
import hall_management.gui.hallAdmin.tableViewClasses.Notice_Info;
import hall_management.gui.hallAdmin.tableViewClasses.Staff_Info;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Abhik_Blaze
 */
public class NotificationsController implements Initializable {

    @FXML
    private TableView<Notice_Info> notice_hall;
    @FXML
    private TableColumn<Notice_Info, String> sidCol;
    @FXML
    private TableColumn<Notice_Info, String> ntypeCol;
    @FXML
    private TableColumn<Notice_Info, String> nDateCol;

    public ObservableList<Notice_Info> notifications_list = FXCollections.observableArrayList();
    @FXML
    private Label nRecordNo;
    @FXML
    private TableColumn<Notice_Info, String> nHallCol;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initNotificationsColumns();
        loadNotificationsData();
        
    }    

    @FXML
    private void handleChecked(ActionEvent event) {
        
        Notice_Info selectedForEdit = notice_hall.getSelectionModel().getSelectedItem() ;
                
        if (selectedForEdit == null) {     
            AlertMaker.showErrorMessage("Error", "Please select a row first");
            return;
        }
        String stmt = "DELETE FROM NOTICE WHERE S_ID = '" + selectedForEdit.getSid() + "'" ;
        if(connector.execAction(stmt)) {
             AlertMaker.showSimpleAlert("Success", "Notification cleared");
             notifications_list.remove(selectedForEdit) ;
             nRecordNo.setText("No. of requests : " + notifications_list.size());
        
        }
        
    }
    
    public void initNotificationsColumns() {
        
        sidCol.setCellValueFactory(new PropertyValueFactory<>("sid"));
        ntypeCol.setCellValueFactory(new PropertyValueFactory<>("ntype"));
        nHallCol.setCellValueFactory(new PropertyValueFactory<>("nhall"));
        nDateCol.setCellValueFactory(new PropertyValueFactory<>("ndate"));
        
    }
    
    public void loadNotificationsData() {
        
          
        try {
   
            notifications_list.clear() ;
            
            String query = "SELECT * FROM NOTICE\n" +
                            "WHERE ADMIN_ID = '0'" ;
            ResultSet res = connector.execQuery(query) ;
            while(res.next()) {
                
                String sid = res.getString(1) ;
                String ntype = res.getString(3) ;
                String nhall = res.getString(4) ;
                String nroom = res.getString(5) ;
                Date date = res.getDate(6) ;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy") ;
                String ndate = sdf.format(date) ;
                String trdate = null ;
                
                notifications_list.add(new Notice_Info(sid, ntype, nhall, nroom, ndate)) ;
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        notice_hall.setItems(notifications_list);
        nRecordNo.setText("No. of requests : " + notifications_list.size());
        
        
    }
    
}
