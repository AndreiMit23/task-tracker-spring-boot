package module;
import java.time.LocalDateTime;

public class TaskRequest {
    private String title;
    private String description;
    private TaskPriority priority;
    private LocalDateTime dueDate;

    public TaskRequest(String title, String description, TaskPriority priority, LocalDateTime dueDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public TaskRequest() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate){
        this.dueDate = dueDate;
    }

}
