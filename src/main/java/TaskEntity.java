import java.util.Date;

/**
 * The type Task entity.
 */
public class TaskEntity {
    /**
     * Task text
     */
    private String taskText;
    /**
     * Task creation time
     */
    private final Date creationTime;
    /**
     * Task done - true, task not done - false
     */
    private Boolean isDone;
    /**
     * Task read - true, task not read - false
     */
    private Boolean isRead;
    /**
     * Task deleted in current session - true, task not deleted in current session - false
     */
    private Boolean isDeleted;

    /**
     * Constructor without parameters for Jackson JSON serialization
     */
    public TaskEntity() {
        this.taskText = "";
        this.creationTime = new Date();
        this.isDone = false;
    }

    /**
     * Instantiates a new Task entity.
     *
     * @param taskText the task text
     */
    public TaskEntity(String taskText) {
        this.taskText = taskText;
        this.creationTime = new Date();
        this.isDone = false;
        this.isRead = false;
        this.isDeleted = false;
    }

    /**
     * Sets deleted.
     *
     * @param deleted the deleted
     */
    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    /**
     * Gets deleted.
     *
     * @return the deleted
     */
    public Boolean getDeleted() {
        return isDeleted;
    }

    /**
     * Sets read.
     *
     * @param read the read
     */
    public void setRead(Boolean read) {
        isRead = read;
    }

    /**
     * Gets read.
     *
     * @return the read
     */
    public Boolean getRead() {
        return isRead;
    }

    /**
     * Gets creation time.
     *
     * @return the creation time
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * Sets task text.
     *
     * @param taskText the task text
     */
    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    /**
     * Gets task text.
     *
     * @return the task text
     */
    public String getTaskText() {
        return taskText;
    }

    /**
     * Sets done.
     *
     * @param done the done
     */
    public void setDone(Boolean done) {
        isDone = done;
    }

    /**
     * Gets done.
     *
     * @return the done
     */
    public Boolean getDone() {
        return isDone;
    }
}
