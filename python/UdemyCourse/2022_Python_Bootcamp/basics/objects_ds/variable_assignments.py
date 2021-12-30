from src import log_debug


def variable_assignments():
    """
    Here we will see how to assign variables in python
    :return: none
    """

    # Rules for variable names:
    # 1. Name shouldn't start with numbers.
    # 2. No spaces in the name.
    # 3. Can't use special symbols like $,",&,etc.
    #
    # Few best practices.
    # 1. Lower case variable names.
    # 2. Avoid words which has special meaning in Python.
    #
    # Python uses dynamic typing.
    # Pros:
    # 1. Easy to work with
    # 2. Faster development time
    # Cons:
    # 1. May result in bugs of unexpected type
    # 2. Need to be aware of type

    a = 5
    log_debug(a)
    a = 10
    log_debug(a)
    log_debug(a + a)
    a = a + a
    log_debug(a)
    a = a + a
    log_debug(a)
    log_debug(type(a))
    a = 30.1
    log_debug(type(a))
    my_income = 100
    tax_rate = 0.1
    my_taxes = my_income * tax_rate
    log_debug(my_taxes)
