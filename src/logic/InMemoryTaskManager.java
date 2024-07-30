package logic;
import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;
import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {

    public HashMap<Integer, Task> tasks = new HashMap<>();
    public HashMap<Integer, Epic> epics = new HashMap<>();
    public HashMap<Integer, Subtask> subtasks = new HashMap<>();
    public InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();

    int countTask = 0;
    int countEpic = 0;
    int countSubTask = 0;

    @Override
    public void createTask(Task task) {
        countTask++;
        task.setId(countTask);
        tasks.put(task.getId(), task);
    }

    @Override
    public void createEpic(Epic epic) {
        countEpic++;
        epic.setId(countEpic);
        epics.put(epic.getId(), epic);
    }

    @Override
    public void createSubtask(Subtask subtask) {
        countSubTask++;
        subtask.setId(countSubTask);
        subtasks.put(subtask.getId(), subtask);
        subtask.getEpicLink().getSubtaskIds().add(countSubTask);
    }

    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public void clearAllTasks() {
        tasks.clear();
    }

    @Override
    public void clearAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void clearAllSubtasks() {
        subtasks.clear();
        for (Epic epic: epics.values()){
            epic.getSubtaskIds().clear();
            updateEpicStatus(epic);
        }

    }

    @Override
    public Task checkIDTask(int id) {
        if (!tasks.containsKey(id)) {
            System.out.println("Задачи с таким идентификатором не существует");
        } else {
            System.out.println(tasks.get(id));
            inMemoryHistoryManager.add(tasks.get(id));
        }
        return tasks.get(id);
    }

    @Override
    public Epic checkIDEpic(int id) {
        if (!epics.containsKey(id)) {
            System.out.println("Эпика с таким идентификатором не существует");
        } else {
            System.out.println(epics.get(id));
            inMemoryHistoryManager.add(tasks.get(id));
        }
        return epics.get(id);
    }

    @Override
    public Subtask checkIDSubtask(int id) {
        if (!subtasks.containsKey(id)) {
            System.out.println("Подзадачи с таким идентификатором не существует");
        } else {
            System.out.println(subtasks.get(id));
            inMemoryHistoryManager.add(subtasks.get(id));
        }
        return subtasks.get(id);
    }

    @Override
    public void updateTask(int id, Task task) {
        if (!tasks.containsKey(id)) {
            System.out.println("Задачи с таким идентификатором не существует");
        } else {
            tasks.put(id, task);
            inMemoryHistoryManager.add(tasks.get(id));
        }
    }

    @Override
    public void updateEpic(int id, Epic epic) {
        if (!epics.containsKey(id)) {
            System.out.println("Эпика с таким идентификатором не существует");
        } else {
            epics.put(id, epic);
            inMemoryHistoryManager.add(epics.get(id));
        }
    }

    @Override
    public void updateSubtask(int id, Subtask subtask) {
        if (!subtasks.containsKey(id)) {
            System.out.println("Подзадачи с таким идентификатором не существует");
        } else {
            subtasks.put(id, subtask);
            updateEpicStatus(subtask.getEpicLink());
            inMemoryHistoryManager.add(subtasks.get(id));
        }
    }

    @Override
    public void updateTaskStatus(int id, Status status) {
        Task task = tasks.get(id);
        task.setStatus(status);
    }

    @Override
    public void updateEpicStatus(Epic epic) {

        int sNew = 0;
        int sDone = 0;
        int sInProgress = 1;
        for (Integer SubtaskIds : epic.getSubtaskIds()) {
            Subtask subtask = subtasks.get(SubtaskIds);
            switch (subtask.getStatus()) {
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

    @Override
    public void updateSubtaskStatus(int id, Status status) {
        Subtask subtask = subtasks.get(id);
        subtask.setStatus(status);
        updateEpicStatus(subtask.getEpicLink());
    }

    @Override
    public void removeTask(int id) {
        tasks.remove(id);
    }

    @Override
    public void removeEpic(int id) {
        Epic epic = epics.get(id);
        for (Integer list : epic.getSubtaskIds()) {
            subtasks.remove(list);
        }
        epics.remove(id);
    }

    @Override
    public void removeSubtask(int id) {
        Subtask subtask = subtasks.get(id);
        Epic epic = subtask.getEpicLink();
        subtasks.remove(id);
        updateEpicStatus(epic);
    }

    @Override
    public ArrayList<Subtask> getSubtaskEpic(int id) {
        Epic epic = epics.get(id);
        ArrayList <Subtask> subtask = new ArrayList<>();
        for (Integer list: epic.getSubtaskIds()){
            subtask.add(subtasks.get(list));
        }
        return subtask;
    }


}