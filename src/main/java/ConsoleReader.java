import java.util.Scanner;

public class ConsoleReader {
    private Scanner scanner;
    private TaskHandler toDoList;

    public ConsoleReader(TaskHandler toDoList) {

        this.toDoList = toDoList;
        this.scanner = new Scanner(System.in);


    }

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
    }

    public void execute(String input) {

        String command;
        String arg;
        if (input.contains("ADD ") || input.contains("REMOVE ") || input.contains("COMPLETE ") || input.contains("READ ") || input.contains("PRINT ")) {
            command = input.substring(0, input.indexOf(" "));
            arg = input.substring(input.indexOf(" ") + 1);
        } else {
            command = input;
            arg = "";
        }
        System.out.println(Commands.getByName(command).execute(this.toDoList, arg));

    }


    }
