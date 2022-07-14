"""Practice questions"""
from .document_distance_algorithm import DocumentDistanceAlgorithm
from .runway_reservation_system import start_reservation_process


"""
Some of the problems are taken from
https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-fall-2011/, contains 
set of lectures related to algorithms.
"""


def run_practice_problems():
    """All practice questions calls are added here"""
    print(f"_reverse_string {_reverse_string('Pradyot')}")
    print(f"_merge_sorted_array {_merge_sorted_array([1, 2, 3, 7], [4])}")
    print(f"_two_sum {_two_sum([3, 1, 4, 2], 7)}")
    print(f"_max_sub_array {_max_sub_array([5, 4, -1, 7, 8])}")
    print(f"_move_zeroes {_move_zeroes([0, 1, 0, 3, 12])}")
    print(f"_contains_duplicate {_contains_duplicate([1, 2, 3, 4])}")
    print(f"_rotate {_rotate([1, 2, 3, 4, 5, 6, 7], 3)}")
    print(f"_rotate {_rotate([-1, -100, 3, 99], 2)}")
    arr = [2, 3, 4, 5, 6, 7, 6, 5, 4]
    print(f"_find_peak {_find_peak(arr)}")
    print(f"_find_peak_2 {_find_peak_2(arr, len(arr), 0, len(arr) - 1)}")
    document_distance = DocumentDistanceAlgorithm(
        file1="Algorithms are fun and educating!\n"
              "Solving the algorithms are as fun as writing about them.\n"
              "Written by a random person",
        file2="Algorithms are fun and educating!\n"
              "Solving the algorithms are as fun as writing about them.\n"
              "Written by Filip"
    )
    print(f"get_document_distance {document_distance.get_document_distance()}")
    print("start_reservation_process")
    start_reservation_process()


def _find_peak_2(arr, n, low, high):
    low = low
    high = high
    mid = int((low + high) / 2)

    if arr[mid - 1] <= arr[mid] and arr[mid + 1] <= arr[mid]:
        return arr[mid]
    elif arr[mid - 1] > arr[mid]:
        return _find_peak_2(arr, n, low=low, high=mid - 1)
    elif arr[mid + 1] > arr[mid]:
        return _find_peak_2(arr, n, low=mid + 1, high=high)


def _find_peak(arr):
    """Fina a peak if it exists"""
    """if and only if arr[i] >= arr[i-1] && arr[i] >= arr[i+1] then arr[i] is a peak"""
    """if no left or right element then arr[i] >= arr[i-1] for left and arr[i] >= arr[i+1] for right"""
    """Solutions:"""
    """1. Straightforward solution is traversal with O(n)"""
    """2. Divide and conquer"""
    length = int(len(arr) / 2)
    print(f"length={length} arr={arr}")
    if length - 1 > 0 and arr[length] < arr[length - 1]:
        return _find_peak(arr[:length])
    elif length + 1 < len(arr) and arr[length] < arr[length + 1]:
        return _find_peak(arr[length:])
    else:
        return arr[length]


def _reverse_string(input_str):
    rev_str = ""
    for i in reversed(range(0, len(input_str))):
        rev_str = f"{rev_str}{input_str[i]}"
    return rev_str


def _merge_sorted_array(arr1, arr2):
    merged_arr = []
    if len(arr1) == 0:
        return arr2
    if len(arr2) == 0:
        return arr1

    arr1_count = 0
    arr2_count = 0
    while arr1_count < len(arr1) and arr2_count < len(arr2):
        if arr1[arr1_count] < arr2[arr2_count]:
            merged_arr.append(arr1[arr1_count])
            arr1_count += 1
        else:
            merged_arr.append(arr2[arr2_count])
            arr2_count += 1

    if arr1_count != len(arr1):
        for i in range(arr1_count, len(arr1)):
            merged_arr.append(arr1[i])

    if arr2_count != len(arr2):
        for i in range(arr2_count, len(arr2)):
            merged_arr.append(arr2[i])

    return merged_arr


def _two_sum(arr, target):
    diff = {}
    for i, item in enumerate(arr):
        if item in diff.values():
            return [get_key(diff, item), i]
        diff[i] = target - item
    return [-1, -1]


def _max_sub_array(arr):
    max_sum = arr[0]
    cur_sum = 0
    for item in arr:
        cur_sum += item
        if max_sum < cur_sum:
            max_sum = cur_sum
        elif cur_sum < 0:
            cur_sum = 0
    return max_sum


def _move_zeroes(arr):
    for i, item in enumerate(arr):
        if item == 0:
            arr.append(arr.pop(i))
    return arr


def _contains_duplicate(arr):
    items = set()
    for item in arr:
        if item in items:
            return True
        items.add(item)
    return False


def _rotate(arr, k):
    if k % len(arr) == 0:
        return arr
    for _ in range(0, k):
        arr.insert(0, arr.pop())
    return arr


def get_key(my_dict, val):
    """Get the key from the value in the dictionary"""
    for key, value in my_dict.items():
        if val == value:
            return key
    return None
