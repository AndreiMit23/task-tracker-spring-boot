//package repository;
//
//import module.Task;
//import module.TaskStatus;
//import org.springframework.stereotype.Component;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.NoSuchElementException;
//
//@Component //componenta care se ocupa cu accesul la date
//public class TaskRepository {
//    private final ArrayList<Task> tasks = new ArrayList<>();
//    // nu mai salvam in heap ci in baza de date
//    public void addTask(Task task){
//        tasks.add(task);
//    }
//
//    public Task getTask(Long id){
//        for(Task task : tasks){
//            if(task.getId().equals(id)){
//                return task;
//            }
//        }
//        throw new NoSuchElementException();
//    }
//    //mi-a sugerat Intellij o alternativa de a scrie remove ul si am acceptat o :D
//    public void removeTask(Long id){
//        tasks.removeIf(task -> task.getId().equals(id));
//        for(int i = 0; i<tasks.size();i++) {
//            if (tasks.get(i).getId().equals(id)) {
//                tasks.remove(i);
//                return;
//            }
//        }
//        throw new NoSuchElementException();
//    }
//
//    public void updateTask(Long id, String title, TaskStatus status){
//        Date date = new Date();
//
//        for(Task task : tasks){
//            if(task.getId().equals(id)){
//                task.setTitle(title);
//                task.setStatus(status);
//                task.setUpdatedAt(date);
//                return;
//            }
//        }
//        throw new NoSuchElementException("Task with id:  " + id + " not found.");
//    }
//
//    public ArrayList<Task> getAllTasks(){
//        return tasks;
//    }
//
//    public int getSize(){
//        return tasks.size() + 1;
//    }
//
//}
