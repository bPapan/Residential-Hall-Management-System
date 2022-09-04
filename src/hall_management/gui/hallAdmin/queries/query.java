/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.gui.hallAdmin.queries;

import hall_management.gui.extras.utilities;
import hall_management.gui.hallAdmin.HallAdmin_viewController;
import java.sql.Date;

/**
 *
 * @author papan
 */
public class query {
    
    private static String str = null;
    
    public static String addStaff(String id, String name, String hid, String job, String age, String salary, String wing,String hDate, String eDate)
    {
        if(Integer.parseInt(hid)==5)
        {
            if(eDate==null)
            {
                str = "INSERT INTO STAFFS VALUES ( "
                        + ""  + hid + ","
                        + "'" + id  + "',"
                        + "'" + wing + "',"
                        + "'" + name + "',"
                        + ""  + age  + ","
                        + "'" + job + "',"
                        + "TO_DATE('" + hDate + "','YYYY-MM-DD'),"
                        + "NULL,"
                        + "" + salary 
                    + ")" ;
            
            }
            if(eDate!=null)
            {
                str = "INSERT INTO STAFFS VALUES ( "
                        + ""  + hid + ","
                        + "'" + id  + "',"
                        + "'" + wing + "',"
                        + "'" + name + "',"
                        + ""  + age  + ","
                        + "'" + job + "',"
                        + "TO_DATE('" + hDate + "','YYYY-MM-DD'),"
                        + "TO_DATE('" + eDate + "','YYYY-MM-DD'),"
                        + "" + salary
                    + ")" ;
            
            }
        }
        if(Integer.parseInt(hid)!=5)
        {
            if(eDate==null)
            {
                str = "INSERT INTO STAFFS VALUES ( "
                        + ""  + hid + ","
                        + "'" + id  + "',"
                        + "NULL,"
                        + "'" + name + "',"
                        + ""  + age  + ","
                        + "'" + job + "',"
                        + "TO_DATE('" + hDate + "','YYYY-MM-DD'),"
                        + "NULL,"
                        + "" + salary
                    + ")" ;
            
            }
            if(eDate!=null)
            {
                str = "INSERT INTO STAFFS VALUES ( "
                        + ""  + hid + ","
                        + "'" + id  + "',"
                        + "NULL,"
                        + "'" + name + "',"
                        + ""  + age  + ","
                        + "'" + job + "',"
                        + "TO_DATE('" + hDate + "','YYYY-MM-DD'),"
                        + "TO_DATE('" + eDate + "','YYYY-MM-DD'),"
                        + "" + salary
                    + ")" ;
            
            }
        }
        return str;
    }
    public static String assignRoom(String id, String sId, int hId, String rId, String stDate, String eDate)
    {
        if(eDate==null)
        {
            str= "INSERT INTO ASSIGNED_ROOM VALUES ( "
                    + "" +id + ","
                    + "'" +sId+"',"
                    + "" + hId+","
                    + "" + hId+","
                    + "'"+ rId+"',"
                    + "TO_DATE('" + stDate +"','YYYY-MM-DD'),"
                    + "NULL)";        
        }
        if(eDate!=null)
        {
            str= "INSERT INTO ASSIGNED_ROOM VALUES ( "
                    + "" +id + ","
                    + "'" +sId+"',"
                    + "" + hId+","
                    + "" + hId+","
                    + "'"+ rId+"',"
                    + "TO_DATE('" + stDate +"','YYYY-MM-DD'),"
                    + "TO_DATE('" + eDate +"','YYYY-MM-DD')";        
        }
        return str;
        
    }
    public static String getStaffTableData(int hID)
    {
        str = "SELECT ST_ID, ST_NAME, ST_AGE, ST_WING, ST_JOB_ID, ST_H_DATE, ST_E_DATE, ST_SALARY "
                + "FROM STAFFS "
                + "WHERE H_ID = "
                + hID
                //+ " ORDER BY ST_ID" 
                ;
        
        return str;
    }
    public static String getStaffCount(int hID)
    {
        str = "SELECT COUNT(*)+ 1 FROM STAFFS" 
                + " WHERE H_ID = " + hID;
        return str ;
    }
    public static String getAssignCount()
    {
        str = "SELECT COUNT(*)+ 2 FROM ASSIGNED_ROOM";
        return str ;
    }
    public static String getCurrentRoom(String sId)
    {
        str = "SELECT R_ID\n" +
                "FROM ASSIGNED_ROOM\n" +
                "WHERE S_ID = '" + sId + "'\n" +
                "AND ASS_ROOM_EDATE IS NULL";
        return str;        
    }
    public static String getSponsorCount()
    {
        str = "SELECT COUNT(*)+ 1 FROM SPONSORS" ;
        return str ;
    }
    public static String addSponsor(int spId, String spName, String manName, String manContact)
    {
        str="INSERT INTO SPONSORS VALUES ("
                        + spId + ","
                + "'"   + spName + "',"
                + "'"   + manName + "',"
                + "'"   + manContact + "')"
                ;
        return str;
    }
    public static String loadSponsorTableData()
    {
        str = "SELECT * FROM SPONSORS ";
        return str;
    }
    public static String updateSponsorData(int spId, String spName, String manName, String manContact)
    {
        str = "UPDATE SPONSORS "
                + " SET SP_MANAGER = '" + manName +"', SP_CONTACT = '" + manContact +"'"
                + " WHERE SP_ID = " + spId;
        return str;
    }
    public static String updateStaffQuery(String stID, int hid, String jobId, int sal, String enDate)
    {
        if(enDate==null)
        {
            str = "UPDATE STAFFS " + "SET ST_JOB_ID = '" + jobId +"', ST_SALARY = "+ sal +" WHERE ST_ID = " + stID + " AND H_ID = " +hid ;
        }
        if(enDate!=null)
        {
            str = "UPDATE STAFFS " + "SET ST_JOB_ID = '" + jobId +"', ST_SALARY = "+ sal +" ,ST_E_DATE = TO_DATE('"+enDate +"','YYYY-MM-DD') WHERE ST_ID = " + stID + " AND H_ID = " +hid ;
        }
        return str;
    }
    public static String getHallStudentTableData(int hid)
    {
        if(hid==5)
        {
            str =   "SELECT SUBSTR(S.S_ID,4),S.S_NAME, AH.ASSIGNED_S_TYPE, R.R_WING, AR.R_ID,  S.S_STATUS, S.S_MOBILE \n" +
                    "FROM STUDENTS S, ASSIGNED_HALL AH, ASSIGNED_ROOM AR, ROOMS R\n" +
                    "WHERE S.S_ID=AH.S_ID AND S.S_ID = AR.S_ID AND R.R_ID=AR.R_ID AND R.R_WING IN('WEST','NORTH','BOTH')\n" +
                    "AND AH.H_ID = 5 AND AH.ASSIGNED_CURRENT='YES' AND " +
                    "((AR.ASS_ROOM_EDATE is null AND S.S_STATUS='Running') OR (AR.ASS_ROOM_EDATE is not null AND S.S_STATUS='Alumni'))\n" +
                    "UNION\n" +
                    "SELECT SUBSTR(S.S_ID,4),S.S_NAME, AH.ASSIGNED_S_TYPE,null, null, S.S_STATUS, S.S_MOBILE\n" +
                    "FROM STUDENTS S, ASSIGNED_HALL AH\n" +
                    "WHERE S.S_ID=AH.S_ID AND AH.ASSIGNED_S_TYPE = 'Attached' AND AH.H_ID ="
                    + hid
                    + " AND AH.ASSIGNED_CURRENT='YES'";
        }
        else
        {
            str =   "SELECT SUBSTR(S.S_ID,4),S.S_NAME, AH.ASSIGNED_S_TYPE, null, AR.R_ID, S.S_STATUS, S.S_MOBILE \n" +
                    "FROM STUDENTS S, ASSIGNED_HALL AH, ASSIGNED_ROOM AR\n" +
                    "WHERE S.S_ID=AH.S_ID AND S.S_ID = AR.S_ID AND AH.H_ID =" + hid +  " AND AH.ASSIGNED_CURRENT='YES' AND ((AR.ASS_ROOM_EDATE is null AND S.S_STATUS='Running') OR (AR.ASS_ROOM_EDATE is not null AND S.S_STATUS='Alumni'))\n" +
                    "UNION\n" +
                    "SELECT SUBSTR(S.S_ID,4),S.S_NAME, AH.ASSIGNED_S_TYPE,null, null, S.S_STATUS, S.S_MOBILE\n" +
                    "FROM STUDENTS S, ASSIGNED_HALL AH\n" +
                    "WHERE S.S_ID=AH.S_ID AND AH.ASSIGNED_S_TYPE = 'Attached' AND AH.H_ID =" + hid + " AND AH.ASSIGNED_CURRENT='YES'";
        }
        return str;
    }
    public static String getAttachedCount(int hid)
    {
        str = "SELECT COUNT(*) \n" +
                "FROM STUDENTS S JOIN ASSIGNED_HALL AH\n" +
                "ON(S.S_ID = AH.S_ID)\n" +
                "WHERE AH.H_ID =" + hid + "\n" +
                " AND S.S_STATUS = 'Running' AND AH.ASSIGNED_S_TYPE = 'Attached' ";
        return str;
    }
    public static String getResidentCount(int hid)
    {
        str = "SELECT COUNT(*) \n" +
                "FROM STUDENTS S JOIN ASSIGNED_HALL AH\n" +
                "ON(S.S_ID = AH.S_ID)\n" +
                "WHERE AH.H_ID =" + hid + "\n" +
                " AND S.S_STATUS = 'Running' AND AH.ASSIGNED_S_TYPE = 'Resident' ";
        return str;
    }
    public static String addRoom(int hid, String rid, String rWing, int rCapacity)
    {
        if(hid!=5 || rWing.equals("Not Applicable"))
        {
            str = "INSERT INTO ROOMS VALUES("
                    + "'" +rid +"',"
                    + hid + ","
                    + rCapacity + ","
                    + "null" + ","
                    + "'YES')";
        }
        else
        {
            str = "INSERT INTO ROOMS VALUES("
                    + "'" +rid +"',"
                    + hid + ","
                    + rCapacity + ","
                    + "upper('"+ rWing + "')" + ","
                    + "'YES')";
        }
        return str;
    }
    public static String searchRoom(int hid, int rid)
    {
        str = "SELECT * FROM ROOMS WHERE R_ID = '" + rid + "' AND H_ID = " + hid;
        return str;
    }
    
