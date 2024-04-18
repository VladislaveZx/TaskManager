package Main;

import Database.GroupManager;
import Database.TaskManager;
import Database.UserManager;
import Holders.Group;
import Holders.Task;
import Holders.User;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        try {
            ArrayList<Task> tasks = TaskManager.getTasks("SELECT * FROM tasks", new String[0]);
            for (Task task : tasks) {
                System.out.println(task);
            }

            ArrayList<User> users = UserManager.getUsers("SELECT * FROM users WHERE \"userlogin\" = ?", new String[]{"irina"});
            for (User user : users) {
                System.out.println(user);
            }

            ArrayList<Group> groups = GroupManager.getGroups("SELECT * FROM usergroups", new String[0]);
            for (Group group : groups) {
                System.out.println(group);
            }
        }
        catch (RuntimeException e){
            UICallback.print(e);
        }
    }
}
