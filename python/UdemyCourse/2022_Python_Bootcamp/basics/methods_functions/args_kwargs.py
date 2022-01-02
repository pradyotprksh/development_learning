from src import log_debug


def my_func(a, b):
    return sum((a, b)) * 0.05


def my_func_c(a, b, c=0):
    return sum((a, b, c)) * 0.05


def my_func_c_d(a, b, c=0, d=0):
    return sum((a, b, c, d)) * 0.05


def my_func_args(*args):
    log_debug(args)
    return sum(args) * 0.05


def my_func_kwargs(**kwargs):
    log_debug(kwargs)
    if 'fruit' in kwargs:
        log_debug('My fruit of choice is {}'.format(kwargs['fruit']))
    else:
        log_debug('Not able to find any fruit here')


def my_func_args_kwargs(*args, **kwargs):
    log_debug(args)
    log_debug(kwargs)
    log_debug('I would like {} {}'.format(args[0], kwargs['food']))


def args_kwargs():
    """
    Let's take a look on what *args and **kwargs is
    :return:
    """

    log_debug(my_func(5, 10))
    log_debug(my_func_c(5, 10, 6))
    log_debug(my_func_c_d(5, 10, 6))

    log_debug(my_func_args(5, 10, 6))
    log_debug(my_func_args(5, 10, 6, 10, 54, 2345, 3))
    log_debug(my_func_args(4))

    log_debug(my_func_kwargs(fruit='apple', veggie='potato'))

    log_debug(my_func_args_kwargs(10, 20, 30, food='orange', animal='dog'))
