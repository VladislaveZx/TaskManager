package Holders;

public class AppUser {
    private static String userLogin = "";
    private static String userPassword = "";

    public static String getUserLogin() {
        return userLogin;
    }

    public static String getUserPassword() {
        return userPassword;
    }

    public static void setUserInfo(String userLogin, String userPassword) {
        AppUser.userLogin = userLogin;
        AppUser.userPassword = userPassword;
    }

}
