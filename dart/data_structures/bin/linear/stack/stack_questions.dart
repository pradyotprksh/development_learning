import 'stack.dart';

class StackQuestions {
  void evaluatePostfixExpression(String expression) {
    final _stack = Stack();
    if (expression.length > 5) {
      print('Can\'t evaluate expression with length > 5');
    } else {
      for (var value in expression.split('')) {
        if (int.tryParse(value) != null) {
          _stack.push(int.parse(value));
        } else {
          switch (value) {
            case '*':
              var firstValue = _stack.peek();
              _stack.pop();
              var secondValue = _stack.peek();
              _stack.pop();
              var output = secondValue * firstValue;
              _stack.push(output);
              break;
            case '/':
              var firstValue = _stack.peek();
              _stack.pop();
              var secondValue = _stack.peek();
              _stack.pop();
              var output = secondValue ~/ firstValue;
              _stack.push(output);
              break;
            case '+':
              var firstValue = _stack.peek();
              _stack.pop();
              var secondValue = _stack.peek();
              _stack.pop();
              var output = secondValue + firstValue;
              _stack.push(output);
              break;
            case '-':
              var firstValue = _stack.peek();
              _stack.pop();
              var secondValue = _stack.peek();
              _stack.pop();
              var output = secondValue - firstValue;
              _stack.push(output);
              break;
          }
          _stack.peek();
        }
      }
    }
  }
}
