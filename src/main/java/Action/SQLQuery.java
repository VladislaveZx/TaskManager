package Action;

public enum SQLQuery {
    LOGIN_USER("SELECT * from users WHERE userlogin = ? AND userpassword = ?"),
    CHECK_IF_USER_EXISTS("SELECT * FROM users WHERE userlogin = ?"),
    GET_TASK_OF_USER("SELECT * FROM tasks"),
    GET_USERS_OF_GROUP("SELECT * FROM users"),
    DELETE_TASK_BY_ID("DELETE FROM tasks WHERE usertaskid = ?"),
    UPDATE_TASK("UPDATE tasks SET(\"CreatorLogin\", \"CreatorGroupId\", \"TaskName\"," +
            "\"TaskDescription\", \"TaskPriority\", \"TaskStatus\", \"TaskExpiryDate\") " +
            "= (?,?,?,?,?,?,?) WHERE \"taskid\" = ?"),
    CREATE_USER("INSERT INTO users(\"username\", \"userlogin\", \"userpassword\") VALUES (?,?,?)"),
    DELETE_USER("DELETE FROM users WHERE users.\"userlogin\" = ?");


    private final String SQLValue;

    SQLQuery(String SQLValue) {
        this.SQLValue = SQLValue;
    }

    public String toString() {
        return SQLValue;
    }
}
