package Database;

import Holders.*;

import java.sql.*;
import java.util.ArrayList;

public class TaskManager extends DatabaseCore {

    public static ArrayList<Task> getTasks(String SQLquery, String[] params){
        ArrayList<Task> tasks = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(databaseURL, databaseUsername,
                        databasePassword);
                PreparedStatement pst = connection.prepareStatement(SQLquery)
        ){
            for(int i = 0; i< params.length; i++)
                pst.setString(i+1, params[i]);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int userTaskId = rs.getInt("UserTaskId");
                String creatorLogin = rs.getString("CreatorLogin");
                int creatorGroupId = rs.getInt("CreatorGroupId");
                String taskName = rs.getString("TaskName");
                String taskDescription = rs.getString("TaskDescription");
                TaskPriority taskPriority = TaskPriority.castFromInt(rs.getInt("TaskPriority"));
                boolean taskStatus = rs.getBoolean("TaskStatus");
                Timestamp taskExpiryDate = rs.getTimestamp("TaskExpiryDate");
                tasks.add(new Task(userTaskId, creatorLogin, creatorGroupId, taskName, taskDescription, taskPriority, taskStatus, taskExpiryDate));
            }
            connection.close();
            return tasks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean addTask(Task task){
        String query = "INSERT INTO tasks(\"CreatorLogin\", \"CreatorGroupID\", \"TaskName\"," +
                "\"TaskDescription\", \"TaskPriority\", \"TaskStatus\", \"TaskExpiryDate\") VALUES (?,?,?,?,?,?,?)";
        try ( Connection connection = DriverManager.getConnection(databaseURL, databaseUsername,
                databasePassword);
              PreparedStatement pst = connection.prepareStatement(query,
                      Statement.RETURN_GENERATED_KEYS)
        )
        {
            pst.setString(1, task.getCreatorLogin());
            pst.setInt(2, task.getCreatorGroupID());
            pst.setString(3, task.getTaskName());
            pst.setString(4, task.getTaskDescription());
            pst.setInt(5, task.getTaskPriority().ordinal());
            pst.setBoolean(6, task.getTaskStatus());
            pst.setTimestamp(7, task.getTaskExpiryDate());

            int rowsAffected = pst.executeUpdate();

            return rowsAffected > 0;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean eraseTask(Task task){
        String query = "DELETE FROM tasks WHERE \"UserTaskId\" = ?";

        try ( Connection connection = DriverManager.getConnection(databaseURL, databaseUsername,
                databasePassword);
              PreparedStatement pst = connection.prepareStatement(query)
        )
        {
            pst.setInt(1, task.getUserTaskID());
            int rowsAffected = pst.executeUpdate();

            return rowsAffected > 0;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean changeTask(Task task){
        String query = "UPDATE tasks SET(\"CreatorLogin\", \"CreatorGroupId\", \"TaskName\", \"TaskDescription\"," +
                " \"TaskPriority\", \"TaskStatus\", \"TaskExpiryDate\") = (?,?,?,?,?,?,?)" +
                " WHERE \"taskId\" = ?";

        try ( Connection connection = DriverManager.getConnection(databaseURL, databaseUsername,
                databasePassword);
              PreparedStatement pst = connection.prepareStatement(query)
        )
        {
            pst.setString(1, task.getCreatorLogin());
            pst.setInt(2, task.getCreatorGroupID());
            pst.setString(3, task.getTaskName());
            pst.setString(4, task.getTaskDescription());
            pst.setInt(5, task.getTaskPriority().ordinal());
            pst.setBoolean(6, task.getTaskStatus());
            pst.setTimestamp(7, task.getTaskExpiryDate());
            pst.setInt(8, task.getUserTaskID());
            int rowsAffected = pst.executeUpdate();

            return rowsAffected > 0;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
