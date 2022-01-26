from src import log_debug, log_exception


def add(n1, n2):
    log_debug(n1 + n2)


def errors_exception_handling():
    """
    Let's take a look on errors and exception handling in python
    :return: none
    """

    add(10, 20)

    try:
        result = 10 + '10'
    # If there is an error in try block
    except TypeError:
        log_debug("Something went wrong")
    # If there is no error in try block
    else:
        log_debug("All well")
        log_debug(result)

    try:
        f = open('data/testfile', 'w')
        f.write('Write a testing line')
    except TypeError:
        log_debug("There was a type error")
    except OSError:
        log_debug("You have an OSError")
    finally:
        log_debug("I always run")

    try:
        f = open('data/testfile', 'r')
        f.write('Write a testing line')
    except TypeError:
        log_debug("There was a type error")
    except OSError:
        log_debug("You have an OSError")
    finally:
        log_debug("I always run")
