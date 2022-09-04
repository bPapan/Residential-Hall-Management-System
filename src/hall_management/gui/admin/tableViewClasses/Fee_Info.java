/*
 * Life sucks....
 */
package hall_management.gui.admin.tableViewClasses;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Abhik_Blaze
 */
public class Fee_Info {
    
    private final SimpleStringProperty fid ;
    private final SimpleStringProperty fname ;
    private final SimpleStringProperty famount ;
    private final SimpleStringProperty fcategory ;
    private final SimpleStringProperty ftime ;

    public String getFid() {
        return fid.get();
    }

    public String getFname() {
        return fname.get();
    }

    public String getFamount() {
        return famount.get();
    }

    public String getFcategory() {
        return fcategory.get();
    }

    public String getFtime() {
        return ftime.get();
    }

    public Fee_Info(String fid, String fname, String famount, String fcategory, String ftime) {
        this.fid = new SimpleStringProperty(fid);
        this.fname = new SimpleStringProperty(fname);
        this.famount = new SimpleStringProperty(famount);
        this.fcategory = new SimpleStringProperty(fcategory);
        this.ftime = new SimpleStringProperty(ftime);
        
        
    }
    
}
