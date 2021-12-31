from src import log_debug


def dictionaries_basics():
    """
    Let's check on dictionaries
    :return:
    """

    # Unordered mappings for storing objects.
    # Use key : value pairing instead.

    my_dict = {'key1': 'value1', 'key2': 'value2'}
    log_debug(my_dict)
    log_debug(my_dict['key1'])

    new_dict = {'k1': [1, 2, 3], 'k2': 123, 'k3': 12.4, 'k4': {'k41': 'indent'}}
    log_debug(new_dict)
    log_debug(new_dict['k1'])
    log_debug(new_dict['k4']['k41'].upper())

    new_dict['k5'] = 'New item in dict'
    log_debug(new_dict)
    new_dict['k1'] = [3, 2, 1]
    log_debug(new_dict)

    log_debug(new_dict.keys())
    log_debug(new_dict.values())
    log_debug(new_dict.items())
