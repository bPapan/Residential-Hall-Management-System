/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.gui.hallAdmin.addSponsor;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import hall_management.database.connector;
import hall_management.gui.alerts.AlertMaker;
import hall_management.gui.extras.utilities;
import hall_management.gui.hallAdmin.HallAdmin_viewController;
import hall_management.gui.hallAdmin.queries.query;
import hall_management.gui.hallAdmin.tableViewClasses.Sponsors_Info;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author papan
 */
public class Add_SponsorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private VBox rootVBox;
    @FXML
    private JFXTextField sponID;
    @FXML
    private JFXTextField sponName;
    @FXML
    private JFXTextField manName;
    @FXML
    private JFXTextField manContact;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancButton;
    
    private boolean inEditMode = false;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connector.getConnection();
        
        sponID.setEditable(false);
        sponID.setText(getSponID());
        
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
    private void save(){
        String id =null;
        if(inEditMode==true)
            id = sponID.getText();
        else
            id = getSponID();
        String name = sponName.getText();
        String mName = manName.getText();
        String mContact = manContact.getText();
        
        if(name ==null)
        {
            AlertMaker.showErrorMessage("Incomplete or Invalid Data Error!", "Please fill up the field Sponsor Name.");
            return;
        }
        
        if(mName!=null && isInteger(mName))
        {
            AlertMaker.showErrorMessage("Incomplete or Invalid Data Error!", "Human name can't be of number type.");
            return;
        }
        
        if(mContact!= null)
        {
            if(mContact.length()!=11 || !isInteger(mContact))
            {
                AlertMaker.showErrorMessage("Incomplete or Invalid Data Error!", "Mobile No. must be equal of 11 digits and can't be charcaters.");
                return;
            }
        }
        
        int spId = Integer.parseInt(id);
        String stmt = null;
        if(inEditMode==false)
            stmt = query.addSponsor(spId, name, mName, mContact);
        else
            stmt = query.updateSponsorData(spId, name, mName, mContact);
        
        System.out.println(stmt);
        
        if (connector.execAction(stmt)) {
            reset();
            AlertMaker.showSimpleAlert("Success", "Sponsor added to Database");
            
            HallAdmin_viewController controller = utilities.getHA_controller() ;
            controller.loadSponsorsData();

        } else {
            //AlertMaker.showErrorMessage("Failed", "Teacher Id already in use.");
        }
    }
    
    @FXML
    private void reset(){
        sponID.setText(getSponID());
        sponName.setText(null);
        manName.setText(null);
        manContact.setText(null);
    }
    
    public String getSponID() {
        String id = null;
        ResultSet res = connector.execQuery(query.getSponsorCount());
        try {
            while (res.next()) {
                id = res.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Add_SponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id ;

    }
    
    public void inflateUI(Sponsors_Info sponsor)
    {
        inEditMode = true;
        sponID.setText(sponsor.getSpnID());
        sponName.setText(sponsor.getSpnName());
        manName.setText(sponsor.getSpMName());
        manContact.setText(sponsor.getSpMContact());
        
        sponID.setEditable(false);
        sponName.setEditable(false);
        
        sponID.setDisable(true);
        sponName.setDisable(true);
        
    }
}
