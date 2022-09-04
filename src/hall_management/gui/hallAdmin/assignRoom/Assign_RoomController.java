/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.gui.hallAdmin.assignRoom;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.alerts.AlertMaker;
import hall_management.gui.extras.utilities;
import hall_management.gui.hallAdmin.HallAdmin_viewController;
import hall_management.gui.hallAdmin.queries.query;
import hall_management.gui.hallAdmin.tableViewClasses.Student_Info_Hall;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author papan
 */
public class Assign_RoomController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private JFXTextField roomNo;
    @FXML
    private JFXComboBox<String> s_batch;
    @FXML
    private JFXComboBox<String> s_dept;
    @FXML
    private JFXComboBox<String> s_roll;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancButton;
    @FXML
    private Label StartDateLabel;
    @FXML
    private Label StartDateLabel1;
    @FXML
    private JFXDatePicker startDate;
    @FXML
    private JFXDatePicker endDate;
    
    private boolean inEditMode = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connector.getConnection();
        Calendar now = Calendar.getInstance() ;
        int year = now.get(Calendar.YEAR) ;
        ObservableList<String> batch_options = FXCollections.observableArrayList(
          
                  String.valueOf(year-5) ,
                  String.valueOf(year-4) ,
                  String.valueOf(year-3) ,
                  String.valueOf(year-2) ,
                  String.valueOf(year-1) ,
                  String.valueOf(year) ,
                  String.valueOf(year+1) 
          
         ) ;
         s_batch.setItems(batch_options);
          
          ObservableList<String> dept_options = FXCollections.observableArrayList(
                  "01" , 
                  "02" ,
                  "04" ,
                  "05" ,
                  "06" ,
                  "08" ,
                  "10" ,
                  "12" ,
                  "15" ,
                  "16" ,
                  "17" ,
                  "18"
          
          ) ;
          s_dept.setItems(dept_options);
          
          ObservableList<String> roll_options = FXCollections.observableArrayList() ;
          for( int i = 1 ; i <= 195 ; i++)
          {
              if( i < 10)
                  roll_options.add("00"+ i) ;
              else if( i < 99 )
                  roll_options.add("0"+i) ;
              else
                  roll_options.add(String.valueOf(i)) ;
          }
          
          s_roll.setItems(roll_options);
    }    

    @FXML
    private void save(ActionEvent event) {
        try{
            String batch = s_batch.getValue() ;
            String dept = s_dept.getValue() ;
            String roll = s_roll.getValue() ;
            String room = roomNo.getText();
            LocalDate stDate = startDate.getValue();
            LocalDate eDate  =   endDate.getValue();
            if(batch==null || dept==null || roll==null || room.isEmpty() || stDate.toString()== null)
            {
                AlertMaker.showErrorMessage("Incomplete Data Error","Please enter values in batch,dept,roll,room and stDate.");
                return;
            }
            int hid = utilities.getHid();
            String id = "S"+batch+dept+roll;
            String stmt = "SELECT * FROM STUDENTS S JOIN ASSIGNED_HALL AH "
                    +     "ON(S.S_ID = AH.S_ID) "
                    +     " WHERE AH.S_ID = '" + id + "' AND AH.H_ID = " + hid;
            ResultSet res = connector.execQuery(stmt);
            String rId = getCurrentRoom(id);
            int checkHID = checkHallIdForRoom(room);
            if(checkHID==0)
            {
                AlertMaker.showErrorMessage("Wrong Information Error","There is no room in the hall having room no. "+room);
                return;
            }
            String stmt2 = "SELECT * FROM ROOMS WHERE R_ID = '" + room +"' AND H_ID = " + hid;
            ResultSet res2 = connector.execQuery(stmt2);
            if(rId.equals(room) )
            {
                AlertMaker.showErrorMessage("Duplicate Data Error","The Student is already in room no."+rId);
                return;
            }
            while(res.next() && res2.next())
            {
                System.out.println("bal2");
                if(!(res2.getString(5).equals("YES")))
                {
                    AlertMaker.showErrorMessage("Wrong Information Error","This room is not available for allocation");
                    return;
                }
                if(res.getString(10).equals("Alumni"))
                {
                    AlertMaker.showErrorMessage("Wrong Information Error","This student has passed from BUET");
                    return;
                }
                if(res.getString(6).equals("Muslim") && res2.getString(2).equals(String.valueOf(5)) && res2.getString(4).equals("NORTH"))
                {
                    AlertMaker.showErrorMessage("Wrong Information Error","A Muslim student must not be allotted in North Block");
                    return;
                }
                if(eDate!=null && eDate.isBefore(stDate))
                {
                    AlertMaker.showErrorMessage("Wrong Information Error","End Date must be after Start Date");
                    return;
                }
                String strtDate = stDate.toString();
                String eId = getAss_Room_Ent_id();
                String enDate = null;
                if(eDate!=null)
                {
                    enDate=eDate.toString();
                }
                else
                    enDate = null;
                String stmt5 = query.assignRoom(eId, id, hid, room, strtDate, enDate);
                System.out.println(stmt5);

                if (connector.execAction(stmt5)) {
                    cancel();
                    AlertMaker.showSimpleAlert("Success", "Student assigned to Room. Information Added to Database.");

                    HallAdmin_viewController controller = utilities.getHA_controller() ;
                    controller.loadStudentData();

                } else {
                    //AlertMaker.showErrorMessage("Failed", "Teacher Id already in use.");
                }
            }                
        }catch(SQLException ex){
            Logger.getLogger(Assign_RoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancel() {
        startDate.setValue(null);
        endDate.setValue(null);
        s_batch.getSelectionModel().clearSelection();
        s_dept.getSelectionModel().clearSelection();
        s_roll.getSelectionModel().clearSelection();
        roomNo.setText(null);
    }
    
    public String getAss_Room_Ent_id() {
        String id = null;
        //HallAdmin_viewController controller2 = utilities.getHA_controller();
        //int hId = utilities.getHid();
        //String hIdInput = String.valueOf(hId);
        ResultSet res = connector.execQuery(query.getAssignCount());
        try {
            while (res.next()) {
                id = res.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Assign_RoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id ;

    }
    
    public String getCurrentRoom(String id)
    {
        String rId = null;
        ResultSet rest = connector.execQuery(query.getCurrentRoom(id));
        try{
            while(rest.next())
            {
                rId = rest.getString(1);
            }
        }catch(SQLException ex){
            Logger.getLogger(Assign_RoomController.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rId;
    }
    
    public int checkHallIdForRoom(String room)
    {
        String stmt3 = "SELECT * FROM ROOMS WHERE R_ID = '" + room + "' AND H_ID = " + utilities.getHid();
        ResultSet res3 = connector.execQuery(stmt3);
        int check=-1;
        try{
            if(res3.next())
            {
                check = 1;
            }
            else
                check=0;
        }catch(SQLException ex){
            Logger.getLogger(Assign_RoomController.class.getName()).log(Level.SEVERE,null,ex);
        }
        return check;
    }
    
    public void inflateUI(Student_Info_Hall siHall)
    {
        inEditMode = true;
        s_batch.setValue("20"+siHall.getStudentId().substring(0, 2));
        s_dept.setValue(siHall.getStudentId().substring(2, 4));
        s_roll.setValue(siHall.getStudentId().substring(4));
        
        s_batch.setEditable(false);
        s_dept.setEditable(false);
        s_roll.setEditable(false);
        
        s_batch.setDisable(true);
        s_dept.setDisable(true);
        s_roll.setDisable(true);
    }
}
