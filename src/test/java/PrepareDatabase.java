import Holders.AppUser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Scanner;

public class PrepareDatabase {
    public static void main(String[] args){
        Properties properties = new Properties();
        try(OutputStream outputStream = new FileOutputStream("Database.properties")){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your DB URL");
            String db_url = scanner.nextLine();
            System.out.println("Enter your DB admin login");
            String db_login = scanner.nextLine();
            System.out.println("Enter your DB admin password");
            String db_password = scanner.nextLine();
            properties.setProperty("DB_URL", db_url);
            properties.setProperty("DB_USER", db_login);
            properties.setProperty("DB_PASSWORD", db_password);
            properties.store(outputStream, null);
            System.out.println("Database prop created");
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
