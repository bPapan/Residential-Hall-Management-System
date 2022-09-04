/*
 * Life sucks....
 */
package hall_management.gui.student;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.admin.Admin_viewController;
import hall_management.gui.admin.queries.query;
import hall_management.gui.admin.tableViewClasses.Payment_Info;

import hall_management.gui.alerts.AlertMaker;
import hall_management.gui.extras.utilities;
import hall_management.gui.hallAdmin.tableViewClasses.Rooms_Info;
import hall_management.gui.student.viewFeeInfo.Update_paymentController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Abhik_Blaze
 */
public class Student_viewController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private BorderPane rootBorderPane;
    @FXML
    private MenuBar menubar;
    @FXML
    private JFXTabPane mainTabPane;
    @FXML
    private ImageView pic;
    @FXML
    private Label s_id;
    @FXML
    private Label s_name;
    @FXML
    private Label s_curr_hall;
    @FXML
    private Label s_type;
    @FXML
    private Label s_curr_room;
    @FXML
    private Label s_status;
   
    @FXML
    private Tab s_infoTab;
    @FXML
    private Tab pInfoTab;
    @FXML
    private TableView<Payment_Info> payment_table;
    @FXML
    private TableColumn<Payment_Info, String> pMonthCol;
    @FXML
    private TableColumn<Payment_Info, String> pYearCol;
    @FXML
    private TableColumn<Payment_Info, String> pFeeAmntCol;
    @FXML
    private TableColumn<Payment_Info, String> pFineCol;
    @FXML
    private TableColumn<Payment_Info, String> pStatusCol;
    @FXML
    private JFXComboBox<String> pSearchCombo;
    @FXML
    private JFXTextField pSearchString;
    @FXML
    private JFXButton payment_search;
    @FXML
    private Label pRecordNo;
    @FXML
    private JFXButton payment_view;
    
    public ObservableList<Payment_Info> payment_list = FXCollections.observableArrayList() ;
    public ObservableList<String> pComboList = FXCollections.observableArrayList(
            
            "Month" ,
            "Year" 
            
     ) ;
    
    
    public ObservableList<Rooms_Info>  Rooms_list_hall = FXCollections.observableArrayList();
    
    public ObservableList<String> roomSearchCombo = FXCollections.observableArrayList(
            "Room No",
             "Wing(North/West/Both)",
            "Availability(Yes/No)"
    ) ;
    
    @FXML
    private MenuItem changePassword;
    @FXML
    private Tab rInfoTab;
    @FXML
    private JFXComboBox<String> roomSearchBox;
    @FXML
    private JFXTextField roomSearchString;
    @FXML
    private TableView<Rooms_Info> Rooms_Table;
    @FXML
    private TableColumn<Rooms_Info, String> roomNoCol;
    @FXML
    private TableColumn<Rooms_Info, String> roomWingCol;
    @FXML
    private TableColumn<Rooms_Info, String> roomCapacityCol;
    @FXML
    private TableColumn<Rooms_Info, String> roomOccupiedCol;
    @FXML
    private TableColumn<Rooms_Info, String> roomAvailabiltyCol;
    @FXML
    private Label avlRoomLbl;
    @FXML
    private Label roomCntLbl;
    @FXML
    private JFXButton refreshButton;
    @FXML
    private JFXButton roomSearchButton;
    @FXML
    private JFXButton hallChangeButton;
    @FXML
    private JFXButton residencyButton;
    @FXML
    private JFXButton roomChangeButton;
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initInfoPage();
        initComboBoxs();
        initPaymentColumns();
        loadPaymentData();
        initRoomColumns();
        loadRoomsData();
        String status = null ;
        String stmt = "SELECT S_STATUS FROM STUDENTS WHERE S_ID = '" + utilities.getSid() + "'" ;
        ResultSet res = connector.execQuery(stmt) ;
        try {
            while(res.next()) {
                status = res.getString(1) ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_viewController.class.getName()).log(Level.SEVERE, null, ex);
        }
       if(status.equals("Alumni")){
          residencyButton.setDisable(true);
          hallChangeButton.setDisable(true);
          refreshButton.setDisable(true);
          roomSearchButton.setDisable(true);
          roomChangeButton.setDisable(true);
       }
    }    

    @FXML
    private void handleLogOut(ActionEvent event) {
        JFXButton yesbtn = new JFXButton("Yes");
        JFXButton nobtn = new JFXButton("No");
        yesbtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e)->{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/hall_management/gui/login/login.fxml"));
                Parent parent = loader.load();
                
                Stage prev = (Stage)Rooms_Table.getScene().getWindow() ;
                prev.close();
                
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Hall management system");
                stage.setScene(new Scene(parent));
                stage.show();
                utilities.setStageIcon(stage);
            } catch (IOException ex) {
                Logger.getLogger(Student_viewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(yesbtn, nobtn), "Log Out", "Are you sure you want to log out?");
        return;
        
        
    }

    @FXML
    private void handleUpdateInfo(ActionEvent event) {
        utilities.loadStage(getClass().getResource("/hall_management/gui/student/updateInfo/add_Student.fxml"), "Update Info", null);
               
    }
    
    
    public void initInfoPage() {
        String id = utilities.getSid() ;
        String query = "SELECT S.S_ID , S.S_NAME , H.H_NAME , AH.ASSIGNED_S_TYPE , AR1.R_ID , S.S_STATUS , S.S_GENDER\n" +
                        "FROM STUDENTS S \n" +
                        "LEFT OUTER JOIN ASSIGNED_HALL AH\n" +
                        "ON(AH.S_ID = S.S_ID AND AH.ASSIGNED_CURRENT = 'YES')\n" +
                        "LEFT OUTER JOIN HALL H\n" +
                        "ON(AH.H_ID = H.H_ID)\n" +
                        "LEFT OUTER JOIN ASSIGNED_ROOM AR1\n" +
                        " ON( AR1.R_H_ID = AH.H_ID AND AR1.S_ID = AH.S_ID AND AR1.ASS_ROOM_EDATE IS NULL )\n" +
                        "LEFT OUTER JOIN HALL H\n" +
                        "ON(AH.H_ID = H.H_ID) WHERE S.S_ID = '" + id + "'" ;
        
        ResultSet res = connector.execQuery(query) ;
           try {
            while (res.next()) {
               
                String sid = res.getString(1) ;
                String name = res.getString(2) ;
                String hall = res.getString(3) ;
                String type = res.getString(4) ;
                String room = res.getString(5) ;
                String status = res.getString(6) ;
                if(status.equals("Alumni")) {
                    room = "N/A" ;
                    hall = "N/A" ;
                    type = "N/A" ;
                }
                if(type.equals("Attached")){
                    room = "N/A" ;
                }
                String gender = res.getString(7) ;
                if(gender.equals("Female"))
                    pic.setImage(new Image("/items/female.jpg"));
                s_id.setText(sid);
                s_name.setText(name);
                s_curr_hall.setText(hall);
                s_type.setText(type);
                s_status.setText(status);
                s_curr_room.setText(room);
                
            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
    }

    @FXML
    private void handlePaymentSearchButton(ActionEvent event) {
        
         String column = pSearchCombo.getValue() ;
        String str = pSearchString.getText() ;
        String query = null ;
        if( column == null || str.isEmpty() ) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please input data in all the fields");
            return ;
        }
        
      
                
        if( column.equals("Month"))
           column = "upper(S_F_MONTH)" ;
        
        else if( column.equals("Year"))
            column = "upper(S_F_YEAR)" ;
        
        
        String middle = null ;
        
    
         middle = "LIKE '%" + str.toUpperCase() + "%'" ;

        
        
        query = "SELECT DISTINCT S_ID , S_F_MONTH , S_F_YEAR , GET_FEE(S_ID,S_F_MONTH,S_F_YEAR) , GET_FINE(S_ID, S_F_MONTH,S_F_YEAR) , S_F_PDATE\n" +
                "FROM STUDENTS_FEES WHERE S_ID = '"+ utilities.getSid() +"' AND "  + column + " " + middle;
        
         payment_list.clear();
        
        ResultSet res = connector.execQuery(query) ;
        
        try {
            while (res.next()) {
                String psid = res.getString(1);
                String pmonth = res.getString(2);
                String pyear = res.getString(3);
                String pfeeamount = res.getString(4);
                String pfine = res.getString(5);
                Date date = res.getDate(6) ;
                String pstatus = null ;
                if(date == null)
                    pstatus = "Pending" ;
                else
                    pstatus = "Cleared" ;
                
                
                payment_list.add(new Payment_Info(psid, pmonth, pyear, pfeeamount, pfine, pstatus)) ;
            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        
       payment_table.setItems(payment_list);
       pRecordNo.setText("No.of Records : "+ payment_list.size());
        
        
        
    }

    @FXML
    private void handlePaymentViewButton(ActionEvent event) {
        
        Payment_Info selectedForEdit = payment_table.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please select a row to update");
            return;
        }
        else if(selectedForEdit.getPstatus().equals("Cleared")) {
            
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Fee has already been cleared. Nothing more to see.");
            return;
        }
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hall_management/gui/student/viewFeeInfo/update_payment.fxml"));
            Parent parent = loader.load();

            Update_paymentController controller = (Update_paymentController) loader.getController();
            
            controller.inflateUI(selectedForEdit);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("View Full Info");
            stage.setScene(new Scene(parent));
            stage.show();
            utilities.setStageIcon(stage);
            
          
            
        } catch (IOException ex) {
            Logger.getLogger(Student_viewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void initComboBoxs() {
        pSearchCombo.setItems(pComboList);
        roomSearchBox.setItems(roomSearchCombo);
    }
    
    public void initPaymentColumns() {
        pMonthCol.setCellValueFactory(new PropertyValueFactory<>("pmonth"));
        pYearCol.setCellValueFactory(new PropertyValueFactory<>("pyear"));
        pFeeAmntCol.setCellValueFactory(new PropertyValueFactory<>("pfeeamount"));
        pFineCol.setCellValueFactory(new PropertyValueFactory<>("pfine"));
        pStatusCol.setCellValueFactory(new PropertyValueFactory<>("pstatus"));
    }
    
    
     public void loadPaymentData() {
        
        payment_list.clear();
        
        String stmt = "SELECT DISTINCT S_ID , S_F_MONTH , S_F_YEAR , GET_FEE(S_ID,S_F_MONTH,S_F_YEAR) , GET_FINE(S_ID, S_F_MONTH,S_F_YEAR) , S_F_PDATE\n" +
                        "FROM STUDENTS_FEES WHERE S_ID = '" + utilities.getSid() + "'" ;
        
        
        
        ResultSet res = connector.execQuery(stmt) ;
        
        try {
            while (res.next()) {
                String psid = res.getString(1);
                String pmonth = res.getString(2);
                String pyear = res.getString(3);
                String pfeeamount = res.getString(4);
                String pfine = res.getString(5);
                Date date = res.getDate(6) ;
                String pstatus = null ;
                if(date == null)
                    pstatus = "Pending" ;
                else
                    pstatus = "Cleared" ;
                
                payment_list.add(new Payment_Info(psid, pmonth, pyear, pfeeamount, pfine, pstatus)) ;
            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        
       payment_table.setItems(payment_list);
       pRecordNo.setText("No.of Records : "+ payment_list.size());
        
    }

    @FXML
    private void handleChangePassword(ActionEvent event) {
        
        utilities.loadStage(getClass().getResource("/hall_management/gui/student/updatePassword/change_pass.fxml"), "Update Password", null);
        
    }

    @FXML
    private void handleRoomSearchButton(ActionEvent event) {
        String column = roomSearchBox.getValue() ;
        String str = roomSearchString.getText() ;
        String query2 = null ;
        if( column == null || str.isEmpty() ) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please input data in all the fields");
            return ;
        }
        
        
        String stmt = "SELECT AH.H_ID\n" +
                    "FROM ASSIGNED_HALL AH\n" +
                    "WHERE AH.S_ID = '" + utilities.getSid() + "' AND AH.ASSIGNED_CURRENT = 'YES'" ;
        ResultSet res = connector.execQuery(stmt) ;
        String hid = null ;
        try {
            while(res.next()) {
                hid = res.getString(1) ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_viewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
        
        
        
        if(column.equals("Room No")) {
           column = "R.R_ID" ;          
        }
        
        if( Integer.parseInt(hid)==5 && column.equals("Wing(North/West/Both)"))
        {
            if(!(str.toUpperCase().equals("NORTH")||str.toUpperCase().equals("WEST")||str.toUpperCase().equals("BOTH")))
            {
               JFXButton btn = new JFXButton("Okay!");
               AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please type 'North' or 'West' or 'Both' if you want to search based on wing.");
               return ; 
            }
            column = "R.R_WING" ; 
        }
        
        if(column.equals("Availability(Yes/No)"))
        {
            if(!(str.toUpperCase().equals("YES")||str.toUpperCase().equals("NO")))
            {
               JFXButton btn = new JFXButton("Okay!");
               AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please type 'Yes' or 'No' if you want to search based on room availability.");
               return ; 
            }
            column = "R.R_AVAILABILITY" ;
        }
        
        String middle = null ;
        
        if( column.equals("R.R_ID")) {
            middle =  " LIKE '%" + str + "%' ";
        }
        else {
            middle = " = '" + str.toUpperCase() + "'" ;
        }
        
        String last = " AND R.H_ID = " + hid ;
        String str3 = null;
        
        str3 =  "SELECT R.R_ID, R.R_WING, R.R_CAPACITY, GET_OCCUPIED_SEATS_CNT(R.R_ID, R.H_ID), R.R_AVAILABILITY\n" +
                "FROM ROOMS R\n" +
                "WHERE " + column + middle +last ;
        
        Rooms_list_hall.clear();
        
        ResultSet resRoomSrch = connector.execQuery(str3);
        
        try {
            while (resRoomSrch.next()) {
                String roomId = resRoomSrch.getString(1);
                String roomWing = resRoomSrch.getString(2);
                String roomCapacity = resRoomSrch.getString(3);
                String roomOccupy = resRoomSrch.getString(4);
                String roomAvl = resRoomSrch.getString(5);
                if(roomWing == null)
                    roomWing ="N/A";

                Rooms_list_hall.add(new Rooms_Info(roomId, roomWing, roomCapacity, roomOccupy, roomAvl)) ;

            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        ResultSet resRoomSrch2 = connector.execQuery(hall_management.gui.hallAdmin.queries.query.getAvailableRooms(Integer.parseInt(hid)));
        ResultSet resRoomSrch3 = connector.execQuery(hall_management.gui.hallAdmin.queries.query.getRoomsCount(Integer.parseInt(hid)));
        String roomCnt = null;
        String avlCnt = null;
        try{
            while(resRoomSrch2.next()){
                roomCnt = resRoomSrch2.getString(1);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        try{
            while(resRoomSrch3.next()){
                avlCnt = resRoomSrch3.getString(1);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        roomCntLbl.setText("Total Rooms : "+ roomCnt);
        avlRoomLbl.setText("Available Rooms : "+ avlCnt);
    }

    @FXML
    private void hallChangeApply(ActionEvent event) {
        
        JFXButton yesbtn = new JFXButton("Yes");
        JFXButton nobtn = new JFXButton("No");
        yesbtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e)->{
            try {
                String admin_id = null ;
                String stmt = "SELECT AH.ASSIGNED_S_TYPE, AH.H_ID\n" +
                        "FROM ASSIGNED_HALL AH\n" +
                        "WHERE AH.S_ID = '" + utilities.getSid() + "' AND AH.ASSIGNED_CURRENT = 'YES'" ;
                ResultSet res = connector.execQuery(stmt) ;
                String status = null ;
                while(res.next()) {
                    status = res.getString(1) ;
                    admin_id = res.getString(2) ;
                }
                if(status.equals("Resident")) {
                    JFXButton btn = new JFXButton("Okay!");
                    AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "Error", "You need to cancel residency before you can apply for changing hall");
                    return;
                }
                
                stmt = "SELECT * FROM NOTICE WHERE S_ID = '" + utilities.getSid() + "'" ;
                res = connector.execQuery(stmt) ;
                while(res.next()) {
                    JFXButton btn = new JFXButton("Okay!");
                    AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "Error", "You already have a request pendiing.");
                    return;
                }
                
                
             utilities.loadStage(getClass().getResource("/hall_management/gui/student/hallApply/change_Hall.fxml"), "Request for hall", null);
        
                
                
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Student_viewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
        });
        AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(yesbtn, nobtn), "Warning", "Are you sure you want to apply to change hall?");
        return;
        
        
        
    }

    @FXML
    private void handleCancelResidency(ActionEvent event) {
        
        
        
        
        JFXButton yesbtn = new JFXButton("Yes");
        JFXButton nobtn = new JFXButton("No");
        yesbtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e)->{
            try {
                String admin_id = null ;
                String stmt = "SELECT AH.ASSIGNED_S_TYPE, AH.H_ID\n" +
                        "FROM ASSIGNED_HALL AH\n" +
                        "WHERE AH.S_ID = '" + utilities.getSid() + "' AND AH.ASSIGNED_CURRENT = 'YES'" ;
                ResultSet res = connector.execQuery(stmt) ;
                String status = null ;
                while(res.next()) {
                    status = res.getString(1) ;
                    admin_id = res.getString(2) ;
                }
                if(status.equals("Attached")) {
                    JFXButton btn = new JFXButton("Okay!");
                    AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "Error", "You are already attached!");
                    return;
                }
                
                stmt = "SELECT * FROM NOTICE WHERE S_ID = '" + utilities.getSid() + "'" ;
                res = connector.execQuery(stmt) ;
                while(res.next()) {
                    JFXButton btn = new JFXButton("Okay!");
                    AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "Error", "You already have a request pendiing.");
                    return;
                }
                
                stmt = "INSERT INTO NOTICE(S_ID,ADMIN_ID,N_TYPE)\n" +
                        "VALUES('"+utilities.getSid()+"','" +admin_id+"','Cancel Residency')" ;
                
                if(connector.execAction(stmt)){
                    JFXButton btn = new JFXButton("Okay!");
                    AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "Success", "Applied to hall admin for canceling residency");
                    return;
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Student_viewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(yesbtn, nobtn), "Warning", "Are you sure you want to apply to cancel residency?");
        return;
        
        
        
    }

    @FXML
    private void handleRoomApply(ActionEvent event) {
        
        
        JFXButton yesbtn = new JFXButton("Yes");
        JFXButton nobtn = new JFXButton("No");
        yesbtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e)->{
            
            
            
        Rooms_Info selectedForEdit = Rooms_Table.getSelectionModel().getSelectedItem() ;
        if (selectedForEdit == null) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please select a row first");
            return;
        }
        
        if( !selectedForEdit.getAvailable().equals("YES")) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Cant apply for a room that is full");
            return;
        }
        
        String stmt = "SELECT AH.H_ID\n" +
                    "FROM ASSIGNED_HALL AH\n" +
                    "WHERE AH.S_ID = '" + utilities.getSid() + "' AND AH.ASSIGNED_CURRENT = 'YES'" ;
        ResultSet res = connector.execQuery(stmt) ;
        String hid = null ;
        try {
            while(res.next()) {
                hid = res.getString(1) ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_viewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         stmt = "SELECT * FROM NOTICE WHERE S_ID = '" + utilities.getSid() + "'" ;
         res = connector.execQuery(stmt) ;
            try {
                while(res.next()) {
                    JFXButton btn = new JFXButton("Okay!");
                    AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "Error", "You already have a request pendiing.");
                    return;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Student_viewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
          stmt = "INSERT INTO NOTICE(S_ID,ADMIN_ID,N_TYPE,N_ROOM)\n" +
                    "VALUES('"+utilities.getSid()+"','" + hid +"','Room Application','" + selectedForEdit.getRoomId()+ "')" ; 
            
            if(connector.execAction(stmt)){
                JFXButton btn = new JFXButton("Okay!");
                 AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "Success", "Applied to hall admin for room");
                 return;
            }
           
        
        });
        AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(yesbtn, nobtn), "Warning", "Are you sure you want to apply to change hall?");
        return;     
        
    }

    @FXML
    private void roomRefresh(ActionEvent event) {
        
        loadRoomsData();
    }

    @FXML
    private void roomHistory(ActionEvent event) {
        
        utilities.loadStage(getClass().getResource("/hall_management/gui/student/history/history.fxml"), "My room history", null);
        
        
    }
    
    
    
     public void initRoomColumns()
    {
        roomNoCol.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        roomWingCol.setCellValueFactory(new PropertyValueFactory<>("roomWing"));
        roomCapacityCol.setCellValueFactory(new PropertyValueFactory<>("roomCapacity"));
        roomOccupiedCol.setCellValueFactory(new PropertyValueFactory<>("roomOccupy"));
        roomAvailabiltyCol.setCellValueFactory(new PropertyValueFactory<>("available"));
    }
    
    public void loadRoomsData()
    {
        Rooms_list_hall.clear();
        
        String stmt = "SELECT AH.H_ID\n" +
                    "FROM ASSIGNED_HALL AH\n" +
                    "WHERE AH.S_ID = '" + utilities.getSid() + "' AND AH.ASSIGNED_CURRENT = 'YES'" ;
        ResultSet res = connector.execQuery(stmt) ;
        String hid = null ;
        try {
            while(res.next()) {
                hid = res.getString(1) ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_viewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
        
        ResultSet resRoom = connector.execQuery(hall_management.gui.hallAdmin.queries.query.getRoomsTableData(Integer.parseInt(hid))) ;
        
        try {
            while (resRoom.next()) {
                String roomId = resRoom.getString(1);
                String roomWing = resRoom.getString(2);
                String roomCapacity = resRoom.getString(3);
                String roomOccupy = resRoom.getString(4);
                String roomAvailability = resRoom.getString(5);
                if(roomWing == null)
                    roomWing ="N/A";
                System.out.println(roomAvailability);

                Rooms_list_hall.add(new Rooms_Info(roomId, roomWing, roomCapacity, roomOccupy, roomAvailability)) ;

            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        Rooms_Table.setItems(Rooms_list_hall);
        ResultSet resRoomSrch2 = connector.execQuery(hall_management.gui.hallAdmin.queries.query.getAvailableRooms(Integer.parseInt(hid)));
        ResultSet resRoomSrch3 = connector.execQuery(hall_management.gui.hallAdmin.queries.query.getRoomsCount(Integer.parseInt(hid)));
        String roomCnt = null;
        String avlCnt = null;
        try{
            while(resRoomSrch2.next()){
                roomCnt = resRoomSrch2.getString(1);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        try{
            while(resRoomSrch3.next()){
                avlCnt = resRoomSrch3.getString(1);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        roomCntLbl.setText("Total Rooms : "+ roomCnt);
        avlRoomLbl.setText("Available Rooms : "+ avlCnt);
    }
    
    
    
}
