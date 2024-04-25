package Action;

public enum SQLQuery {
    LOGIN_USER("SELECT * from users WHERE userlogin = ? AND userpassword = ?");

    private final String SQLValue;

    SQLQuery(String SQLValue) {
        this.SQLValue = SQLValue;
    }

    public String toString() {
        return SQLValue;
    }
}
