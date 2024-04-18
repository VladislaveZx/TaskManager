package Holders;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Task {
    private int userTaskID;
    private String creatorLogin;
    private int creatorGroupID;
    private String taskName;
    private String taskDescription;
    TaskPriority taskPriority;
    private boolean taskStatus;
    private Timestamp taskExpiryDate;

    public Task(int userTaskID, String creatorLogin, int creatorGroupID, String taskName,
                String taskDescription, TaskPriority taskPriority, boolean taskStatus,
                Timestamp taskExpiryDate) {
        this.userTaskID = userTaskID;
        this.creatorLogin = creatorLogin;
        this.creatorGroupID = creatorGroupID;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskPriority = taskPriority;
        this.taskStatus = taskStatus;
        this.taskExpiryDate = taskExpiryDate;
    }

    public Task(String creatorLogin, int creatorGroupID, String taskName,
                String taskDescription, TaskPriority taskPriority, boolean taskStatus,
                Timestamp taskExpiryDate) {
        this.userTaskID = -1;
        this.creatorLogin = creatorLogin;
        this.creatorGroupID = creatorGroupID;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskPriority = taskPriority;
        this.taskStatus = taskStatus;
        this.taskExpiryDate = taskExpiryDate;
    }

    public int getUserTaskID() {
        return userTaskID;
    }

    public String getCreatorLogin() {
        return creatorLogin;
    }

    public int getCreatorGroupID() {
        return creatorGroupID;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public boolean isTaskStatus() {
        return taskStatus;
    }

    public Timestamp getTaskExpiryDate() {
        return taskExpiryDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Task{" +
                "userTaskID=" + userTaskID +
                ", creatorLogin='" + creatorLogin + '\'' +
                ", creatorGroupID=" + creatorGroupID +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskPriority=" + taskPriority +
                ", taskStatus=" + taskStatus +
                ", taskExpiryDate=" + formater.format(taskExpiryDate) +
                '}';
    }
}
