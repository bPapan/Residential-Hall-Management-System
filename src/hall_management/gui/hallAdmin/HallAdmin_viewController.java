/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.gui.hallAdmin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import hall_management.database.connector;
import hall_management.gui.alerts.AlertMaker;
import hall_management.gui.extras.utilities;
import hall_management.gui.hallAdmin.addSponsor.Add_SponsorController;
import hall_management.gui.hallAdmin.addSponsorship.Add_SponsorshipController;
import hall_management.gui.hallAdmin.assignRoom.Assign_RoomController;
import hall_management.gui.hallAdmin.queries.query;
import hall_management.gui.hallAdmin.studentHistory.Student_HistoryController;
import hall_management.gui.hallAdmin.tableViewClasses.Events_Info;
import hall_management.gui.hallAdmin.tableViewClasses.Rooms_Info;
import hall_management.gui.hallAdmin.tableViewClasses.SpShip_Info;
import hall_management.gui.hallAdmin.tableViewClasses.Sponsors_Info;
import hall_management.gui.hallAdmin.tableViewClasses.Staff_Info;
import hall_management.gui.hallAdmin.updateStaff.UpdateStaffController;
import hall_management.gui.hallAdmin.tableViewClasses.Student_Info_Hall;
import hall_management.gui.student.Student_viewController;
import java.io.IOException;
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
import javafx.event.Event;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author papan
 */
