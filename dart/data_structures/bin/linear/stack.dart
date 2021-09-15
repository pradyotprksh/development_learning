class Stack {
  final _stack = [];
  var _maxElements = 5;
  var top = -1;

  void push(int value) {
    if (isFull()) {
      print('Max height reached. Can\'t add $value');
    } else {
      _stack.insert(0, value);
      top = value;
      --_maxElements;
      print('Added $value');
      printStack();
    }
  }

  void pop() {
    if (isEmpty()) {
      print('Stack empty');
    } else {
      print('Removed $top');
      _stack.removeAt(0);
      ++_maxElements;
      if (isEmpty()) {
        top = -1;
      } else {
        top = _stack.first;
        printStack();
      }
    }
  }

  bool isEmpty() {
    return _maxElements == 5;
  }

  bool isFull() {
    return _maxElements == 0;
  }

  void peek() {
    print('Top - $top');
  }

  void printStack() {
    var elements = '';
    for (var i in _stack) {
      elements = '$elements$i--->';
    }
    print(elements);
  }
}
