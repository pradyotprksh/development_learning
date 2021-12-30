from ..src import log_debug, log_exception


def strings_basics():
    """
    Let's check on the data type string in Python
    :return: none
    """

    # For Strings, we can use either " or '

    log_debug('hello')
    log_debug("hello")

    # Can mix ' and " quotes
    log_debug("i don't know")

    # escape characters using \
    log_debug("i \n don't know")
    log_debug("i \t don't know")

    log_debug(len("hello "))


def string_slicing_indexing():
    """
    Let's check on the slicing and indexing of strings in Python
    :return: none
    """

    # Since, Strings are ordered sequences, so we can use indexing and slicing to get subsection
    # of the string.
    # Every character has an index assign to it in String, starting from 0.
    # Slicing allows to grab a subsection of a string using the start index and the end index.
    # And also a step for the jump needed from start to stop.

    # Indexing
    my_string = "Hello World"
    log_debug(my_string)
    log_debug(my_string[0])
    log_debug(my_string[-3])

    # Slicing
    my_string = 'abcdefghijk'
    log_debug(my_string)
    log_debug(my_string[2])
    log_debug(my_string[2:])

    # Stop index is exclusive
    log_debug(my_string[:3])

    log_debug(my_string[3:6])

    # Slicing using steps
    log_debug(my_string[::2])
    log_debug(my_string[2:7:2])

    # Reverse the string using steps
    log_debug(my_string[::-1])


def string_properties_methods():
    """
    Let's take a look on some strings properties and methods
    :return: none
    """

    # Strings are immutable
    try:
        my_string = "Hello"
        my_string[0] = "P"
    except TypeError as err:
        log_exception(err)

    # The above can be achieved by using concatenation
    my_string = "Hello World"
    last_letters = my_string[1:]
    log_debug(last_letters)
    last_letters = 'P' + last_letters
    log_debug(last_letters)

    letter = 'z'
    log_debug(letter * 5)

    log_debug(my_string.upper())
    log_debug(my_string.lower())
    log_debug(my_string.split())
    log_debug(my_string.split('o'))


def string_formatting():
    """
    Let's see how we can format strings in Python.
    Check https://pyformat.info for more details.
    :return: none
    """

    # Using format method
    log_debug("This is one way of {} string".format("formatting"))
    log_debug("This is {} way of {} string".format("one", "formatting"))
    # Can assign index
    log_debug("The {2} {1} {0}".format("fox", "brown", "quick"))
    log_debug("The {1} {1} {0}".format("fox", "brown", "quick"))
    # Can assign key to the values
    log_debug("The {q} {b} {f}".format(f="fox", b="brown", q="quick"))

    # Format floating value
    result = 100/77
    log_debug(result)
    log_debug("The result was {r:1.3f}".format(r=result))

    # Using formatting string literals
    name = "Pradyot"
    log_debug(name)
    log_debug(f"My name is {name}")
