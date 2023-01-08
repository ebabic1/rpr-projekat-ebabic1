package ba.unsa.etf.rpr.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private  Connection connection = null;
    public ConnectionManager() throws SQLException, IOException {
        final Properties login = new Properties();
        login.load(new FileInputStream("src/main/java/ba/unsa/etf/rpr/login.properties"));
        connection = DriverManager.getConnection(login.getProperty("connection.string"), login.getProperty("username"), login.getProperty("password"));
    }

}
