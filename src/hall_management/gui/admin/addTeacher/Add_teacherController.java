/*
 * Life sucks....
 */
package hall_management.gui.admin.addTeacher;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.admin.Admin_viewController;
import hall_management.gui.admin.queries.query;
import hall_management.gui.alerts.AlertMaker;
import hall_management.gui.extras.utilities;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abhik_Blaze
 */
public class Add_teacherController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private JFXTextField t_id;
    @FXML
    private JFXTextField t_name;
    @FXML
    private JFXComboBox<String> t_designation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //connector.getConnection();
        
        t_id.setEditable(false);
        t_id.setText(getTid());
        ObservableList<String> options = FXCollections.observableArrayList(
                "Professor",
                "Assistant Professor",
                "Associate Professor",
                "Lecturer"
        );
        t_designation.setItems(options);
    }

    @FXML
    private void save(ActionEvent event) {
        String id = getTid() ;
        String name = t_name.getText();
        String desig = t_designation.getValue();

        if (name.isEmpty() || (desig == null) || desig.isEmpty()) {
            AlertMaker.showErrorMessage("Incomplete Data Error", "Please enter values in all the fields.");
            return;
        }

        String stmt = query.addTeacher(id, name, desig);
        System.out.println(stmt);

        if (connector.execAction(stmt)) {
            reset();
            AlertMaker.showSimpleAlert("Success", "Teacher added to Database");
            
            Admin_viewController controller = utilities.getA_controller() ;
            controller.loadTeacherData();

        } else {
            //AlertMaker.showErrorMessage("Failed", "Teacher Id already in use.");
        }

    }

    @FXML
    private void cancel(ActionEvent event) {

       // connector.closeConnection();
        Stage stage = (Stage) anchorpane.getScene().getWindow();
        stage.close();

    }

    public void reset() {
        t_id.setText(getTid());
        t_name.setText(null);
        t_designation.getSelectionModel().clearSelection();
    }

    public String getTid() {
        String id = null;
        ResultSet res = connector.execQuery(query.getTeacherCount());
        try {
            while (res.next()) {
                id = res.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Add_teacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id ;

    }

}
