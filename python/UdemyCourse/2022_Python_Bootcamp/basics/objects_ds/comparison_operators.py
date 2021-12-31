from src import log_debug


def comparison_operators_basics():
    """
    Let's check on comparison operators in Python
    :return:
    """

    # ==, !=, >, <, <=, >=

    log_debug(2 == 2)
    log_debug(2 == 4)
    log_debug("ABC" == "ABC")
    log_debug(2.0 == 2)

    log_debug(3 != 3)
    log_debug(4 != 5)

    log_debug(2 > 1)
    log_debug(1 > 2)

    log_debug(1 < 2)
    log_debug(2 < 4)

    log_debug(3 <= 5)
    log_debug(6 <= 3)
    log_debug(6 <= 6)

    log_debug(4 >= 3)
    log_debug(4 >= 8)


def comparison_with_logical_operators_basics():
    """
    Let's check how to combine comparison operators with logical operators
    :return:
    """

    # and, or, not

    log_debug(1 < 2 < 3)
    log_debug(1 < 2 > 3)
    # or we can write the above as
    log_debug(1 < 2 and 2 > 3)

    log_debug(1 < 2 or 5 > 3)
    log_debug(1 < 2 or 5 < 3)
    log_debug(1 > 2 or 5 < 3)

    # not returns the opposite boolean value
    log_debug(not 1 < 2)
