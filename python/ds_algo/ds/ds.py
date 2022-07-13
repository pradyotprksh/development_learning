import src
from ds import linked_list, stack, queue, trees


def start_with_ds():
    user_selection = 1
    while user_selection in [1, 2, 3, 4]:
        user_selection = src.UserInput.get_user_selection(
            "Please select an option"
            "\n1. Linked List"
            "\n2. Stack"
            "\n3. Queue"
            "\n4. Trees"
        )
        if user_selection == 1:
            linked_list_user_selection = src.UserInput.get_user_selection(
                "Please select an option"
                "\n1. Singly Linked List"
                "\n2. Doubly Linked List"
            )
            while linked_list_user_selection in [1, 2]:
                if linked_list_user_selection == 1:
                    linked_list.start_with_singly_linked_list()
                elif linked_list_user_selection == 2:
                    linked_list.start_with_doubly_linked_list()
        elif user_selection == 2:
            stack_user_selection = src.UserInput.get_user_selection(
                "Please select an option"
                "\n1. Linked List"
                "\n2. Arrays"
            )
            while stack_user_selection in [1, 2]:
                if stack_user_selection == 1:
                    stack.start_with_stack_linked_list()
                elif stack_user_selection == 2:
                    stack.start_with_stack_arrays()
        elif user_selection == 3:
            queue_user_selection = src.UserInput.get_user_selection(
                "Please select an option"
                "\n1. Linked List"
                "\n2. Stacks"
            )
            while queue_user_selection in [1, 2]:
                if queue_user_selection == 1:
                    queue.start_with_queue_linked_list()
                elif queue_user_selection == 2:
                    queue.start_with_queue_stack()
        elif user_selection == 4:
            trees.start_with_trees()
