import java.util.Date;

public class ToDo {
    private int toDoId;
    private String toDoText;
    private Date creationTime;
    private Boolean isDone;
    private Boolean isRead;

    public ToDo() {
        this.toDoText = "";
        this.creationTime = new Date();
        this.isDone = false;
    }

    public ToDo(String taskText) {
        this.toDoText = taskText;
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

    public void setToDoId(int taskId) {
        this.toDoId = taskId;
    }

    public int getToDoId() {
        return toDoId;
    }

    public void setToDoText(String taskText) {
        this.toDoText = taskText;
    }

    public String getToDoText() {
        return toDoText;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public Boolean getDone() {
        return isDone;
    }
}
