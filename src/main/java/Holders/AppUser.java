package Holders;

public class AppUser {

    private static boolean isUserLogged = false;
    private static String userLogin = "";
    private static String userPassword = "";

    public static String getUserLogin() {
        return userLogin;
    }

    public static String getUserPassword() {
        return userPassword;
    }

    public static boolean getIsUserLogged() {
        return isUserLogged;
    }

    public static void setIsUserLogged(boolean isUserLogged) {
        AppUser.isUserLogged = isUserLogged;
    }

    public static void setUserInfo(String userLogin, String userPassword) {
        AppUser.userLogin = userLogin;
        AppUser.userPassword = userPassword;
    }

}
