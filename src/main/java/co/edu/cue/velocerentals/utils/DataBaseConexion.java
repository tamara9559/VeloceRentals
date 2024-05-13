package co.edu.cue.velocerentals.utils;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseConexion {

    public static Connection getConnection() throws SQLException, NamingException{
        Context initContext = null;
        initContext = new InitialContext();
        Context envContext  = (Context)initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource)envContext.lookup("jdbc/vsDB");
        return ds.getConnection();

    }
}

