package main

func main() {
	cards := newDeck()
	cards.print()
}

func newCard() string {
	return "Five of diamonds"
}
