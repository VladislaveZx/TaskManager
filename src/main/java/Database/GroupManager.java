package Database;

import Holders.Group;

import java.sql.*;
import java.util.ArrayList;

public class GroupManager extends DatabaseCore {

    public static ArrayList<Group> getGroups(String SQLquery, String[] params){
        ArrayList<Group> groups = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(databaseURL, databaseUsername,
                        databasePassword);
                PreparedStatement pst = connection.prepareStatement(SQLquery)
        ){
            for(int i = 0; i< params.length; i++)
                pst.setString(i+1, params[i]);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int groupId = rs.getInt("groupID");
                String groupName = rs.getString("groupName");
                groups.add(new Group(groupId, groupName));
            }
            connection.close();
            return groups;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
