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
public class Std_Hist_Info_Hall {
    private final SimpleStringProperty roomId;
    private final SimpleStringProperty rmStDate;
    private final SimpleStringProperty rmEDate;

    public String getRoomId() {
        return roomId.get();
    }

    public String getRmStDate() {
        return rmStDate.get();
    }

    public String getRmEDate() {
        return rmEDate.get();
    }
    
    public Std_Hist_Info_Hall(String roomId, String rmStDate, String rmEDate) {
        this.roomId = new SimpleStringProperty(roomId);
        this.rmStDate = new SimpleStringProperty(rmStDate);
        this.rmEDate = new SimpleStringProperty(rmEDate);
    }
}
