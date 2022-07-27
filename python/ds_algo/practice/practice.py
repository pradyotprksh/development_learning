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
    print(f"_sock_merchant {_sock_merchant([10, 20, 20, 10, 10, 30, 50, 10, 20])}")
    print(f"_counting_valleys {_counting_valleys('DDUUUUDD')}")
    print(f"_repeated_string {_repeated_string('aba', 10)}")
    print(f"_jumping_on_clouds {_jumping_on_clouds([0, 0, 1, 0, 0, 1, 0])}")
    print(f"_diagonal_difference {_diagonal_difference([[11, 2, 4], [4, 5, 6], [10, 8, -12]])}")
    print(f"_staircase {_staircase(6)}")
    print(f"_mini_max_sum {_mini_max_sum([1, 2, 3, 4, 5])}")
    print(f"_birthday_cake_candles {_birthday_cake_candles([4, 4, 1, 3])}")
    print(f"_time_conversion {_time_conversion('12:01:00PM')}")
    print(f"_grading_students {_grading_students([73, 67, 38, 33])}")
    print(f"_count_apples_and_oranges {_count_apples_and_oranges(7, 11, 5, 15, [-2, 2, 1], [5, -6])}")
    print(f"_is_palindrome {_is_palindrome('abbabba')}")
    print(f"_get_total_x {_get_total_x([1], [100])}")
    print(f"_breaking_records {_breaking_records([10, 5, 20, 20, 4, 5, 2, 25, 1])}")
    print(f"_birthday {_birthday([1, 2, 1, 3, 2], 3, 2)}")
    print(f"_divisible_sum_pairs {_divisible_sum_pairs(6, 3, [1, 3, 2, 6, 1, 2])}")
    print(f"_day_of_programmer {_day_of_programmer(1800)}")
    print(f"_bon_appetit {_bon_appetit([3, 10, 2, 9], 1, 12)}")
    print(f"_climbing_leaderboard {_climbing_leaderboard([100, 100, 50, 40, 40, 20, 10], [5, 25, 50, 120])}")
    print(f"_is_isomorphic {_is_isomorphic('badc', 'baba')}")
    print(f"_is_isomorphic {_is_isomorphic('foo', 'bar')}")
    print(f"_is_subsequence {_is_subsequence('abc', 'ahbgdc')}")


def _is_subsequence(s: str, t: str) -> bool:
    if len(s) > len(t):
        return False
    s_itr = 0
    t_itr = 0
    while s_itr < len(s) and t_itr < len(t):
        print(s[s_itr], t[t_itr])
        if s[s_itr] == t[t_itr]:
            s_itr += 1
        t_itr += 1
    if s_itr == len(s):
        return True
    return False


def _is_isomorphic(s: str, t: str) -> bool:
    if len(s) != len(t):
        return False
    map_s_t = {}
    map_t_s = {}
    for s1, t1 in zip(s, t):
        if s1 not in map_s_t and t1 not in map_t_s:
            map_s_t[s1] = t1
            map_t_s[t1] = s1
        elif map_s_t.get(s1) != t1 and map_t_s.get(t1) != s1:
            return False
    return True


# TODO: Fix Time Exceed error, optimise solution needed
# https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem?isFullScreen=true
def _climbing_leaderboard(ranked, player):
    current_leaderboard_rank = []
    for i in range(0, len(ranked)):
        if i - 1 >= 0:
            if ranked[i] == ranked[i - 1]:
                current_leaderboard_rank.append(current_leaderboard_rank[i - 1])
            else:
                current_leaderboard_rank.append(current_leaderboard_rank[i - 1] + 1)
        else:
            current_leaderboard_rank.append(1)

    all_rank = []
    last_found_i = len(ranked)
    for score in player:
        cs_rank = None
        for i in range(0, last_found_i):
            if score < ranked[i]:
                continue
            else:
                last_found_i = i + 1
                cs_rank = current_leaderboard_rank[i]
                break
        if cs_rank is None:
            all_rank.append(current_leaderboard_rank[-1] + 1)
        else:
            all_rank.append(cs_rank)

    return all_rank


def _bon_appetit(bill, k, b):
    actual_split = 0
    for i, item in enumerate(bill):
        if i == k:
            continue
        actual_split += item

    print(actual_split)

    if actual_split / 2 == b:
        return "Bon Appetit"
    else:
        return int(b - (actual_split / 2))


# TODO: In-Progress
def _day_of_programmer(year):
    sep_day = 10
    return f"{sep_day}.09.{year}"


