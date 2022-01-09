from src import log_debug, log_error, Constants

row_1 = [' ', ' ', ' ']
row_2 = [' ', ' ', ' ']
row_3 = [' ', ' ', ' ']


def display_tic_tac_toe():
    log_debug(row_1)
    log_debug(row_2)
    log_debug(row_3)


def is_any_one_won():
    if (row_1[0] == row_2[0] == row_3[0] == ('X' or 'O')) or (row_1[0] == row_2[1] == row_3[2] == ('X' or 'O')):
        return True, row_1[0]
    elif row_1[1] == row_2[1] == row_3[1] == ('X' or 'O'):
        return True, row_1[1]
    elif (row_1[2] == row_2[2] == row_3[2] == ('X' or 'O')) or (row_1[2] == row_2[1] == row_3[0] == ('X' or 'O')):
        return True, row_1[2]
    return False, ' '


def is_game_draw():
    return row_1[0] == row_1[1] == row_1[2] == row_2[0] == row_2[1] == row_2[2] == row_3[0] == row_3[1] == row_3[2] == (
            'X' or 'O')


def get_input_from_user():
    user_input_row_string = ' '
    while user_input_row_string.isdigit() is False and user_input_row_string not in ['0', '1', '2']:
        user_input_row_string = input("Please select the row ")
    user_input_row = int(user_input_row_string)

    user_input_item_string = ''
    while user_input_item_string.isdigit() is False and user_input_item_string not in ['0', '1', '2']:
        user_input_item_string = input("Please select the column ")
    user_input_item = int(user_input_item_string)

    return user_input_row, user_input_item


def update_tic_tac_toe(row, item, current_item):
    if row not in range(0, 3) or item not in range(0, 3):
        return False
    if row == 0:
        if row_1[item] != ' ':
            return False
        row_1[item] = current_item
    elif row == 1:
        if row_2[item] != ' ':
            return False
        row_2[item] = current_item
    else:
        if row_3[item] != ' ':
            return False
        row_3[item] = current_item
    return True


def tic_tac_toe():
    """
    Let's make a tic-tac-toe game using Python
    :return:
    """
    # TODO: Uncomment this when done with the learning
    # game_won, who_won = is_any_one_won()
    # game_draw = is_game_draw()
    # current_item = 'X'
    #
    # while game_won is False and game_draw is False:
    #     log_debug(Constants.Messages.WELCOME_TO_TIC_TAC_TOE)
    #     display_tic_tac_toe()
    #
    #     row, item = get_input_from_user()
    #
    #     if update_tic_tac_toe(row, item, current_item) is False:
    #         log_error("Please enter a valid row or item")
    #         continue
    #     game_won, who_won = is_any_one_won()
    #     game_draw = is_game_draw()
    #
    #     if current_item == 'X':
    #         current_item = 'O'
    #     else:
    #         current_item = 'X'
    #
    # if game_won:
    #     log_debug(who_won)
    # else:
    #     log_debug("It's a draw")

