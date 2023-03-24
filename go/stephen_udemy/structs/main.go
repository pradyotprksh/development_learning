package main

import "fmt"

type person struct {
	firstname string
	lastname  string
	contactinfo
}

type contactinfo struct {
	email   string
	zipcode int
}

func main() {
	// pradyot := person{"Pradyot", "Prakash"}
	// pradyot := person{firstname: "Pradyot", lastname: "Prakash"}
	var pradyot person
	pradyot.firstname = "Pradyot"
	pradyot.lastname = "Prakash"
	pradyot.contactinfo = contactinfo{
		email:   "pradyotprksh4@gmail.com",
		zipcode: 560067,
	}

	// pradyotpoiinter := &pradyot
	// pradyotpoiinter.updateName("Pradyot's")

	pradyot.updateName("Pradyot's")

	pradyot.print()
}

func (personPointer *person) updateName(newFirstName string) {
	(*personPointer).firstname = newFirstName
}

func (p person) print() {
	fmt.Printf("%+v", p)
}
