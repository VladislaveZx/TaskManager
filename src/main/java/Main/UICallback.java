package Main;

public class UICallback {

    public static void print(String out){
        System.out.println(out + "\n");
    }

    public static void print(Throwable e){
        System.out.println(e.getMessage());
    }
}
