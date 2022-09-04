/*
 * Life sucks....
 */
package hall_management.gui.student.history;

import hall_management.database.connector;
import hall_management.gui.extras.utilities;
import hall_management.gui.hallAdmin.studentHistory.Student_HistoryController;
import hall_management.gui.hallAdmin.tableViewClasses.Std_Hist_Info_Hall;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Abhik_Blaze
 */
public class HistoryController implements Initializable {

    @FXML
    private TableView<History_Info> historyTable;
    @FXML
    private TableColumn<History_Info, String> rHallCol;
    @FXML
    private TableColumn<History_Info, String> stDateCol;
    @FXML
    private TableColumn<History_Info, String> eDateCol;
    @FXML
    private TableColumn<History_Info, String> rNoCol;

    public ObservableList<History_Info> history_list = FXCollections.observableArrayList() ;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initColumns();
        loadData();
    }    
    
    public void initColumns() {
       rNoCol.setCellValueFactory(new PropertyValueFactory<>("room"));
       rHallCol.setCellValueFactory(new PropertyValueFactory<>("hall"));
       stDateCol.setCellValueFactory(new PropertyValueFactory<>("sdate"));
       eDateCol.setCellValueFactory(new PropertyValueFactory<>("edate"));
        
    }
    
    public void loadData() {
        
        try {
            history_list.clear();
            
            String query =  "SELECT (SELECT H_NAME FROM HALL WHERE H_ID = ARM.R_H_ID) ,ARM.R_ID, ARM.ASS_ROOM_SDATE, ARM.ASS_ROOM_EDATE\n" +
                    "FROM ASSIGNED_ROOM ARM\n" +
                    "WHERE ARM.S_ID = '" + utilities.getSid() + "'"  ;
            
            ResultSet res = connector.execQuery(query) ;
            while(res.next()) {
                
                String hall = res.getString(1) ;
                String room = res.getString(2) ;
                Date rStdate = res.getDate(3) ;
                Date rEdate = res.getDate(4) ;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy") ;
                String sDate = sdf.format(rStdate) ;
                String eDate = null ;
                if(rEdate == null) {
                    eDate = "On Room" ;
                }
                else {
                    eDate = sdf.format(rEdate) ;
                }
                
                history_list.add(new History_Info(hall, room, sDate, eDate)) ;
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        historyTable.setItems(history_list);
        
        
    }
    
    
    
}
