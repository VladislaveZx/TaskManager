package Main;

import Action.LoginService;
import Action.SQLQuery;
import Database.DatabaseCore;
import Database.TaskManager;
import Database.UserManager;
import Holders.AppUser;
import Holders.Task;
import Holders.User;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        DatabaseCore.loadDatabaseInfo();

        try {
            System.out.println("Create new user: Y/N");
            if(Input.getString().equalsIgnoreCase("y")) {
                while(!AppUser.getIsUserLogged()) {
                    System.out.println("Create new user");
                    LoginService.loginUser();
                    System.out.println("Enter name");
                    String userName = Input.getString();

                    User newUser = new User(userName, AppUser.getUserLogin());
                    boolean result = UserManager.addUser(newUser, AppUser.getUserPassword());
                    AppUser.setIsUserLogged(result);
                    if(!result) System.out.println("Account is busy or something went wrong\nTry again");
                }
            }
            else LoginService.retrieveUserDataFromFile();

            if(LoginService.accountFound()) AppUser.setIsUserLogged(true);

            while(!AppUser.getIsUserLogged())
            {
                System.out.println("Log in:");
                LoginService.loginUser();
                AppUser.setIsUserLogged(LoginService.accountFound());
            }

            LoginService.saveUserData();

            System.out.println("LOGGED UNDER: " + AppUser.getUserLogin());

            System.out.println("Commands:\n" +
                    "1 - my tasks\n" +
                    "2 - get users from my group\n" +
                    "3 - actions with tasks\n" +
                    "4 - change user\n" +
                    "5 - action with users withing group\n" +
                    "6 - exit program");

            boolean shouldClose = false;
            UserActions actions = UserActions.UNDEFINED;
            while(!shouldClose) {
                System.out.println("Enter command code:");
                actions = UserActions.values()[Input.getInt()];
                switch (actions){
                    case GET_USER_TASKS:
                        ArrayList<Task> tasks = TaskManager.getTasks(SQLQuery.GET_TASK_OF_USER.toString(), new String[0]);
                        for(Task task : tasks){
                            System.out.println(task);
                        }
                    break;
                    case CHANGE_USER:
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
                        break;
                    case CLOSE_PROGRAM:
                        shouldClose = true;
                        break;
                    default:
                        System.out.println("No such command");
                        break;
                }
            }
        }
        catch (RuntimeException e){
            UICallback.print(e);
        }
    }
}
