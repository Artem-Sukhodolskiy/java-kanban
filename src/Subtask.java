public class Subtask extends Task{

    String epicLink;
    static int idSubtask = 0;

    public Subtask(String name, String description, Status status, String epicLink) {
        super(name, description, status);
        this.epicLink = epicLink;;
    }

    public static int getIdSubtask() {
        idSubtask++;
        return idSubtask;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", эпик'='" + epicLink + '\'' +
                '}';
    }
}
