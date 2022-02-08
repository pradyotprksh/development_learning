from src import log_debug, log_info
import time
import timeit


def func_one(n):
    return [str(num) for num in range(n)]


def func_two(n):
    return list(map(str, range(n)))


def checking_using_time():
    log_info("Checking code time using time module")
    before_time_one = time.time()
    log_debug(func_one(100))
    after_time_one = time.time()
    log_debug(after_time_one - before_time_one)

    before_time_two = time.time()
    log_debug(func_two(100))
    after_time_two = time.time()
    log_debug(after_time_two - before_time_two)


def checking_using_timeit():
    log_info("Checking code time using timeit module")
    stmt = """
func_one(100)
    """
    setup = """
def func_one(n):
    return [str(num) for num in range(n)]
    """
    log_debug(timeit.timeit(stmt, setup, number=100000))

    stmt = """
func_two(100)
    """
    setup = """
def func_two(n):
    return list(map(str, range(n)))
    """
    log_debug(timeit.timeit(stmt, setup, number=100000))


def timing_code():
    """
    Let's take a look on how to time your code for performance in python
    :return:
    """

    checking_using_time()
    checking_using_timeit()
