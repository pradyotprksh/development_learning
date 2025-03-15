import csv
from datetime import datetime
import os
import sys

EXPENSE_FILE = "expense_file.csv"

ADD = "add"
LIST = "list"
SUMMARY = "summary"
DELETE = "delete"

def check_expense_file():
    if not os.path.exists(EXPENSE_FILE):
        with open(EXPENSE_FILE, "w") as f:
            fieldnames = ["ID", "Date", "Description", "Amount"]
            csvFile = csv.writer(f)
            csvFile.writerow(fieldnames)
        pass

def check_input():
    if len(sys.argv) > 1:
        command = sys.argv[1]
        if command == ADD:
            if len(sys.argv) == 6:
                description = sys.argv[3]
                amount = sys.argv[5]
                return (command, description, amount, None, None)
            else:
                print(f"Please provide description and amount.")
        elif command == DELETE:
            if len(sys.argv) == 4:
                id = sys.argv[3]
                return (command, None, None, id, None)
            else:
                print(f"Please provide id.")
        elif command == LIST:
            if len(sys.argv) == 2:
                return (command, None, None, None, None)
            else:
                print(f"No such command present.")
        elif command == SUMMARY:
            if len(sys.argv) == 2:
                return (command, None, None, None, None)
            elif len(sys.argv) == 4:
                month = sys.argv[3]
                if month in range(1, 13):
                    return (command, None, None, None, month)
                else:
                    print("Please provide a valid month number.")
            else:
                print(f"No such command present.")
        else:
            print(f"Please provide a valid instruction.")    
    else:
        print(f"Please provide a valid instruction.")
    return (None, None, None, None, None)

def get_next_highest_id():
    with open(EXPENSE_FILE, mode ='r') as file:
        csvFile = csv.reader(file)
        if csvFile is not None:
            number = 0
            for lines in csvFile:
                print(number)
                try:
                    print(lines)
                    number = int(lines[0])
                except ValueError:
                    pass
            return number + 1

    return 1

def add_expense(description, amount):
    id = get_next_highest_id()
    date = datetime.today().strftime('%Y-%m-%d')

    row = [id, date, description, amount]

    with open(EXPENSE_FILE, "w") as f:
        csvFile = csv.writer(f)
        csvFile.writerow(row)

def delete_expense(id):
    pass

def list_expense():
    with open(EXPENSE_FILE, mode ='r') as file:
        csvFile = csv.reader(file)
        if csvFile is not None:
            for lines in csvFile:
                print(lines)

def summary_expense(month):
    pass

def perform_operation(command, description, amount, id):    
    if command == ADD:
        add_expense(description, amount)
    elif command == DELETE:
        pass
    elif command == LIST:
        list_expense()
    elif command == SUMMARY:
        pass

if __name__ == '__main__':
    # Check if expense file exists, if not then create it
    check_expense_file()

    # Check command inputs
    command, description, amount, id, month = check_input()

    if command != None:
        # Perform operations based on command
        perform_operation(command, description, amount, id)
