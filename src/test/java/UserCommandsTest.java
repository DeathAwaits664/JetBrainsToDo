import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserCommandsTest {
    TaskHandler taskHandler = new TaskHandler("src/main/resources/todo-list_test.json");
    UserCommands uc = new UserCommands();

    @BeforeEach
    void load() throws IOException {
        taskHandler.loadList();
    }

    @Test
    void executeAdd() {
        assertEquals("Task was successfully added", uc.execute(taskHandler, "ADD", "Hello"));

    }

    @Test
    void executePrint() {
        TaskEntity t1 = new TaskEntity("Hello");
        TaskEntity t2 = new TaskEntity("Friend");
        taskHandler.addTaskToList(t1);
        taskHandler.addTaskToList(t2);

        assertEquals("№  Task Read Done\n" +
                "1. Hello false false  \n" +
                "2. Friend false false  \n", uc.execute(taskHandler, "PRINT", "ALL"));
        t1.setDone(true);
        assertEquals("№  Task Read Done\n" +
                "2. Friend false false  \n", uc.execute(taskHandler, "PRINT", "UPCOMING"));


    }

    @Test
    void executeHelp() {
        assertEquals("There are some command that will help you.\n" +
                "ADD \"TODO-text\" - allows you to add task to the to-do list \n" +
                "REMOVE \"TODO-id\" - allows you to remove task from the to-do list \n" +
                "READ \"TODO-id\" - allows you to mark task as read\n" +
                "COMPLETE \"TODO-id\" - allows you to mark task as done\n" +
                "SAVE - allows you to save your list to TO-DO.json file\n" +
                "PRINT ALL - allows you to print your TODO-list\n" +
                "PRINT UPCOMING - allows you to print only not completed tasks\n", uc.execute(taskHandler, "HELP", ""));
    }

    @Test
    void executeRemove() {
        TaskEntity t1 = new TaskEntity("Hello");
        TaskEntity t2 = new TaskEntity("Friend");
        taskHandler.addTaskToList(t1);
        taskHandler.addTaskToList(t2);
        assertEquals("Success", uc.execute(taskHandler, "REMOVE", "1"));
        assertEquals("Wrong argument format ,try something else", uc.execute(taskHandler, "REMOVE", "barabarabara"));
        assertEquals("There is no such TODO", uc.execute(taskHandler, "REMOVE", "5"));

    }

    @Test
    void executeSave() {
        TaskEntity t1 = new TaskEntity("Hello");
        TaskEntity t2 = new TaskEntity("Friend");
        taskHandler.addTaskToList(t1);
        taskHandler.addTaskToList(t2);
        assertEquals("Successfully saved", uc.execute(taskHandler, "SAVE", ""));

    }

    @Test
    void executeComplete() {
        TaskEntity t1 = new TaskEntity("Hello");
        TaskEntity t2 = new TaskEntity("Friend");
        taskHandler.addTaskToList(t1);
        taskHandler.addTaskToList(t2);
        assertEquals("Successfully completed", uc.execute(taskHandler, "COMPLETE", "1"));
        assertEquals("Wrong argument format ,try something else", uc.execute(taskHandler, "COMPLETE", "bereberebere"));
        assertEquals("There is no such TODO", uc.execute(taskHandler, "COMPLETE", "5"));
    }

    @Test
    void executeRead() {
        TaskEntity t1 = new TaskEntity("Hello");
        TaskEntity t2 = new TaskEntity("Friend");
        taskHandler.addTaskToList(t1);
        taskHandler.addTaskToList(t2);
        assertEquals("Successfully read", uc.execute(taskHandler, "READ", "1"));
        assertEquals("Wrong argument format ,try something else", uc.execute(taskHandler, "READ", "bereberebere"));
        assertEquals("There is no such TODO", uc.execute(taskHandler, "READ", "5"));

    }
}