package main

import (
    "fmt"
	"log"
    "pradyot.com/greetings"
	"rsc.io/quote"
)

func main() {
	// Setup log
	log.SetPrefix("greetings: ")
    log.SetFlags(0)

	// Print
	fmt.Println("Hello, World!")

	// Import packages from https://pkg.go.dev
    fmt.Println(quote.Go())
	fmt.Println(quote.Glass())
	fmt.Println(quote.Hello())
	fmt.Println(quote.Opt())

	// Creating your own module with
	crr_message, err_nil := greetings.Hello("Pradyot")
	if err_nil == nil {
    	fmt.Println(crr_message)
	}

	// Greeting list of users
	messages, errs := greetings.Hellos([]string{"Pradyot", "Prakash"})

	if errs != nil {
		log.Fatal(errs)
	} else {
		fmt.Println(messages)
	}

	// error handling
	_, err := greetings.Hello("")
	if err != nil {
    	log.Fatal(err)
	}
}