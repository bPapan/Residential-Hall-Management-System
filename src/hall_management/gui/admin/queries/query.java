/*
 * Life sucks....
 */
package hall_management.gui.admin.queries;

/**
 *
 * @author Abhik_Blaze
 */
public class query {
    
    private static String str = null ;
    
    public static String addStudent(String s_id , String s_name, String s_father , String s_mother , String s_gender , String s_religion , String s_mobile , String s_bld_grp , String s_prm_addr)
    {
        String s_status = "Running" ;
        
        str = "INSERT INTO STUDENTS VALUES ( "
                + "'" + s_id + "',"
                + "'" + s_name + "',"
                + "'" + s_father + "',"
                + "'" + s_mother + "',"
                + "'" + s_gender + "',"
                + "'" + s_religion + "',"
                + "'" + s_mobile + "',"
                + "'" + s_bld_grp + "',"
                + "'" + s_prm_addr + "',"
                + "'" + s_status + "'"
                + ")" ;
        return str ;
    }
    
    public static String addTeacher(String t_id , String t_name , String t_desig )
    {
        str = "INSERT INTO TEACHERS VALUES ( "
                + t_id + ","
                + "'" + t_name + "',"
                + "'" + t_desig + "'"
                + ")" ;
        
        return str;
    }
    
    public static String addHall(String h_id , String h_name , String h_type )
    {
        str = "INSERT INTO HALL VALUES ( "
                + h_id + ","
                + "'" + h_name + "',"
                + "'" + h_type + "'"
                + ")" ;
        
        return str;
    }
    
    public static String addFee(String f_id, String f_name, String f_amount , String f_category , String f_time )
    {
        str = "INSERT INTO FEES VALUES( "
                + f_id + ","
                + "'" + f_name + "',"
                + f_amount + ","
                + "'" + f_category + "',"
                + "'" + f_time + "'"
                + ")" ;
        return str; 
    }
    
    
    public static String getTeacherCount()
    {
        str = "SELECT COUNT(*)+ 1 FROM TEACHERS" ;
        return str ;
    }
    
    public static String getHallCount()
    {
        str = "SELECT COUNT(*) + 1 FROM HALL" ;
        return str ;
    }
    
    public static String getFeeCount()
    {
        str = "SELECT COUNT(*) + 1 FROM FEES" ;
        return str ;
    }
    
    public static String getHallTableData()
    {
        str = "SELECT H_ID , H_NAME , H_TYPE, GET_CAPACITY_COUNT(H_ID) , GET_OCCUPIED_SEAT_COUNT(H_ID) , GET_ATTACHED_COUNT(H_ID) FROM HALL ORDER BY H_ID" ;
        return str ;
    }
    
    public static String getTeacherTableData()  // contains join query
    {
        str = "SELECT T.T_ID , T.T_NAME , T.T_DESIG , H.H_NAME , D.DES_ADMIN_ROLE \n" +
              "FROM TEACHERS T\n" +
              "LEFT OUTER JOIN DESIGNATED_HALL D\n" +
              "ON( T.T_ID = D.T_ID AND D.DES_ADMIN_RDATE IS NULL )\n" +
              "LEFT OUTER JOIN HALL H\n" +
              "ON ( D.H_ID = H.H_ID )\n" +
              "ORDER BY TO_NUMBER(T.T_ID) ASC " ;
        
        return str ;
              
    }
    
    public static String getFeeTableData()
    {
        str = "SELECT * FROM FEES ORDER BY F_ID" ;
        return str ;
    }
    
    public static String getStudentTableData() //contains sub-query
    {
        
        str = "SELECT S.S_ID ,S.S_NAME , T.NAME , T.TYPE , S.S_STATUS \n" +
               "FROM STUDENTS S ,\n" +
                "(\n" +
                "       SELECT H.H_NAME NAME , AH.ASSIGNED_S_TYPE TYPE , AH.S_ID ID\n" +
                "	FROM ASSIGNED_HALL AH\n" +
                "	JOIN HALL H\n" + 
                "	ON( AH.H_ID = H.H_ID ) WHERE AH.ASSIGNED_CURRENT = 'YES'\n" +
                ") T\n" +
                "WHERE S.S_ID = T.ID" ;
        return str ;
    }
    
    
    public static String getPaymentTableData() {
        str = "SELECT DISTINCT S_ID , S_F_MONTH , S_F_YEAR , GET_FEE(S_ID,S_F_MONTH,S_F_YEAR) , GET_FINE(S_ID, S_F_MONTH,S_F_YEAR) , S_F_PDATE\n" +
                "FROM STUDENTS_FEES WHERE S_F_PDATE IS NULL" ;
        return str; 
    }
    
}
