class MergeSort {
  List<int> mergeSort(List<int> arr) {
    if (arr.length == 1) return arr;
    var middle = arr.length ~/ 2;
    var left = arr.getRange(0, middle).toList();
    var right = arr.getRange(middle, arr.length).toList();
    return merge(mergeSort(left), mergeSort(right));
  }

  List<int> merge(List<int> left, List<int> right) {
    var result = <int>[];
    var leftIndex = 0;
    var rightIndex = 0;
    while (leftIndex < left.length && rightIndex < right.length) {
      if (left[leftIndex] < right[rightIndex]) {
        result.add(left[leftIndex]);
        ++leftIndex;
      } else {
        result.add(right[rightIndex]);
        ++rightIndex;
      }
    }
    for (var i = leftIndex; i < left.length; i++) {
      result.add(left[i]);
    }
    for (var i = rightIndex; i < right.length; i++) {
      result.add(right[i]);
    }
    return result;
  }
}
