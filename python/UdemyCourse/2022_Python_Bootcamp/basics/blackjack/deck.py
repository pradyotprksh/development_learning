from .constants import suits, ranks
from .card import Card
from random import shuffle


class Deck:

    def __init__(self, values):
        self.all_cards = []
        for suit in suits:
            for rank in ranks:
                created_card = Card(suit, rank, values)
                self.all_cards.append(created_card)

    def shuffle_deck(self):
        """
        Shuffle the cards
        :return: none
        """
        shuffle(self.all_cards)

    def deal_one(self):
        """
        Deal one card from the deck
        :return: Single card which is popped from the deck
        """
        return self.all_cards.pop()
