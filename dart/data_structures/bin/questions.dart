import 'dart:math';

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

  int maxSubArray(List<int> nums) {
    var globalMax = nums.first;
    var currentMax = nums.first;
    for (var i = 1; i < nums.length; i++) {
      currentMax = max(currentMax + nums[i], nums[i]);
      globalMax = max(globalMax, currentMax);
    }
    return globalMax;
  }

  List<int> movesZero(List<int> nums) {
    var newNums = <int>[];
    var numberOfZeroes = 0;
    for (var i in nums) {
      if (i == 0) {
        ++numberOfZeroes;
      } else {
        newNums.add(i);
      }
    }
    for (var i = 0; i < numberOfZeroes; i++) {
      newNums.add(0);
    }
    for (int i = 0; i < newNums.length; i++) {
      nums[i] = newNums[i];
    }
    return nums;
  }

  bool containsDuplicate(List<int> nums) {
    for (var i = 0; i < nums.length; i++) {
      for (var j = 0; j < nums.length && i != j; j++) {
        if (nums[i] == nums[j]) {
          return true;
        }
      }
    }
    return false;
  }

  List<int> rotateArray(List<int> nums, int k) {
    for (var i = 0; i < k; i++) {
      var first = nums.last;
      nums.removeAt(nums.length - 1);
      nums.insert(0, first);
    }
    return nums;
  }

  String longestWord(String value) {
    var maxLength = -1;
    var arrayString = value.split(' ');
    var longestWord = arrayString.first;
    for (var i = 0; i < arrayString.length; i++) {
      var value = arrayString[i].replaceAll(RegExp(r'[^\w\s]+'), '');
      if (value.length > maxLength) {
        maxLength = value.length;
        longestWord = value;
      }
    }
    return longestWord;
  }

  int recurringNumber(List<int> nums) {
    Map<int, int> unique = {};
    for (var num in nums) {
      if (unique.containsValue(num)) {
        return num;
      } else {
        unique[num] = num;
      }
    }
    return -1;
  }

  bool isStringUnique(String str) {
    var characters = <String>{};
    for (var s in str.split('')) {
      if (!characters.add(s)) {
        return false;
      }
    }
    return true;
  }

  bool isStringUniqueWOEDS(String str) {
    for (var i = 0; i < str.length; i++) {
      for (var j = 0; j < str.length && j != i; j++) {
        if (str.split('')[i] == str.split('')[j]) {
          return false;
        }
      }
    }
    return true;
  }

  bool isStringABPermutation(String a, String b) {
    if (a.length != b.length) {
      return false;
    }
    var aArr = a.split('');
    var bArr = b.split('');
    aArr.sort();
    bArr.sort();

    var sortedA = aArr.toString();
    var sortedB = bArr.toString();

    return sortedA.compareTo(sortedB) == 0;
  }

  String urlIfy(String str) {
    return str.trim().replaceAll(' ', '%20');
  }

  bool isOneAway(String main, String edited) {
    if ((main.length - edited.length).abs() > 1) {
      return false;
    }
    var numberOfEdits = 0;
    var mainArr = main.split('');
    var editedArr = edited.split('');
    var mainLength = 0;
    var editedLength = 0;
    while (mainLength < mainArr.length && editedLength < editedArr.length) {
      if (mainArr[mainLength] != editedArr[editedLength]) {
        ++numberOfEdits;
        if (main.length > edited.length) {
          ++mainLength;
          continue;
        } else if (edited.length > main.length) {
          ++editedLength;
          continue;
        }
      }
      ++mainLength;
      ++editedLength;
    }
    return numberOfEdits <= 1;
  }

  String stringCompression(String str) {
    var count = 0;
    var compressedString = '';
    var lastChar = str.split('').first;

    for (var s in str.split('')) {
      if (lastChar == s) {
        ++count;
      } else {
        compressedString = '$compressedString$lastChar$count';
        lastChar = s;
        count = 1;
      }
    }

    compressedString = '$compressedString$lastChar$count';

    if (compressedString.length < str.length) {
      return compressedString;
    }
    return str;
  }
}
