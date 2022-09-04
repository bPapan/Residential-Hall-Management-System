/*
 * Life sucks....
 */
package hall_management.gui.student.history;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Abhik_Blaze
 */
public class History_Info {
    
    private final SimpleStringProperty hall ;
    private final SimpleStringProperty room ;
    private final SimpleStringProperty sdate ;
    private final SimpleStringProperty edate ;

    public String getHall() {
        return hall.get();
    }

    public String getRoom() {
        return room.get();
    }

    public String getSdate() {
        return sdate.get();
    }

    public String getEdate() {
        return edate.get();
    }

    public History_Info(String hall, String room, String sdate, String edate) {
        this.hall = new SimpleStringProperty(hall);
        this.room = new SimpleStringProperty(room);
        this.sdate = new SimpleStringProperty(sdate);
        this.edate = new SimpleStringProperty(edate);
    }
    
    
    
}
