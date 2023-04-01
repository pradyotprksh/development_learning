package main

import (
	"fmt"
	"io"
	"net/http"
	"os"
)

type logWriter struct{}

func main() {
	res, err := http.Get("https://google.com")
	if err != nil {
		fmt.Println("Error occured", err)
		os.Exit(1)
	}

	// bs := make([]byte, 99999)
	// res.Body.Read(bs)

	// fmt.Println(string(bs))

	lw := logWriter{}

	io.Copy(lw, res.Body)
}

func (logWriter) Write(bs []byte) (int, error) {
	fmt.Println(string(bs))
	fmt.Println("Just wrote this many bytes: ", len(bs))
	return len(bs), nil
}
