class Sorting {
  List<int> bubbleSort(List<int> arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length && j != i; j++) {
        if (arr[i] < arr[j]) {
          arr[i] = arr[i] + arr[j];
          arr[j] = arr[i] - arr[j];
          arr[i] = arr[i] - arr[j];
        }
      }
    }
    return arr;
  }

  List<int> selectionSort(List<int> arr) {
    for (var i = 0; i < arr.length; i++) {
      var min = i;
      var temp = arr[i];
      for (var j = i + 1; j < arr.length; j++) {
        if (arr[j] < arr[min]) {
          min = j;
        }
      }
      arr[i] = arr[min];
      arr[min] = temp;
    }
    return arr;
  }

  List<int> insertionSort(List<int> arr) {
    for (var i = 1; i < arr.length; i++) {
      var start = i - 1;
      var current = i;
      while (start >= 0) {
        if (arr[start] > arr[current]) {
          arr[start] = arr[start] + arr[current];
          arr[current] = arr[start] - arr[current];
          arr[start] = arr[start] - arr[current];
        } else {
          break;
        }
        --current;
        --start;
      }
    }
    return arr;
  }
}
