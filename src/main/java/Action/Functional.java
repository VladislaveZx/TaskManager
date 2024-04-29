package Action;

import Database.DatabaseCore;
import Database.TaskManager;
import Database.UserManager;
import Holders.AppUser;
import Holders.Task;
import Holders.User;
import Main.Input;

import java.util.ArrayList;

public class Functional {

    public static void showUserTasks() {
        ArrayList<Task> tasks = TaskManager.getTasks(SQLQuery.GET_TASK_OF_USER.toString(), new String[0]);
        if(tasks.isEmpty()) {
            System.out.println("No tasks found");
            return;
        }
        System.out.println("Tasks:");
        for (Task task : tasks)
            System.out.println(task);
    }

    public static void showUsersFromAppUserGroup(){
        System.out.println("Users:");
        ArrayList<User> users = UserManager.getUsers(SQLQuery.GET_USERS_OF_GROUP.toString(), new String[0]);
        for (User user : users)
            System.out.println(user);
    }

    public static void reloginUser(){
        System.out.println("Changing user");
        AppUser.setIsUserLogged(false);

        while(!AppUser.getIsUserLogged())
        {
            System.out.println("Log in:");
            Input.skipLine();
            LoginService.loginUser();
            AppUser.setIsUserLogged(LoginService.accountFound());
        }
        LoginService.saveUserData();
        System.out.println("LOGGED UNDER: " + AppUser.getUserLogin());
    }
}
