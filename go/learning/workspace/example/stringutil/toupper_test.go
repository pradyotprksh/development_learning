package stringutil

import "testing"

func TestToUpper(t *testing.T) {
	for _, c := range []struct {
		in, want string
	}{
		{"Hello, world", "HELLO, WORLD"},
		{"", ""},
	} {
		got := ToUpper(c.in)
		if got != c.want {
			t.Errorf("Reverse(%q) == %q, want %q", c.in, got, c.want)
		}
	}
}
