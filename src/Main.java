import logic.*;
import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

public class Main {

    public static void main(String[] args) {

        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        InMemoryTaskManager taskManager = new InMemoryTaskManager(historyManager);

        System.out.println("Поехали!");

        Task task1 = new Task("Задача1", "Описание задачи1");
        Task task2 = new Task("Задача2", "Описание задачи2");
        Epic epic1 = new Epic("Эпик1", "Описание эпика1");
        Epic epic2 = new Epic("Эпик2", "Описание эпика2");
        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 1", epic1);
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание подзадачи 2", epic1);
        Subtask subtask3 = new Subtask("Подзадача 3", "Описание подзадачи 3", epic2);

        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createEpic(epic1);
        taskManager.createEpic(epic2);
        taskManager.createSubtask(subtask1);
        taskManager.createSubtask(subtask2);
        taskManager.createSubtask(subtask3);

        System.out.println(taskManager.tasks);
        System.out.println(taskManager.epics);
        System.out.println(taskManager.subtasks);

        taskManager.updateTaskStatus(2, Status.IN_PROGRESS);
        taskManager.updateSubtaskStatus(3, Status.IN_PROGRESS);

        System.out.println("_".repeat(50));
        System.out.println(taskManager.tasks);
        System.out.println(taskManager.epics);
        System.out.println(taskManager.subtasks);

        //taskManager.removeTask(1);
        //taskManager.removeEpic(1);

        System.out.println("_".repeat(50));
        System.out.println(taskManager.tasks);
        System.out.println(taskManager.epics);
        System.out.println(taskManager.subtasks);

        taskManager.checkIDTask(1);
        taskManager.checkIDTask(2);
        taskManager.checkIDEpic(2);

        System.out.println("_".repeat(50));

        System.out.println(taskManager.inMemoryHistoryManager.getHistory());
    }
}