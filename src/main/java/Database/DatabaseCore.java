package Database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class DatabaseCore {

    protected static String databaseURL;
    protected static String databaseUsername;
    protected static String databasePassword;

    public static void loadDatabaseInfo(){
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("Database.properties");
            property.load(fis);

            databaseURL = property.getProperty("DB_URL");
            databaseUsername = property.getProperty("DB_USER");
            databasePassword = property.getProperty("DB_PASSWORD");

        } catch (IOException e) {
           throw new RuntimeException("Database login data is missing");
        }
    }

    public static boolean doesAnyExist(String SQLquery, String[] params){
        try (
                Connection connection = DriverManager.getConnection(databaseURL, databaseUsername,
                        databasePassword);
                PreparedStatement pst = connection.prepareStatement(SQLquery)
        ){
            for(int i = 0; i< params.length; i++)
                pst.setString(i+1, params[i]);
            ResultSet rs = pst.executeQuery();
            int rows = 0;
            while(rs.next()){
                rows+=1;
            }
            connection.close();
            return rows>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean doesSingleExist(String SQLquery, String[] params){
        try (
                Connection connection = DriverManager.getConnection(databaseURL, databaseUsername,
                        databasePassword);
                PreparedStatement pst = connection.prepareStatement(SQLquery)
        ){
            for(int i = 0; i< params.length; i++)
                pst.setString(i+1, params[i]);
            ResultSet rs = pst.executeQuery();
            rs.next();
            connection.close();
            return rs.isLast();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
