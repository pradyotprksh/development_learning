package main

import (
	"fmt"
	"io"
	"os"
)

func main() {
	argmuents := os.Args

	fmt.Println("Getting file name...")
	if len(argmuents) > 1 {
		filename := argmuents[1]
		fmt.Println("Reading data from", filename)

		file, err := os.Open(filename)
		if err != nil {
			fmt.Println("Something went wrong while opening the file", filename, "Error:", err)
			os.Exit(1)
		}

		io.Copy(os.Stdout, file)
	} else {
		fmt.Println("No file name was provided")
		os.Exit(1)
	}
}
