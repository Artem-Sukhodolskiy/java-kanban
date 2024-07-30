package logic;

public class Managers {

    private static logic.TaskManager TaskManager;
    private static logic.InMemoryHistoryManager InMemoryHistoryManager;

    public static TaskManager getDefault(){
        return TaskManager;
    }

    public static InMemoryHistoryManager getDefaultHistory(){
        return InMemoryHistoryManager;
    }
}
