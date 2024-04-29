package Holders;

public class User {

    private String userLogin;
    private String userName;

    public User(String userName, String userLogin) {
        this.userName = userName;
        this.userLogin = userLogin;
    }

    public User(String userLogin){
        this(userLogin, "");
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "User{ userName='" + userName + '\'' +
                ", userLogin='" + userLogin + '\'' +
                '}';
    }
}
