package Main;

import Action.SQLQuery;
import Action.Functional;
import Action.LoginService;
import Database.DatabaseCore;
import Database.UserManager;
import Holders.AppUser;
import Holders.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseCore.loadDatabaseInfo(scanner);

        try {
            System.out.println("Create new user: Y/N");
            String newUserAsk = scanner.nextLine();
            if(newUserAsk.equalsIgnoreCase("y")) {
                System.out.println("Create new user");
                LoginService.enterLoginData(scanner);
                System.out.println("Enter user name:");
                String userName = scanner.nextLine();

                User newUser = new User(userName, AppUser.getUserLogin());
                UserManager.addUser(newUser, AppUser.getUserPassword());
            }
            else LoginService.retrieveUserDataFromFile();

            while(!LoginService.doesInfoFilled() ||
                    !DatabaseCore.doesSingleExist(SQLQuery.LOGIN_USER.toString(), new String[]{AppUser.getUserLogin(), AppUser.getUserPassword()}))
            {
                System.out.println("Log in failed");
                LoginService.enterLoginData(scanner);
            }

            LoginService.saveUserData();

            System.out.println("LOGGED UNDER: " + AppUser.getUserLogin());

            System.out.println("Commands:\n" +
                    "1 - get tasks of mine\n" +
                    "2 - get users from my group\n" +
                    "3 - actions with tasks\n" +
                    "4 - change user\n" +
                    "5 - exit");

            boolean shouldClose = false;
            while(!shouldClose) {
                System.out.println("Enter command:");
                int code = scanner.nextInt();
                switch (code) {
                    case 1:
                        Functional.showUserTasks();
                        break;
                    case 2:
                        Functional.showUsersFromAppUserGroup();
                        break;

                    case 3:
                        // not finished
                        System.out.println("Actions:\n1 - Add task\n2 - Delete task\n3 - Change task");
                        break;
                    case 4:
                        Functional.reloginUser(scanner);
                        break;
                    case 5:
                        shouldClose = true;
                        System.out.println("Exited");
                        break;
                    default:
                        System.out.print("Doesn't exist");
                        break;
                }
            }
        }
        catch (RuntimeException e){
            UICallback.print(e);
        }
    }
}
