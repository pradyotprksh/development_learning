import 'recursion.dart';
import 'sorting.dart';

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
  print('***** INSERTION SORT *****');
  print(sorting.insertionSort([1, 2, 3, 4, 5, 1]));
  print('***** MERGE SORT *****');
  print(sorting.mergeSort([1, 2, 3, 4, 5, 6]));
  print('**********\n');
}
