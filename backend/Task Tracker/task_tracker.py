import os
import sys

TASK_FILE = "task_file.json"

ADD = "add"
UPDATE = "update"
DELETE = "delete"
MARK_IN_PROGRESS = "mark-in-progress"
MARK_DONE = "mark-done"
LIST = "list"
DONE = "done"
TODO = "todo"
IN_PROGRESS = "in-progress"

def check_task_file():
    if not os.path.exists(TASK_FILE):
        with open(TASK_FILE, "w") as f:
            pass

def check_input():
    if len(sys.argv) > 1:
        command = sys.argv[1]
        if command == ADD:
            if len(sys.argv) > 2:
                description = sys.argv[2]
                return (command, None, description)
            else :
                print(f"Please provide task description.")
        elif command == DELETE or command == MARK_IN_PROGRESS or command == MARK_DONE:
            if len(sys.argv) > 2:
                id = sys.argv[2]
                return (command, id)
            else :
                print(f"Please provide task id.")
        elif command == UPDATE:
            if len(sys.argv) > 3:
                id = sys.argv[2]
                description = sys.argv[3]
                return (command, id, description)
            else :
                print(f"Please provide task id and updated description.")
        elif command == LIST:
            if len(sys.argv) > 2:
                status = sys.argv[4]
                if status == DONE or status == TODO or status == IN_PROGRESS:
                    return (status)
                else:
                    print(f"Please provide a valid list option.")
            else:
                return (command)
        else:
            print(f"Please provide a valid instruction.")    
    else:
        print(f"Please provide a valid instruction.")

def add_task(description):
    with open(TASK_FILE, "w") as f:
        pass      

def perform_operation(command, id, description):    
    if command == ADD:
        add_task(description)

if __name__ == '__main__':
    # Check if json file exists, if not then create it
    check_task_file()

    # Check command inputs
    command, id, description = check_input()

    # Perform operations based on command
    perform_operation(command, id, description)
