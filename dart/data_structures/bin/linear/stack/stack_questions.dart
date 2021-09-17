import 'stack.dart';

class StackQuestions {
  void evaluatePostfixExpression(String expression) {
    if (expression.length > 10) {
      print('Can\'t evaluate expression with length > 5');
    } else {
      final _stack = Stack();
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

  void findDuplicateParenthesis(String expression) {
    if (expression.isEmpty) {
      print('No duplicates found');
    } else if (expression.length > 10) {
      print('Can\'t evaluate expression with length > 5');
    } else {
      final _stack = Stack();
      var duplicates = false;
      for (var value in expression.split('')) {
        if (value == ')') {
          if (_stack.peek() == '(') {
            duplicates = true;
            break;
          } else {
            while (_stack.peek() != '(') {
              _stack.pop();
            }
          }
        } else {
          _stack.push(value);
        }
      }
      if (duplicates) {
        print('Duplicates found');
      } else {
        print('No duplicates found');
      }
    }
  }

  String decodeSequenceAndGetMinimum(String sequence) {
    var output = '';
    var stack = Stack();
    for (var i = 0; i <= sequence.length; i++) {
      stack.push(i + 1);
      if (i == sequence.length || sequence[i] == 'I') {
        while (!stack.isEmpty()) {
          output = '$output${stack.peek()}';
          stack.pop();
        }
      }
    }
    return output;
  }
}
