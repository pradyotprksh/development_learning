package greetings

import (
    "testing"
    "regexp"
)

func TestHelloName(t *testing.T) {
	name := "Pradyot"
    want := regexp.MustCompile(`\b`+name+`\b`)

	message, err := Hello(name)
	if !want.MatchString(name) || err != nil {
		t.Fatalf(`Hello("Pradyot") = %q, %v, want match for %#q, nil`, message, err, want)
	}
}

func TestHelloEmptyName(t *testing.T) {
	message, err := Hello("")
	if message != "" || err == nil {
		t.Fatalf(`Hello("") = %q, %v, want "", error`, message, err)
	}
}