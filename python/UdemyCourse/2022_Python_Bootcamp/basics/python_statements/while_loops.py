from src import log_debug


def while_loops_basics():
    """
    Let's check on while loops statements
    :return:
    """

    x = 0
    while x < 5:
        log_debug(f'current value of x is {x}')
        x += 1
    else:
        log_debug('x is not <5')

    # can use the below statements
    # break = breaks out of the current loop
    # continue = goes to the top of the closet enclosing loop
    # pass = does nothing
    x = 0
    while x < 5:
        log_debug(f'current value of x is {x}')
        x += 1
    else:
        # if you need the part but don't want to add anything then use pass
        pass

    x = 0
    while x < 10:
        log_debug(f'current value of x is {x}')
        x += 1
        if x > 5:
            break
    else:
        log_debug(f"This will not be called because the while loop breaks")

    x = 0
    while x < 10:
        x += 1
        if x % 2 == 0:
            continue
        log_debug(f'current value of x is {x} which is odd')
    else:
        log_debug("While loop is done")
