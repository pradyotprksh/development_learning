# https://www.hackerrank.com/interview/interview-preparation-kit/arrays/challenges
def start_with_array_questions():
    print("Starting with arrays questions")
    print(f"_left_rotation {_left_rotation([1, 2, 3, 4, 5], 4)}")


# https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
def _left_rotation(a, d):
    for _ in range(0, d):
        a.append(a.pop(0))
    return a
