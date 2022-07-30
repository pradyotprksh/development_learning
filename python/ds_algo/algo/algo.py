import src
from .sorting import start_with_sorting
from .search import start_with_search


def start_with_algo():
    user_selection = 1
    while user_selection in [1]:
        user_selection = src.UserInput.get_user_selection(
            "Please select an option"
            "\n1. Sorting"
            "\n2. Searching"
        )
        if user_selection == 1:
            start_with_sorting()
        elif user_selection == 2:
            start_with_search()
