class Searching {
  bool linearSearch(List<int> arr, int value) {
    for (var i = 0; i < arr.length; i++) {
      if (arr[i] == value) {
        return true;
      }
    }
    return false;
  }

  bool binarySearch(List<int> arr, int value) {
    var middle = arr.length ~/ 2;
    var middleItem = arr[middle];
    if (middleItem == value) {
      return true;
    } else {
      if (arr.length <= 1) {
        return false;
      } else {
        if (value < middleItem) {
          return binarySearch(arr.getRange(0, middle - 1).toList(), value);
        } else {
          return binarySearch(arr.getRange(middle, arr.length).toList(), value);
        }
      }
    }
  }
}
