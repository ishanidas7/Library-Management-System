package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {

        // ✅ Explicitly load MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/librarydb?useSSL=false&serverTimezone=UTC",
            "root",
            "qwertyop"
        );
    }
}



