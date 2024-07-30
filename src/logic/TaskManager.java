package logic;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;

public interface TaskManager {
    void createTask(Task task);

    void createEpic(Epic epic);

    void createSubtask(Subtask subtask);

    ArrayList<Task> getTasks();

    ArrayList<Epic> getEpics();

    ArrayList<Subtask> getSubtasks();

    void clearAllTasks();

    void clearAllEpics();

    void clearAllSubtasks();

    Task checkIDTask(int id);

    Epic checkIDEpic(int ID);

    Subtask checkIDSubtask(int ID);

    void updateTask(int id, Task task);

    void updateEpic(int ID, Epic epic);

    void updateSubtask(int id, Subtask subtask);

    void updateTaskStatus(int id, Status status);

    void updateEpicStatus(Epic epic);

    void updateSubtaskStatus(int id, Status status);

    void removeTask(int id);

    void removeEpic(int id);

    void removeSubtask(int id);

    ArrayList<Subtask> getSubtaskEpic(int id);
}
