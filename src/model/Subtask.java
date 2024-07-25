package model;

public class Subtask extends Task{

    @Override
    public String toString() {
        return "Subtask{" +
                "epicLink=" + epicLink +
                "} " + super.toString();
    }

    public Epic epicLink;

    public Subtask(String name, String description, Epic epicLink) {
        super(name, description);
        this.epicLink = epicLink;
    }

}
