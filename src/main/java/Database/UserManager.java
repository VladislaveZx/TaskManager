package Database;
import Main.UICallback;
import Holders.*;

import java.sql.*;
import java.util.ArrayList;

public class UserManager {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/TM";
    private static final String DATABASE_USERNAME = "postgres";
    private static final String DATABASE_PASSWORD = "00000";

    public static ArrayList<User> getUsers(String SQLquery, String[] params){
        ArrayList<User> users = new ArrayList<>();
        try (   Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME,
                DATABASE_PASSWORD);
                PreparedStatement pst = connection.prepareStatement(SQLquery);
             ){
            for(int i =0; i< params.length; i++){
                pst.setString(i+1, params[i]);
            }
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String userName = rs.getString("userName");
                String userLogin = rs.getString("userLogin");
                users.add(new User(userName, userLogin));
            }
            connection.close();
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isUserExists(User user){
        String query = "SELECT * FROM users\n WHERE \"userLogin\" = ?";
        try (   Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME,
                DATABASE_PASSWORD);
                PreparedStatement pst = connection.prepareStatement(query);
                ){
            pst.setString(1, user.getUserLogin());
            ResultSet rs = pst.executeQuery();
            rs.next();
            connection.close();
            return rs.isLast();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addUser(User user, String userPassword){
        if(isUserExists(user)) {
            UICallback.print("User already exists");
            return;
        }
        String query = "INSERT INTO users(\"userName\", \"userLogin\", \"userPassword\") VALUES (?,?,?)";
        try ( Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME,
                DATABASE_PASSWORD);
                PreparedStatement pst = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)
        )
        {
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getUserLogin());
            pst.setString(3, userPassword);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    String insertLogin = generatedKeys.getString("userLogin");
                    System.out.println("Record inserted successfully with ID: " + insertLogin);
                } else {
                    System.out.println("Failed to retrieve insert ID.");
                }
            } else {
                System.out.println("No records inserted.");
            }
            connection.close();
            return;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void eraseUser(User user){
        if(!isUserExists(user)) throw new RuntimeException("User doesn't exist");
        String query = "DELETE FROM users WHERE \"userLogin\" = ?";

        try ( Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME,
                DATABASE_PASSWORD);
              PreparedStatement pst = connection.prepareStatement(query)
        )
        {
            pst.setString(1, user.getUserLogin());
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.printf("Records deleted %d\n", rowsAffected);
            } else {
                System.out.println("No records deleted.");
            }
            connection.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
