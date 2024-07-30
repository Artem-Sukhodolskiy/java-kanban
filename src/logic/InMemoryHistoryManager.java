package logic;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    public List<Task> history = new ArrayList<>();
    public int countHistoryList = 0;


    @Override
    public void add(Task task){
        history.add(countHistoryList, task);
        countHistoryList++;
        if (countHistoryList > 9){
            countHistoryList = 0;
        }
    }

    @Override
    public List<Task> getHistory(){
        return history;
    }
}
