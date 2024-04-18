package Database;

import Holders.*;

import java.sql.*;
import java.util.ArrayList;

public class TaskManager {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/TM";
    private static final String DATABASE_USERNAME = "postgres";
    private static final String DATABASE_PASSWORD = "00000";

    public static ArrayList<Task> getTasks(String SQLquery, String[] params){
        ArrayList<Task> tasks = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME,
                        DATABASE_PASSWORD);
                PreparedStatement pst = connection.prepareStatement(SQLquery);
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


    public static void addTask(Task task){
        String query = "INSERT INTO tasks(\"title\", \"description\", \"creatorLogin\"," +
                " \"isCompleted\", \"expiryDate\") VALUES (?,?,?,?,?)";
        try ( Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME,
                DATABASE_PASSWORD);
              PreparedStatement pst = connection.prepareStatement(query,
                      Statement.RETURN_GENERATED_KEYS)
        )
        {
            pst.setString(1, task.getTitle());


            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int insertId = generatedKeys.getInt(1);
                    System.out.println("Record inserted successfully with ID: " + insertId);
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

    public static void eraseTask(int taskId){
        String query = "DELETE FROM tasks WHERE \"UserTaskId\" = ?";

        try ( Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME,
                DATABASE_PASSWORD);
              PreparedStatement pst = connection.prepareStatement(query);
        )
        {
            pst.setInt(1, taskId);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.printf("Records deleted %d\n", rowsAffected);
            } else {
                System.out.println("No records deleted");
            }
            connection.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void eraseTask(Task task){
        String query = "DELETE FROM tasks WHERE \"UserTaskId\" = ?";

        try ( Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME,
                DATABASE_PASSWORD);
              PreparedStatement pst = connection.prepareStatement(query);
        )
        {
            pst.setInt(1, task.getUserTaskID());
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.printf("Records deleted %d\n", rowsAffected);
            } else {
                System.out.println("No records deleted");
            }
            connection.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void changeTask(Task task){
        String query = "UPDATE tasks SET(\"title\", \"description\", \"creatorLogin\", \"isCompleted\", \"expiryDate\") = (?,?,?,?,?)" +
                " WHERE \"taskId\" = ?";

        try ( Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME,
                DATABASE_PASSWORD);
              PreparedStatement pst = connection.prepareStatement(query);
        )
        {
            pst.setString(1, task.getTitle());
            pst.setString(2, task.getDescription());
            pst.setString(3, task.getCreatorLogin());
            pst.setBoolean(4, task.isCompleted());
            pst.setTimestamp(5, task.getExpiryDate());
            pst.setInt(6, task.getTaskId());
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.printf("Records updated %d\n", rowsAffected);
            } else {
                System.out.println("No records updated");
            }
            connection.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void changeTask(int taskId, Task task){
        String query = "UPDATE tasks SET(\"title\", \"description\", \"creatorLogin\", \"isCompleted\", \"expiryDate\") = (?,?,?,?,?)" +
                " WHERE \"taskId\" = ?";

        try ( Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME,
                DATABASE_PASSWORD);
              PreparedStatement pst = connection.prepareStatement(query);
        )
        {
            pst.setString(1, task.getTitle());
            pst.setString(2, task.getDescription());
            pst.setString(3, task.getCreatorLogin());
            pst.setBoolean(4, task.isCompleted());
            pst.setTimestamp(5, task.getExpiryDate());
            pst.setInt(6, taskId);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.printf("Records updated %d\n", rowsAffected);
            } else {
                System.out.println("No records updated");
            }
            connection.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
