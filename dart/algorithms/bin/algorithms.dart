import 'recursion.dart';
import 'sorting/sorting.dart';

void main(List<String> arguments) {
  print('***** RECURSION *****');
  var recursion = Recursion();
  print(recursion.factorial(10));
  print(recursion.sumOfStringNumbers('1234567'));
  print(recursion.fibonacci(5));
  print(recursion.reverseString('abc'));
  print('**********\n');

  print('***** SORTING *****');
  var sorting = Sorting();
  print('***** BUBBLE SORT *****');
  print(sorting.bubbleSort([1, 4, 3, 5, 6, 7]));
  print('***** SELECTION SORT *****');
  print(sorting.selectionSort([1, 4, 3, 5, 6, 7]));
  print('**********\n');
}
