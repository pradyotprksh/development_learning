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

//#1 - Sort 10 schools around your house by distance: Insertion sort

//#2 - eBay sorts listings by the current Bid amount: Merge / Radix / Counting sort

//#3 - Sport scores on ESPN : Merge / Quick sort

//#4 - Massive database (can't fit all into memory) needs to sort through past year's user data - Quick sort / Merge Sort

//#5 - Almost sorted Udemy review data needs to update and add 2 new reviews - Quick sort / Insertion sort

//#6 - Temperature Records for the past 50 years in Canada - Quick / Radix / Counting sort

//#7 - Large user name database needs to be sorted. Data is very random. - Merge / Quick sort

//#8 - You want to teach sorting for the first time - Bubble sort
