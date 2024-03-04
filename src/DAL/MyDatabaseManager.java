/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author haidu
 */
public class MyDatabaseManager {
    public PreparedStatement p;
    public Connection conn;
    public Statement stmt;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/school";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";

    public void connectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            stmt = conn.createStatement();
            System.out.println("connected!!!!!!!!!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Vui long mo mysql");
            Logger.getLogger(MyDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet doReadQuery(String sql) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Vui long mo mysql");
            Logger.getLogger(MyDatabaseManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
}
