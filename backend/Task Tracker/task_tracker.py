from datetime import datetime
import json
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

def get_next_highest_id():
    try:
        with open(TASK_FILE, "r", encoding="utf-8") as f:
            tasks = json.load(f)
    except (FileNotFoundError, json.JSONDecodeError):
        print("Error: File not found or invalid JSON format.")
        return 1

    if not tasks:
        return 1

    tasks.sort(key=lambda x: int(x["id"]))

    highest_id_task = tasks[-1]
    return highest_id_task + 1

def add_task(description):
    try:
        with open(TASK_FILE, "r", encoding="utf-8") as f:
            data = json.load(f)
    except (FileNotFoundError, json.JSONDecodeError):
        data = []

    id = get_next_highest_id()
    timestamp = datetime.now().isoformat()

    new_task = {
        "id": id,
        "description": description,
        "status": TODO,
        "createdAt": timestamp,
        "updatedAt": timestamp
    }

    data.append(new_task)

    with open(TASK_FILE, "w", encoding="utf-8") as f:
        json.dump(data, f, indent=4)

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
