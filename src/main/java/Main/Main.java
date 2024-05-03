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
            else
                LoginService.retrieveUserDataFromFile();

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
            while(!shouldClose) {
                System.out.println("Enter command code:");
                switch (UserActions.getActionFromInt(Input.getInt())){
                    case GET_USER_TASKS:
                        Functional.showUserTasks();
                        break;
                    case GET_GROUP_USERS:
                        Functional.showUsersFromAppUserGroup();
                        break;
                    case CHANGE_USER:
                        Functional.reloginUser();
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
