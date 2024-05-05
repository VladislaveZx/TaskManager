package Main;

import Action.Functional;
import Action.LoginService;
import Database.DatabaseCore;
import Holders.AppUser;
import Tools.Input;
import Tools.UICallback;
import Tools.UserActions;

public class Main {
    public static void main(String[] args) {

        DatabaseCore.loadDatabaseInfo();

        try {
            System.out.println("Create new user: Y/N");
            if(Input.getString().equalsIgnoreCase("y"))
                Functional.createNewUser();

            System.out.println("Use exiting data as login: Y/N");
            if(Input.getString().equalsIgnoreCase("y")) {
                LoginService.tryRetrieveUserDataFromFile();
                AppUser.setIsUserLogged(LoginService.accountFound());
            }

            while(!AppUser.getIsUserLogged())
            {
                System.out.println("Log in:");
                LoginService.loginUser();
                AppUser.setIsUserLogged(LoginService.accountFound());
            }

            LoginService.saveUserData();

            System.out.println("LOGGED UNDER: " + AppUser.getUserLogin());

            System.out.println("""
                    Commands:
                    1 - my tasks
                    2 - get users from my group
                    3 - actions with tasks
                    4 - action with users within group
                    5 - exit program""");

            boolean shouldClose = false;
            while(!shouldClose) {
                System.out.println("Enter command code:");
                switch (UserActions.getActionFromInt(Input.getInt())){
                    case GET_USER_TASKS:
                        Functional.showUserTasks();
                        break;
                    case GET_GROUP_USERS:
                        Functional.showUsersFromAppUserGroup();
                        break;
                    case TASK_ACTIONS:
                        System.out.println("Task actions:\n" +
                                "1 - Add task\n" +
                                "2 - Change task\n" +
                                "3 - Delete task");
                        break;
                    case GROUP_ACTIONS:
                        System.out.println("Group actions:\n" +
                                "1 - Add user to my group\n" +
                                "2 - Delete user from my group\n"+
                                "3 - Leave group");
                        break;
                    case CLOSE_PROGRAM:
                        shouldClose = true;
                        break;
                    case UNDEFINED:
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
