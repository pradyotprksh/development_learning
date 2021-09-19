class Questions {
  String reverseAString(String input) {
    var inputArr = input.split('');
    for (var i = 0; i < inputArr.length / 2; i++) {
      var temp = inputArr[i];
      inputArr[i] = inputArr[inputArr.length - 1 - i];
      inputArr[inputArr.length - 1 - i] = temp;
    }
    return inputArr.join();
  }

  List<int> mergedSortedArray(List<int> first, List<int> second) {
    var finalArray = <int>[];
    var firstLength = 0;
    var secondLength = 0;
    while (firstLength < first.length && secondLength < second.length) {
      if (first[firstLength] < second[secondLength]) {
        finalArray.add(first[firstLength]);
        ++firstLength;
      } else {
        finalArray.add(second[secondLength]);
        ++secondLength;
      }
    }
    for (var i = firstLength; i < first.length; i++) {
      finalArray.add(first[i]);
    }
    for (var i = secondLength; i < second.length; i++) {
      finalArray.add(second[i]);
    }
    return finalArray;
  }

  List<int> twoSum(List<int> nums, int target) {
    var i = 0;
    var j = 0;

    while (i < nums.length) {
      ++j;
      if (i != j) {
        if (nums[i] + nums[j] == target) {
          if (i < j) {
            return [i, j];
          } else {
            return [j, i];
          }
        }
      }
      if (j == nums.length) {
        j = 0;
        ++i;
      }
    }

    return [];
  }
}
