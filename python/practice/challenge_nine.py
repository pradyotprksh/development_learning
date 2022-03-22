# Taken from https://codingcompetitions.withgoogle.com/kickstart/round/00000000008cb33e/00000000009e7997
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
    """
    :param n: Number of test cases
    :return: None
    """

    for num in range(n):
        n = int(input("Number"))

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

        print(divisible_by_nine_list[0])
