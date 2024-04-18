package Holders;

public enum TaskPriority {
    LOW("Низкая"), MIDDLE("Средняя"), HIGH("Высокая");

    private final String stringValue;

    TaskPriority(String value){
        this.stringValue=value;
    }

    public static TaskPriority castFromInt(int index){
        return TaskPriority.values()[index];
    }

    @Override
    public String toString() {
        return this.stringValue;
    }
}
