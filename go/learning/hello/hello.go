package main

import (
    "fmt"
    "pradyot.com/greetings"
	"rsc.io/quote"
)

func main() {
	// Print
	fmt.Println("Hello, World!")

	// Import packages from https://pkg.go.dev
    fmt.Println(quote.Go())
	fmt.Println(quote.Glass())
	fmt.Println(quote.Hello())
	fmt.Println(quote.Opt())

	// Creating your own module
	message := greetings.Hello("Pradyot")
    fmt.Println(message)
}