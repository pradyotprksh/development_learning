import src


def start_with_search():
    user_selection = 1
    while user_selection in [1]:
        user_selection = src.UserInput.get_user_selection(
            "Please select an option"
            "\n1. Binary Search"
        )
        if user_selection == 1:
            print(f"_binary_search {_binary_search([-1, 0, 3, 5, 9, 12], 9)}")
            print(f"_binary_search {_binary_search([-1, 0, 3, 5, 9, 12], 13)}")


def _binary_search(nums: [int], target: int) -> int:
    return _binary_search_call(nums, 0, len(nums) - 1, target)


def _binary_search_call(nums, low, high, target):
    if low > high:
        return -1
    mid = (low + high) // 2
    if nums[mid] == target:
        return mid
    elif nums[mid] > target:
        return _binary_search_call(nums, low, mid - 1, target)
    elif nums[mid] < target:
        return _binary_search_call(nums, mid + 1, high, target)
