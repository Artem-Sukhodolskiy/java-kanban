package logic;

public class Managers {

    private static logic.TaskManager TaskManager;
    private static logic.InMemoryHistoryManager InMemoryHistoryManager;

    public static TaskManager getDefault(){
        return new InMemoryTaskManager(InMemoryHistoryManager);
    }

    public static HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }
}
