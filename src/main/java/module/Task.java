package module;

import java.util.Date;

import static module.TaskStatus.PENDING;

//!Task is used for data storing

public class Task {
    private final Long id;
    private String title;
    private TaskStatus status = PENDING; //default status PENDING
    private final Date createdAt;
    private Date updatedAt;

    public Task(long ID, String titleTask, TaskStatus statusTask, Date CreatedAt, Date UpdatedAt) {
        this.id = ID;
        this.title = titleTask;
        this.status = statusTask;
        this.createdAt = CreatedAt;
        this.updatedAt = UpdatedAt;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public void setStatus(TaskStatus newStatus){
        this.status = newStatus;
    }

    public void setUpdatedAt(Date UpdatedAt){
        this.updatedAt = UpdatedAt;
    }

}
