/*
 * Life sucks....
 */
package hall_management.gui.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import hall_management.database.connector;
import hall_management.gui.admin.addFees.Add_feeController;
import hall_management.gui.admin.addHall.Add_hallController;
import hall_management.gui.admin.queries.query;
import hall_management.gui.admin.tableViewClasses.Fee_Info;
import hall_management.gui.admin.tableViewClasses.Hall_Info;
import hall_management.gui.admin.tableViewClasses.Payment_Info;
import hall_management.gui.admin.tableViewClasses.Student_Info;
import hall_management.gui.admin.tableViewClasses.Teacher_Info;
import hall_management.gui.admin.teacherHistory.Teacher_historyController;
import hall_management.gui.admin.updatePayment.Update_paymentController;
import hall_management.gui.admin.updateStudent.Update_studentController;
import hall_management.gui.admin.updateTeacher.Update_teacherController;
import hall_management.gui.alerts.AlertMaker;
import hall_management.gui.extras.utilities;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
 * @author Abhik_Blaze
 */
public class Admin_viewController implements Initializable {

    //observable lists for tableview datas
    public ObservableList<Hall_Info> hall_list = FXCollections.observableArrayList();
    public ObservableList<Teacher_Info> teacher_list = FXCollections.observableArrayList();
    public ObservableList<Fee_Info> fee_list = FXCollections.observableArrayList();
    public ObservableList<Student_Info> student_list = FXCollections.observableArrayList();
    public ObservableList<Payment_Info> payment_list = FXCollections.observableArrayList() ;
     
   
    
    
    
    //observable lists for combo box-s
    public ObservableList<String> hComboList = FXCollections.observableArrayList(
            "ID",
            "Name",
            "Type"
    ) ;
    
    public ObservableList<String> tComboList = FXCollections.observableArrayList(
            "ID",
            "Name",
            "Designation",
            "Current Hall",
            "Current Role"
    ) ;
    
    public ObservableList<String> sComboList = FXCollections.observableArrayList(
            
            "ID" , 
            "Name" ,
            "Current Hall" ,
            "Type" ,
            "Status"
            
            
     ) ;
    
     public ObservableList<String> fComboList = FXCollections.observableArrayList(
            
            "ID" , 
            "Name" ,
            "Category" ,
            "Time"
            
     ) ;
    
