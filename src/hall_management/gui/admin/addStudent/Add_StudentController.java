/*
 * Life sucks....
 */
package hall_management.gui.admin.addStudent;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.admin.Admin_viewController;
import hall_management.gui.admin.queries.query;
import hall_management.gui.admin.updateTeacher.Update_teacherController;
import hall_management.gui.alerts.AlertMaker;
import hall_management.gui.extras.utilities;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
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
public class Add_StudentController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    
    @FXML
    private JFXTextField s_name;
    @FXML
    private JFXTextField s_f_name;
    @FXML
    private JFXTextField s_m_name;
    @FXML
    private JFXComboBox<String> s_gender;
    @FXML
    private JFXComboBox<String> s_religion;
    @FXML
    private JFXTextField s_mobile;
    @FXML
    private JFXComboBox<String> s_bld_grp;
    @FXML
    private JFXTextField s_prm_addr;
    @FXML
    private JFXComboBox<String> s_batch;
    @FXML
    private JFXComboBox<String> s_dept;
    @FXML
    private JFXComboBox<String> s_roll;
    @FXML
    private JFXComboBox<String> s_hall;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //connector.getConnection() ;
        // TODO
         ObservableList<String> gender_options = FXCollections.observableArrayList(
                
                "Male",
                "Female"
        ) ;
        s_gender.setItems(gender_options);
        
        ObservableList<String> religion_options = FXCollections.observableArrayList(
                
                "Muslim",
                "Hindu" ,
                "Buddhism",
                "Christian"
        ) ;
        
        s_religion.setItems(religion_options);
        
         ObservableList<String> bld_grp_options = FXCollections.observableArrayList(
                
                "A+",
                "A-" ,
                "B+" ,
                "B-" ,
                "AB+" ,
                "AB-" ,
                "O+" ,
                "O-"
                
        ) ;
         s_bld_grp.setItems(bld_grp_options);
         
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
          
                  "02" ,
                  "03" ,
                  "04" ,
                  "05" ,
                  "06" ,
                  "08" ,
                  "10" ,
                  "12"
          
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
          
        ObservableList<String> hall_options = FXCollections.observableArrayList() ;
        String str = "SELECT H_NAME FROM HALL" ;
        ResultSet res = connector.execQuery(str) ;
        try {
            while(res.next()) {
                
                hall_options.add(res.getString(1)) ;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Add_StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        s_hall.setItems(hall_options);
        
          
        
    }    

    @FXML
    private void save(ActionEvent event) {
       
        try {
            String batch = s_batch.getValue() ;
            String dept = s_dept.getValue() ;
            String roll = s_roll.getValue() ;
            
            
            String name = s_name.getText() ;
            String father = s_f_name.getText() ;
            String mother = s_m_name.getText() ;
            String gender = s_gender.getValue() ;
            String religion = s_religion.getValue() ;
            String mobile = s_mobile.getText() ;
            String bld_grp = s_bld_grp.getValue() ;
            String prm_addr = s_prm_addr.getText() ;
            String hall = s_hall.getValue() ;
            
            if(batch == null || name.isEmpty() || father.isEmpty() || mother.isEmpty() || gender == null || religion == null || mobile.isEmpty() || bld_grp == null || prm_addr.isEmpty() || hall == null )
            {
                AlertMaker.showErrorMessage("Incomplete Data Error", "Please enter values in all the fields.");
                return ;
            }
            if(!isInteger(mobile) || mobile.length()!= 11) {
                
                AlertMaker.showErrorMessage("Invalid Data error", "Please enter a valid mobile number");
                return ;
            }
             
            String id = "S"+batch+dept+roll ;
            
            String stmt = "SELECT * FROM STUDENTS WHERE S_ID = '" + id + "'" ;
            ResultSet res = connector.execQuery(stmt) ;
            
            while(res.next()) {
                AlertMaker.showErrorMessage("Invalid Data Error", "Another student by this id already exists.");
                return ;
            }
            
            stmt = "SELECT H_TYPE FROM HALL WHERE H_ID = (SELECT H_ID FROM HALL WHERE H_NAME = '" + hall + "')" ;
            res = connector.execQuery(stmt) ;
            String hgender= null ;
            while(res.next()) {
                hgender = res.getString(1) ;
            }
            
            if(!gender.equals(hgender)){
                AlertMaker.showErrorMessage("Invalid Data Error", "Student Gender and Hall Type mismatch");
                return ;
            }
            
            stmt = query.addStudent(id, name, father, mother, gender, religion, mobile, bld_grp, prm_addr) ;
            
            String stmt1 = "INSERT INTO ASSIGNED_HALL VALUES( (SELECT H_ID FROM HALL WHERE H_NAME = '" + hall + "')," + "'" + id + "'," + "'Attached','YES')" ;
            String stmt2 = "INSERT INTO LOGIN VALUES('" + id + "','" + id.substring(3) + "','student','1234')"  ;
            
            if (connector.execAction(stmt) && connector.execAction(stmt1)&& connector.execAction(stmt2)) {
                reset() ;
                AlertMaker.showSimpleAlert("Success", "Student added to Database");
                Admin_viewController controller = utilities.getA_controller() ;
                controller.loadStudentData();
                controller.loadHallData();
                
            }
            else //Error
            {
                //taken care of in connector part...
                //AlertMaker.showErrorMessage("Failed", "Student Id already in use.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Add_StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
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
    private void cancel(ActionEvent event) {
        
        //connector.closeConnection(); 
        Stage stage = (Stage) anchorpane.getScene().getWindow();
        stage.close();
        
    }
    
 
    public void reset()
    {
        s_batch.getSelectionModel().clearSelection();
        s_dept.getSelectionModel().clearSelection();
        s_roll.getSelectionModel().clearSelection();
        s_name.setText(null);
        s_f_name.setText(null);
        s_m_name.setText(null);
        s_gender.getSelectionModel().clearSelection() ;
        s_religion.getSelectionModel().clearSelection() ;
        s_mobile.setText(null);
        s_bld_grp.getSelectionModel().clearSelection() ;
        s_prm_addr.setText(null);
        s_hall.getSelectionModel().clearSelection(); 
    }
  
    
}
