package module;

import jakarta.persistence.*;

import java.util.Date;

import static module.TaskStatus.PENDING;

//!Task is used for data storing
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    TaskStatus status = PENDING; //default status PENDING
    @Column(name = "created_at")
    Date createdAt;
    Date updatedAt;

    public Task(Long ID, String titleTask, TaskStatus statusTask, Date CreatedAt, Date UpdatedAt) {
        this.id = ID;
        this.title = titleTask;
        this.status = statusTask;
        this.createdAt = CreatedAt;
        this.updatedAt = UpdatedAt;
    }
    public Task(String titleTask, TaskStatus statusTask, Date CreatedAt, Date UpdatedAt) {
        this.title = titleTask;
        this.status = statusTask;
        this.createdAt = CreatedAt;
        this.updatedAt = UpdatedAt;
    }

    public Task() {

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
