package model;

import java.util.ArrayList;

public class Epic extends Task{

    public ArrayList <Integer> list = new ArrayList<>();
    Status status = Status.NEW;

    public Epic(String name, String description) {
        super(name, description);
    }


    @Override
    public String toString() {
        return "Epic{" +
                "list=" + list +
                ", status=" + status +
                "} " + super.toString();
    }
}