public class HallAdmin_viewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //String a = loginController.username.getText();
    
    public ObservableList<Staff_Info> Staff_list = FXCollections.observableArrayList();
    
    public ObservableList<String> stComboList = FXCollections.observableArrayList(
            "Staff ID",
            "Staff Name",
            "Job"
    ) ;
    
    public ObservableList<Student_Info_Hall> Student_list_hall = FXCollections.observableArrayList();
    
    public ObservableList<String> studentSearchCombo = FXCollections.observableArrayList(
            "Student ID",
            "Name",
            "Room No"
    ) ;
    
    public ObservableList<Rooms_Info>  Rooms_list_hall = FXCollections.observableArrayList();
    
    public ObservableList<String> roomSearchCombo = FXCollections.observableArrayList(
            "Room No",
            "Wing(North/West/Both)",
            "Availability(Yes/No)"
    ) ;
    
     public ObservableList<Sponsors_Info> Sponsors_list = FXCollections.observableArrayList();
    
    public ObservableList<String> sponSearchCombo = FXCollections.observableArrayList(
            "Sponsor ID",
            "Sponsor Name",
            "Manager Name"
    ) ;
    
    public ObservableList<Events_Info> Events_list = FXCollections.observableArrayList();
    
    public ObservableList<String> eveSearchCombo =FXCollections.observableArrayList(
            "Event ID",
            "Event Name"
    );
    
    public ObservableList<SpShip_Info> Spnsrship_list = FXCollections.observableArrayList();
    
    public ObservableList<String> spShipSrchCombo =FXCollections.observableArrayList(
            "Event Name",
            "Sponsor Name",
            "Contribution"
    );
    
    
    @FXML
    private JFXDrawer Drawer;
    @FXML
    private JFXHamburger Hamburger;
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private BorderPane rootBorderPane;
    @FXML
    private JFXTabPane mainTabPane;
    
    @FXML
    private StackPane rootPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Tab View_Staff;
    @FXML
    private TableColumn<Staff_Info, String> stIdCol;
    @FXML
    private TableColumn<Staff_Info, String> stNameCol;
    @FXML
    private TableColumn<Staff_Info, String> stWingCol;
    @FXML
    private TableColumn<Staff_Info, String> stJobCol;
    @FXML
    private TableColumn<Staff_Info, String> stHireDateCol;
    @FXML
    private TableColumn<Staff_Info, String> stEndDateCol;
    @FXML
    private TableView<Staff_Info> Staff_Table;
    @FXML
    private JFXComboBox<String> stSearchCombo;
    @FXML
    private Label stRecordNo;
    @FXML
    private JFXTextField stSearchString;
    @FXML
    private JFXButton stSearchButton;
    @FXML
    private JFXButton StaffRefreshButton;
    @FXML
    private JFXButton staffUpdateButton;
    @FXML
    private TableColumn<Staff_Info, String> stSalaryCol;
    @FXML
    private TableColumn<Student_Info_Hall, String> sIdCol;
    @FXML
    private TableColumn<Student_Info_Hall, String> sNameCol;
    @FXML
    private TableColumn<Student_Info_Hall, String> sTypeCol;
    @FXML
    private TableColumn<Student_Info_Hall, String> sRoomCol;
    @FXML
    private TableColumn<Student_Info_Hall, String> sStatusCol;
    @FXML
    private TableColumn<Student_Info_Hall, String> mobNoCol;
    @FXML
    private JFXComboBox<String> searchComboBox;
    @FXML
    private JFXTextField searchField;
    @FXML
    private JFXButton studentSearchButton;
    @FXML
    private JFXButton studentAssignRoom;
    @FXML
    private JFXButton viewStudentHistory;
    @FXML
    private Label attStudentLabel;
    @FXML
    private Label resStudentLabel;
    @FXML
    private JFXButton studentRefreshButton;
    @FXML
    private Tab View_Students;
    @FXML
    private TableView<Student_Info_Hall> student_Table;
    @FXML
    private TableColumn<Student_Info_Hall, String> stuWingCol;
    @FXML
    private TableColumn<Staff_Info, String> stAge;
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
    private JFXComboBox<String> roomSearchBox;
    @FXML
    private JFXTextField roomSearchString;
    @FXML
    private JFXButton roomSearchButton;
    @FXML
    private JFXButton refreshButton;
    @FXML
    private TableView<Rooms_Info> Rooms_Table;
    @FXML
    private Label avlRoomLbl;
    @FXML
    private Label roomCntLbl;
    @FXML
    private MenuItem changePassword;
   @FXML
    private AnchorPane sponAnchorPane;
    @FXML
    private TableView<Sponsors_Info> sponsTable;
    @FXML
    private TableColumn<Sponsors_Info, String> sponIdCol;
    @FXML
    private TableColumn<Sponsors_Info, String> spNameCol;
    @FXML
    private TableColumn<Sponsors_Info, String> manNameCol;
    @FXML
    private TableColumn<Sponsors_Info, String> manContactCol;
    @FXML
    private JFXButton sponSrchBtn;
    @FXML
    private JFXButton sponUpdtBtn;
    @FXML
    private JFXButton addSponBtn;
    @FXML
    private JFXButton sponRefreshBtn;
    @FXML
    private JFXComboBox<String> sponComboBox;
    @FXML
    private JFXTextField sponSrchString;
    @FXML
    private TableView<Events_Info> eventsTab;
    @FXML
    private TableColumn<Events_Info, String> eventIdCol;
    @FXML
    private TableColumn<Events_Info, String> eveNameCol;
    @FXML
    private TableColumn<Events_Info, String> eStDateCol;
    @FXML
    private TableColumn<Events_Info, String> eEnDateCol;
    @FXML
    private JFXComboBox<String> evnSearchBox;
    @FXML
    private JFXTextField evnSearchTxt;
    @FXML
    private JFXButton evnSrchBtn;
    @FXML
    private JFXButton evntRefreshBtn;
    @FXML
    private TableColumn<SpShip_Info, String> evtNmCol;
    @FXML
    private TableColumn<SpShip_Info, String> yearCol;
    @FXML
    private TableColumn<SpShip_Info, String> SponsNmCol;
    @FXML
    private TableColumn<SpShip_Info, String> ContrTypeCol;
    @FXML
    private TableColumn<SpShip_Info, String> AmntCol;
    @FXML
    private JFXButton SpShipRfrshBtn;
    @FXML
    private JFXButton spShipSrchBtn;
    @FXML
    private JFXComboBox<String> spShpSrchBox;
    @FXML
    private JFXTextField spShipSrcStr;
    @FXML
    private TableView<SpShip_Info> spShpTable;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //connector.getConnection();
        initDrawer();
        initComboBoxs();
        initStaffColumns();
        loadStaffData();
        initStudentColumns();
        loadStudentData();
        initRoomColumns();
        loadRoomsData();
        initSponsorColumns();
        loadSponsorsData();
        initEventsColumns();
        loadEventsData();
        initSponsorshipColumns();
        loadSpnsrshipTabData();
    }

    public void initStaffColumns() {
        
        stIdCol.setCellValueFactory(new PropertyValueFactory<>("stId"));
        stNameCol.setCellValueFactory(new PropertyValueFactory<>("stName"));
        stAge.setCellValueFactory(new PropertyValueFactory<>("stfAge"));
        stWingCol.setCellValueFactory(new PropertyValueFactory<>("stWing"));
        stJobCol.setCellValueFactory(new PropertyValueFactory<>("stJob"));
        stHireDateCol.setCellValueFactory(new PropertyValueFactory<>("stHireDate"));
        stEndDateCol.setCellValueFactory(new PropertyValueFactory<>("stEndDate"));
        stSalaryCol.setCellValueFactory(new PropertyValueFactory<>("stSalary"));
    }
    
    
    public void loadStaffData() {
        
        System.out.println('4'+utilities.getHid());
        Staff_list.clear();
//        HallAdmin_viewController controller = utilities.getHA_controller();
//        
//        int hId = controller.hid;
        
        ResultSet res = connector.execQuery(query.getStaffTableData(utilities.getHid())) ;
        
        try {
            while (res.next()) {
                String stId = res.getString(1);
                String stName = res.getString(2);
                String stfAge = res.getString(3);
                String stWing = res.getString(4);
                String stJob = res.getString(5);
                Date stHireDate = res.getDate(6);
                Date stEndDate = res.getDate(7);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String stJDate = sdf.format(stHireDate);
                String stFDate = null;
                String stSalary = res.getString(8);
                if(stEndDate == null)   
                    stFDate = "N/A" ;
                else
                    stFDate = sdf.format(stEndDate);
                if(stWing == null)
                    stWing = "N/A";
                //if(stEndDate == null)
                    //stEndDate = "N/A";

                Staff_list.add(new Staff_Info(stId, stName, stfAge, stWing, stJob, stJDate, stFDate, stSalary)) ;

            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        
        
       Staff_Table.setItems(Staff_list);
       stRecordNo.setText("No.of Records : "+ Staff_list.size());
        

        
    }
    
    public void initStudentColumns(){
        sIdCol.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        sNameCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        sTypeCol.setCellValueFactory(new PropertyValueFactory<>("studentType"));
        stuWingCol.setCellValueFactory(new PropertyValueFactory<>("studentRoomWing"));
        sRoomCol.setCellValueFactory(new PropertyValueFactory<>("studentRoomNo"));
        sStatusCol.setCellValueFactory(new PropertyValueFactory<>("studentStatus"));
        mobNoCol.setCellValueFactory(new PropertyValueFactory<>("studentMobileNo"));
    }
    
    public void loadStudentData(){
        Student_list_hall.clear();
        ResultSet resSt = connector.execQuery(query.getHallStudentTableData(utilities.getHid())) ;
        
        try {
            while (resSt.next()) {
                String studentId = resSt.getString(1);
                String studentName = resSt.getString(2);
                String studentType = resSt.getString(3);
                String studentRoomWing = resSt.getString(4);
                String studentRoomNo = resSt.getString(5);
                String studentStatus = resSt.getString(6);
                String studentMobileNo = resSt.getString(7);
                if(studentRoomNo == null)   
                    studentRoomNo = "N/A" ;
                if(studentRoomWing == null)
                    studentRoomWing ="N/A";

                Student_list_hall.add(new Student_Info_Hall(studentId, studentName, studentType, studentRoomWing, studentRoomNo, studentStatus, studentMobileNo)) ;

            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        student_Table.setItems(Student_list_hall);
        ResultSet resSt2 = connector.execQuery(query.getResidentCount(utilities.getHid()));
        ResultSet resSt3 = connector.execQuery(query.getAttachedCount(utilities.getHid()));
        String resCnt = null;
        String attCnt = null;
        try{
            while(resSt2.next()){
                resCnt = resSt2.getString(1);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        try{
            while(resSt3.next()){
                attCnt = resSt3.getString(1);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        attStudentLabel.setText("Attached Students: "+ attCnt);
        resStudentLabel.setText("Resident Students: "+ resCnt);
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
        ResultSet resRoom = connector.execQuery(query.getRoomsTableData(utilities.getHid())) ;
        
        try {
            while (resRoom.next()) {
                String roomId = resRoom.getString(1);
                String roomWing = resRoom.getString(2);
                String roomCapacity = resRoom.getString(3);
                String roomOccupy = resRoom.getString(4);
                String roomAvailability = resRoom.getString(5);
                if(roomWing == null)
                    roomWing ="N/A";
                //System.out.println(roomAvailability);

                Rooms_list_hall.add(new Rooms_Info(roomId, roomWing, roomCapacity, roomOccupy, roomAvailability)) ;

            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        Rooms_Table.setItems(Rooms_list_hall);
        ResultSet resRoomSrch2 = connector.execQuery(query.getAvailableRooms(utilities.getHid()));
        ResultSet resRoomSrch3 = connector.execQuery(query.getRoomsCount(utilities.getHid()));
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
    
     public void initSponsorColumns()
    {
        sponIdCol.setCellValueFactory(new PropertyValueFactory<>("spnID"));
        spNameCol.setCellValueFactory(new PropertyValueFactory<>("spnName"));
        manNameCol.setCellValueFactory(new PropertyValueFactory<>("spMName"));
        manContactCol.setCellValueFactory(new PropertyValueFactory<>("spMContact"));
    }
    
    public void loadSponsorsData()
    {
        Sponsors_list.clear();
        ResultSet resSponsor = connector.execQuery(query.loadSponsorTableData()) ;
        
        try {
            while (resSponsor.next()) {
                String spId = resSponsor.getString(1);
                String spName = resSponsor.getString(2);
                String spMnName = resSponsor.getString(3);
                String spMnCntct = resSponsor.getString(4);
                if(spMnName == null)
                    spMnName ="N/A";
                if(spMnCntct == null)
                    spMnCntct ="N/A";
                //System.out.println(roomAvailability);

                Sponsors_list.add(new Sponsors_Info(spId, spName, spMnName, spMnCntct)) ;

            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        sponsTable.setItems(Sponsors_list);
    }
    
    public void initEventsColumns()
    {
        eventIdCol.setCellValueFactory(new PropertyValueFactory<>("evId"));
        eveNameCol.setCellValueFactory(new PropertyValueFactory<>("evName"));
        eStDateCol.setCellValueFactory(new PropertyValueFactory<>("evStDate"));
        eEnDateCol.setCellValueFactory(new PropertyValueFactory<>("evEnDate"));
    }
    
    public void loadEventsData()
    {
        Events_list.clear();
        ResultSet resSponsor = connector.execQuery(query.loadEventsData(utilities.getHid())) ;
        
        try {
            while (resSponsor.next()) {
                String eId = resSponsor.getString(1);
                String eName = resSponsor.getString(2);
                Date eStDate = resSponsor.getDate(3);
                Date eEnDate = resSponsor.getDate(4);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String esDateStr = sdf.format(eStDate);
                String efDateStr = null;
                if(eEnDate == null)
                    efDateStr ="Running";
                else
                    efDateStr =sdf.format(eEnDate);
                //System.out.println(roomAvailability);

                Events_list.add(new Events_Info(eId, eName, esDateStr, efDateStr)) ;

            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        eventsTab.setItems(Events_list);
    }
    
    public void initSponsorshipColumns()
    {
        evtNmCol.setCellValueFactory(new PropertyValueFactory<>("ename"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("eyear"));
        SponsNmCol.setCellValueFactory(new PropertyValueFactory<>("sname"));
        ContrTypeCol.setCellValueFactory(new PropertyValueFactory<>("cntrbt"));
        AmntCol.setCellValueFactory(new PropertyValueFactory<>("amnt"));
    }
    
    public void loadSpnsrshipTabData()
    {
        Spnsrship_list.clear();
        ResultSet resSponsor1 = connector.execQuery(query.loadSpnsrshipData(utilities.getHid())) ;
        
        try {
            while (resSponsor1.next()) {
                String eName = resSponsor1.getString(1);
                String eYear = resSponsor1.getString(2);
                String spnName = resSponsor1.getString(3);
                String spnCntr = resSponsor1.getString(4);
                String spnAmnt = resSponsor1.getString(5);
                if(spnAmnt==null)
                    spnAmnt = "N/A";
                //System.out.println(roomAvailability);

                Spnsrship_list.add(new SpShip_Info(eName, eYear, spnName, spnCntr, spnAmnt)) ;

            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        spShpTable.setItems(Spnsrship_list);
    }
    
    
    
    public void initComboBoxs() {
        //hSearchCombo.setItems(hComboList);
        stSearchCombo.setItems(stComboList);
        if(utilities.getHid()!=5)
        {
            roomSearchCombo.remove(1);
        }
        roomSearchBox.setItems(roomSearchCombo);
        searchComboBox.setItems(studentSearchCombo);
        sponComboBox.setItems(sponSearchCombo);
        spShpSrchBox.setItems(spShipSrchCombo);
        evnSearchBox.setItems(eveSearchCombo);
        //sSearchCombo.setItems(sComboList);
        //fSearchCombo.setItems(fComboList);
        
    }
    
    public void initDrawer() {
        try {
            VBox toolbar = FXMLLoader.load(getClass().getResource("/hall_management/gui/hallAdmin/toolbar/toolbar.fxml"));
            Drawer.setSidePane(toolbar);
            Drawer.setPrefWidth(0);
            
        } catch (IOException ex) {
            Logger.getLogger(HallAdmin_viewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(Hamburger);
        task.setRate(-1);
        Hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
            task.setRate(task.getRate() * -1);
            task.play();
            //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow() ;
            
            if (Drawer.isHidden()) {
                Drawer.open();
                Drawer.setPrefWidth(200);           
            } else {
                Drawer.close();
                Drawer.setPrefWidth(0);
            }
        });
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

    @FXML
    private void handleStaffSearchButton(ActionEvent event) {
            
        String column = stSearchCombo.getValue() ;
        String str = stSearchString.getText() ;
        String query = null ;
        if( column == null || str.isEmpty() ) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please input data in all the fields");
            return ;
        }
        
        if( column.equals("Staff ID")) {
           if(!isInteger(str)) {
               JFXButton btn = new JFXButton("Okay!");
               AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please enter numeric data for this search type");
               return ;
           }
           column = "ST.ST_ID" ;          
        }
                
        else if( column.equals("Staff Name"))
           column = "upper(ST.ST_NAME)" ;
        
        else if( column.equals("Job"))
            column = "upper(ST.ST_JOB_ID)" ;
       
        String middle = null ;
        
        if( column.equals("ST.ST_ID")) {
            middle =  " LIKE '%" + str + "%'" ;
        }
        else {
            middle = " LIKE '%" + str.toUpperCase() + "%'" ;
        }
        
        String last = " AND ST.H_ID = " + utilities.getHid() ;
        
        query = "SELECT ST.ST_ID , ST.ST_NAME , ST.ST_AGE, ST.ST_WING , ST.ST_JOB_ID , ST.ST_H_DATE, ST.ST_E_DATE, ST.ST_SALARY\n" +
              "FROM STAFFS ST\n" +
              "WHERE " + column + middle + last +"\n" +
              "ORDER BY TO_NUMBER(ST.ST_ID) ASC " ; 
        
        Staff_list.clear();
        
        ResultSet res = connector.execQuery(query) ;
        
        try {
            while (res.next()) {
                String stId = res.getString(1);
                String stName = res.getString(2);
                String stfAge = res.getString(3);
                String stWing = res.getString(4);
                String stJob = res.getString(5);
                Date stHireDate = res.getDate(6);
                Date stEndDate = res.getDate(7);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String stJnDate = sdf.format(stHireDate);
                String stFnDate = null;
                String stSalary = res.getString(8);
                if(stEndDate == null)
                    stFnDate = "N/A" ;
                else
                    stFnDate = sdf.format(stEndDate);
                if(stWing==null)
                    stWing = "N/A";
                //if(stEndDate == null)
                    //stEndDate = "N/A";

                Staff_list.add(new Staff_Info(stId, stName, stfAge, stWing, stJob, stJnDate, stFnDate, stSalary)) ;

            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        
        
       Staff_Table.setItems(Staff_list);
       stRecordNo.setText("No.of Records : "+ Staff_list.size());
        
    }

    @FXML
    private void handleStaffRefreshButton(ActionEvent event) {
        loadStaffData();
    }

    @FXML
    private void handleStaffUpdateButton(ActionEvent event) {
        Staff_Info selectedForEdit = Staff_Table.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please select a row to update");
            return;
        }
        if (!selectedForEdit.getStEndDate().equals("N/A"))
        {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "You can't update the staff because he has already retired.");
            return;
        }
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hall_management/gui/hallAdmin/updateStaff/updateStaff.fxml"));
            Parent parent = loader.load();

            UpdateStaffController controller = (UpdateStaffController) loader.getController();
            
            controller.inflateUI(selectedForEdit);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Staff");
            stage.setScene(new Scene(parent));
            stage.show();
            utilities.setStageIcon(stage);
            
          
            
        } catch (IOException ex) {
            Logger.getLogger(HallAdmin_viewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleStudentSearchButton(ActionEvent event) {
        String column = searchComboBox.getValue() ;
        String str = searchField.getText() ;
        String query2 = null ;
        if( column == null || str.isEmpty() ) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please input data in all the fields");
            return ;
        }
        
        if( column.equals("Student ID")) {
           if(!isInteger(str)) {
               JFXButton btn = new JFXButton("Okay!");
               AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please enter numeric data for this search type");
               return ;
           }
           column = "S.S_ID" ;          
        }
        
        if( column.equals("Room No")) {
           if(!isInteger(str)) {
               JFXButton btn = new JFXButton("Okay!");
               AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please enter numeric data for this search type");
               return ;
           }
           column = "AR.R_ID" ;          
        }
        
        else if( column.equals("Name"))
           column = "upper(S.S_NAME)" ;
        
        String middle = null ;
        
        if( column.equals("S.S_ID") || column.equals("AR.R_ID")) {
            middle =  " LIKE '%" + str + "%' ";
        }
        else {
            middle = " LIKE '%" + str.toUpperCase() + "%'" ;
        }
        
        String last = " AND AH.H_ID = " + utilities.getHid() ;
        String str2 = null;
        
        str2 =  "SELECT SUBSTR(S.S_ID,4),S.S_NAME,AH.ASSIGNED_S_TYPE,R.R_WING,AR.R_ID,S.S_STATUS,S.S_MOBILE\n" +
                "FROM STUDENTS S,ASSIGNED_HALL AH,ASSIGNED_ROOM AR,ROOMS R\n" +
                "WHERE " + column + middle +  "AND S.S_ID = AH.S_ID AND AR.R_H_ID = AH.H_ID AND AR.S_ID=S.S_ID AND AR.R_ID = R.R_ID AND R.H_ID=AR.R_H_ID AND AH.ASSIGNED_CURRENT = 'YES'" +
                last;
        
        Student_list_hall.clear();
        
        ResultSet resStSrch = connector.execQuery(str2);
        
        try {
            while (resStSrch.next()) {
                String studentId = resStSrch.getString(1);
                String studentName = resStSrch.getString(2);
                String studentType = resStSrch.getString(3);
                String studentRoomWing = resStSrch.getString(4);
                String studentRoomNo = resStSrch.getString(5);
                String studentStatus = resStSrch.getString(6);
                String studentMobileNo = resStSrch.getString(7);
                if(studentRoomNo == null)   
                    studentRoomNo = "N/A" ;
                if(studentRoomWing == null)
                    studentRoomWing ="N/A";

                Student_list_hall.add(new Student_Info_Hall(studentId, studentName, studentType, studentRoomWing, studentRoomNo, studentStatus, studentMobileNo)) ;

            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        ResultSet resStSrch2 = connector.execQuery(query.getResidentCount(utilities.getHid()));
        ResultSet resStSrch3 = connector.execQuery(query.getAttachedCount(utilities.getHid()));
        String resCnt = null;
        String attCnt = null;
        try{
            while(resStSrch2.next()){
                resCnt = resStSrch2.getString(1);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        try{
            while(resStSrch3.next()){
                attCnt = resStSrch3.getString(1);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        attStudentLabel.setText("Attached Students: "+ attCnt);
        resStudentLabel.setText("Resident Students: "+ resCnt);
    }

    @FXML
    private void loadAssignRoom(ActionEvent event) {
        Student_Info_Hall selected = student_Table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please select a row to update");
            return;
        }
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hall_management/gui/hallAdmin/assignRoom/assign_Room.fxml"));
            Parent parent = loader.load();

            Assign_RoomController controller = (Assign_RoomController) loader.getController();
            
            controller.inflateUI(selected);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Assign Room");
            stage.setScene(new Scene(parent));
            stage.show();
            utilities.setStageIcon(stage);
            
          
            
        }catch (IOException ex) {
                Logger.getLogger(HallAdmin_viewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadStudentHistory(ActionEvent event) {
        Student_Info_Hall selectedForEdit = student_Table.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please select a row to update");
            return;
        }
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hall_management/gui/hallAdmin/studentHistory/student_History.fxml"));
            Parent parent = loader.load();

            Student_HistoryController controller = (Student_HistoryController) loader.getController();
            
            controller.inflateUI(selectedForEdit);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Student History");
            stage.setScene(new Scene(parent));
            stage.show();
            utilities.setStageIcon(stage);
            
          
            
        }catch (IOException ex) {
                Logger.getLogger(HallAdmin_viewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void studentRefresh(ActionEvent event) {
        loadStudentData();
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
        
        if(column.equals("Room No")) {
           column = "R.R_ID" ;          
        }
        
        if(utilities.getHid()==5 && column.equals("Wing(North/West/Both)"))
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
        
        String last = " AND R.H_ID = " + utilities.getHid() ;
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
        ResultSet resRoomSrch2 = connector.execQuery(query.getAvailableRooms(utilities.getHid()));
        ResultSet resRoomSrch3 = connector.execQuery(query.getRoomsCount(utilities.getHid()));
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
    private void roomRefresh(ActionEvent event) {
        loadRoomsData();
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
                Logger.getLogger(HallAdmin_viewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(yesbtn, nobtn), "Log Out", "Are you sure you want to log out?");
        return;
        
        
    }

    @FXML
    private void handleChangePassword(ActionEvent event) {
        
         utilities.loadStage(getClass().getResource("/hall_management/gui/hallAdmin/changePassword/change_pass.fxml"), "Update Password", null);
       
    }

    @FXML
    private void handleNotifications(ActionEvent event) {
         utilities.loadStage(getClass().getResource("/hall_management/gui/hallAdmin/Notifications/notifications.fxml"), "View Notifications", null);
      
        
    }

    @FXML
    private void handleSposnorSearch(ActionEvent event) {
        String column = sponComboBox.getValue() ;
        String str = sponSrchString.getText() ;
        String query = null ;
        if( column == null || str.isEmpty() ) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please input data in all the fields");
            return ;
        }
        
        if( column.equals("Sponsor ID")) {
           if(!isInteger(str)) {
               JFXButton btn = new JFXButton("Okay!");
               AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please enter numeric data for this search type");
               return ;
           }
           column = "SP.SP_ID" ;          
        }
                
        else if( column.equals("Sponsor Name"))
           column = "upper(SP.SP_NAME)" ;
        
        else if( column.equals("Manager Name"))
        {
            if(isInteger(str))
            {
               JFXButton btn = new JFXButton("Okay!");
               AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please enter non-numeric data for this search type");
               return ; 
            }
            column = "upper(SP.SP_MANAGER)" ;
        }
        
        String middle = null ;
        
        if( column.equals("SP.SP_ID")) {
            middle =  " = " + str + " " ;
        }
        else {
            middle = " LIKE '%" + str.toUpperCase() + "%'" ;
        }
        
        query = "SELECT SP.SP_ID , SP.SP_NAME , SP.SP_MANAGER, SP.SP_CONTACT \n" +
              "FROM SPONSORS SP\n" +
              "WHERE " + column + middle +"\n" +
              "ORDER BY SP.SP_ID ASC " ; 
        
        Sponsors_list.clear();
        
        ResultSet res = connector.execQuery(query) ;
        try{
            while(res.next())
            {
                String spId = res.getString(1);
                String spName = res.getString(2);
                String spMnName = res.getString(3);
                String spMnCntct = res.getString(4);
                if(spMnName == null)
                    spMnName ="N/A";
                if(spMnCntct == null)
                    spMnCntct ="N/A";
                //System.out.println(roomAvailability);

                Sponsors_list.add(new Sponsors_Info(spId, spName, spMnName, spMnCntct)) ;
            }
        }catch(SQLException ex)
        {
            System.out.println(ex);
        }
        sponsTable.setItems(Sponsors_list);
    }

    @FXML
    private void handleSponUpdate(ActionEvent event) {
        Sponsors_Info selected = sponsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please select a row to update");
            return;
        }
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hall_management/gui/hallAdmin/addSponsor/add_Sponsor.fxml"));
            Parent parent = loader.load();

            Add_SponsorController controller = (Add_SponsorController) loader.getController();
            
            controller.inflateUI(selected);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Update Sponsor");
            stage.setScene(new Scene(parent));
            stage.show();
            utilities.setStageIcon(stage);
            
          
            
        }catch (IOException ex) {
                Logger.getLogger(HallAdmin_viewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleAddSponsorship(ActionEvent event) {
        Sponsors_Info selectedForEdit = sponsTable.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please select a row to update");
            return;
        }
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hall_management/gui/hallAdmin/addSponsorship/add_Sponsorship.fxml"));
            Parent parent = loader.load();

            Add_SponsorshipController controller = (Add_SponsorshipController) loader.getController();
            
            controller.inflateUI(selectedForEdit);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Add Sponsorship");
            stage.setScene(new Scene(parent));
            stage.show();
            utilities.setStageIcon(stage);
            
          
            
        } catch (IOException ex) {
            Logger.getLogger(HallAdmin_viewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleSponRefresh(ActionEvent event) {
        loadSponsorsData();
        sponSrchString.setText(null);
        sponComboBox.setValue(null);
    }

    @FXML
    private void handleEvnSearch(ActionEvent event) {
        String column = evnSearchBox.getValue() ;
        String str = evnSearchTxt.getText() ;
        String query = null ;
        if( column == null || str.isEmpty() ) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please input data in all the fields");
            return ;
        }
        
        if( column.equals("Event ID")) {
           if(!isInteger(str)) {
               JFXButton btn = new JFXButton("Okay!");
               AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please enter numeric data for this search type");
               return ;
           }
           column = "E.E_ID" ;          
        }
                
        else if( column.equals("Event Name"))
           column = "upper(E.E_NAME)" ;
        
        String middle = null ;
        
        if( column.equals("E.E_ID")) {
            middle =  " = " + str + " " ;
        }
        else {
            middle = " LIKE '%" + str.toUpperCase() + "%'" ;
        }
        
        query = "SELECT EI.E_ID, E.E_NAME, EI.E_INFO_SDATE, EI.E_INFO_EDATE\n" +
                "FROM EVENTS E, EVENT_INFO EI\n" +
                "WHERE E.E_ID = EI.E_ID AND "
                + column + middle +" AND EI.H_ID = " + utilities.getHid() +
                " ORDER BY EI.E_ID ASC " ; 
        
        Events_list.clear();
        
        ResultSet res = connector.execQuery(query) ;
        try{
            while(res.next())
            {
                String evId = res.getString(1);
                String evName = res.getString(2);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date evDate = res.getDate(3);
                String evsDate = sdf.format(evDate);
                Date EvDate = res.getDate(4);
                String eveDate = sdf.format(EvDate);
                //System.out.println(roomAvailability);

                Events_list.add(new Events_Info(evId, evName, evsDate, eveDate)) ;
            }
        }catch(SQLException ex)
        {
            System.out.println(ex);
        }
        eventsTab.setItems(Events_list);
    }

    @FXML
    private void handleEventUpdate(ActionEvent event) {
        
    }

    @FXML
    private void handleEvntRefresh(ActionEvent event) {
        loadEventsData();
    }

    @FXML
    private void handleSpShipRfrsh(ActionEvent event) {
        loadSpnsrshipTabData();
    }

    @FXML
    private void handleSpnsrshipSearch(ActionEvent event) {
        String column = spShpSrchBox.getValue() ;
        String str = spShipSrcStr.getText() ;
        String query = null ;
        if( column == null || str.isEmpty() ) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please input data in all the fields");
            return ;
        }
                
        if( column.equals("Event Name"))
           column = "upper(E.E_NAME)" ;
        
        else if(column.equals("Sponsor Name"))
            column = "upper(S.SP_NAME)";
        
        else if(column.equals("Contribution"))
            column = "upper(SP.SPON_CMNT)";
            
        String middle = null ;
        
        middle = " LIKE '%" + str.toUpperCase() + "%'" ;
        
        query = "SELECT E.E_NAME, EXTRACT(YEAR FROM EI.E_INFO_EDATE), S.SP_NAME, SP.SPON_CMNT, SP.SPON_AMNT\n" +
                "FROM SPONSORSHIP SP, SPONSORS S, EVENTS E, EVENT_INFO EI\n" +
                "WHERE E.E_ID  = SP.E_ID AND S.SP_ID = SP.SP_ID AND EI.E_ID = E.E_ID AND EI.H_ID = SP.SP_H_ID AND SP.SP_H_ID = \n"
                + utilities.getHid() + " AND " + column + middle ; 
        
        Spnsrship_list.clear();
        
        ResultSet res = connector.execQuery(query) ;
        try{
            while(res.next())
            {
                String evName = res.getString(1);
                String evYear = res.getString(2);
                String spName = res.getString(3);
                String spCntr = res.getString(4);
                String spAmnt = res.getString(5);
                if(spAmnt==null)
                    spAmnt = "N/A";
                //System.out.println(roomAvailability);

                Spnsrship_list.add(new SpShip_Info(evName, evYear, spName, spCntr, spAmnt)) ;
            }
        }catch(SQLException ex)
        {
            System.out.println(ex);
        }
        spShpTable.setItems(Spnsrship_list);
    }
   
    
}
