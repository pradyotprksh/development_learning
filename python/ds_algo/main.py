"""Main file to be run"""
import practice
from ds import start_with_ds
import src

if __name__ == '__main__':
    user_selection = src.UserInput.get_user_selection(
        "Please select an option"
        "\n1. DS"
        "\n2. Queue"
    )
    if user_selection == 1:
        start_with_ds()
    elif user_selection == 3:
        practice.run_practice_problems()
    else:
        print("Please select a valid option")
