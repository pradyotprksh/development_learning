from src import log_debug


x = 25


def printer():
    x = 50
    return x


# Global
name = 'THIS IS A GLOBAL STRING'


def greet():
    # 2. but name is defined in enclose level
    # If we comment below line then it will take the above name variable
    # Enclosed
    name = 'Sammy'

    def hello():
        # 1. name is not on the local level
        # If we uncomment below line it will print Hello Pradyot
        # Local
        # name = 'Pradyot'
        log_debug('Hello ' + name)

    hello()


y = 50


def affect_global():
    global y
    log_debug(f'Y is {y}')

    y = 200
    log_debug(f'Global Y is changed locally to {y}')


def nested_statements_scope():
    """
    Let's have a look on nested statements and scopes
    :return: none
    """

    log_debug(x)
    log_debug(printer())
    log_debug(x)

    # Order for which python will look for variables
    # LEGB rule -
    # Local
    # Enclosing
    # Global
    # Built-In

    # Local = num is local to the below lambda expression
    log_debug(lambda num: num**2)

    # Enclosing
    greet()

    affect_global()
    log_debug(f'Y after affect_global is {y}')
