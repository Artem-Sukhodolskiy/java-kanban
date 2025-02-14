import logic.InMemoryHistoryManager;
import logic.InMemoryTaskManager;
import model.Epic;
import model.Subtask;
import model.Task;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @org.junit.jupiter.api.Test
    void checkIdTask() {
        InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
        InMemoryTaskManager taskManager = new InMemoryTaskManager(inMemoryHistoryManager);
        Task task1 = new Task("Задача1", "Описание задачи1");
        taskManager.createTask(task1);
        assertEquals(taskManager.checkIDTask(1), taskManager.checkIDTask(1));
    }

    @org.junit.jupiter.api.Test
    void checkIdEpic() {
        InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
        InMemoryTaskManager taskManager = new InMemoryTaskManager(inMemoryHistoryManager);
        Epic epic1 = new Epic("Эпик1", "Описание эпика1");
        taskManager.createEpic(epic1);
        assertEquals(taskManager.checkIDEpic(1), taskManager.checkIDEpic(1));
    }

    @org.junit.jupiter.api.Test
    void checkIdSubtask() {
        InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
        InMemoryTaskManager taskManager = new InMemoryTaskManager(inMemoryHistoryManager);
        Epic epic1 = new Epic("Эпик1", "Описание эпика1");
        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 1", epic1);
        taskManager.createSubtask(subtask1);
        assertEquals(taskManager.checkIDSubtask(1), taskManager.checkIDSubtask(1));
    }

    @org.junit.jupiter.api.Test
    void checkInMemoryTaskManager() {
        InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
        InMemoryTaskManager taskManager = new InMemoryTaskManager(inMemoryHistoryManager);
        Task task1 = new Task("Задача1", "Описание задачи1");
        Epic epic1 = new Epic("Эпик1", "Описание эпика1");
        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 1", epic1);
        taskManager.createTask(task1);
        taskManager.createEpic(epic1);
        taskManager.createSubtask(subtask1);
        assertNotNull(taskManager.checkIDTask(1));
        assertNotNull(taskManager.checkIDEpic(1));
        assertNotNull(taskManager.checkIDSubtask(1));
    }

    @org.junit.jupiter.api.Test
    void checkTask() {
        InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
        InMemoryTaskManager taskManager = new InMemoryTaskManager(inMemoryHistoryManager);
        Task task1 = new Task("Задача1", "Описание задачи1");
        taskManager.createTask(task1);
        Task task2 = taskManager.tasks.get(1);
        assertEquals(task1.getName(), task2.getName());
        assertEquals(task1.getDescription(), task2.getDescription());
    }

    @org.junit.jupiter.api.Test
    void add() {
        InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
        InMemoryTaskManager taskManager = new InMemoryTaskManager(inMemoryHistoryManager);
        Task task1 = new Task("Задача1", "Описание задачи1");
        taskManager.createTask(task1);
        taskManager.checkIDTask(1);
        assertNotNull(taskManager.inMemoryHistoryManager.getHistory());
    }
    @org.junit.jupiter.api.Test
    void testRemoveSubtaskAndEpicConsistency() {
        InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
        InMemoryTaskManager taskManager = new InMemoryTaskManager(inMemoryHistoryManager);
        Epic epic = new Epic("Эпик1", "Epic 1");
        Subtask subtask1 = new Subtask("Подзадача2", "Subtask 1", epic);
        Subtask subtask2 = new Subtask("Подзадача3", "Subtask 2", epic);

        inMemoryHistoryManager.add(epic);
        inMemoryHistoryManager.add(subtask1);
        inMemoryHistoryManager.add(subtask2);

        inMemoryHistoryManager.remove(2); // Удаляем подзадачу

        assertFalse(epic.getSubtaskIds().contains(2)); // ID подзадачи удалён из эпика
    }
}
