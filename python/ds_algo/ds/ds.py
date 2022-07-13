import src
from ds import linked_list, stack, queue


def start_with_ds():
    user_selection = src.UserInput.get_user_selection(
        "Please select an option"
        "\n1. Linked List"
        "\n2. Stack"
        "\n3. Queue"
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
        stack_user_selection = src.UserInput.get_user_selection(
            "Please select an option"
            "\n1. Linked List"
            "\n2. Arrays"
        )
        if stack_user_selection == 1:
            stack.start_with_stack_linked_list()
        elif stack_user_selection == 2:
            stack.start_with_stack_arrays()
        else:
            print("Please select a valid option")
    elif user_selection == 3:
        queue_user_selection = src.UserInput.get_user_selection(
            "Please select an option"
            "\n1. Linked List"
            "\n2. Stacks"
        )
        if queue_user_selection == 1:
            queue.start_with_queue_linked_list()
        elif queue_user_selection == 2:
            queue.start_with_queue_stack()
        else:
            print("Please select a valid option")
    else:
        print("Please select a valid option")
