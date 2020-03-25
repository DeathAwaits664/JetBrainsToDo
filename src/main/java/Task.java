import java.util.Date;

public class Task {
    private int taskId;
    private String taskText;
    private Date creationTime;
    private Boolean isDone;
    private Boolean isRead;

    public Task() {
        this.taskText = "";
        this.creationTime = new Date();
        this.isDone = false;
    }

    public Task(String taskText) {
        this.taskText = taskText;
        this.creationTime = new Date();
        this.isDone = false;
        this.isRead = false;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Boolean getRead() {
        return isRead;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public Boolean getDone() {
        return isDone;
    }
}
