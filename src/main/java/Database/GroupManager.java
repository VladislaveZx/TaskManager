package Database;

import Holders.Group;

import java.sql.*;
import java.util.ArrayList;

public class GroupManager {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/TM";
    private static final String DATABASE_USERNAME = "postgres";
    private static final String DATABASE_PASSWORD = "00000";

    public static ArrayList<Group> getGroups(String SQLquery, String[] params){
        ArrayList<Group> groups = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME,
                        DATABASE_PASSWORD);
                PreparedStatement pst = connection.prepareStatement(SQLquery);
        ){
            for(int i = 0; i< params.length; i++)
                pst.setString(i+1, params[i]);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String groupName = rs.getString("groupName");
                groups.add(new Group(groupName));
            }
            connection.close();
            return groups;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
