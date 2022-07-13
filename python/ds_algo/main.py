"""Main file to be run"""
import practice
from ds import start_with_ds
import src

if __name__ == '__main__':
    print("At any given point you can give a invalid option to go back in the selection process")
    user_selection = 1
    while user_selection in [1, 2]:
        user_selection = src.UserInput.get_user_selection(
            "Please select an option"
            "\n1. DS"
            "\n2. Practice Problems"
        )
        if user_selection == 1:
            start_with_ds()
        elif user_selection == 2:
            practice.run_practice_problems()
