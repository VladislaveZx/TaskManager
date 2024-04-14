package Holders;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Task {
    private int taskId;
    private String title;
    private String description;
    private String creatorLogin;
    private boolean isCompleted;
    private Timestamp expiryDate;

    public Task(int taskId, String title, String description, String creatorLogin, boolean isCompleted, Timestamp expiryDate) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.creatorLogin = creatorLogin;
        this.isCompleted = isCompleted;
        this.expiryDate = expiryDate;
    }

    public Task(String title, String description, String creatorLogin, boolean isCompleted, Timestamp expiryDate) {
        this.taskId = -1;
        this.title = title;
        this.description = description;
        this.creatorLogin = creatorLogin;
        this.isCompleted = isCompleted;
        this.expiryDate = expiryDate;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatorLogin() {
        return creatorLogin;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Task{" +
                "taskId=" + taskId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creatorLogin='" + creatorLogin + '\'' +
                ", isCompleted=" + isCompleted +
                ", expiryDate=" + formater.format(expiryDate) +
                '}';
    }
}
