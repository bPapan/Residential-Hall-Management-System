/*
 * Life sucks....
 */
package hall_management.gui.admin.tableViewClasses;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Abhik_Blaze
 */
public class Student_Info {
    
    private final SimpleStringProperty sid ;
    private final SimpleStringProperty sname ;
    private final SimpleStringProperty scurrhall ;
    private final SimpleStringProperty sstatus ;
    private final SimpleStringProperty stype ;

    public String getSid() {
        return sid.get();
    }

    public String getSname() {
        return sname.get();
    }

    public String getScurrhall() {
        return scurrhall.get();
    }

    public String getSstatus() {
        return sstatus.get();
    }
    
    public String getStype() {
        return stype.get() ;
    }

    public Student_Info(String sid, String sname, String scurrhall, String sstatus , String stype) {
        this.sid = new SimpleStringProperty(sid);
        this.sname = new SimpleStringProperty(sname);
        this.scurrhall = new SimpleStringProperty(scurrhall);
        this.sstatus = new SimpleStringProperty(sstatus);
        this.stype = new SimpleStringProperty(stype) ;
    }
    
    
}
