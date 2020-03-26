import java.io.IOException;

/**
 * The type Main.
 */
public class Main {


    public static void main(String[] args) throws IOException {
        TaskHandler tl = new TaskHandler("src/main/resources/todo-list.json");
        ConsoleReader cr = new ConsoleReader(tl);
        cr.start();


    }


}
