/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.gui.hallAdmin.addTeam;

import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.hallAdmin.queries.query;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author papan
 */
public class Add_TeamController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField teamID;
    @FXML
    private JFXTextField teamSport;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connector.getConnection();
        
        teamID.setEditable(false);
        teamID.setText(getTeamID());
        
    }    
    
    
    @FXML
    private void save(){
        String id = getTeamID();
        String sport = teamSport.getText();
    }
    
    @FXML
    private void reset(){
        teamID.setText(getTeamID());
        teamSport.setText(null);
    }
    
    public String getTeamID() {
        String id = null;
        ResultSet res = connector.execQuery(query.getAssignCount());
        try {
            while (res.next()) {
                id = res.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Add_TeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id ;

    }
}
