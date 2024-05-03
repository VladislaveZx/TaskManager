package Action;

public enum SQLQuery {
    LOGIN_USER("SELECT * from users WHERE userlogin = ? AND userpassword = ?"),
    CHECK_IF_USER_EXISTS("SELECT * FROM users WHERE userlogin = ?"),
    GET_TASK_OF_USER("SELECT * FROM tasks"),
    GET_USERS_OF_GROUP("SELECT * FROM users");

    private final String SQLValue;

    SQLQuery(String SQLValue) {
        this.SQLValue = SQLValue;
    }

    public String toString() {
        return SQLValue;
    }
}
