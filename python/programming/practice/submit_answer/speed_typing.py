# Result
# S ✅ ✅

def _speed_typing_result(i, p):
    if len(p) < len(i):
        return "IMPOSSIBLE"

    i_p = 0
    p_p = 0

    while i_p < len(i) and p_p < len(p):
        if i[i_p] == p[p_p]:
            i_p += 1
            p_p += 1
        else:
            p_p += 1

    if i_p == len(i):
        return len(p) - len(i)
    else:
        return "IMPOSSIBLE"


def _get_test_cases(test_cases_str):
    return int(test_cases_str)


num_cases = int(input())
for i in range(num_cases):
    print(f"Case #{i + 1}: {_speed_typing_result(input(), input())}")
