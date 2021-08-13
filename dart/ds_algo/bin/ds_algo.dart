import 'package:ds_algo/algorithms/algorithms.dart';
import 'package:ds_algo/data_structures/data_structures.dart';
import 'package:ds_algo/util/utility.dart';

/// A Data Structure and Algorithms programs written in Dart.
///
/// This is been divided between 2 parts. And each have been maintained in
/// different directory.
void main() {
  var dataStructures = DataStructures();
  var algorithms = Algorithms();

  /// Data structures call
  Utility.printILog('Started Data Structure Examples...');

  /// Reverse a string
  var stringToReverse = 'Pradyot Prakash';
  Utility.printILog('Reverse the String `$stringToReverse`...');
  var reverseString = dataStructures.reverseString(stringToReverse);
  Utility.printDLog('Reveresed `$stringToReverse` to `$reverseString`...');

  /// Merge two sorted array
  var first = [0, 3, 4, 31, 80];
  var second = [4, 6, 30, 32, 33, 45];
  Utility.printILog('Merging $first and $second');
  var merged = dataStructures.mergeArray(first, second);
  Utility.printDLog('Merged $first and $second and the output is $merged');

  /// Algorithms call
  Utility.printILog('Started Algorithms Examples $algorithms...');
}
