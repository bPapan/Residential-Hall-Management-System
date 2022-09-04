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
public class Staff_Info {
    private final SimpleStringProperty stId;
    private final SimpleStringProperty stName;
    private final SimpleStringProperty stfAge;
    private final SimpleStringProperty stWing;
    private final SimpleStringProperty stJob;
    private final SimpleStringProperty stHireDate;
    private final SimpleStringProperty stEndDate;
    private final SimpleStringProperty stSalary;
    
    public String getStId() {
        return stId.get();
    }

    public String getStName() {
        return stName.get();
    }

    public String getStWing() {
        return stWing.get();
    }

    public String getStfAge() {
        return stfAge.get();
    }

    public String getStJob() {
        return stJob.get();
    }

    public String getStHireDate() {
        return stHireDate.get();
    }
    
    public String getStEndDate() {
        return stEndDate.get();
    }

    public String getStSalary() {
        return stSalary.get();
    }

    public Staff_Info(String stId, String stName, String stfAge, String stWing, String stJob, String stHireDate, String stEndDate, String stSalary) {
        this.stId = new SimpleStringProperty(stId);
        this.stName = new SimpleStringProperty(stName);
        this.stfAge = new SimpleStringProperty(stfAge);
        this.stWing = new SimpleStringProperty(stWing);
        this.stJob = new SimpleStringProperty(stJob);
        this.stHireDate = new SimpleStringProperty(stHireDate);
        this.stEndDate = new SimpleStringProperty(stEndDate);
        this.stSalary = new SimpleStringProperty(stSalary);
    }
    
}
