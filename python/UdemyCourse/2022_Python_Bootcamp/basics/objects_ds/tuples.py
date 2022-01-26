from src import log_debug, log_exception


def tuples_basics():
    """
    Let's check on tuples in Python
    :return: none
    """

    my_tuple = (1, 2, 3)
    my_list = [1, 2, 3]

    # Similar to list but they are immutable
    try:
        my_tuple[0] = "P"
    except TypeError as err:
        log_exception(err)

    log_debug(my_tuple)
    log_debug(my_list)
    log_debug(type(my_tuple))
    log_debug(type(my_list))

    new_tuple = (1, 'one')
    log_debug(new_tuple)
    log_debug(new_tuple[1])
    log_debug(my_tuple[-1])

    t = ('a', 'a', 'a')
    log_debug(t.count('a'))
    log_debug(t.index('a'))
