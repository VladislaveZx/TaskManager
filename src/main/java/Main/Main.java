package Main;

import Database.DatabaseCore;
import Database.TaskManager;
import Database.UserManager;
import Holders.AppUser;
import Holders.Task;
import Holders.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        DatabaseCore.loadDatabaseInfo();
        try {
            Scanner scanner = new Scanner(System.in);

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
            String loginSql = "SELECT * from users WHERE userlogin = ? AND userpassword = ?";
            while(!LoginService.doesInfoFilled() ||
                    !DatabaseCore.doesSingleExist(loginSql, new String[]{AppUser.getUserLogin(), AppUser.getUserPassword()}))
            {
                System.out.println("Log in failed or file lack");
                LoginService.enterLoginData(scanner);
            }

            LoginService.saveUserData();

            System.out.println("LOGGED UNDER: " + AppUser.getUserLogin());

            System.out.println("Commands:\n" +
                    "1 - get tasks of mine\n" +
                    "2 - get users from my group\n" +
                    "3 - actions with tasks\n4 - change group\n4 - exit");

            boolean shouldClose = false;
            while(!shouldClose) {
                System.out.println("Enter:");
                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println("Tasks:");
                        String getTasks = "SELECT * FROM tasks";//выбарать задачи пользователя
                        ArrayList<Task> tasks = TaskManager.getTasks(getTasks, new String[0]);
                        for (Task task : tasks)
                            System.out.println(task);
                        break;
                    case 2:
                        System.out.println("Users:");
                        String getUsers = "SELECT * FROM users";//выбарать задачи пользователя
                        ArrayList<User> users = UserManager.getUsers(getUsers, new String[0]);
                        for (User user : users)
                            System.out.println(user);
                        break;

                    case 3:
                        System.out.println("Actions:\n1 - Add task\n2 - Delete task\n3 - Change task");
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
