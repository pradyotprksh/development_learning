from src import log_debug


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


def list_comprehension():
    """
    Let's check one of the concept in python known as list comprehension
    :return:
    """

    my_string = 'Hello'
    my_list = []
    for letter in my_string:
        my_list.append(letter)
    log_debug(my_list)

    # let's do the above using comprehension concept
    my_list = [letter for letter in my_string]
    log_debug(my_list)

    my_list = [x for x in 'word']
    log_debug(my_list)

    my_list = [num for num in range(0, 11)]
    log_debug(my_list)

    my_list = [num**2 for num in range(0, 11)]
    log_debug(my_list)

    my_list = [num for num in range(0, 11) if num % 2 == 0]
    log_debug(my_list)

    celcius = [0, 45, 13, 35.5]
    farenheit = [(9/5) * temp + 32 for temp in celcius]
    log_debug(farenheit)

    # use if and else
    results = [x if x % 2 == 0 else 'ODD' for x in range(0, 10)]
    log_debug(results)

    # nested loops
    my_list = []
    for x in [2, 4, 6]:
        for y in [1, 10, 100]:
            my_list.append(x * y)
    log_debug(my_list)

    # lets do the above using comprehension
    my_list = [x*y for x in [2, 4, 6] for y in [1, 10, 100]]
    log_debug(my_list)
