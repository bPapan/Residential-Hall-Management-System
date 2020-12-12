/*
 * Life sucks....
 */
package hall_management.gui.admin.queries;

import java.util.Date;

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
    
    
    
}
