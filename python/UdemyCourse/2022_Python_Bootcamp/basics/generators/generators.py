from src import log_debug, log_exception
import random


def create_cubes(n):
    result = []
    for x in range(n):
        result.append(x**3)
    return result


def create_cubes_generator(n):
    for x in range(n):
        yield x**3


def generate_fibonacci(n):
    a = 0
    b = 1

    for i in range(n):
        yield a
        a, b = b, a + b


def simple_gen():
    for x in range(3):
        yield x


def generate_random(low, high, n):
    for x in range(n):
        yield random.randint(low, high)


def generators():
    """
    Let's take a look on generators in Python
    :return: none
    """

    log_debug(create_cubes(10))

    # Normally have to wait for the cubes has to be done and return the list
    for x in create_cubes(10):
        log_debug(x)

    # With generator, it yields the value and go on with the next task
    for x in create_cubes_generator(10):
        log_debug(x)

    for x in generate_fibonacci(10):
        log_debug(x)

    g = simple_gen()
    log_debug(g)

    log_debug(next(g))
    log_debug(next(g))
    log_debug(next(g))
    try:
        log_debug(next(g))
    except Exception as e:
        log_exception(e)

    s = "hello"
    try:
        next(s)
    except Exception as e:
        log_exception(e)

    s_itr = iter(s)
    log_debug(next(s_itr))
    log_debug(next(s_itr))
    log_debug(next(s_itr))
    log_debug(next(s_itr))
    log_debug(next(s_itr))
    try:
        log_debug(next(s_itr))
    except Exception as e:
        log_exception(e)

    for x in generate_random(low=3, high=20, n=10):
        log_debug(x)
