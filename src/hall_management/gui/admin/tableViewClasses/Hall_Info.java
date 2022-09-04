/*
 * Life sucks....
 */
package hall_management.gui.admin.tableViewClasses;

import javafx.beans.property.SimpleStringProperty;



/**
 *
 * @author Abhik_Blaze
 */
public class Hall_Info {

    private final SimpleStringProperty hid ;
    private final SimpleStringProperty hname ;
    private final SimpleStringProperty htype ;
    private final SimpleStringProperty hcapacity ;
    private final SimpleStringProperty hoccupied ;
    private final SimpleStringProperty hattached ;
    
    public Hall_Info(String hid, String hname, String htype, String hcapacity, String hoccupied, String hattached) {
        this.hid = new SimpleStringProperty(hid);
        this.hname = new SimpleStringProperty(hname);
        this.htype = new SimpleStringProperty(htype);
        this.hcapacity = new SimpleStringProperty(hcapacity);
        this.hoccupied = new SimpleStringProperty(hoccupied);
        this.hattached = new SimpleStringProperty(hattached) ;
    }
    
    

    public String getHid() {
        return hid.get();
    }

    public String getHname() {
        return hname.get();
    }

    public String getHtype() {
        return htype.get();
    }

    public String getHcapacity() {
        return hcapacity.get();
    }

    public String getHoccupied() {
        return hoccupied.get();
    }
    
    public String getHattached() {
        return hattached.get();
    }
    
   
}
