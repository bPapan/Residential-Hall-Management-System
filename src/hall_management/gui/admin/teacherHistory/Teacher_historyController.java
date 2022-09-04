/*
 * Life sucks....
 */
package hall_management.gui.admin.teacherHistory;

import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.admin.tableViewClasses.Payment_Info;
import hall_management.gui.admin.tableViewClasses.Teacher_History_Info;
import hall_management.gui.admin.tableViewClasses.Teacher_Info;
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
public class Teacher_historyController implements Initializable {

    @FXML
    private TableView<Teacher_History_Info> teacher_history_table;
    @FXML
    private TableColumn<Teacher_History_Info, String> th_hall;
    @FXML
    private TableColumn<Teacher_History_Info, String> th_role;
    @FXML
    private TableColumn<Teacher_History_Info, String> th_jdate;
    @FXML
    private TableColumn<Teacher_History_Info, String> th_rdate;
    @FXML
    private JFXTextField t_id;
    @FXML
    private JFXTextField t_name;
    
    
    public ObservableList<Teacher_History_Info> history_info_list = FXCollections.observableArrayList() ;
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initHistoryColumns();
        
    } 
    
    public void initHistoryColumns() {
        th_hall.setCellValueFactory(new PropertyValueFactory<>("thall"));
        th_role.setCellValueFactory(new PropertyValueFactory<>("trole"));
        th_jdate.setCellValueFactory(new PropertyValueFactory<>("tjdate"));
        th_rdate.setCellValueFactory(new PropertyValueFactory<>("trdate"));
    }
    
    
    public void inflateUI(Teacher_Info teacher) {
        
        try {
            t_id.setText(teacher.getTid());
            t_name.setText(teacher.getTname());
            
            t_id.setEditable(false);
            t_name.setEditable(false);
            
            history_info_list.clear() ;
            
            String query = "SELECT (SELECT H.H_NAME FROM HALL H WHERE D.H_ID = H.H_ID), D.DES_ADMIN_ROLE , D.DES_ADMIN_SDATE , D.DES_ADMIN_RDATE FROM DESIGNATED_HALL D WHERE D.T_ID = '" + teacher.getTid() + "'" ;
            
            ResultSet res = connector.execQuery(query) ;
            while(res.next()) {
                
                String thall = res.getString(1) ;
                String trole = res.getString(2) ;
                Date tsdate = res.getDate(3) ;
                Date tedate = res.getDate(4) ;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy") ;
                String tjdate = sdf.format(tsdate) ;
                String trdate = null ;
                if(tedate == null) {
                    trdate = "On Duty" ;
                }
                else {
                    trdate = sdf.format(tedate) ;
                }
                
                history_info_list.add(new Teacher_History_Info(thall, trole, tjdate, trdate)) ;
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Teacher_historyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        teacher_history_table.setItems(history_info_list);
        
    }
    
    
}
    
  
