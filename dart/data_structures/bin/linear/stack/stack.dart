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

  int peek() {
    print('Top - $top');
    return top;
  }

  void printStack() {
    var elements = '';
    for (var i in _stack) {
      elements = '$elements$i--->';
    }
    print(elements);
  }
}

class DoubleStack {
  final _stack = [-1, -1, -1, -1, -1];
  final _maxElements = 5;
  var _top1 = -1;
  var _top2 = 5;

  void push1(int value) {
    if (_top1 >= _top2 - 1) {
      print('Stack 1 full can\'t push $value');
    } else {
      ++_top1;
      _stack[_top1] = value;
    }
  }

  void push2(int value) {
    if (_top2 <= _top1 + 1) {
      print('Stack 2 full can\'t push $value');
    } else {
      --_top2;
      _stack[_top2] = value;
    }
  }

  void pop1() {
    if (_top1 == -1) {
      print('Stack 1 empty');
    } else {
      _stack[_top1] = -1;
      --_top1;
    }
  }

  void pop2() {
    if (_top2 == _maxElements) {
      print('Stack 2 empty');
    } else {
      _stack[_top2] = -1;
      ++_top2;
    }
  }

  void printStacks() {
    var firstElements = '';
    var secondElements = '';

    for (var i = _top1; i >= 0; i--) {
      if (_stack[i] == -1) {
        firstElements = '$firstElements#--->';
      } else {
        firstElements = '$firstElements${_stack[i]}--->';
      }
    }
    for (var i = _top2; i < _maxElements; i++) {
      if (_stack[i] == -1) {
        secondElements = '$secondElements#--->';
      } else {
        secondElements = '$secondElements${_stack[i]}--->';
      }
    }
    print('First Stack $firstElements');
    print('Second Stack $secondElements');
  }
}
