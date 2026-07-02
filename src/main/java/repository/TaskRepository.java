package repository;

import application.Task;
import application.TaskStatus;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class TaskRepository {
    public ArrayList<Task> tasks = new ArrayList<>();
    // nu mai salvam in heap ci in baza de date
    public void addTask(Task task){
        tasks.add(task);
    }

    public Task getTask(Long id){
        for(Task task : tasks){
            if(task.getId().equals(id)){
                return task;
            }
        }
        throw new NoSuchElementException();
    }
    //mi-a sugerat Intellij o alternativa de a scrie remove ul si am acceptat o :D
    public void removeTask(Long id){
        tasks.removeIf(task -> task.getId().equals(id));
    }

    public void updateTask(Long id, String title, TaskStatus status){
        for(Task task : tasks){
            if(task.getId().equals(id)){
                task.setTitle(title);
                task.setStatus(status);
            }else{
                throw new NoSuchElementException();
            }
        }
    }

}
