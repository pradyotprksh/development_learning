from src import log_debug
from random import shuffle, randint


def useful_operators():
    """
    Let's see some useful operators in python
    :return: none
    """

    # range
    for num in range(10):
        log_debug(num)
    for num in range(3, 10):
        log_debug(num)
    for num in range(0, 11, 2):
        log_debug(num)

    # enumerator
    index_count = 0
    for letter in 'abcde':
        log_debug('At index {} the letter is {}'.format(index_count, letter))
        index_count += 1
    index_count = 0
    word = 'abcde'
    for _ in word:
        log_debug(word[index_count])
        index_count += 1
    for item in enumerate(word):
        log_debug(item)
    for (index, letter) in enumerate(word):
        log_debug(letter)

    # zip
    my_list = [3, 6, 2, 8, 9]
    my_list2 = ['a', 'b', 'c']
    my_list3 = [45.2, 543.23, 2345.234]
    for item in zip(my_list, my_list2, my_list3):
        log_debug(item)
    log_debug(list(zip(my_list, my_list2, my_list3)))

    # in
    log_debug(1 in my_list)
    log_debug('b' in my_list2)
    log_debug('a' in 'a world')
    log_debug('key1' in {'key1': 'value1'})
    my_dict = {'key1': 'value1'}
    log_debug('value1' in my_dict.values())

    # min
    log_debug(min(my_list))

    # max
    log_debug(max(my_list))

    # random
    my_list = [3, 6, 2, 8, 9]
    shuffle(my_list)
    log_debug(my_list)
    shuffle(my_list)
    log_debug(my_list)

    log_debug(randint(3, 6))

    value = input('Enter a number: ')
    log_debug(value)
