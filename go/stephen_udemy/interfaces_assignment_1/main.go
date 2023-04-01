package main

import "fmt"

type shape interface {
	getArea() float64
}

type square struct {
	sideLength float64
}

type rectangle struct {
	base   float64
	height float64
}

func main() {
	sq := square{
		sideLength: 10,
	}
	rc := rectangle{
		base: 12,
		height: 23,
	}

	printArea(sq)
	printArea(rc)
}

func printArea(s shape) {
	fmt.Println(s.getArea())
}

func (s square) getArea() float64 {
	return s.sideLength * s.sideLength
}

func (s rectangle) getArea() float64 {
	return 0.5 * s.base * s.height
}
