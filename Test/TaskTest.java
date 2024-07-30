import logic.InMemoryHistoryManager;
import logic.InMemoryTaskManager;
import model.Epic;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    void checkIdTask() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        Task task1 = new Task("Задача1", "Описание задачи1");
        taskManager.createTask(task1);
        assertEquals(taskManager.checkIDTask(1), taskManager.checkIDTask(1));
    }

    @Test
    void checkIdEpic() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        Epic epic1 = new Epic("Эпик1", "Описание эпика1");
        taskManager.createEpic(epic1);
        assertEquals(taskManager.checkIDEpic(1), taskManager.checkIDEpic(1));
    }

    @Test
    void checkIdSubtask() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        Epic epic1 = new Epic("Эпик1", "Описание эпика1");
        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 1", epic1);
        taskManager.createSubtask(subtask1);
        assertEquals(taskManager.checkIDSubtask(1), taskManager.checkIDSubtask(1));
    }

    @Test
    void checkInMemoryTaskManager() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
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

    @Test
    void checkTask() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        Task task1 = new Task("Задача1", "Описание задачи1");
        taskManager.createTask(task1);
        Task task2 = taskManager.tasks.get(1);
        assertEquals(task1.getName(), task2.getName());
        assertEquals(task1.getDescription(), task2.getDescription());
    }

    @Test
    void add() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        Task task1 = new Task("Задача1", "Описание задачи1");
        taskManager.createTask(task1);
        taskManager.checkIDTask(1);
        assertNotNull(taskManager.inMemoryHistoryManager.getHistory());
    }
}
