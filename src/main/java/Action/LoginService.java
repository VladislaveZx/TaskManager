package Action;

import Database.DatabaseCore;
import Holders.AppUser;
import Main.Input;

import java.io.*;
import java.util.Properties;

public class LoginService {

    public static void loginUser() {
        System.out.println("Enter login");
        String userLogin = Input.getString();
        System.out.println("Enter password");
        String password = Input.getString();
        AppUser.setUserInfo(userLogin, password);
    }

    public static void retrieveUserDataFromFile(){
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("userData.properties");
            property.load(fis);

            String login = property.getProperty("login");
            String password = property.getProperty("password");

            AppUser.setUserInfo(login, password);
        } catch (IOException e) {
            AppUser.setIsUserLogged(false);
        }
    }

    public static void saveUserData(){
        Properties properties = new Properties();
        try(OutputStream outputStream = new FileOutputStream("userData.properties")){
            properties.setProperty("login", AppUser.getUserLogin());
            properties.setProperty("password", AppUser.getUserPassword());
            properties.store(outputStream, null);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    static public boolean accountFound(){
            return DatabaseCore.doesSingleExist(SQLQuery.LOGIN_USER.toString(),
                new String[]{AppUser.getUserLogin(), AppUser.getUserPassword()});
    }

}
