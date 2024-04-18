package Database;

import Holders.Task;
import Holders.User;

import java.sql.Timestamp;
import java.util.Random;

public class Test {
    final static String[] USER_NAMES = {"Irina", "Vladislav", "Nikita", "Sasha", "Michail", "Artiom"};
    final static String[] LOGINS = {"irina", "vlad", "nikita", "sasha", "misha", "artiom"};
    final static String[] PASSWORDS = {"1234", "1516", "8211", "1567", "1278", "1597"};

    public static void addTestTasks(int tasksCount){
        Timestamp timeDate = Timestamp.valueOf("2024-09-23 10:10:10.0");
        Random rd = new Random();
        for(int i =0; i<tasksCount; i++){
            Task testTask = new Task(String.format("Task %d", i+1), "Test task description", LOGINS[rd.nextInt(0,LOGINS.length-1)], false, timeDate);
            TaskManager.addTask(testTask);
        }
    }

    public static void addTestUsers(){
        for(int i = 0; i<USER_NAMES.length; i++){
            User user = new User(USER_NAMES[i], LOGINS[i]);
            UserManager.addUser(user, PASSWORDS[i]);
        }
    }
}
