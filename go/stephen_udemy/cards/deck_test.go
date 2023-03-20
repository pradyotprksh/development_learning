package main

import (
	"os"
	"testing"
)

func TestNewDeck(t *testing.T) {
	d := newDeck()
	if len(d) != 52 {
		t.Errorf("Expected a deck of length 52 but got %v", len(d))
	}

	if d[0] != "Ace of Spades" {
		t.Errorf("Expected Ace of Spades but got %v", d[0])
	}

	if d[len(d)-1] != "King of Club" {
		t.Errorf("Expected King of Club but got %v", d[len(d)-1])
	}
}

func TestSaveToFileAndNewDeckFromFile(t *testing.T) {
	os.Remove("_decktesting")

	d := newDeck()
	d.saveToFile("_decktesting")

	loadeddeck := newDeckFromFile("_decktesting")
	if len(loadeddeck) != 52 {
		t.Errorf("Expected a deck of length 52 but got %v", len(loadeddeck))
	}

	os.Remove("_decktesting")
}
