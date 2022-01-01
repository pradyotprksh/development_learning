from src import log_debug


def for_loops_basics():
    """
    Let's check on for loops statements
    :return:
    """

    my_list = [1, 2, 3, 4, 5, 6, 7, 8, 9]
    for item in my_list:
        log_debug("Hello {}".format(item))

    for num in my_list:
        if num % 2 == 0:
            log_debug(f"{num} is even")
        else:
            log_debug(f"{num} is odd")

    list_sum = 0
    for num in my_list:
        list_sum += num
    log_debug(list_sum)

    my_string = "Hello"
    for char in my_string:
        log_debug(char)

    my_list_tuples = [(1, 2, 3), (4, 5, 6), (7, 8, 9)]
    for (a, b, c) in my_list_tuples:
        log_debug(f"a={a} b={b} c={c}")

    my_dict = {'key1': 'value1', 'key2': 'value2'}
    for item in my_dict:
        log_debug(item)
    for item in my_dict.items():
        log_debug(item)
    for (key, value) in my_dict.items():
        log_debug(f'key={key} : value={value}')
