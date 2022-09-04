/*
 * Life sucks....
 */
package hall_management.database;

import hall_management.gui.alerts.AlertMaker;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Abhik_Blaze
 */
public class connector {
    
    //private static final String url = "jdbc:oracle:thin:blaze/blaze@localhost:1521/orclpdb:";
    private static String url = "jdbc:oracle:thin:@localhost:1521:orcl2" ;
    private static String user = "sys as SYSDBA" ;
    private static String pass = "papan" ;
    private static String driver = "oracle.jdbc.OracleDriver" ;
    private static Connection con = null ;
    public static Statement stmt = null ;
    public static PreparedStatement pst = null ;
    public static CallableStatement cst = null ;
    
    public static Connection getConnection()
    {
        try {
            //Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);
//            con = DriverManager.getConnection(url);
        } catch (Exception ex) {
            AlertMaker.showErrorMessage(ex, "Database Error", "Couldn't access database.");
            System.exit(0);
        }
        return con ;
    }
    
    public static void closeConnection()
    {
        if(con != null){
            try {
                con.close();
                con = null ; 
            } catch (SQLException ex) {
               Logger.getLogger(connector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    public static ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = con.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            AlertMaker.showErrorMessage(ex, "Exception at execQuery:connector", ex.getLocalizedMessage());
            return null;
        } 
        return result;
    }

    public static boolean execAction(String qu) {
        try {
            stmt = con.createStatement();
            stmt.execute(qu);
            return true;
        } catch (SQLException ex) {
            AlertMaker.showErrorMessage(ex, "Exception at execAction:connector", ex.getLocalizedMessage());
            return false;
        } 
    }
    
    public static PreparedStatement getPST(String sql) {
        
        try {
            pst = con.prepareStatement(sql) ;
        } catch (SQLException ex) {
            Logger.getLogger(connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pst ;
        
    }
    
    public static CallableStatement getCST(String sql)  {
        
        try {
            cst = con.prepareCall(sql) ;
        } catch (SQLException ex) {
            Logger.getLogger(connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cst ;
        
    }
  
}
