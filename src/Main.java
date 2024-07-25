import logic.TaskManager;
import model.Epic;
import model.Status;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        System.out.println("Поехали!");

        taskManager.createTask("Задача1", "Описание задачи1");
        taskManager.createTask("Задача2", "Описание задачи2");
        taskManager.createEpic("Эпик1", "Описание эпика1");
        taskManager.createEpic("Эпик2", "Описание эпика2");
        taskManager.createSubtask("Подзадача 1", "Описание подзадачи 1", taskManager.epics.get(1));
        taskManager.createSubtask("Подзадача 2", "Описание подзадачи 2", taskManager.epics.get(1));
        taskManager.createSubtask("Подзадача 3", "Описание подзадачи 3", taskManager.epics.get(2));

        System.out.println(taskManager.tasks);
        System.out.println(taskManager.epics);
        System.out.println(taskManager.subtasks);

        taskManager.updateTaskStatus(1, Status.IN_PROGRESS);
        taskManager.updateSubtaskStatus(3, Status.IN_PROGRESS);

        System.out.println("_".repeat(50));
        System.out.println(taskManager.tasks);
        System.out.println(taskManager.epics);
        System.out.println(taskManager.subtasks);

        taskManager.removeTask(1);
        taskManager.removeEpic(1);

        System.out.println("_".repeat(50));
        System.out.println(taskManager.tasks);
        System.out.println(taskManager.epics);
        System.out.println(taskManager.subtasks);

    }
}
