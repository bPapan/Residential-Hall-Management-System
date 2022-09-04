/*
 * Life sucks....
 */
package hall_management.gui.admin.tableViewClasses;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Abhik_Blaze
 */
public class Teacher_History_Info {
    
    private final SimpleStringProperty thall ;
    private final SimpleStringProperty trole ;
    private final SimpleStringProperty tjdate ;
    private final SimpleStringProperty trdate ;

    public String getThall() {
        return thall.get();
    }

    public String getTrole() {
        return trole.get();
    }

    public String getTjdate() {
        return tjdate.get();
    }

    public String getTrdate() {
        return trdate.get();
    }

    public Teacher_History_Info(String thall, String trole, String tjdate, String trdate) {
        this.thall = new SimpleStringProperty(thall);
        this.trole = new SimpleStringProperty(trole);
        this.tjdate = new SimpleStringProperty(tjdate);
        this.trdate = new SimpleStringProperty(trdate);
    }
    
    
}
