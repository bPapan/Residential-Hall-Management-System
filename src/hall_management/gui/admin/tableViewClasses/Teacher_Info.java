/*
 * Life sucks....
 */
package hall_management.gui.admin.tableViewClasses;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Abhik_Blaze
 */
public class Teacher_Info {
    
    private final SimpleStringProperty tid ;
    private final SimpleStringProperty tname ;
    private final SimpleStringProperty tdesig ;
    private final SimpleStringProperty tcurrhall ;
    private final SimpleStringProperty tcurrrole ;

    public String getTid() {
        return tid.get();
    }

    public String getTname() {
        return tname.get();
    }

    public String getTdesig() {
        return tdesig.get();
    }

    public String getTcurrhall() {
        return tcurrhall.get();
    }

    public String getTcurrrole() {
        return tcurrrole.get();
    }

    public Teacher_Info(String tid, String tname, String tdesig, String tcurrhall, String tcurrrole) {
        this.tid = new SimpleStringProperty(tid);
        this.tname = new SimpleStringProperty(tname);;
        this.tdesig = new SimpleStringProperty(tdesig);;
        this.tcurrhall = new SimpleStringProperty(tcurrhall);;
        this.tcurrrole = new SimpleStringProperty(tcurrrole);;
    }
    
}
