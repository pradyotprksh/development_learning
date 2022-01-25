from .deck import Deck
from .player import Player
from src import log_debug, log_info


def start_war_game():
    """
    Start the war game made with python
    :return:
    """

    # Create players
    player_1 = Player("One")
    player_2 = Player("Two")

    # Create a deck
    new_deck = Deck()
    # Shuffle the cards in the newly created deck
    new_deck.shuffle_deck()

    for x in range(26):
        player_1.add_cards(new_deck.deal_one())
        player_2.add_cards(new_deck.deal_one())

    game_on = True
    round_number = 0

    get_card_in_war = 5

    while game_on:

        # Current round of the game
        round_number += 1
        log_debug(f'Currently on round {round_number} \n Player 1 = {player_1} \n player 2 = {player_2}')

        # Check for game is on or not
        if len(player_1.all_cards) == 0:
            log_info(f'Player {player_1.name} out of cards. Player {player_2.name} wins')
            break
        if len(player_2.all_cards) == 0:
            log_info(f'Player {player_2.name} out of cards. Player {player_1.name} wins')
            break

        # New round
        player_1_cards = [player_1.remove_one()]
        player_2_cards = [player_2.remove_one()]

        at_war = True

        while at_war:
            # If this round is won by player 1
            if player_1_cards[-1].value > player_2_cards[-1].value:
                player_1.add_cards(player_1_cards)
                player_1.add_cards(player_2_cards)
                at_war = False
            # If this round is won by player 2
            elif player_1_cards[-1].value < player_2_cards[-1].value:
                player_2.add_cards(player_2_cards)
                player_2.add_cards(player_1_cards)
                at_war = False
            # If it's a war
            else:
                log_info("WAR!!!")

                # If player 1 has not enough cards
                if len(player_1.all_cards) < get_card_in_war:
                    log_info(
                        f'Player {player_1.name} out of {get_card_in_war} cards, so no WAR. Player {player_2.name} wins')
                    game_on = False
                    break
                # If player 2 has not enough cards
                elif len(player_2.all_cards) < get_card_in_war:
                    log_info(
                        f'Player {player_2.name} out of {get_card_in_war} cards, so no WAR. Player {player_1.name} wins')
                    game_on = False
                    break
                # If both have enough cards then grab card
                else:
                    for num in range(get_card_in_war):
                        player_1_cards.append(player_1.remove_one())
                        player_2_cards.append(player_2.remove_one())
