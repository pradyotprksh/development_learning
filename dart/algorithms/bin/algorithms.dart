import 'recursion.dart';

void main(List<String> arguments) {
  print('***** RECURSION *****');
  var recursion = Recursion();
  print(recursion.factorial(10));
  print(recursion.sumOfStringNumbers('1234567'));
  print(recursion.fibonacci(5));
  print(recursion.reverseString('abc'));
  print('**********\n');
}
