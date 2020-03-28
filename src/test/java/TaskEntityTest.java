import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TaskEntityTest {
    TaskEntity taskEntity;

    @BeforeEach
    void setUp() {
        this.taskEntity = new TaskEntity();
    }

    @AfterEach
    void tearDown() {
        this.taskEntity = null;
    }

    @Test
    void setDeleted() throws NoSuchFieldException, IllegalAccessException {


        final Field field = taskEntity.getClass().getDeclaredField("isDeleted");
        field.setAccessible(true);
        assertEquals(false, field.get(taskEntity));
        taskEntity.setDeleted(true);
        assertEquals(true, field.get(taskEntity));

    }

    @Test
    void getDeleted() throws NoSuchFieldException, IllegalAccessException {

        final Field field = taskEntity.getClass().getDeclaredField("isDeleted");
        field.setAccessible(true);

        assertEquals(false, taskEntity.getDeleted());

        field.set(taskEntity, true);

        assertEquals(true, taskEntity.getDeleted());
    }


    @Test
    void setRead() throws NoSuchFieldException, IllegalAccessException {
        final Field field = taskEntity.getClass().getDeclaredField("isRead");
        field.setAccessible(true);
        assertEquals(false, field.get(taskEntity));
        taskEntity.setRead(true);
        assertEquals(true, field.get(taskEntity));

    }

    @Test
    void getRead() throws NoSuchFieldException, IllegalAccessException {
        final Field field = taskEntity.getClass().getDeclaredField("isRead");
        field.setAccessible(true);

        assertEquals(false, taskEntity.getRead());

        field.set(taskEntity, true);

        assertEquals(true, taskEntity.getRead());


    }

    @Test
    void getCreationTime() throws NoSuchFieldException {

        final Field field = taskEntity.getClass().getDeclaredField("creationTime");
        field.setAccessible(true);
        assertNotEquals(null, taskEntity.getCreationTime());

    }

    @Test
    void setTaskText() throws NoSuchFieldException, IllegalAccessException {
        final Field field = taskEntity.getClass().getDeclaredField("taskText");
        field.setAccessible(true);
        assertEquals("", field.get(taskEntity));
        taskEntity.setTaskText("Hello");
        assertEquals("Hello", field.get(taskEntity));
    }

    @Test
    void getTaskText() throws NoSuchFieldException, IllegalAccessException {
        final Field field = taskEntity.getClass().getDeclaredField("taskText");
        field.setAccessible(true);

        assertEquals("", taskEntity.getTaskText());

        field.set(taskEntity, "Hello");

        assertEquals("Hello", taskEntity.getTaskText());

    }

    @Test
    void setDone() throws NoSuchFieldException, IllegalAccessException {
        final Field field = taskEntity.getClass().getDeclaredField("isDone");
        field.setAccessible(true);
        assertEquals(false, field.get(taskEntity));
        taskEntity.setDone(true);
        assertEquals(true, field.get(taskEntity));

    }

    @Test
    void getDone() throws IllegalAccessException, NoSuchFieldException {

        final Field field = taskEntity.getClass().getDeclaredField("isDone");
        field.setAccessible(true);

        assertEquals(false, taskEntity.getDone());

        field.set(taskEntity, true);

        assertEquals(true, taskEntity.getDone());
    }
}