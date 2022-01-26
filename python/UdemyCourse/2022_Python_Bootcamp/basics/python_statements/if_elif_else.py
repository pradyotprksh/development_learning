from src import log_debug


def if_elif_else_basics():
    """
    Let's check on if elif and else statements
    :return: none
    """

    if True:
        log_debug("Is True")
    if 3 > 2:
        log_debug("Is True")
    hungry = True
    if hungry:
        log_debug("Eating")

    hungry = False
    if hungry:
        log_debug("Eating")
    else:
        log_debug("Don't eat")

    loc = 'Bank'
    if loc == 'Shop':
        log_debug("Welcome to my shop")
    elif loc == 'Bank':
        log_debug("Welcome to the bank")
    elif loc == 'Store':
        log_debug("Nice store")
    else:
        log_debug("Not sure where you are")
