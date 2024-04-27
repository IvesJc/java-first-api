package org.venda.infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OracleDBConfiguration {

    public OracleDBConfiguration() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("database" +
                ".properties")){
            Properties prop = new Properties();

            if (inputStream == null){
                System.out.println("Unable to find file");
            }
            prop.load(inputStream);
            URL_CONNECTION = prop.getProperty("database.url");
            USER= prop.getProperty("database.user");
            PASSWORD = prop.getProperty("database.password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String URL_CONNECTION;
    public static String USER;
    public static String PASSWORD;

    public Connection getConnection(){
        try {
            return DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
