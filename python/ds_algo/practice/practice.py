def run_practice_problems():
    print(f"{_reverse_string('Pradyot')}")
    print(f"{_merge_sorted_array([1, 2, 3, 7], [4])}")
    print(f"{_two_sum([3, 1, 4, 2], 7)}")
    print(f"{_max_sub_array([5, 4, -1, 7, 8])}")
    print(f"{_move_zeroes([0, 1, 0, 3, 12])}")
    print(f"{_contains_duplicate([1, 2, 3, 4])}")
    print(f"{_rotate([1, 2, 3, 4, 5, 6, 7], 3)}")
    print(f"{_rotate([-1, -100, 3, 99], 2)}")


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
    for i in range(0, len(arr)):
        if arr[i] in diff.values():
            return [get_key(diff, arr[i]), i]
        else:
            diff[i] = target - arr[i]
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
    for i in range(0, len(arr)):
        if arr[i] == 0:
            arr.append(arr.pop(i))
    return arr


def _contains_duplicate(arr):
    items = set()
    for item in arr:
        if item in items:
            return True
        else:
            items.add(item)
    return False


def _rotate(arr, k):
    if k % len(arr) == 0:
        return arr
    for i in range(0, k):
        arr.insert(0, arr.pop())
    return arr


def get_key(my_dict, val):
    for key, value in my_dict.items():
        if val == value:
            return key
    return None
