class Stack {
  final _stack = [];
  final _minimumStack = [];
  var _maxElements = 5;
  var top = -1;

  void push(int value) {
    if (isFull()) {
      print('Max height reached. Can\'t add $value');
    } else {
      _stack.insert(0, value);
      if (_minimumStack.isEmpty) {
        _minimumStack.insert(0, value);
      } else {
        if (_minimumStack[_minimumStack.length - 1] > value) {
          _minimumStack.insert(0, value);
        }
      }
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
      if (_minimumStack[0] == _stack[0]) {
        _minimumStack.removeAt(0);
      }
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

  void getMinimumValue() {
    print('Minimum - ${_minimumStack[0]}');
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