def _divisible_sum_pairs(n, k, ar):
    pair = 0
    for i in range(0, n):
        for j in range(i + 1, n):
            print(i, j, ar[i], ar[j], ar[i] + ar[j], k, (ar[i] + ar[j]) % k, pair)
            if (ar[i] + ar[j]) % k == 0:
                pair += 1
    return pair


def _birthday(s, d, m):
    count = 0
    for i in range(0, len(s)):
        bar_sum = sum(s[i:m + i])
        print(i, bar_sum)
        if bar_sum == d:
            count += 1
    return count


def _breaking_records(scores):
    current_min = scores[0]
    current_max = scores[0]
    min_scores = 0
    max_scores = 0
    for i in range(1, len(scores)):
        if scores[i] < current_min:
            current_min = scores[i]
            min_scores += 1
        elif scores[i] > current_max:
            current_max = scores[i]
            max_scores += 1
    return [max_scores, min_scores]


def _get_total_x(a, b):
    total_x = 0
    for item_b in b:
        is_divisible_all = True
        for item_a in a:
            if item_b % item_a != 0:
                is_divisible_all = False
                break
        if is_divisible_all:
            total_x += 1
    return total_x


def _is_palindrome(s):
    if len(s) <= 1:
        return True
    return s[0] == s[-1] and _is_palindrome(s[1:len(s) - 1])


def _count_apples_and_oranges(s, t, a, b, apples, oranges):
    apple_landed = 0
    orange_landed = 0
    for apple in apples:
        if s <= apple + a <= t:
            apple_landed += 1
    for orange in oranges:
        if s <= orange + b <= t:
            orange_landed += 1
    return apple_landed, orange_landed


def _grading_students(grades):
    for index, grade in enumerate(grades):
        next_div_five = (int(grade / 5) + 1) * 5
        if next_div_five - grade < 3 and grade >= 38:
            grades[index] = next_div_five
    return grades


def _birthday_cake_candles(candles):
    tallest = 1
    current_tallest = candles[0]
    for candle in candles[1:]:
        if current_tallest == candle:
            tallest += 1
        elif current_tallest < candle:
            current_tallest = candle
            tallest = 1
    return tallest


def _time_conversion(s):
    hour = s[:2]
    am_pm = s[len(s) - 2:]
    if am_pm == 'AM':
        if hour == "12":
            return "00" + s[2:len(s) - 2]
        return hour + s[2:len(s) - 2]
    else:
        if hour == "12":
            return s[:len(s) - 2]
        return f"{12 + int(hour)}" + s[2:len(s) - 2]


def _mini_max_sum(arr):
    arr.sort()
    min_sum = 0
    max_sum = 0
    for i, item in enumerate(arr):
        if i == 0:
            min_sum += item
        elif i == len(arr) - 1:
            max_sum += item
        else:
            min_sum += item
            max_sum += item
    print(min_sum, max_sum)


def _staircase(n):
    for i in range(1, n + 1):
        print(" " * (n - i) + "#" * i)


def _diagonal_difference(arr):
    diagonal_sum = 0
    for i in range(0, len(arr)):
        diagonal_sum += arr[i][i] - arr[i][len(arr) - 1 - i]
    return abs(diagonal_sum)


def _jumping_on_clouds(c):
    jumps = 0
    i = 0
    while i < len(c):
        if i + 2 < len(c) and c[i + 2] == 0:
            jumps += 1
            i += 2
        elif i + 1 < len(c) and c[i + 1] == 0:
            jumps += 1
            i += 1
        else:
            i += 1
    return jumps


def _sock_merchant(ar):
    socks_details = {}
    pairs = 0
    for item in ar:
        if item in socks_details.keys():
            socks_details[item] = socks_details[item] + 1
            if socks_details[item] == 2:
                pairs += 1
                socks_details[item] = 0
        else:
            socks_details[item] = 1
    return pairs


def _counting_valleys(path):
    is_going_down = False
    down_count = 0
    valleys = 0
    for step in path:
        if step == 'D':
            is_going_down = True
            down_count += 1
        else:
            down_count -= 1
            if is_going_down:
                if down_count == 0:
                    is_going_down = False
                    valleys += 1
    return valleys


def _repeated_string(s, n):
    num_as = 0
    for c in s:
        if c == 'a':
            num_as += 1
    total_as = n // len(s) * num_as
    extra_char_len = n % len(s)
    for i in range(0, extra_char_len):
        if s[i] == 'a':
            total_as += 1
    return total_as


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
    length = len(arr) // 2
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
