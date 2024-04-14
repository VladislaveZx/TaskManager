package Main;

import Database.TaskManager;
import Database.UserManager;
import Holders.Task;
import Holders.User;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        try {

            ArrayList<Task> tasks = TaskManager.getUserTasks();
            for (Task task : tasks) {
                System.out.println(task);
            }

            ArrayList<User> users = UserManager.getGroupUsers();
            for (User user : users) {
                System.out.println(user);
            }

            //User user = new User("Igor", "igor");
            //String userPas = "1256560";
            //Database.addUser(user, userPas);

            Timestamp timeDate = Timestamp.valueOf("2024-09-23 10:10:10.0");
            //for(int i = 0; i<10; i++){
            //    Task testTask = new Task(String.format("Task %d", i), "Do smth", "vlad", false, timeDate);
            //    TaskManager.addTask(testTask);
            //}


            Task testTask = new Task(20, "Task 100", "Do more", "irina", false, timeDate);
            TaskManager.changeTask(testTask);
        }
        catch (RuntimeException e){
            UICallback.print(e);
        }
    }
}
