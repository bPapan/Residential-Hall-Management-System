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
public class Student_Info_Hall {
    
    private final SimpleStringProperty studentId;
    private final SimpleStringProperty studentName;
    private final SimpleStringProperty studentType;
    private final SimpleStringProperty studentRoomWing;
    private final SimpleStringProperty studentRoomNo;
    private final SimpleStringProperty studentStatus;
    private final SimpleStringProperty studentMobileNo;

    public String getStudentId() {
        return studentId.get();
    }

    public String getStudentName() {
        return studentName.get();
    }

    public String getStudentType() {
        return studentType.get();
    }

    public String getStudentRoomWing() {
        return studentRoomWing.get();
    }

    public String getStudentRoomNo() {
        return studentRoomNo.get();
    }

    public String getStudentStatus() {
        return studentStatus.get();
    }

    public String getStudentMobileNo() {
        return studentMobileNo.get();
    }
    
    public Student_Info_Hall(String studentId, String studentName, String studentType, String studentRoomWing, String studentRoomNo, String studentStatus, String studentMobileNo)
    {
        this.studentId = new SimpleStringProperty(studentId);
        this.studentName = new SimpleStringProperty(studentName);
        this.studentType = new SimpleStringProperty(studentType);
        this.studentRoomWing = new SimpleStringProperty(studentRoomWing);
        this.studentRoomNo = new SimpleStringProperty(studentRoomNo);
        this.studentStatus = new SimpleStringProperty(studentStatus);
        this.studentMobileNo = new SimpleStringProperty(studentMobileNo);
    }
}
