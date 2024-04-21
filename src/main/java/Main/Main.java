package Main;

import Database.GroupManager;
import Database.TaskManager;
import Database.UserManager;
import Holders.AppUser;
import Holders.Group;
import Holders.Task;
import Holders.User;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        try {
            AppUser appUser;
            appUser = LoginService.isAlreadyLogged();
            if(appUser==null){
                //Ввод логина и пароля пользователя
                appUser = LoginService.loginUser("irina", "1234");
            }

        }
        catch (RuntimeException e){
            UICallback.print(e);
        }
    }
}
