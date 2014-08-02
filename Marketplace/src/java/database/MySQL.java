/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ttu01
 */
public class MySQL {
    private final String mysql_url = "jdbc:mysql://localhost:3306/marketplace";
    private final String mysql_user = "root";
    private final String mysql_pass = "dsg@123"; 
    private Connection conn = null;
    public MySQL(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(mysql_url, mysql_user, mysql_pass);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @param sql_command
     * @return 
     * @throws SQLException
     */
    public int execute(String sql_command) throws SQLException
    {
        int updatecount = 0;
        if (conn != null) {
            Statement stmt = conn.createStatement();
            stmt.execute(sql_command);
            updatecount = stmt.getUpdateCount();
            stmt.closeOnCompletion();
        }
        return updatecount;
    }
    
    public void disconnect() throws SQLException{
        if (conn != null) {
            conn.close();
        }
    }
    
    public ResultSet query(String sql_command) throws SQLException{
        ResultSet result = null;
        if (conn != null) {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery(sql_command);
            stmt.closeOnCompletion();
        }
        return result;
    }
}
