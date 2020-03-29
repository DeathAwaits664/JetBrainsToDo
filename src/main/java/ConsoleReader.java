import java.util.Scanner;

public class ConsoleReader {
    private final Scanner scanner;
    private final TaskHandler taskHandler;
    public ConsoleReader(TaskHandler taskHandler) {
        this.taskHandler = taskHandler;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts console reader
     */
    public void start() {
        System.out.println("You are working with JetBrains TODO-list.\n" +
                "Enter HELP to learn about the functionality.");
        String input = "";


        while (!input.equals("exit")) {
            input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            } else {

                execute(input);


            }
        }
        this.taskHandler.saveList();
    }

    /**
     * Execute users command
     *
     * @param input Input string
     */
    public void execute(String input) {


        String command;
        String arg;
        if (input.contains("ADD ") ||
                input.contains("REMOVE ") ||
                input.contains("COMPLETE ") ||
                input.contains("READ ") ||
                input.contains("PRINT ")) {
            command = input.substring(0, input.indexOf(" "));
            arg = input.substring(input.indexOf(" ") + 1);
        } else {
            command = input;
            arg = "";
        }

        UserCommands uc = new UserCommands();
        System.out.println(uc.execute(this.taskHandler, command, arg));


    }


    }
