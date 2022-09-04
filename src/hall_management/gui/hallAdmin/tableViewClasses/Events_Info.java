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
public class Events_Info {
    private final SimpleStringProperty evId;
    private final SimpleStringProperty evName;
    private final SimpleStringProperty evStDate;
    private final SimpleStringProperty evEnDate;

    public String getEvId() {
        return evId.get();
    }

    public String getEvName() {
        return evName.get();
    }

    public String getEvStDate() {
        return evStDate.get();
    }

    public String getEvEnDate() {
        return evEnDate.get();
    }

    public Events_Info(String evId, String evName, String evStDate, String evEnDate) {
        this.evId = new SimpleStringProperty(evId);
        this.evName = new SimpleStringProperty(evName);
        this.evStDate = new SimpleStringProperty(evStDate);
        this.evEnDate = new SimpleStringProperty(evEnDate);
    }
    
}
