"""Main file to be run"""
import practice
import linked_list
import src

if __name__ == '__main__':
    user_selection = src.UserInput.get_user_selection(
        "Please select an option"
        "\n1. Linked List"
        "\n2. Practice Problems"
    )
    if user_selection == 1:
        linked_list_user_selection = src.UserInput.get_user_selection(
            "Please select an option"
            "\n1. Singly Linked List"
            "\n2. Doubly Linked List"
        )
        if linked_list_user_selection == 1:
            linked_list.start_with_singly_linked_list()
        elif linked_list_user_selection == 2:
            linked_list.start_with_doubly_linked_list()
        else:
            print("Please select a valid option")
    elif user_selection == 2:
        practice.run_practice_problems()
    else:
        print("Please select a valid option")
