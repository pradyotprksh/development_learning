from src import log_debug


def func():
    return 1


def hello():
    return "Hello"


def hello_new(name="Pradyot"):
    log_debug("The hello function has been executed!")

    def greet():
        return '\t This is the greet function inside hello'

    def welcome():
        return '\t This is the welcome function inside hello'

    log_debug("I am going to return a functions!!")

    if name == "Pradyot":
        return greet
    return welcome


def other(some_func):
    log_debug("Other code runs here")
    log_debug(some_func())


def new_decorator(original_func):

    def wrap_func():
        log_debug("Some extra code before original function")
        original_func()
        log_debug("Some extra code after original function")

    return wrap_func


def func_needs_decorator():
    log_debug("I want to be decorated")


@new_decorator
def func_need_decorator():
    log_debug("I want to be decorated")


def decorators():
    """
    Let's check on what is decorators in Python
    :return: none
    """

    log_debug(func)
    log_debug(hello())
    log_debug(hello)

    greet = hello
    log_debug(greet())

    # Delete the hello function
    # del hello

    # If tried to call again it will throw exception
    # try:
    #     hello()
    # except Exception as e:
    #     log_exception(e)

    # But greet will still show "Hello" because greet contains the copy of hello function
    # log_debug(greet())

    message = hello_new()
    log_debug(message())

    log_debug(hello_new()())

    other(hello)

    new_decorator(func_needs_decorator)()

    func_need_decorator()
