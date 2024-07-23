public class Task {

    String name;
    String description;
    Status status;
    static int idTask = 0;

    public static int getIdTask() {
        idTask++;
        return idTask;
    }

    public Task(String name, String description, Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
