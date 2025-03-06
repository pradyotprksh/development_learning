# Project Details
https://roadmap.sh/projects/task-tracker

# How to run?
Use command `python3 task_tracker.py <command> <options>`

- Adding a new task
`python3 task_tracker.py add "Buy  groceries"`

- Updating and deleting tasks
`python3 task_tracker.py update 1 "Buy groceries and cook dinner"`

`python3 task_tracker.py delete 1`

- Marking a task as in progress or done
`python3 task_tracker.py mark-in-progress 1`

`python3 task_tracker.py mark-done 1`

- Listing all tasks
`python3 task_tracker.py list`

- Listing tasks by status
`python3 task_tracker.py list done`

`python3 task_tracker.py list todo`

`python3 task_tracker.py list in-progress`