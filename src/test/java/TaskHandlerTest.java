import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskHandlerTest {
    TaskHandler th = new TaskHandler("");
    final Field pathField = th.getClass().getDeclaredField("path");
    final Field listField = th.getClass().getDeclaredField("taskList");

    TaskHandlerTest() throws NoSuchFieldException {
    }

    @BeforeEach
    void setUp() {


    }

    @AfterEach
    void tearDown() {
        this.th = null;
    }


    //The method tries to get the file at the specified path, if the file does not exist, it creates a new file. The to-do list remains empty.
    @Test
    void loadListWithoutFile() throws IOException, IllegalAccessException {
        pathField.setAccessible(true);
        pathField.set(th, "src/main/resources/todo-list_NOFILE.json");
        List<TaskEntity> taskEntityList = th.loadList();
        File file = new File("src/main/resources/todo-list_NOFILE.json");
        assertTrue(file.exists());
        List<TaskEntity> emptyTaskEntityList = new LinkedList<>();
        assertEquals(emptyTaskEntityList, taskEntityList);

    }

    @Test
    void loadListWithEmptyFile() throws IOException, IllegalAccessException {
        pathField.setAccessible(true);
        pathField.set(th, "src/main/resources/todo-list_EMPTY.json");
        List<TaskEntity> taskEntityList = th.loadList();
        List<TaskEntity> emptyTaskEntityList = new LinkedList<>();
        assertEquals(emptyTaskEntityList, taskEntityList);

    }

    @Test
    void loadListWithLittleLoad() throws IOException, IllegalAccessException {
        pathField.setAccessible(true);
        pathField.set(th, "src/main/resources/todo-list_LITTLE.json");
        List<TaskEntity> taskEntityList = th.loadList();
        assertEquals(101, taskEntityList.size());

    }

    @Test
    void loadListWithMiddleLoad() throws IOException, IllegalAccessException {
        pathField.setAccessible(true);
        pathField.set(th, "src/main/resources/todo-list_MIDDLE.json");
        List<TaskEntity> taskEntityList = th.loadList();
        assertEquals(1001, taskEntityList.size());

    }

    @Test
    void loadListWithHighLoad() throws IOException, IllegalAccessException {
        pathField.setAccessible(true);
        pathField.set(th, "src/main/resources/todo-list_HIGH.json");
        List<TaskEntity> taskEntityList = th.loadList();
        assertEquals(10001, taskEntityList.size());

    }

    @Test
    void loadListWithHugeLoad() throws IOException, IllegalAccessException {
        pathField.setAccessible(true);
        pathField.set(th, "src/main/resources/todo-list_HUGE.json");
        List<TaskEntity> taskEntityList = th.loadList();
        assertEquals(100001, taskEntityList.size());

    }


    @Test
    void addTaskToList() throws IllegalAccessException {
        listField.setAccessible(true);
        listField.set(th, new LinkedList<TaskEntity>());
        TaskEntity te = new TaskEntity("HelloTask");
        th.addTaskToList(te);
        assertEquals(listField.get(this.th), new LinkedList<>(Arrays.asList(te)));


    }

    @Test
    void completeTask() throws IllegalAccessException {
        listField.setAccessible(true);
        TaskEntity te = new TaskEntity("Hello");
        listField.set(th, new LinkedList<>(Arrays.asList(te)));
        th.completeTask(0);
        assertEquals(true, ((LinkedList<TaskEntity>) listField.get(this.th)).get(0).getDone());
    }

    @Test
    void markTaskAsDeleted() throws IllegalAccessException {
        listField.setAccessible(true);
        TaskEntity te = new TaskEntity("Hello");
        listField.set(th, new LinkedList<>(Arrays.asList(te)));
        th.markTaskAsDeleted(0);
        assertEquals(true, ((LinkedList<TaskEntity>) listField.get(this.th)).get(0).getDeleted());
    }

    @Test
    void readTask() throws IllegalAccessException {
        listField.setAccessible(true);
        TaskEntity te = new TaskEntity("Hello");
        listField.set(th, new LinkedList<>(Arrays.asList(te)));
        th.readTask(0);
        assertEquals(true, ((LinkedList<TaskEntity>) listField.get(this.th)).get(0).getRead());

    }

    @Test
    void removeTaskFromList() throws IllegalAccessException {
        listField.setAccessible(true);
        TaskEntity te = new TaskEntity("HelloTask");
        listField.set(th, new LinkedList<>(Arrays.asList(te)));

        th.removeTaskFromList(0);
        assertEquals(0, ((LinkedList<TaskEntity>) listField.get(this.th)).size());

    }


    @Test
    void saveList() throws IllegalAccessException, IOException {
        pathField.setAccessible(true);
        pathField.set(th, "src/main/resources/todo-list_test.json");
        listField.setAccessible(true);
        TaskEntity te = new TaskEntity("HelloTask");
        listField.set(th, new LinkedList<>(Arrays.asList(te)));
        th.saveList();
        StringBuilder sb = new StringBuilder();
        FileReader fr = new FileReader(((String) pathField.get(this.th)));
        Scanner scan = new Scanner(fr);

        while (scan.hasNextLine()) {
            sb.append(scan.nextLine());
            sb.append("\n");
        }
        String result = sb.toString();
        StringBuilder sbt = new StringBuilder();
        sbt.append("[{\"taskText\":\"").append(te.getTaskText()).append("\",\"creationTime\":")
                .append(te.getCreationTime()).append(",\"done\":")
                .append(te.getDone()).append(",\"deleted\":")
                .append(te.getDeleted()).append(",\"read\":")
                .append(te.getRead()).append("}]");
        assertEquals(sb.toString(), result);
        File file = new File(((String) pathField.get(this.th)));
        file.delete();
    }

    @Test
    void printList() throws IllegalAccessException {
        listField.setAccessible(true);

        TaskEntity t1 = new TaskEntity("Hello");
        TaskEntity t2 = new TaskEntity("My friend");
        TaskEntity t3 = new TaskEntity("Lorem");
        TaskEntity t4 = new TaskEntity("Ipsum");
        listField.set(th, new LinkedList<>(Arrays.asList(t1, t2, t3, t4)));
        assertEquals("№  Task Read Done\n" +
                "1. Hello false false  \n" +
                "2. My friend false false  \n" +
                "3. Lorem false false  \n" +
                "4. Ipsum false false  \n", th.printList(false));
        t1.setDone(true);
        assertEquals("№  Task Read Done\n" +
                "2. My friend false false  \n" +
                "3. Lorem false false  \n" +
                "4. Ipsum false false  \n", th.printList(true));


    }
}