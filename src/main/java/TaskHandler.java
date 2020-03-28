import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


/**
 * The type Task handler.
 */
public class TaskHandler {
    /**
     * List of all tasks
     */
    private List<TaskEntity> taskList;

    /**
     * Variable to store the path to the file to which our list will be saved
     */
    private final String path;

    /**
     * Instantiates a new Task handler.
     *
     * @param path the path to file
     * @throws IOException the io exception
     */
    public TaskHandler(String path) {
        this.path = path;


    }


    /**
     * Add task to list boolean.
     *
     * @param t New task
     * @return true - success, false - fails
     */
    public Boolean addTaskToList(TaskEntity t) {
        try {
            this.taskList.add(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Mark task as completed.
     *
     * @param index Task index in list
     * @return true - success, false - fails
     */
    public Boolean completeTask(int index) {
        try {
            TaskEntity td = this.taskList.get(index);
            if (!td.getDeleted()) {
                td.setDone(true);
            } else return false;
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Mark task as deleted boolean.
     *
     * @param index Task index in list
     * @return true - success, false - fails
     */
    public Boolean markTaskAsDeleted(int index) {
        try {
            TaskEntity td = this.taskList.get(index);
            if (!td.getDeleted()) {
                td.setDeleted(true);
            } else return false;
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }


    }


    /**
     * Mark task as read.
     *
     * @param index Task index in list
     * @return true - success, false - fails
     */
    public Boolean readTask(int index) {
        try {
            TaskEntity td = this.taskList.get(index);
            if (!td.getDeleted()) {
                td.setRead(true);
            } else return false;
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Remove task from list.
     *
     * @param t Task to remove
     * @return true - success, false - fails
     */
    public Boolean removeTaskFromList(int t) {
        try {


            this.taskList.remove(t);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

    }


    /**
     * Loads a to-do list along the path specified in the constructor,
     * loading occurs when the application starts.
     * If the file is missing, an empty file is created and the default collection is empty.
     *
     * @return List from the file
     * @throws IOException the io exception
     */
    public List<TaskEntity> loadList() throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            FileReader fr = new FileReader(this.path);
            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine()) {
                sb.append(scan.nextLine());
                sb.append("\n");
            }
            fr.close();
        } catch (IOException ex) {

            System.out.println("We could not find the TODO-json file, so we decided to create it ourselves");
            File file = new File(path);
            file.createNewFile();
            loadList();
        }
        String result = sb.toString();
        if (result.isEmpty()) {
            this.taskList = new LinkedList<>();
            return this.taskList;

        } else {
            ObjectMapper objectMapper = new ObjectMapper();

            CollectionType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, TaskEntity.class);


            this.taskList = objectMapper.readValue(result, javaType);
            return this.taskList;
        }


    }

    /**
     * Save list to the file. Before writing to a file,
     * it checks the collection for tasks marked as deleted and deletes them
     * If the file does not exist at the time of saving, automatically creates it. If the file fails to create throws an exception.
     *
     * @return String
     */
    public String saveList() {
        this.taskList.removeIf(TaskEntity::getDeleted);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(this.path), this.taskList);
            return "Successfully saved";
        } catch (IOException ex) {
            return "Can't create file";

        }


    }


    /**
     * If true, it returns only tasks that have not been completed in string format, otherwise it returns all tasks in string format.
     *
     * @param printOnlyNotDone true - returns not completed, false - returns all
     * @return Collection in string format.
     */
    public String printList(Boolean printOnlyNotDone) {
        Formatter f = new Formatter();
        f.format("№  Task Read Done\n");
        if (this.taskList.isEmpty()) return "Empty TODO-list...";
        taskList.stream().filter(t -> !printOnlyNotDone || t.getDone() == false)
                .forEach(t -> f.format("%d. %5s %b %b %s %n", this.taskList.indexOf(t) + 1, t.getTaskText(), t.getRead(), t.getDone(), t.getDeleted() == true ? "DELETED" : ""));
        return f.toString();
    }


}