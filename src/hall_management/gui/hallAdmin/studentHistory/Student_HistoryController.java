/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.gui.hallAdmin.studentHistory;

import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.extras.utilities;
import hall_management.gui.hallAdmin.tableViewClasses.Std_Hist_Info_Hall;
import hall_management.gui.hallAdmin.tableViewClasses.Student_Info_Hall;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author papan
 */
public class Student_HistoryController implements Initializable {

    @FXML
    private JFXTextField stNameTxtFd;
    @FXML
    private JFXTextField stIdTextField;
    @FXML
    private TableView<Std_Hist_Info_Hall> historyTable;
    @FXML
    private TableColumn<Std_Hist_Info_Hall, String> rNoCol;
    @FXML
    private TableColumn<Std_Hist_Info_Hall, String> stDateCol;
    @FXML
    private TableColumn<Std_Hist_Info_Hall, String> eDateCol;
    @FXML
    private Label histTabLabel;
    
    public ObservableList<Std_Hist_Info_Hall> std_hist_tab = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initStdHistColumns();
    }    
    
    public void initStdHistColumns()
    {
        rNoCol.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        stDateCol.setCellValueFactory(new PropertyValueFactory<>("rmStDate"));
        eDateCol.setCellValueFactory(new PropertyValueFactory<>("rmEDate"));
    }
    
    public void inflateUI(Student_Info_Hall student) {
        
        try {
            stNameTxtFd.setText(student.getStudentName());
            stIdTextField.setText(student.getStudentId());
            
            stNameTxtFd.setEditable(false);
            stIdTextField.setEditable(false);
            
            std_hist_tab.clear() ;
            
            String query =  "SELECT ARM.R_ID, ARM.ASS_ROOM_SDATE, ARM.ASS_ROOM_EDATE\n" +
                            "FROM ASSIGNED_ROOM ARM\n" +
                            "WHERE ARM.S_ID LIKE '%" + student.getStudentId() + "%' AND ARM.R_H_ID = " + utilities.getHid() ;
            
            ResultSet res = connector.execQuery(query) ;
            while(res.next()) {
                
                String rId = res.getString(1) ;
                Date rStdate = res.getDate(2) ;
                Date rEdate = res.getDate(3) ;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy") ;
                String sDate = sdf.format(rStdate) ;
                String eDate = null ;
                if(rEdate == null) {
                    eDate = "N/A" ;
                }
                else {
                    eDate = sdf.format(rEdate) ;
                }
                
                std_hist_tab.add(new Std_Hist_Info_Hall(rId, sDate, eDate)) ;
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        historyTable.setItems(std_hist_tab);
        
    }
}
