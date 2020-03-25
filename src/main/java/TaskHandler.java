import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TaskHandler {
    private List<Task> taskList;
    private String path;

    public TaskHandler(String path) throws IOException {
        this.path = path;
        this.taskList = loadList();


    }


    public Boolean addTaskToList(Task t) {
        try {
            this.taskList.add(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean completeTask(int index) {
        try {
            Task td = this.taskList.get(index);
            td.setDone(true);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public Boolean readTask(int index) {
        try {
            Task td = this.taskList.get(index);
            td.setRead(true);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public Boolean removeTaskFromList(int t) {
        try {


            this.taskList.remove(t);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

    }


    public List<Task> loadList() throws IOException {
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
            return new LinkedList<>();

        } else {
            ObjectMapper objectMapper = new ObjectMapper();

            CollectionType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Task.class);


            return objectMapper.readValue(result, javaType);
        }


    }

    public String saveList() {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(this.path), this.taskList);
            return "Successfuly saved";
        } catch (IOException ex) {
            return "Can't create file";

        }


    }


    public String printList(Boolean printOnlyNotDone) {
        Formatter f = new Formatter();
        f.format("№  Task Readed Done\n");

        if (this.taskList.isEmpty()) return "Empty TODO-list...";

        if (printOnlyNotDone) {
            for (Task t : this.taskList) {


                if (!t.getDone())
                    f.format("%d. %5s %b %b%n", this.taskList.indexOf(t) + 1, t.getTaskText(), t.getRead(), t.getDone());


            }


        } else {


            for (Task t : this.taskList) {


                f.format("%d. %s %b %b%n", this.taskList.indexOf(t) + 1, t.getTaskText(), t.getRead(), t.getDone());


            }
        }

        return f.toString();
    }


}