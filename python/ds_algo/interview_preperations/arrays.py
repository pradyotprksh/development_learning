# https://www.hackerrank.com/interview/interview-preparation-kit/arrays/challenges
def start_with_array_questions():
    print("Starting with arrays questions")
    print(f"_left_rotation {_left_rotation([1, 2, 3, 4, 5], 4)}")
    print(
        f"_hourglass_sum "
        f"{_hourglass_sum([[1, 1, 1, 0, 0, 0], [0, 1, 0, 0, 0, 0], [1, 1, 1, 0, 0, 0], [0, 0, 2, 4, 4, 0], [0, 0, 0, 2, 0, 0], [0, 0, 1, 2, 4, 0]])}")
    print("_minimum_bribes")
    _minimum_bribes([1, 2, 5, 3, 7, 8, 6, 4])
    print(f"_minimum_swaps {_minimum_swaps([1, 3, 5, 2, 4, 6, 7])}")
    print(f"_array_manipulation {_array_manipulation(10, [[1, 5, 3], [4, 8, 7], [6, 9, 1]])}")


# https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
def _left_rotation(a, d):
    for _ in range(0, d):
        a.append(a.pop(0))
    return a


# https://www.hackerrank.com/challenges/2d-array/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
def _hourglass_sum(arr):
    max_sum = None
    for i in range(0, len(arr) - 2):
        for j in range(0, len(arr[i]) - 2):
            hourglass_sum = arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j + 1] + arr[i + 2][j] + \
                            arr[i + 2][j + 1] + arr[i + 2][j + 2]
            if max_sum is None:
                max_sum = hourglass_sum
            else:
                max_sum = max(max_sum, hourglass_sum)
    return max_sum


# https://www.hackerrank.com/challenges/new-year-chaos/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
def _minimum_bribes(q):
    is_chaotic = False
    number_of_bribes = 0
    for i in range(0, len(q)):
        if q[i] - (i + 1) > 2:
            is_chaotic = True
            break
        else:
            for j in range(max(0, q[i] - 2), i):
                if q[j] > q[i]:
                    number_of_bribes += 1
    if is_chaotic:
        print("Too chaotic")
    else:
        print(number_of_bribes)


# https://www.hackerrank.com/challenges/minimum-swaps-2/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
def _minimum_swaps(arr):
    num_swaps = 0
    for i in range(0, len(arr)):
        while arr[i] != i + 1:
            temp = arr[i] - 1
            arr[i], arr[temp] = arr[temp], arr[i]
            num_swaps += 1
    return num_swaps


# https://www.hackerrank.com/challenges/crush/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
# TODO: Runtime error for some of the test cases
def _array_manipulation(n, queries):
    sum_arr = []
    for _ in range(0, n):
        sum_arr.append(0)
    for i in range(0, len(queries)):
        sum_arr[queries[i][0] - 1] += queries[i][2]
        if queries[i][1] < len(sum_arr):
            sum_arr[queries[i][1]] -= queries[i][2]
    max_sum = sum_arr[0]
    for i in range(1, len(sum_arr)):
        sum_arr[i] = sum_arr[i - 1] + sum_arr[i]
        max_sum = max(max_sum, sum_arr[i])
    return max_sum
