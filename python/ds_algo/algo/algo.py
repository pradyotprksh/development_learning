import src
from .sorting import start_with_sorting


def start_with_algo():
    user_selection = 1
    while user_selection in [1]:
        user_selection = src.UserInput.get_user_selection(
            "Please select an option"
            "\n1. Sorting"
        )
        if user_selection == 1:
            start_with_sorting()
