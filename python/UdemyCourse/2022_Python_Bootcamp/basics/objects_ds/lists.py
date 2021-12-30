from ..src import log_debug


def lists_basic():
    """
    Let's check some list basics in Python
    :return: none
    """

    # Ordered sequences that can hold variety of objects.
    # Supports indexing and slicing like Strings.
    # It can nested also.

    my_list = [1, 2, 3]
    log_debug(my_list)
    my_list = ['One', 2, 3.0]
    log_debug(my_list)
    log_debug(len(my_list))

    # Indexing
    log_debug(my_list[0])
    log_debug(my_list[-1])

    # Slicing
    log_debug(my_list[0:])
    log_debug(my_list[:2])

    # Concat lists
    another_list = ["Four", "Five"]
    log_debug(my_list + another_list)

    # List is mutable
    my_list[0] = 1
    log_debug(my_list)

    # Add new item at the end
    my_list.append(4.0)
    my_list.append(5.0)
    my_list.append(7.0)
    my_list.append("Eight")
    my_list.append(9)
    log_debug(my_list)

    # Remove from list from the end
    my_list.pop()
    log_debug(my_list)
    popped_item = my_list.pop()
    log_debug(my_list)
    log_debug(popped_item)
    popped_item = my_list.pop(3)
    log_debug(my_list)
    log_debug(popped_item)

    # Sort or revers. These are in-place, meaning it doesn't return anything.
    my_list.sort()
    log_debug(my_list)
    my_list.reverse()
    log_debug(my_list)
