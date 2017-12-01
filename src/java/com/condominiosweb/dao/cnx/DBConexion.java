package com.condominiosweb.dao.cnx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConexion {
    private static DBConexion instance = new DBConexion();
    
    private static String DATABASE_URL;
    private static String DATABASE_USER;
    private static String DATABASE_PASS;
    private static String DATABASE_DRIVER;
    
    ResourceBundle rb = ResourceBundle.getBundle("conf");
    
    private DBConexion(){
        try {
            DATABASE_URL = rb.getString("database.url");
            DATABASE_USER = rb.getString("database.user");
            DATABASE_PASS = rb.getString("database.pass");
            DATABASE_DRIVER = rb.getString("database.driver");
            Class.forName(DATABASE_DRIVER);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }
    
    public static Connection getConnection() {
        return instance.createConnection();
    }
}