     public ObservableList<String> pComboList = FXCollections.observableArrayList(
            
            "ID" , 
            "Month" ,
            "Year" 
            
     ) ;
        
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
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
    private Tab h_infoTab;
    @FXML
    private TableView<Hall_Info> hall_table;
    @FXML
    private TableColumn<Hall_Info, String> hidCol;
    @FXML
    private TableColumn<Hall_Info, String> hnameCol;
    @FXML
    private TableColumn<Hall_Info, String> htypeCol;
    @FXML
    private TableColumn<Hall_Info, String> hcapacityCol;
    @FXML
    private TableColumn<Hall_Info, String> hoccupiedCol;
    @FXML
    private TableColumn<Hall_Info, String> hattached;
    @FXML
    private Tab t_infoTab;
    @FXML
    private TableView<Teacher_Info> teacher_table;
    @FXML
    private TableColumn<Teacher_Info, String> tidCol;
    @FXML
    private TableColumn<Teacher_Info, String> tnameCol;
    @FXML
    private TableColumn<Teacher_Info, String> tdesigCol;
    @FXML
    private TableColumn<Teacher_Info, String> tcurrhallCol;
    @FXML
    private TableColumn<Teacher_Info, String> tcurrroleCol;
    @FXML
    private Tab f_infoTab;
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
    private Tab s_infoTab;
    @FXML
    private TableView<Student_Info> student_table;
    @FXML
    private TableColumn<Student_Info, String> sidCol;
    @FXML
    private TableColumn<Student_Info, String> snameCol;
    @FXML
    private TableColumn<Student_Info, String> scurrhallCol;
    @FXML
    private TableColumn<Student_Info, String> sstatusCol;
    @FXML
    private TableColumn<Student_Info, String> stypeCol;
    @FXML
    private JFXButton hall_refresh;
    @FXML
    private JFXButton hall_update;
    @FXML
    private JFXComboBox<String> hSearchCombo;
    @FXML
    private JFXTextField hSearchString;
    @FXML
    private JFXButton hall_Search;
    @FXML
    private JFXButton teacher_update;
    @FXML
    private JFXComboBox<String> tSearchCombo;
    @FXML
    private JFXTextField tSearchString;
    @FXML
    private JFXButton teacher_Search1;
    @FXML
    private JFXButton teacher_refresh;
    @FXML
    private Label hRecordNo;
    @FXML
    private Label sRecordNo;
    @FXML
    private Label tRecordNo;
    @FXML
    private Label fRecordNo;
    @FXML
    private JFXButton student_refresh;
    @FXML
    private JFXButton student_update1;
    @FXML
    private JFXComboBox<String> sSearchCombo;
    @FXML
    private JFXTextField sSearchString;
    @FXML
    private JFXButton student_Search;
    @FXML
    private JFXButton fee_update;
    @FXML
    private JFXComboBox<String> fSearchCombo;
    @FXML
    private JFXTextField fSearchString;
    @FXML
    private JFXButton fee_Search;
    @FXML
    private JFXButton fee_refresh;
    @FXML
    private Tab p_infoTab;
    @FXML
    private TableView<Payment_Info> payment_table;
    @FXML
    private TableColumn<Payment_Info, String> pSidCol;
    @FXML
    private TableColumn<Payment_Info, String> pMonthCol;
    @FXML
    private TableColumn<Payment_Info, String> pYearCol;
    @FXML
    private TableColumn<Payment_Info, String> pStatusCol;
    @FXML
    private JFXButton payment_update;
    @FXML
    private JFXComboBox<String> pSearchCombo;
    @FXML
    private JFXTextField pSearchString;
    @FXML
    private JFXButton payment_search;
    @FXML
    private JFXButton payment_refresh;
    @FXML
    private Label pRecordNo;
    @FXML
    private TableColumn<Payment_Info, String> pFeeAmntCol;
    @FXML
    private TableColumn<Payment_Info, String> pFineCol;
    @FXML
    private JFXButton teacher_history;
    @FXML
    private MenuItem changePassword;
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //connector.getConnection() ;
        