    public static String getRoomsTableData(int hid)
    {
        str= "SELECT R.R_ID, R.R_WING, R.R_CAPACITY, GET_OCCUPIED_SEATS_CNT(R.R_ID, R.H_ID), R.R_AVAILABILITY\n" +
            "FROM ROOMS R\n" +
            "WHERE R.H_ID= " + hid;
        return str;
    }
    public static String getAvailableRooms(int hid)
    {
        str =   "SELECT COUNT(*)\n" +
                "FROM ROOMS R\n" +
                "WHERE R.R_AVAILABILITY = 'YES' AND R.H_ID = " + hid ;
        return str;
    }
    public static String getRoomsCount(int hid)
    {
        str =   "SELECT COUNT(*)\n" +
                "FROM ROOMS R\n" +
                "WHERE R.H_ID = " + hid ;
        return str;
    }
    public static String loadEventsData(int hid)
    {
        str =   "SELECT EI.E_ID, E.E_NAME, EI.E_INFO_SDATE, EI.E_INFO_EDATE\n" +
                "FROM EVENTS E, EVENT_INFO EI\n" +
                "WHERE E.E_ID =EI.E_ID AND EI.H_ID = " +  hid ;
        return str;
    }
    public static String loadEventsCount()
    {
        str = "SELECT COUNT (*) +1 FROM EVENTS";
        return str;
    }
    public static String addEvent(int eId, String eName)
    {
        str = "INSERT INTO EVENTS VALUES (" + eId +",'" + eName + "')";
        return str;
    }
    public static String addEventInfo(int eId, int hid, String eSDate, String eEDate)
    {
        if(eEDate!=null)
            str = "INSERT INTO EVENT_INFO VALUES(" + eId + "," + hid +","
                + "TO_DATE('" + eSDate +"','YYYY-MM-DD'),"
                + "TO_DATE('" + eEDate +"','YYYY-MM-DD'))";
        if(eEDate==null)
            str = "INSERT INTO EVENT_INFO VALUES(" + eId + "," + hid +","
                + "TO_DATE('" + eSDate +"','YYYY-MM-DD'),"
                + "null)";
        return str;
    }
    public static String updateEventInfo(int eId, int hid, String eEDate)
    {
        if(eEDate==null)
            str ="UPDATE EVENT_INFO SET E_INFO_EDATE = null WHERE E_ID = " + eId + " AND H_ID = " + hid;
        else
            str ="UPDATE EVENT_INFO SET E_INFO_EDATE = TO_DATE('" + eEDate +"','YYYY-MM-DD')WHERE E_ID = " + eId + " AND H_ID = " + hid;
        return str;
    }
    public static String loadSpnsrshipData(int hid)
    {
        str =   "SELECT E.E_NAME, EXTRACT(YEAR FROM EI.E_INFO_EDATE), S.SP_NAME, SP.SPON_CMNT, SP.SPON_AMNT\n" +
                "FROM SPONSORSHIP SP, SPONSORS S, EVENTS E, EVENT_INFO EI\n" +
                "WHERE E.E_ID  = SP.E_ID AND S.SP_ID = SP.SP_ID AND EI.E_ID = E.E_ID AND EI.H_ID = SP.SP_H_ID AND SP.SP_H_ID = "
                + hid;
        return str;
    }
    public static String addSponsorship(int eid, int spid, int amnt, String cmnt, int hid)
    {
        if(amnt!=0)
            str = "INSERT INTO SPONSORSHIP VALUES(" + spid + "," + eid + "," + amnt + ",'" +cmnt + "'," + hid + ")";
        else
            str = "INSERT INTO SPONSORSHIP VALUES(" + spid + "," + eid + ",null,'" +cmnt + "'," + hid + ")";
        return str;
    }
    public static String checkEventinHall(int eid, int hid)
    {
        str = "SELECT * FROM EVENT_INFO WHERE E_ID = " + eid + " AND H_ID = " +hid ;
        return str;
    }
}
