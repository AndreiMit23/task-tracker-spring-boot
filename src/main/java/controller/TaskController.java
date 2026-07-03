package controller;

import module.Task;
import module.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.TaskService;

import java.util.ArrayList;


@RestController //componenta care primeste request-uri HTTP
@RequestMapping("/tasks") // scriu asa ca sa nu mai scriu in toate metodele "/tasks" ... toate endpoint urile incep cu /tasks
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@RequestParam String title, @RequestParam(required = false) TaskStatus status){ //idea la status este ca el este optional, deci varianta asta imi permite sa nu l oblig pe utilizator sa trimita mereu si statusul (ca tot in cerinta zicea ca e optional)
        return taskService.createTask(title,status);
    }

    @GetMapping
    public Task getTaskById(@RequestParam Long id){
        return taskService.getTaskByID(id);
    }

    //intre cele 2 GetMapping exista diferentierea aceasta deoarece apare intrebarea : Ce metoda sa aleg?
    //amandoua sunt "rutate" spre acelasi endpoint
    @GetMapping("/all")
    public ArrayList<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @PutMapping
    public void updateTask(@RequestParam Long id, @RequestParam String title, @RequestParam TaskStatus status){
        taskService.updateTask(id,title,status);
    }

    @DeleteMapping
    public void deleteTask(@RequestParam Long id){
        taskService.deleteTask(id);
    }
}
