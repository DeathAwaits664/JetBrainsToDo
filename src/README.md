# TODO CONSOLE APPLICATION

Opportunities:
  - Create TODO-list
  - Print TODO-list 
  - Print only not completed tasks
  - Add task to the list
  - Delete task from list
  - Mark task as read
  - Mark task as done
  - Save your list to JSON-file



# COMMANDS
There are some commands you can use 

```sh
  HELP 
  ```
Prints a list of all commands

  
```sh
ADD "TODO-text"
  ```
Add task to the to-do list
```sh
REMOVE "TODO-id"
 ```
 Remove task from to-do list. You need to use the task-id from the PRINT command.
 

```sh
READ "TODO-id"
 ```
 Mark task as read. You need to use the task-id from the PRINT command.
```sh
COMPLETE "TODO-id"
 ```
  Mark task as done. You need to use the task-id from the PRINT command.
  
  ```sh
SAVE
 ```
 Save your list to TO-DO.json file.
 
   ```sh
PRINT ALL
 ```
 Print your TODO-list
 
   ```sh
PRINT UPCOMING
 ```
 Print only not completed task