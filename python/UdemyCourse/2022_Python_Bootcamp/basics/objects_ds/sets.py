from src import log_debug


def sets_basics():
    """
    Let's check on sets
    :return:
    """

    # Unordered collection of unique elements.

    my_set = set()
    my_set.add(1)
    my_set.add(2)
    log_debug(my_set)

    my_set.add(2)
    log_debug(my_set)

    my_list = [1, 1, 1, 2, 2, 3, 3, 3]
    log_debug(my_list)
    log_debug(set(my_list))
    log_debug(set('Mississippi'))
