package Tools;

public enum UserActions {
    UNDEFINED,
    GET_USER_TASKS,
    GET_GROUP_USERS,
    TASK_ACTIONS,
    CHANGE_USER,
    GROUP_ACTIONS,
    CLOSE_PROGRAM;

    public static UserActions getActionFromInt(int actionCode){
        if(actionCode > UserActions.values().length || actionCode < 0) return UNDEFINED;
        return UserActions.values()[actionCode];
    }
}
