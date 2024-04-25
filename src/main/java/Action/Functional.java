package Action;

import Database.DatabaseCore;
import Database.TaskManager;
import Database.UserManager;
import Holders.AppUser;
import Holders.Task;
import Holders.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Functional {
    public static void showUserTasks() {
        System.out.println("Tasks:");
        String getTasks = "SELECT * FROM tasks";//выбарать задачи пользователя
        ArrayList<Task> tasks = TaskManager.getTasks(getTasks, new String[0]);
        for (Task task : tasks)
            System.out.println(task);
    }

    public static void showUsersFromAppUserGroup(){
        System.out.println("Users:");
        String getUsers = "SELECT * FROM users"; //выбарать пользователей группы
        ArrayList<User> users = UserManager.getUsers(getUsers, new String[0]);
        for (User user : users)
            System.out.println(user);
    }

    public static void reloginUser(Scanner scanner){
        boolean isDataFirstEntered = false;
        while(!isDataFirstEntered || !DatabaseCore.doesSingleExist(SQLQuery.LOGIN_USER.toString(), new String[]{AppUser.getUserLogin(), AppUser.getUserPassword()})){
            System.out.println(isDataFirstEntered?"Try again":"Log in:");
            if(!isDataFirstEntered) scanner.nextLine();
            LoginService.enterLoginData(scanner);
            isDataFirstEntered = true;
        }
        LoginService.saveUserData();
        System.out.println("LOGGED UNDER: " + AppUser.getUserLogin());
    }
}
