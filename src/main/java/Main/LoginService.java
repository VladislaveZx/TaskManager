package Main;

import Holders.AppUser;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class LoginService {

    public static boolean doesInfoFilled(){
        return !AppUser.getUserLogin().isEmpty() && !AppUser.getUserPassword().isEmpty();
    }

    public static void enterLoginData(Scanner scanner){
        System.out.println("Enter login");
        String userLogin = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        AppUser.setUserInfo(userLogin, password);
    }

    public static void retrieveUserDataFromFile(){
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("user.properties");
            property.load(fis);

            String login = property.getProperty("login");
            String password = property.getProperty("password");

            AppUser.setUserInfo(login, password);
        } catch (IOException e) {
            return;
        }
    }

    public static void saveUserData(){

        Properties properties = new Properties();
        try(OutputStream outputStream = new FileOutputStream("user.properties")){
            properties.setProperty("login", AppUser.getUserLogin());
            properties.setProperty("password", AppUser.getUserPassword());
            properties.store(outputStream, null);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
