public class Epic extends Task{

    static int idEpic = 0;

    public Epic(String name, String description, Status status) {
        super(name, description, status);
    }

    public static int getIdEpic() {
        idEpic++;
        return idEpic;
    }



    @Override
    public String toString() {
        return "Epic{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
