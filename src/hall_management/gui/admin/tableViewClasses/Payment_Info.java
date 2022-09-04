/*
 * Life sucks....
 */
package hall_management.gui.admin.tableViewClasses;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Abhik_Blaze
 */
public class Payment_Info {
    
    private final SimpleStringProperty psid ;
    private final SimpleStringProperty pmonth ;
    private final SimpleStringProperty pyear ;
    private final SimpleStringProperty pfeeamount ;
    private final SimpleStringProperty pfine ;
    private final SimpleStringProperty pstatus ;

    public String getPsid() {
        return psid.get();
    }

    public String getPmonth() {
        return pmonth.get();
    }

    public String getPyear() {
        return pyear.get();
    }

    public String getPfeeamount() {
        return pfeeamount.get();
    }

    public String getPfine() {
        return pfine.get();
    }

    public String getPstatus() {
        return pstatus.get();
    }

    public Payment_Info(String psid, String pmonth, String pyear, String pfeeamount, String pfine, String pstatus) {
        this.psid = new SimpleStringProperty(psid);
        this.pmonth = new SimpleStringProperty(pmonth);
        this.pyear = new SimpleStringProperty(pyear);
        this.pfeeamount = new SimpleStringProperty(pfeeamount);
        this.pfine = new SimpleStringProperty(pfine);
        this.pstatus = new SimpleStringProperty(pstatus);
    }
    
    
    
}
