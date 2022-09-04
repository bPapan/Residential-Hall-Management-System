/*
 * Life sucks....
 */
package hall_management.gui.hallAdmin.tableViewClasses;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Abhik_Blaze
 */
public class Notice_Info {
    
    private final SimpleStringProperty sid ;
    private final SimpleStringProperty ntype ;
    private final SimpleStringProperty nhall ;
    private final SimpleStringProperty nroom ;
    private final SimpleStringProperty ndate ;

    public String getSid() {
        return sid.get();
    }

    public String getNtype() {
        return ntype.get();
    }

    public String getNhall() {
        return nhall.get();
    }

    public String getNroom() {
        return nroom.get();
    }

    public String getNdate() {
        return ndate.get();
    }

    public Notice_Info(String sid, String ntype, String nhall, String nroom, String ndate) {
        this.sid = new SimpleStringProperty(sid);
        this.ntype = new SimpleStringProperty(ntype);
        this.nhall = new SimpleStringProperty(nhall);
        this.nroom = new SimpleStringProperty(nroom);
        this.ndate = new SimpleStringProperty(ndate);
    }
    
    
}
