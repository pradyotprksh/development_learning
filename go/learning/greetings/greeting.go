package greetings

import (
    "errors"
    "fmt"
    "math/rand"
    "time"
)

// Hello returns a greeting for the named person.
func Hello(name string) (string, error) {
    if name == "" {
        return "", errors.New("empty name")
    }

    // Return a greeting that embeds the name in a message.
    message := fmt.Sprintf(randomMessage(), name)
    return message, nil
}

// Return map of greetings for the list of users
func Hellos(names []string) (map[string]string, error) {
    if len(names) == 0 {
        return make(map[string]string), errors.New("empty users name")
    }
    messages := make(map[string]string)
    for _, name := range names {
        message, err := Hello(name)
        if err != nil {
            return nil, err
        }
        messages[name] = message
    }
    return messages, nil
}

// Get random
var randNum = rand.New(rand.NewSource(time.Now().UnixNano()))

// Get a random message
func randomMessage() string {
    messages := []string{
        "Hello %v to our world",
        "%v is a rockstar",
        "Did you mean \"%v\"?",
    }

    return messages[randNum.Intn(len(messages))]
}
