package repository;

import application.Task;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class TaskRepository {
    public ArrayList<Task> tasks = new ArrayList<>();
    // nu mai salvam in heap ci in baza de date
    public void addTask(Task task){
        tasks.add(task);
    }

    public Task getTask(Long id){
        for(Task t : tasks){
            if(t.getId().equals(id)){
                return t;
            }
        }
        throw new NoSuchElementException();
    }
}
