package co.edu.cue.velocerentals.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static String url = "jdbc:mysql://localhost:3306/velocerentals?serverTimezone=America/Bogota";
    private static String username = "root";
    private static String password = " ";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
