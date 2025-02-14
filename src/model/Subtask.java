package model;

import java.util.Objects;

public class Subtask extends Task {

    private Epic epicLink;

    public Subtask(String name, String description, Epic epicLink) {
        super(name, description);
        this.epicLink = epicLink;
    }

    public Epic getEpicLink() {
        return epicLink;
    }

    public void setEpicLink(Epic epicLink) {
        this.epicLink = epicLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Subtask subtask = (Subtask) o;
        return Objects.equals(epicLink, subtask.epicLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epicLink);
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "epicLink=" + epicLink +
                "} " + super.toString();
    }
}