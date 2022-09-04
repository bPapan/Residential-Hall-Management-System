/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.gui.hallAdmin.addSponsorship;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author papan
 */
public class Add_SponsorshipController implements Initializable {

    @FXML
    private JFXTextField spIdFd;
    @FXML
    private JFXTextField eIdFd;
    @FXML
    private JFXTextField cntrFd;
    @FXML
    private JFXTextField amtFd;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancButton;

    private boolean inEditMode= false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save(ActionEvent event) {
        String id = eIdFd.getText();
        String sid = spIdFd.getText();
        String ctrbt = cntrFd.getText();
        String amnt = amtFd.getText();
        
        if(id==null || ctrbt==null)
        {
            AlertMaker.showErrorMessage("Incomplete Data Errror","Please input Data in Event ID and Sponsor Contribution.");
            return;
        }
        if(id!=null && isInteger(id)==false)
        {
            AlertMaker.showErrorMessage("Invalid Data Errror","Event ID must be numerical.");
            return;
        }
        if(!amnt.isEmpty() && isInteger(amnt)==false)
        {
            AlertMaker.showErrorMessage("Invalid Data Error","Amount of Money must be numerical.");
            return;
        }
         
                
        int eid = Integer.parseInt(id);
        int spid = Integer.parseInt(sid);
        int amt =0;
        if(!amnt.isEmpty())
            amt = Integer.parseInt(amnt);
        else
            amt = 0;
        
        ResultSet str2 = connector.execQuery(query.checkEventinHall(eid, utilities.getHid()));
        
        try{
            if(!str2.next())
            {
                AlertMaker.showErrorMessage("Invalid Data Errror","This Event is not organized in your hall.");
                return;
            }
        }catch(SQLException ex)
        {
            System.out.println(ex);
        }
        String stmt = query.addSponsorship(eid, spid, amt, ctrbt, utilities.getHid());
        System.out.println(stmt);
        
        if(connector.execAction(stmt))
        {
            reset();
            AlertMaker.showSimpleAlert("Success!", "Sponsorship added to Database.");
            
            HallAdmin_viewController controller = utilities.getHA_controller() ;
            controller.loadSpnsrshipTabData();
        }
        else
        {
            
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
    private void reset() {
        inEditMode = true;
        spIdFd.setText(spIdFd.getText());
        spIdFd.setEditable(false);
        spIdFd.setDisable(true);
        eIdFd.setText(null);
        cntrFd.setText(null);
        amtFd.setText(null);
    }
    
    public void inflateUI(Sponsors_Info sp)
    {
        inEditMode = true;
        spIdFd.setText(sp.getSpnID());
        spIdFd.setEditable(false);
        spIdFd.setDisable(true);
    }
    
}
