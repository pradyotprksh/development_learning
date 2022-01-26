from .constants import values_1, values_11
from .deck import Deck
from src import log_debug


# TODO: Need to work on this. Concept of blackjack is not clear
def start_blackjack():
    """
    Start the black jack game which is made using Python
    :return: none
    """

    ace_type = 1
    if ace_type == 1:
        value_type = values_1
    else:
        value_type = values_11

    new_deck = Deck(value_type)
    new_deck.shuffle_deck()
