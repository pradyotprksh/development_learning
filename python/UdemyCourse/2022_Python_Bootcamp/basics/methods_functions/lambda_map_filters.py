from src import log_debug


def square(num):
    return num**2


def slicer(my_string):
    if len(my_string) % 2 == 0:
        return "Even"
    return my_string[0]


def is_even(num):
    return num % 2 == 0


def lambda_map_filters():
    """
    Let's have a look on lambda expression, map and filters in Python
    :return:
    """

    # maps
    my_nums = [1, 2, 3, 4, 5]
    for item in map(square, my_nums):
        log_debug(item)
    log_debug(list(map(square, my_nums)))
    log_debug(list(map(slicer, ['Andy', 'Pradyot', 'Ram'])))

    # filters
    log_debug(list(filter(is_even, [1, 4, 5, 14, 45])))

    # lambda
    log_debug(list(map(lambda num: num ** 2, my_nums)))
    log_debug(list(filter(lambda num: num % 2 == 0, [1, 4, 5, 14, 45])))

