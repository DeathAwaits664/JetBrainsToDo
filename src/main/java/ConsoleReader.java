import java.io.IOException;
import java.util.Scanner;

public class ConsoleReader {
    private Scanner scanner;
    private ToDoList toDoList;
    private static String help = "There are some command that will help you.\n" +
            "ADD \"TODO-text\" - allows you to add task to the to-do list \n" +
            "REMOVE \"TODO-id\" - allows you to remove task from the to-do list \n" +
            "READ \"TODO-id\" - allows you to mark task as read\n" +
            "COMPLETE \"TODO-id\" - allows you to mark task as done\n" +
            "SAVE - allows you to save your list to TO-DO.json file\n" +
            "PRINT ALL - allows you to print your TODO-list\n" +
            "PRINT UPCOMING - allows you to print only not completed tasks\n";

    public ConsoleReader(ToDoList toDoList) {

        this.toDoList = toDoList;
        this.scanner = new Scanner(System.in);


    }

    public void start() throws IOException {
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

    public void execute(String input) throws IOException {

        String command;
        String arg;
        if (input.contains("ADD ") || input.contains("REMOVE ") || input.contains("COMPLETE ") || input.contains("READ ") || input.contains("PRINT ")) {
            command = input.substring(0, input.indexOf(" "));

            arg = input.substring(input.indexOf(" ") + 1);
        } else {
            command = input;
            arg = "";
        }


        try {
            switch (command) {
                case "HELP": {
                    System.out.println(help);
                    break;
                }

                case "PRINT": {

                    switch (arg) {
                        case "ALL": {
                            System.out.println(toDoList.printList(false));
                            break;
                        }
                        case "UPCOMING": {
                            System.out.println(toDoList.printList(true));
                            break;
                        }
                        default:
                            System.out.println("Wrong argument");
                    }
                    break;
                }

                case "ADD": {
                    if (toDoList.addToDoToList(new ToDo(arg))) {
                    } else {
                        System.out.println("Something goes wrong with adding new TODO");
                    }
                    break;
                }

                case "SAVE": {
                    toDoList.saveList();
                    break;
                }

                case "REMOVE": {
                    int index = Integer.parseInt(arg) - 1;
                    if (toDoList.removeToDoFromList(index)) {
                    } else {
                        System.out.println("There is no such TODO");
                    }
                    break;
                }

                case "COMPLETE": {
                    int index = Integer.parseInt(arg) - 1;
                    if (this.toDoList.completeToDo(index)) {
                    } else {
                        System.out.println("There is no such TODO");
                    }
                    break;


                }
                case "READ": {
                    int index = Integer.parseInt(arg) - 1;
                    if (this.toDoList.readToDo(index)) {
                    } else {
                        System.out.println("There is no such TODO");
                    }
                    break;


                }
                default:
                    System.out.println("There is no such command");


            }

        } catch (NumberFormatException e) {
            System.out.println("Wrong argument format ,try something else");

        }


    }


}
