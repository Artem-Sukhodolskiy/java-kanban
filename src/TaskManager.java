import java.util.HashMap;

public class TaskManager {

    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();


    public void createTask(String name, String description) {
        Task task = new Task(name, description, Status.NEW);
        tasks.put(Task.getIdTask(), task);
    }

    public void createEpic(String name, String description) {
        Epic epic = new Epic(name, description, Status.NEW);
        epics.put(Epic.getIdEpic(), epic);
    }

    public void createSubtask(String name, String description, String epicLink) {
        Subtask subtask = new Subtask(name, description, Status.NEW, epicLink);
        subtasks.put(Subtask.getIdSubtask(), subtask);
    }

    public void printAllTasks() {

        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст");
        }else{
            System.out.println(tasks);
        }
    }

    public void printAllEpics() {

        if (epics.isEmpty()) {
            System.out.println("Список эпиков пуст");
        } else {
            System.out.println(epics);
        }
    }

    public void printAllSubtasks(){
        if (subtasks.isEmpty()) {
            System.out.println("Список подзадач пуст");
        } else {
            System.out.println(subtasks);
        }
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
    }

    public void checkIDTask(int ID) {
        if (!tasks.containsKey(ID)) {
            System.out.println("Задачи с таким идентификатором не существует");
        } else {
            System.out.println(tasks.get(ID));
        }
    }

    public void checkIDEpic(int ID) {

        if (epics.containsKey(ID)) {
            System.out.println(epics.get(ID));
        } else {
            System.out.println("Эпика с таким идентификатором не существует");
        }
    }

    public void checkIDSubtask(int ID) {

        if (subtasks.containsKey(ID)) {
            System.out.println(subtasks.get(ID));
        } else {
            System.out.println("Подзадачи с таким идентификатором не существует");
        }

    }


    public void updateTask(int ID, Task task) {
        tasks.put(ID, task);
    }

    public void updateEpic(int ID, Epic epic) {
        epics.put(ID, epic);
    }

    public void updateSubtask(int ID, Subtask subtask) {
        subtasks.put(ID, subtask);

        String epicLink = subtask.epicLink;
        int sNew = 0;
        int sDone = 0;
        int sInProgress = 1;

        for (int i = 1; i <= Subtask.idSubtask; i++) {

            Subtask subtaskSearch = subtasks.get(i);
            if (subtaskSearch == null){

            }else if (epicLink.equals(subtaskSearch.epicLink)) {
                switch (subtaskSearch.status) {
                    case NEW -> sNew = -1;
                    case DONE -> sDone = 1;
                    case IN_PROGRESS -> sInProgress = 0;
                }
            }
        }
        int status = sInProgress * (sNew + sDone);


        for (int i = 1; i <= Epic.idEpic; i++) {
            Epic epicSearch = epics.get(i);
            if (epicSearch == null){

            } else if (epicLink.equals(epicSearch.name)) {
                switch (status) {
                    case -1:
                        epicSearch.status = Status.NEW;
                        break;
                    case 0:
                        epicSearch.status = Status.IN_PROGRESS;
                        break;
                    case 1:
                        epicSearch.status = Status.DONE;
                        break;
                }
            }
        }
    }

    public void removeTask(int ID){
        tasks.remove(ID);
    }

    public void removeEpic(int ID){
        Epic epic = epics.get(ID);
        String epicName = epic.name;
        epics.remove(ID);

        for (int i = 1; i <= subtasks.size(); i++) {
            Subtask subtaskSearch = subtasks.get(i);
            if (epicName.equals(subtaskSearch.epicLink)){
                subtasks.remove(i);
            }
        }

    }

    public void removeSubtask(int ID){
        subtasks.remove(ID);
    }

    public void printSubtaskInEpic(String epicName){
        for (int i = 1; i <= Subtask.idSubtask; i++) {
            Subtask subtaskSearch = subtasks.get(i);
            if (subtaskSearch == null){

            } else if (epicName.equals(subtaskSearch.epicLink)){
                System.out.println(subtaskSearch);
            }
        }
    }


    @Override
    public String toString() {
        return "TaskManager{" +
              //  "ID=" + ID +
                ", tasks=" + tasks +
                ", epics=" + epics +
                ", subtasks=" + subtasks +
                '}';
    }
}


    //public void updateTask (task.getID(), task){
    //    tasks.put(task.getId(), task));
//тут еще и обновление задачи
    //}

