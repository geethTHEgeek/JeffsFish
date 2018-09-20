package com.jfs.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private Connection connection;

    public Connection getConnection(){
        String dbName="tutorial";
        String userName="root";
        String password="";

        try {
            Class.forName("com.mysql.jdbc.Driver");

           connection= DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
