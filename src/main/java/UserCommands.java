import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserCommands {

    private static final Map<String, UserCommands> COMMANDS_MAP;

    static {
        final Map<String, UserCommands> commandsMap = new HashMap<>();
        commandsMap.put("ADD", new UserCommands() {
            @Override
            public String execute(TaskHandler taskHandler, String command, String param) {
                if (taskHandler.addTaskToList(new TaskEntity(param)))
                    return "Task was successfully added";
                else return "Something goes wrong";
            }
        });

        commandsMap.put("PRINT", new UserCommands() {
            @Override
            public String execute(TaskHandler taskHandler, String command, String param) {

                switch (param) {
                    case "ALL": {
                        return taskHandler.printList(false);
                    }
                    case "UPCOMING": {
                        return taskHandler.printList(true);
                    }
                    default:
                        return "Wrong argument";
                }


            }
        });
        commandsMap.put("HELP", new UserCommands() {
            @Override
            public String execute(TaskHandler taskHandler, String command, String param) {
                return "There are some command that will help you.\n" +
                        "ADD \"TODO-text\" - allows you to add task to the to-do list \n" +
                        "REMOVE \"TODO-id\" - allows you to remove task from the to-do list \n" +
                        "READ \"TODO-id\" - allows you to mark task as read\n" +
                        "COMPLETE \"TODO-id\" - allows you to mark task as done\n" +
                        "SAVE - allows you to save your list to TO-DO.json file\n" +
                        "PRINT ALL - allows you to print your TODO-list\n" +
                        "PRINT UPCOMING - allows you to print only not completed tasks\n";
            }
        });

        commandsMap.put("REMOVE", new UserCommands() {
            @Override
            public String execute(TaskHandler taskHandler, String command, String param) {
                try {

                    int index = Integer.parseInt(param) - 1;


                    if (!taskHandler.markTaskAsDeleted(index)) {
                        return ("There is no such TODO");
                    }
                    return "Success";
                } catch (NumberFormatException e) {
                    return "Wrong argument format ,try something else";

                }
            }
        });
        commandsMap.put("SAVE", new UserCommands() {
            @Override
            public String execute(TaskHandler taskHandler, String command, String param) {
                return taskHandler.saveList();
            }
        });
        commandsMap.put("COMPLETE", new UserCommands() {
            @Override
            public String execute(TaskHandler taskHandler, String command, String param) {
                try {
                    int index = Integer.parseInt(param) - 1;
                    if (taskHandler.completeTask(index)) {
                        return "Successfully completed";
                    } else {
                        return "There is no such TODO";
                    }

                } catch (NumberFormatException e) {
                    return "Wrong argument format ,try something else";

                }

            }
        });
        commandsMap.put("READ", new UserCommands() {
            @Override
            public String execute(TaskHandler taskHandler, String command, String param) {
                try {
                    int index = Integer.parseInt(param) - 1;
                    if (taskHandler.readTask(index)) {
                        return "Successfully read";
                    } else {
                        return "There is no such TODO";
                    }

                } catch (NumberFormatException e) {
                    return "Wrong argument format ,try something else";

                }
            }
        });


        COMMANDS_MAP = Collections.unmodifiableMap(commandsMap);
    }

    public String execute(TaskHandler taskHandler, String command, String param) {
        UserCommands comm = COMMANDS_MAP.get(command);

        if (comm == null) {
            return "There is no such command";
        }

        return comm.execute(taskHandler, command, param);
    }

}