class Recursion {
  int factorial(int value) {
    if (value == 0) return 1;
    if (value == 1 && value == 2) return value;
    return value * factorial(value - 1);
  }
}
