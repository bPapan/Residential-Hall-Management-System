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
public class Rooms_Info {
    private final SimpleStringProperty roomId;
    private final SimpleStringProperty roomWing;
    private final SimpleStringProperty roomCapacity;
    private final SimpleStringProperty roomOccupy;
    private final SimpleStringProperty available;

    public String getAvailable() {
        return available.get();
    }

    public String getRoomId() {
        return roomId.get();
    }

    public String getRoomWing() {
        return roomWing.get();
    }

    public String getRoomCapacity() {
        return roomCapacity.get();
    }

    public String getRoomOccupy() {
        return roomOccupy.get();
    }
    
    public Rooms_Info(String roomId, String roomWing, String roomCapacity, String roomOccupy, String roomAvailability)
    {
        this.roomId = new SimpleStringProperty(roomId);
        this.roomWing = new SimpleStringProperty(roomWing);
        this.roomCapacity = new SimpleStringProperty(roomCapacity);
        this.roomOccupy = new SimpleStringProperty(roomOccupy);
        this.available = new SimpleStringProperty(roomAvailability);
    }
}
