package model;

public class Node {

    public Task task; // Задача
    public Node prev; // Указатель на предыдущий узел
    public Node next; // Указатель на следующий узел

    public Node(Task task) {
        this.task = task;
        this.prev = null;
        this.next = null;
    }
}
