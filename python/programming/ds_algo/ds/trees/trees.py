from .binary_search_tree import start_with_binary_search_tree
import src


def start_with_trees():
    user_selection = 1
    while user_selection in [1]:
        user_selection = src.UserInput.get_user_selection(
            "Please select an option"
            "\n1. BST"
        )
        if user_selection == 1:
            start_with_binary_search_tree()
