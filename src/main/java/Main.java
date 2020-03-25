import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {

        ToDoList tl = new ToDoList("src/main/resources/todo-list.json");
        ConsoleReader cr = new ConsoleReader(tl);
        cr.start();


    }


}
