package logic;
import model.Node;
import model.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InMemoryHistoryManager implements HistoryManager {

    private Node head;
    private Node tail;
    private final HashMap<Integer, Node> taskMap;

    public InMemoryHistoryManager() {
        this.head = null;
        this.tail = null;
        this.taskMap = new HashMap<>();
    }

    private void linkLast(Task task) {
        Node newNode = new Node(task);

        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        taskMap.put(task.getId(), newNode);
    }

    private void removeNode(Node node) {
        if (node == null) return;
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
        taskMap.remove(node.task.getId());
    }

    @Override
    public void add(Task task) {
        if (task == null) return;
        if (taskMap.containsKey(task.getId())) {
            removeNode(taskMap.get(task.getId()));
        }
        linkLast(task);
    }

    @Override
    public void remove(int id) {
        if (taskMap.containsKey(id)) {
            removeNode(taskMap.get(id));
        }
    }

    @Override
    public List<Task> getHistory() {
        List<Task> history = new ArrayList<>();
        Node current = head;

        while (current != null) {
            history.add(current.task);
            current = current.next;
        }
        return history;
    }
}