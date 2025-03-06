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
                description = " ".join(sys.argv[2:])
                return (command, None, description)
            else :
                print(f"Please provide task description.")
        elif command == DELETE or command == MARK_IN_PROGRESS or command == MARK_DONE:
            if len(sys.argv) > 2:
                id = sys.argv[2]
                return (command, id, None)
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
            if len(sys.argv) > 1:
                status = sys.argv[2]
                if status == DONE or status == TODO or status == IN_PROGRESS:
                    return (status, None, None)
                else:
                    print(f"Please provide a valid list option.")
            else:
                return (command, None, None)
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

    highest_id_task = tasks[-1]["id"]
    return highest_id_task + 1

def add_task(description):
    try:
        with open(TASK_FILE, "r", encoding="utf-8") as f:
            tasks = json.load(f)
    except (FileNotFoundError, json.JSONDecodeError):
        tasks = []

    id = get_next_highest_id()
    timestamp = datetime.now().isoformat()

    new_task = {
        "id": id,
        "description": description,
        "status": TODO,
        "createdAt": timestamp,
        "updatedAt": timestamp
    }

    tasks.append(new_task)

    with open(TASK_FILE, "w", encoding="utf-8") as f:
        json.dump(tasks, f, indent=4)
        print(f"Task with id {id} added successfully.")

def delete_task(id):
    try:
        with open(TASK_FILE, "r", encoding="utf-8") as f:
            tasks = json.load(f)
    except (FileNotFoundError, json.JSONDecodeError):
        print("Nothing to delete")
        return

    filtered_tasks = [task for task in tasks if str(task["id"]) != str(id)]

    if len(filtered_tasks) == len(tasks):
        print(f"No task found with id {id}.")
        return

    with open(TASK_FILE, "w", encoding="utf-8") as f:
        json.dump(filtered_tasks, f, indent=4)

    print(f"Task with id {id} removed successfully.")

def list_tasks(command):
    try:
        with open(TASK_FILE, "r", encoding="utf-8") as f:
            data = json.load(f)
    except (FileNotFoundError, json.JSONDecodeError):
        data = []

    if (command == None):
        for task in data:
            print(f"Id: {task["id"]}\nStatus: {task["status"]}\nDescription: {task["description"]}\n\n")
    else:
        for task in data:
            if (task["status"] == command):
                print(f"Id: {task["id"]}\nStatus: {task["status"]}\nDescription: {task["description"]}\n\n")


def update_task_status(id, new_status):
    try:
        with open(TASK_FILE, "r", encoding="utf-8") as f:
            tasks = json.load(f)
    except (FileNotFoundError, json.JSONDecodeError):
        print("Nothing to mark in progress")
        return
    
    task_id = str(id)
    task_found = False

    for task in tasks:
        if str(task["id"]) == task_id:
            task["status"] = new_status
            task["updatedAt"] = datetime.now().isoformat()
            task_found = True
            break

    if not task_found:
        print(f"No task found with id {task_id}.")
        return
    
    with open(TASK_FILE, "w", encoding="utf-8") as f:
        json.dump(tasks, f, indent=4)

    print(f"Task id {task_id} updated to status '{new_status}'.")


def perform_operation(command, id, description):    
    if command == ADD:
        add_task(description)
    elif command == DELETE:
        delete_task(id)
    elif command == LIST:
        list_tasks(None)
    elif command == MARK_IN_PROGRESS:
        update_task_status(id, IN_PROGRESS)
    elif command == MARK_DONE:
        update_task_status(id, DONE)
    elif command == DONE or command == TODO or command == IN_PROGRESS:
        list_tasks(command)


if __name__ == '__main__':
    # Check if json file exists, if not then create it
    check_task_file()

    # Check command inputs
    command, id, description = check_input()

    # Perform operations based on command
    perform_operation(command, id, description)
