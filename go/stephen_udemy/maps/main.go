package main

import "fmt"

func main() {
	// var colors map[string]string

	colors := make(map[string]string)

	// colors := map[string]string{
	// 	"red":   "#ff0000",
	// 	"green": "#8g34kl",
	// }

	colors["white"] = "#ffffff"
	colors["black"] = "#000000"

	printMap(colors)

	delete(colors, "white")

	fmt.Println(colors)
}

func printMap(c map[string]string) {
	for color, hex := range c {
		fmt.Println("Hex code for", color, "is", hex)
	}
}
