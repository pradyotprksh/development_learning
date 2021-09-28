class DynamicProgramming {
  var cache = <int, int>{};
  var count = 0;

  int fibonacci(int value) {
    if (value < 2) return value;
    if (cache.containsKey(value)) return cache[value]!;
    cache[value] = fibonacci(value - 1) + fibonacci(value - 2);
    ++count;
    return fibonacci(value - 1) + fibonacci(value - 2);
  }
}
