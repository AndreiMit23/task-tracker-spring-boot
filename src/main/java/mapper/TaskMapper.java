package mapper;

import module.Task;
import module.TaskRequest;
import module.TaskResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {

    public TaskResponse toResponse(Task task) {
        return new TaskResponse(task.getId(), task.getTitle(), task.getStatus(), task.getDescription(), task.getTaskPriority(), task.getDueDate());
    }

    public List<TaskResponse> toResponseList(List<Task> tasks){
        List<TaskResponse> taskResponses = new ArrayList<>();
        for(Task task : tasks){
            taskResponses.add(toResponse(task));
        }

        return taskResponses;
    }

    public Task toEntity(TaskRequest taskRequest){
        return new Task(taskRequest.getTitle(), taskRequest.getDescription(), taskRequest.getPriority(), taskRequest.getDueDate());
    }
}
