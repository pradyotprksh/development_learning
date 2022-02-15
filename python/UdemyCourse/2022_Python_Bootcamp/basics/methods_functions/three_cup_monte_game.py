from random import shuffle
from src import log_debug


def shuffle_cups(my_list):
    shuffle(my_list)
    return my_list


def player_guess():
    guess = ''
    while guess not in ['0', '1', '2']:
        guess = input("Enter the cup count: 0, 1, 2 = ")
    return int(guess)


def check_result(shuffle_list, user_guess):
    if shuffle_list[user_guess] == 'O':
        log_debug(f"Right Guess {shuffle_list}")
    else:
        log_debug(f"Oops!! Better luck next time {shuffle_list}")


def three_cup_monte():
    """
    Let's create a three cup montee game using Python.
    This will only be a code game, no visualization.
    :return: none
    """

    my_cups = ['', 'O', '']
    new_shuffle_cups = shuffle_cups(my_cups)
    user_guess = player_guess()
    check_result(new_shuffle_cups, user_guess)
