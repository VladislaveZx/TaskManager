package Main;

import java.util.Scanner;

public class Input {

    private static final Scanner scanner = new Scanner(System.in);

    public static String getString(){
        return scanner.nextLine();
    }

    public static int getInt(){
        return scanner.nextInt();
    }

    public static void skipLine(){
        scanner.nextLine();
    }

}
