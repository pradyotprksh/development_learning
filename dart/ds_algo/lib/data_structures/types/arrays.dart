mixin Arrays {
  /// [value] : The value which needed to be reveresed.
  String reverseString(String value) {
    if (value.length < 2) return value;

    var newStringArray = value.split('');
    var start = 0;
    var end = newStringArray.length - 1;
    while (start < end) {
      var temp = newStringArray[start];
      newStringArray[start] = newStringArray[end];
      newStringArray[end] = temp;
      ++start;
      --end;
    }
    return newStringArray.join('');
  }

  /// Given two sorted arrays. Merge those 2 arrays and return it.
  ///
  /// [first] and [second] are the 2 sorted arrays which needed to be merged.
  List<int> mergeArray(List<int> first, List<int> second) {
    if (first.isEmpty) return second;
    if (second.isEmpty) return first;

    var lengthFirst = 0;
    var lengthSecond = 0;
    var newArray = <int>[];
    while (lengthFirst < first.length && lengthSecond < second.length) {
      if (first[lengthFirst] < second[lengthSecond]) {
        newArray.add(first[lengthFirst]);
        ++lengthFirst;
      } else {
        newArray.add(second[lengthSecond]);
        ++lengthSecond;
      }
    }
    if (lengthFirst != first.length) {
      for (var i = lengthFirst; i < first.length; i++) {
        newArray.add(first[i]);
      }
    }
    if (lengthSecond != second.length) {
      for (var i = lengthSecond; i < second.length; i++) {
        newArray.add(second[i]);
      }
    }
    return newArray;
  }
}
