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
public class SpShip_Info {
    private final SimpleStringProperty ename;
    private final SimpleStringProperty eyear;
    private final SimpleStringProperty sname;
    private final SimpleStringProperty cntrbt;
    private final SimpleStringProperty amnt;

    public String getEname() {
        return ename.get();
    }

    public String getEyear() {
        return eyear.get();
    }

    public String getSname() {
        return sname.get();
    }

    public String getCntrbt() {
        return cntrbt.get();
    }

    public String getAmnt() {
        return amnt.get();
    }
    

    public SpShip_Info(String ename, String eyear, String sname, String cntrbt, String amnt) {
        this.ename = new SimpleStringProperty(ename);
        this.eyear = new SimpleStringProperty(eyear);
        this.sname = new SimpleStringProperty(sname);
        this.cntrbt = new SimpleStringProperty(cntrbt);
        this.amnt = new SimpleStringProperty(amnt);
    }
    
    
    
}
