from src import log_debug


def say_hello():
    log_debug("Hello user")
    log_debug("How are you?")
    log_debug("Where are you?")


def says_hello(name='Unknown'):
    log_debug(f"Hello {name}")
    log_debug(f"How are you {name}?")
    log_debug(f"Where are you {name}?")


def add_num(num1, num2):
    return num1 + num2


def check_even(num):
    return num % 2 == 0


def check_even_list(nums):
    for num in nums:
        if num % 2 == 0:
            return True
        else:
            pass
    return False


def employee_check(work_hours):
    current_max = 0
    employee_month = ''

    for (name, hours) in work_hours:
        if current_max < hours:
            current_max = hours
            employee_month = name

    return employee_month, current_max


def methods_functions():
    """
    Let's check few examples related to methods and functions in Python
    :return:
    """

    say_hello()

    says_hello("Pradyot")
    says_hello()

    result = add_num(1, 3)
    log_debug(result)

    result = check_even(20)
    log_debug(result)
    result = check_even(21)
    log_debug(result)

    result = check_even_list([1, 2, 4])
    log_debug(result)
    result = check_even_list([1, 3, 5])
    log_debug(result)

    result = employee_check([('Abc', 20), ('Def', 21), ('Ghi', 42)])
    log_debug(result)
    name, hours = result
    log_debug(f"{name} worked for {hours}hrs. So employee of the month")
