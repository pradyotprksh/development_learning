package main

func main() {
	cards := newDeck()
	cards.shuffle()
	cards.saveToFile("my_deck")

	localdeck := newDeckFromFile("my_deck")
	localdeck.shuffle()

	hand, remainingCards := deal(localdeck, 5)
	hand.shuffle()
	hand.saveToFile("hand_deck")
	remainingCards.shuffle()
	remainingCards.saveToFile("remaining_deck")

	localhand := newDeckFromFile("hand_deck")
	localdeck.shuffle()
	localremainingcards := newDeckFromFile("remaining_deck")
	localremainingcards.shuffle()

	localhand.print()
}
