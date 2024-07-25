package logic;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    public HashMap<Integer, Task> tasks = new HashMap<>();
    public HashMap<Integer, Epic> epics = new HashMap<>();
    public HashMap<Integer, Subtask> subtasks = new HashMap<>();

    int countTask = 0;
    int countEpic = 0;
    int countSubTask = 0;


    public void createTask(String name, String description) {
        Task task = new Task(name, description);
        countTask++;
        task.setId(countTask);
        tasks.put(task.getId(), task);
    }

    public void createEpic(String name, String description) {
        Epic epic = new Epic(name, description);
        countEpic++;
        epic.setId(countEpic);
        epics.put(epic.getId(), epic);
    }

    public void createSubtask(String name, String description, Epic epicLink) {
        Subtask subtask = new Subtask(name, description, epicLink);
        countSubTask++;
        subtask.setId(countSubTask);
        subtasks.put(subtask.getId(), subtask);
        epicLink.list.add(countSubTask);
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }


    public void clearAllTasks() {
        tasks.clear();
    }

    public void clearAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void clearAllSubtasks() {
        subtasks.clear();
        for (Epic epic: epics.values()){
            updateEpicStatus(epic);
        }

    }

    public void checkIDTask(int id) {
        if (!tasks.containsKey(id)) {
            System.out.println("Задачи с таким идентификатором не существует");
        } else {
            System.out.println(tasks.get(id));
        }
    }

    public void checkIDEpic(int ID) {
        if (!epics.containsKey(ID)) {
            System.out.println("Эпика с таким идентификатором не существует");
        } else {
            System.out.println(epics.get(ID));
        }
    }

    public void checkIDSubtask(int ID) {
        if (!subtasks.containsKey(ID)) {
            System.out.println("Подзадачи с таким идентификатором не существует");
        } else {
            System.out.println(subtasks.get(ID));
        }
    }


    public void updateTask(int id, Task task) {
        if (!tasks.containsKey(id)) {
            System.out.println("Задачи с таким идентификатором не существует");
        } else {
            tasks.put(id, task);
        }
    }

    public void updateEpic(int ID, Epic epic) {
        if (!epics.containsKey(ID)) {
            System.out.println("Эпика с таким идентификатором не существует");
        } else {
            epics.put(ID, epic);
        }
    }

    public void updateSubtask(int ID, Subtask subtask) {
        if (!subtasks.containsKey(ID)) {
            System.out.println("Подзадачи с таким идентификатором не существует");
        } else {
            subtasks.put(ID, subtask);
            updateEpicStatus(subtask.epicLink);
        }
    }

    public void updateTaskStatus (int id, Status status) {
        Task task = tasks.get(id);
        task.setStatus(status);
    }

    public void updateEpicStatus(Epic epic) {
        int sNew = 0;
        int sDone = 0;
        int sInProgress = 1;
        for (Integer list : epic.list) {
            Subtask subtask = subtasks.get(list);
            switch (subtask.status) {
                case NEW -> sNew = -1;
                case DONE -> sDone = 1;
                case IN_PROGRESS -> sInProgress = 0;
            }
        }
        int status = sInProgress * (sNew + sDone);
        switch (status) {
            case -1:
                epic.setStatus(Status.NEW);
                break;
            case 0:
                epic.setStatus(Status.IN_PROGRESS);
                break;
            case 1:
                epic.setStatus(Status.DONE);
                break;
        }
    }

    public void updateSubtaskStatus (int id, Status status) {
        Subtask subtask = subtasks.get(id);
        subtask.setStatus(status);
        updateEpicStatus(subtask.epicLink);
    }



    public void removeTask(int id) {
        tasks.remove(id);
    }

    public void removeEpic(int id) {
        Epic epic = epics.get(id);
        for (Integer list : epic.list) {
            subtasks.remove(list);
        }
        epics.remove(id);
    }

    public void removeSubtask(int id) {
        Subtask subtask = subtasks.get(id);
        Epic epic = subtask.epicLink;
        subtasks.remove(id);
        updateEpicStatus(epic);
    }

    public ArrayList<Subtask> getSubtaskEpic(int id) {
        Epic epic = epics.get(id);
        ArrayList <Subtask> subtask = new ArrayList<>();
        for (Integer list: epic.list){
            subtask.add(subtasks.get(list));
        }
        return subtask;
    }
}