        initComboBoxs();
        initDrawer();
        //setting hall_info table columns and loading from database
        initHallColumns();
        initTeacherColumns();
        initFeeColumns();
        initStudentColumns();
        initPaymentColumns();
        loadHallData();
        loadTeacherData();
        loadFeeData();
        loadStudentData();
        loadPaymentData();
        
        

    }
    
    public void initHallColumns() {
   
        hidCol.setCellValueFactory(new PropertyValueFactory<>("hid"));
        hnameCol.setCellValueFactory(new PropertyValueFactory<>("hname"));
        htypeCol.setCellValueFactory(new PropertyValueFactory<>("htype"));
        hcapacityCol.setCellValueFactory(new PropertyValueFactory<>("hcapacity"));
        hoccupiedCol.setCellValueFactory(new PropertyValueFactory<>("hoccupied"));
        hattached.setCellValueFactory(new PropertyValueFactory<>("hattached"));
  
    }
    
    public void initTeacherColumns() {
        
        tidCol.setCellValueFactory(new PropertyValueFactory<>("tid"));
        tnameCol.setCellValueFactory(new PropertyValueFactory<>("tname"));
        tdesigCol.setCellValueFactory(new PropertyValueFactory<>("tdesig"));
        tcurrhallCol.setCellValueFactory(new PropertyValueFactory<>("tcurrhall"));
        tcurrroleCol.setCellValueFactory(new PropertyValueFactory<>("tcurrrole"));
        
    }
    
    public void initFeeColumns() {
        
        fidCol.setCellValueFactory(new PropertyValueFactory<>("fid"));
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        famountCol.setCellValueFactory(new PropertyValueFactory<>("famount"));
        fcategoryCol.setCellValueFactory(new PropertyValueFactory<>("fcategory"));
        ftimeCol.setCellValueFactory(new PropertyValueFactory<>("ftime"));
        
    }
    
    public void initStudentColumns() {
        
        sidCol.setCellValueFactory(new PropertyValueFactory<>("sid"));
        snameCol.setCellValueFactory(new PropertyValueFactory<>("sname"));
        scurrhallCol.setCellValueFactory(new PropertyValueFactory<>("scurrhall"));
        sstatusCol.setCellValueFactory(new PropertyValueFactory<>("sstatus"));
        stypeCol.setCellValueFactory(new PropertyValueFactory<>("stype"));
    
    }
    
    public void initPaymentColumns() {
        pSidCol.setCellValueFactory(new PropertyValueFactory<>("psid"));
        pMonthCol.setCellValueFactory(new PropertyValueFactory<>("pmonth"));
        pYearCol.setCellValueFactory(new PropertyValueFactory<>("pyear"));
        pFeeAmntCol.setCellValueFactory(new PropertyValueFactory<>("pfeeamount"));
        pFineCol.setCellValueFactory(new PropertyValueFactory<>("pfine"));
        pStatusCol.setCellValueFactory(new PropertyValueFactory<>("pstatus"));
    }
    
    public void loadHallData() {
        
        hall_list.clear();
        
        ResultSet res = connector.execQuery(query.getHallTableData()) ;
        
        try {
            while (res.next()) {
                String hid = res.getString(1);
                String hname = res.getString(2);
                String htype = res.getString(3);
                String hcapacity = res.getString(4);
                String hoccupied = res.getString(5);
                String hattached = res.getString(6) ;
                

                hall_list.add(new Hall_Info(hid, hname, htype, hcapacity, hoccupied, hattached));

            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        
        
        hall_table.setItems(hall_list);
        hRecordNo.setText("No.of Records : "+ hall_list.size());
        
    }
    
    public void loadTeacherData() {

        teacher_list.clear();
        
        ResultSet res = connector.execQuery(query.getTeacherTableData()) ;
        
        try {
            while (res.next()) {
                String tid = res.getString(1);
                String tname = res.getString(2);
                String tdesig = res.getString(3);
                String tcurrhall = res.getString(4);
                if(tcurrhall == null)   
                    tcurrhall = "N/A" ;
                String tcurrrole = res.getString(5);
                if(tcurrrole == null)
                    tcurrrole = "N/A" ;

                teacher_list.add(new Teacher_Info(tid, tname, tdesig, tcurrhall, tcurrrole)) ;

            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        
        
       teacher_table.setItems(teacher_list);
       tRecordNo.setText("No.of Records : "+ teacher_list.size());
        

        
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
        fRecordNo.setText("No.of Records : "+ fee_list.size());

        
    }
    
    public void loadStudentData() {
        
        student_list.clear();
        
        ResultSet res = connector.execQuery(query.getStudentTableData()) ;
        
        try {
            while (res.next()) {
                String sid = res.getString(1);
                String sname = res.getString(2);
                String scurrhall = res.getString(3);
                String stype = res.getString(4);
                String sstatus = res.getString(5);
                
                if(sstatus.equals("Alumni"))
                {
                    scurrhall = "N/A" ;
                    stype = "N/A" ;
                }
                
                student_list.add(new Student_Info(sid, sname, scurrhall, sstatus, stype)) ;

            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        
       student_table.setItems(student_list);
       sRecordNo.setText("No.of Records : "+ student_list.size());
        
    }
    
    public void loadPaymentData() {
        
        payment_list.clear();
        
        ResultSet res = connector.execQuery(query.getPaymentTableData()) ;
        
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
                {
                    pstatus = "Pending" ;
                }
                
                payment_list.add(new Payment_Info(psid, pmonth, pyear, pfeeamount, pfine, pstatus)) ;
            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        
       payment_table.setItems(payment_list);
       pRecordNo.setText("No.of Records : "+ payment_list.size());
        
    }

    public void initDrawer() {
        try {
            VBox toolbar = FXMLLoader.load(getClass().getResource("/hall_management/gui/admin/toolbar/toolbar.fxml"));
            drawer.setSidePane(toolbar);
            drawer.setPrefWidth(0);
            
        } catch (IOException ex) {
            Logger.getLogger(Admin_viewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
        task.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
            task.setRate(task.getRate() * -1);
            task.play();
            //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow() ;
            
            if (drawer.isHidden()) {
                drawer.open();
                drawer.setPrefWidth(200);           
            } else {
                drawer.close();
                drawer.setPrefWidth(0);
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
    
    
    public void initComboBoxs() {
        hSearchCombo.setItems(hComboList);
        tSearchCombo.setItems(tComboList);
        sSearchCombo.setItems(sComboList);
        fSearchCombo.setItems(fComboList);
        pSearchCombo.setItems(pComboList);
        
    }
  
    @FXML
    private void handleHallRefreshButton(ActionEvent event) {
        
        loadHallData(); 
    }

    @FXML
    private void handleHallUpdateButton(ActionEvent event) {
        
        Hall_Info selectedForEdit = hall_table.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please select a row to update");
            return;
        }
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hall_management/gui/admin/addHall/add_hall.fxml"));
            Parent parent = loader.load();

            Add_hallController controller = (Add_hallController) loader.getController();
            
            controller.inflateUI(selectedForEdit);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Hall");
            stage.setScene(new Scene(parent));
            stage.show();
            utilities.setStageIcon(stage);
            
          
            
        } catch (IOException ex) {
            Logger.getLogger(Admin_viewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void handleHallSearchButton(ActionEvent event) {
        
        String column = hSearchCombo.getValue() ;
        String str = hSearchString.getText() ;
        String query = null ;
        if( column == null || str.isEmpty() ) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please input data in all the fields");
            return ;
        }
        
        if( column.equals("ID")) {
           if(!isInteger(str)) {
               JFXButton btn = new JFXButton("Okay!");
               AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please enter numeric data for this search type");
               return ;
           }
           
           query = "SELECT H_ID , H_NAME , H_TYPE , GET_CAPACITY_COUNT(H_ID) , GET_OCCUPIED_SEAT_COUNT(H_ID) , GET_ATTACHED_COUNT(H_ID) FROM HALL WHERE H_ID = " + str + "ORDER BY H_ID" ;
        }
        else if( column.equals("Name"))
          query = "SELECT H_ID , H_NAME , H_TYPE , GET_CAPACITY_COUNT(H_ID) , GET_OCCUPIED_SEAT_COUNT(H_ID) , GET_ATTACHED_COUNT(H_ID) FROM HALL WHERE upper(H_NAME) LIKE '%" + str.toUpperCase() + "%' ORDER BY H_ID" ;
     
        
        else if( column.equals("Type"))
          query = "SELECT H_ID , H_NAME , H_TYPE , GET_CAPACITY_COUNT(H_ID) , GET_OCCUPIED_SEAT_COUNT(H_ID) , GET_ATTACHED_COUNT(H_ID) FROM HALL WHERE upper(H_TYPE) = '" + str.toUpperCase() + "' ORDER BY H_ID" ;;
     

        
       
        
         hall_list.clear();
        
        ResultSet res = connector.execQuery(query) ;
        
        try {
            while (res.next()) {
                String hid = res.getString(1);
                String hname = res.getString(2);
                String htype = res.getString(3);
                String hcapacity = res.getString(4);
                String hoccupied = res.getString(5);
                String hattached = res.getString(6) ;

                hall_list.add(new Hall_Info(hid, hname, htype, hcapacity, hoccupied, hattached));

            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        
//        
//        query = "SELECT DES_ADMIN_SDATE FROM DESIGNATED_HALL WHERE H_ID = 1 AND T_ID ='1'" ;
//        res = connector.execQuery(query );
//        try {
//            while(res.next())
//            {
//                Date date = res.getDate(1) ;
//                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy") ;
//                System.out.println(sdf.format(date));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Admin_viewController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
        hall_table.setItems(hall_list);
        hRecordNo.setText("No.of Records : "+ hall_list.size());
        
        
    }

    @FXML
    private void handleTeacherUpdateButton(ActionEvent event) {
        
        Teacher_Info selectedForEdit = teacher_table.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please select a row to update");
            return;
        }
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hall_management/gui/admin/updateTeacher/update_teacher.fxml"));
            Parent parent = loader.load();

            Update_teacherController controller = (Update_teacherController) loader.getController();
            
            controller.inflateUI(selectedForEdit);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Teacher");
            stage.setScene(new Scene(parent));
            stage.show();
            utilities.setStageIcon(stage);
            
          
            
        } catch (IOException ex) {
            Logger.getLogger(Admin_viewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void handleTeacherSearchButton(ActionEvent event) {
            
        String column = tSearchCombo.getValue() ;
        String str = tSearchString.getText() ;
        String query = null ;
        if( column == null || str.isEmpty() ) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please input data in all the fields");
            return ;
        }
        
        if( column.equals("ID")) {
           if(!isInteger(str)) {
               JFXButton btn = new JFXButton("Okay!");
               AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please enter numeric data for this search type");
               return ;
           }
           column = "T.T_ID" ;          
        }
                
        else if( column.equals("Name"))
           column = "upper(T.T_NAME)" ;
        
        else if( column.equals("Designation"))
           column = "upper(T.T_DESIG)" ;
        
        else if( column.equals("Current Hall"))
            column = "upper(H.H_NAME)" ;
        
        else if( column.equals("Current Role"))
            column = "upper(D.DES_ADMIN_ROLE)" ;
       
        String middle = null ;
        
        if( column.equals("T.T_ID")) {
            middle =  " = '" + str + "'" ;
        }
        else {
            middle = " LIKE '%" + str.toUpperCase() + "%'" ;
        }
        
        query = "SELECT T.T_ID , T.T_NAME , T.T_DESIG , H.H_NAME , D.DES_ADMIN_ROLE\n" +
              "FROM TEACHERS T\n" +
              "LEFT OUTER JOIN DESIGNATED_HALL D\n" +
              "ON( T.T_ID = D.T_ID AND D.DES_ADMIN_RDATE IS NULL )\n" +
              "LEFT OUTER JOIN HALL H\n" +
              "ON ( D.H_ID = H.H_ID )\n" +
              "WHERE " + column + middle +"\n" +
              "ORDER BY TO_NUMBER(T.T_ID) ASC " ; 
        
         teacher_list.clear();
        
        ResultSet res = connector.execQuery(query) ;
        
        try {
            while (res.next()) {
                String tid = res.getString(1);
                String tname = res.getString(2);
                String tdesig = res.getString(3);
                String tcurrhall = res.getString(4);
                if(tcurrhall == null)   
                    tcurrhall = "N/A" ;
                String tcurrrole = res.getString(5);
                if(tcurrrole == null)
                    tcurrrole = "N/A" ;

                teacher_list.add(new Teacher_Info(tid, tname, tdesig, tcurrhall, tcurrrole)) ;

            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        
        
       teacher_table.setItems(teacher_list);
       tRecordNo.setText("No.of Records : "+ teacher_list.size());
        
        
        
    }

    @FXML
    private void handleTeacherRefreshButton(ActionEvent event) {
        
        loadTeacherData();
    }
    
    @FXML
    private void handleTeacherHistoryButton(ActionEvent event) {
        
        Teacher_Info selectedForEdit = teacher_table.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please select a row to update");
            return;
        }
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hall_management/gui/admin/teacherHistory/teacher_history.fxml"));
            Parent parent = loader.load();

            Teacher_historyController controller = (Teacher_historyController) loader.getController();
            
            controller.inflateUI(selectedForEdit);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("View History");
            stage.setScene(new Scene(parent));
            stage.show();
            utilities.setStageIcon(stage);
            
          
            
        } catch (IOException ex) {
            Logger.getLogger(Admin_viewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    

    @FXML
    private void handleStudentRefreshButton(ActionEvent event) {
        
        loadStudentData();
    }

    @FXML
    private void handleStudentUpdateButton(ActionEvent event) {
        
        Student_Info selectedForEdit = student_table.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please select a row to update");
            return;
        }
        
        else if( selectedForEdit.getStype().equals("Resident")) {
            
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "Updating not allowed", "This student is still alloted in a room. ");
            return;
        }
        
        else if( selectedForEdit.getSstatus().equals("Alumni")) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "Updating not allowed", "Cant update an alumni. ");
            return;
        
        }
        
        
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hall_management/gui/admin/updateStudent/update_student.fxml"));
            Parent parent = loader.load();

            Update_studentController controller = (Update_studentController) loader.getController();
            
            controller.inflateUI(selectedForEdit);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Student");
            stage.setScene(new Scene(parent));
            stage.show();
            utilities.setStageIcon(stage);
            
          
            
        } catch (IOException ex) {
            Logger.getLogger(Admin_viewController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }

    @FXML
    private void handleStudentSearchButton(ActionEvent event) {
        
        
        String column = sSearchCombo.getValue() ;
        String str = sSearchString.getText() ;
        String query = null ;
        if( column == null || str.isEmpty() ) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please input data in all the fields");
            return ;
        }
        
        if( column.equals("ID")) {
           column = "upper(S.S_ID)" ;          
        }
                
        else if( column.equals("Name"))
           column = "upper(S.S_NAME)" ;
        
        else if( column.equals("Current Hall"))
            column = "upper(T.NAME)" ;
        
        else if( column.equals("Type"))
            column = "upper(T.TYPE)" ;
        
        else if ( column.equals("Status"))
            column = "upper(S.S_STATUS)" ;
       
        String middle = null ;
        
        if(column.equals("upper(T.TYPE)")) {
            
            middle = "LIKE '%" + str.toUpperCase() + "%'" + "AND upper(S.S_STATUS) <> 'ALUMNI' " ;
        }
        else
            middle = "LIKE '%" + str.toUpperCase() + "%'" ;

        
        
        query = "SELECT S.S_ID ,S.S_NAME , T.NAME , T.TYPE , S.S_STATUS \n" +
               "FROM STUDENTS S ,\n" +
                "(\n" +
                "       SELECT H.H_NAME NAME , AH.ASSIGNED_S_TYPE TYPE , AH.S_ID ID\n" +
                "	FROM ASSIGNED_HALL AH\n" +
                "	JOIN HALL H\n" +
                "	ON( AH.H_ID = H.H_ID )  WHERE AH.ASSIGNED_CURRENT = 'YES'\n" +
                ") T\n" +
                "WHERE S.S_ID = T.ID AND S_STATUS = 'Running' AND " + column + " " + middle ;
        
         student_list.clear();
        
        ResultSet res = connector.execQuery(query) ;
        
        try {
            while (res.next()) {
                String sid = res.getString(1);
                String sname = res.getString(2);
                String scurrhall = res.getString(3);
                String stype = res.getString(4);
                String sstatus = res.getString(5);
                
                if(sstatus.equals("Alumni"))
                {
                    scurrhall = "N/A" ;
                    stype = "N/A" ;
                }
                
                student_list.add(new Student_Info(sid, sname, scurrhall, sstatus, stype)) ;

            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        
       student_table.setItems(student_list);
       sRecordNo.setText("No.of Records : "+ student_list.size());
        
        
    }

    @FXML
    private void handleFeeUpdateButton(ActionEvent event) {
        
        Fee_Info selectedForEdit = fee_table.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please select a row to update");
            return;
        }
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hall_management/gui/admin/addFees/add_fee.fxml"));
            Parent parent = loader.load();

            Add_feeController controller = (Add_feeController) loader.getController();
            
            controller.inflateUI(selectedForEdit);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Fee");
            stage.setScene(new Scene(parent));
            stage.show();
            utilities.setStageIcon(stage);
            
          
            
        } catch (IOException ex) {
            Logger.getLogger(Admin_viewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleFeeSearchButton(ActionEvent event) {
        
          
        String column = fSearchCombo.getValue() ;
        String str = fSearchString.getText() ;
        String query = null ;
        if( column == null || str.isEmpty() ) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please input data in all the fields");
            return ;
        }
        
      if( column.equals("ID")) {
           if(!isInteger(str)) {
               JFXButton btn = new JFXButton("Okay!");
               AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please enter numeric data for this search type");
               return ;
           }
           
         query = "SELECT * FROM FEES WHERE F_ID = " + str + "ORDER BY F_ID" ;
        }
        else if( column.equals("Name"))
          query = "SELECT * FROM FEES WHERE upper(F_NAME) LIKE '%" + str.toUpperCase() + "%' ORDER BY F_ID" ;
     
        
        else if( column.equals("Category"))
          query = "SELECT * FROM FEES WHERE upper(F_CATEGORY) = '" + str.toUpperCase() + "' ORDER BY F_ID" ;
     
        else if( column.equals("Time"))
          query = "SELECT * FROM FEES WHERE upper(F_TIME) = '" + str.toUpperCase() + "' ORDER BY F_ID" ;
     
        
       
       fee_list.clear();
        
        ResultSet res = connector.execQuery(query) ;
        
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
        fRecordNo.setText("No.of Records : "+ fee_list.size());

        
    }

    @FXML
    private void handleFeeRefreshButton(ActionEvent event) {
        loadFeeData();
    }

    @FXML
    private void handlePaymentUpdateButton(ActionEvent event) {
        
        Payment_Info selectedForEdit = payment_table.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "ERROR!", "Please select a row to update");
            return;
        }
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hall_management/gui/admin/updatePayment/update_payment.fxml"));
            Parent parent = loader.load();

            Update_paymentController controller = (Update_paymentController) loader.getController();
            
            controller.inflateUI(selectedForEdit);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Student Payment");
            stage.setScene(new Scene(parent));
            stage.show();
            utilities.setStageIcon(stage);
            
          
            
        } catch (IOException ex) {
            Logger.getLogger(Admin_viewController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        if( column.equals("ID")) {
           column = "upper(S_ID)" ;          
        }
                
        else if( column.equals("Month"))
           column = "upper(S_F_MONTH)" ;
        
        else if( column.equals("Year"))
            column = "upper(S_F_YEAR)" ;
        
        
        String middle = null ;
        
    
         middle = "LIKE '%" + str.toUpperCase() + "%'" ;

        
        
        query = "SELECT DISTINCT S_ID , S_F_MONTH , S_F_YEAR , GET_FEE(S_ID,S_F_MONTH,S_F_YEAR) , GET_FINE(S_ID, S_F_MONTH,S_F_YEAR) , S_F_PDATE\n" +
                "FROM STUDENTS_FEES WHERE S_F_PDATE IS NULL AND "  + column + " " + middle;
        
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
                {
                    pstatus = "Pending" ;
                }
                
                payment_list.add(new Payment_Info(psid, pmonth, pyear, pfeeamount, pfine, pstatus)) ;
            }
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        
       payment_table.setItems(payment_list);
       pRecordNo.setText("No.of Records : "+ payment_list.size());
        
        
        
    }

    @FXML
    private void handlePaymentRefreshButton(ActionEvent event) {
        
        loadPaymentData();
    }

    @FXML
    private void handleLogOut(ActionEvent event) {
        
        JFXButton yesbtn = new JFXButton("Yes");
        JFXButton nobtn = new JFXButton("No");
        yesbtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e)->{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/hall_management/gui/login/login.fxml"));
                Parent parent = loader.load();
                
                Stage prev = (Stage)drawer.getScene().getWindow() ;
                prev.close();
                
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Hall management system");
                stage.setScene(new Scene(parent));
                stage.show();
                utilities.setStageIcon(stage);
            } catch (IOException ex) {
                Logger.getLogger(Admin_viewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(yesbtn, nobtn), "Log Out", "Are you sure you want to log out?");
        return;
        
        
    }

    @FXML
    private void handleChangePassword(ActionEvent event) {
        
         utilities.loadStage(getClass().getResource("/hall_management/gui/admin/changePassword/change_pass.fxml"), "Update Password", null);
        
    }

    @FXML
    private void handleNotifications(ActionEvent event) {
         utilities.loadStage(getClass().getResource("/hall_management/gui/admin/notifications/notifications.fxml"), "View Notifications", null);
        
    }

   
}
