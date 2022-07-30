# Result
# S ✅ TLE

import math


def _get_num_to_add(n):
    if n < 10:
        return n
    s = 0
    log_n = math.floor(math.log(n, 10) + 1)
    while log_n > 0:
        s += n % 10
        n //= 10
        log_n -= 1
    return _get_num_to_add(s)


def challenge_nine(n):
    num_to_add = (9 - _get_num_to_add(n)).__str__()

    divisible_by_nine = set()

    for i in reversed(range(0, len(n.__str__()) + 1)):
        if num_to_add == "0" and i == 0:
            break
        n_list = list(n.__str__())
        n_list.insert(i, num_to_add)
        divisible_by_nine.add(int("".join(n_list)))

    divisible_by_nine_list = list(divisible_by_nine)
    divisible_by_nine_list.sort()

    return divisible_by_nine_list[0]


test_cases = int(input())
for i in range(test_cases):
    print(f"Case #{i + 1}: {challenge_nine(int(input()))}")