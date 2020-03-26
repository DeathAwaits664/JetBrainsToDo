//
//public enum Commands {
//    ADD {
//        @Override
//        public String execute(TaskHandler taskHandler, String arg) {
//
//            if (taskHandler.addTaskToList(new TaskEntity(arg)))
//                return "Task was successfully added";
//            else return "Something goes wrong";
//        }
//
//    },
//    REMOVE {
//        @Override
//        public String execute(TaskHandler taskHandler, String arg) {
//
//            try {
//
//                int index = Integer.parseInt(arg) - 1;
//
//
//                if (!taskHandler.markTaskAsDeleted(index)) {
//                    return ("There is no such TODO");
//                }
//                return "Success";
//            } catch (NumberFormatException e) {
//                return "Wrong argument format ,try something else";
//
//            }
//
//
//        }
//
//    },
//
//    PRINT {
//        @Override
//        public String execute(TaskHandler taskHandler, String arg) {
//            switch (arg) {
//                case "ALL": {
//                    return taskHandler.printList(false);
//                }
//                case "UPCOMING": {
//                    return taskHandler.printList(true);
//                }
//                default:
//                    return "Wrong argument";
//            }
//        }
//
//    },
//    HELP {
//        @Override
//        public String execute(TaskHandler taskHandler, String arg) {
//            return "There are some command that will help you.\n" +
//                    "ADD \"TODO-text\" - allows you to add task to the to-do list \n" +
//                    "REMOVE \"TODO-id\" - allows you to remove task from the to-do list \n" +
//                    "READ \"TODO-id\" - allows you to mark task as read\n" +
//                    "COMPLETE \"TODO-id\" - allows you to mark task as done\n" +
//                    "SAVE - allows you to save your list to TO-DO.json file\n" +
//                    "PRINT ALL - allows you to print your TODO-list\n" +
//                    "PRINT UPCOMING - allows you to print only not completed tasks\n";
//
//        }
//    },
//    SAVE {
//        @Override
//        public String execute(TaskHandler taskHandler, String arg) {
//            return taskHandler.saveList();
//
//        }
//    },
//    COMPLETE {
//        @Override
//        public String execute(TaskHandler taskHandler, String arg) {
//
//            try {
//                int index = Integer.parseInt(arg) - 1;
//                if (taskHandler.completeTask(index)) {
//                    return "Successfully completed";
//                } else {
//                    return "There is no such TODO";
//                }
//
//            } catch (NumberFormatException e) {
//                return "Wrong argument format ,try something else";
//
//            }
//
//        }
//    },
//    READ {
//        @Override
//        public String execute(TaskHandler taskHandler, String arg) {
//
//            try {
//                int index = Integer.parseInt(arg) - 1;
//                if (taskHandler.readTask(index)) {
//                    return "Successfully read";
//                } else {
//                    return "There is no such TODO";
//                }
//
//            } catch (NumberFormatException e) {
//                return "Wrong argument format ,try something else";
//
//            }
//
//
//        }
//    },
//
//    UNKNOWN {
//        @Override
//        public String execute(TaskHandler taskHandler, String arg) {
//            return "No such command";
//        }
//    };
//
//
//    public abstract String execute(TaskHandler taskHandler, String arg);
//
//    public static Commands getByName(String command) {
//        for (Commands commands : Commands.values()) {
//            if (command.equals(commands.name())) {
//                return commands;
//            }
//        }
//        return Commands.UNKNOWN;
//    }
//}