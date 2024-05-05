package Action;

import Database.TaskManager;
import Database.UserManager;
import Holders.AppUser;
import Holders.Task;
import Holders.User;
import Tools.Input;

import java.util.ArrayList;

public class Functional {

    public static void createNewUser(){
        while(!AppUser.getIsUserLogged()) {
            System.out.println("Create new user");
            LoginService.loginUser();
            System.out.println("Enter name");
            String userName = Input.getString();
            if(AppUser.getUserLogin().length() < 5){
                System.out.println("Login is too short(should be more than 5 symbols)");
                continue;
            }
            if(AppUser.getUserPassword().length() < 5){
                System.out.println("Password is unsafe(should be more than 5 symbols)");
                continue;
            }
            User newUser = new User(userName, AppUser.getUserLogin());
            boolean result = UserManager.addUser(newUser, AppUser.getUserPassword());
            AppUser.setIsUserLogged(result);
            if(!result) System.out.println("Account is busy or something went wrong\nTry again");
        }
    }

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
}
