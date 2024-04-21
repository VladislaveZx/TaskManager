package Main;

import Holders.AppUser;

import java.io.*;
import java.util.Properties;

public class LoginService {

    public static AppUser isAlreadyLogged(){
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("config.properties");
            property.load(fis);

            String login = property.getProperty("login");
            String password = property.getProperty("password");

            System.out.println("LOGIN: " + login + ", PASSWORD: " + password);

            return new AppUser(login, password);
        } catch (IOException e) {
            return null;
        }
    }

    public static AppUser loginUser(String userLogin, String userPassword){

        Properties properties = new Properties();
        try(OutputStream outputStream = new FileOutputStream("config.properties")){
            properties.setProperty("login", userLogin);
            properties.setProperty("password", userPassword);
            properties.store(outputStream, null);
            return new AppUser(userLogin, userPassword);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
