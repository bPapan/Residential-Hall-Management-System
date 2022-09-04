/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.gui.hallAdmin.tableViewClasses;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author papan
 */
public class Sponsors_Info {
    private final SimpleStringProperty spnID;
    private final SimpleStringProperty spnName;
    private final SimpleStringProperty spMName;
    private final SimpleStringProperty spMContact;

    public String getSpnID() {
        return spnID.get();
    }

    public String getSpnName() {
        return spnName.get();
    }

    public String getSpMName() {
        return spMName.get();
    }

    public String getSpMContact() {
        return spMContact.get();
    }

    public Sponsors_Info(String spnID, String spnName, String spMName, String spMContact) {
        this.spnID = new SimpleStringProperty(spnID);
        this.spnName = new SimpleStringProperty(spnName);
        this.spMName = new SimpleStringProperty(spMName);
        this.spMContact = new SimpleStringProperty(spMContact);
    }
    
    
}
