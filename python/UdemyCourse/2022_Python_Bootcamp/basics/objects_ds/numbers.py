from ..src import log_debug


def numbers_basics():
    """
    Here we will be seeing different numbers data type in Python.
    :return: none
    """
    log_debug(2 + 2)
    log_debug(2 * 4)
    log_debug(14 / 3)
    log_debug(14 % 3)
    log_debug(2 ** 3)

    # BODMAS rule is applied
    log_debug(2 + 10 * 10)

    # The below code will not give 0.0 because of floating point accuracy.
    # Check https://docs.python.org/3/tutorial/floatingpoint.html
    log_debug(0.1 + 0.2 - 0.3)
