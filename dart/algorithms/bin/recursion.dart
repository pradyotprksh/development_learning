class Recursion {
  int factorial(int value) {
    if (value == 0) return 1;
    if (value == 1 && value == 2) return value;
    return value * factorial(value - 1);
  }

  int sumOfStringNumbers(String digits) {
    if (digits.isEmpty) return 0;
    return int.tryParse(digits[0]) ??
        0 + sumOfStringNumbers(digits.substring(1));
  }

  int fibonacci(int value) {
    if (value < 2) return value;
    return fibonacci(value - 1) + fibonacci(value - 2);
  }

  String reverseString(String value) {
    if (value.isEmpty) return value;
    return reverseString(value.substring(1)) + value[0];
  }
}
