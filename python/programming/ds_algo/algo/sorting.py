import src


def insertion_sort(arr):
    for i in range(1, len(arr)):
        if arr[i] < arr[i - 1]:
            j = i
            while (j - 1) >= 0 and arr[j] < arr[j - 1]:
                temp = arr[j - 1]
                arr[j - 1] = arr[j]
                arr[j] = temp
                j -= 1
    return arr


def merge_sort(arr):
    if len(arr) > 1:
        mid = len(arr) // 2
        left = arr[:mid]
        right = arr[mid:]

        merge_sort(left)
        merge_sort(right)

        i = j = k = 0
        while i < len(left) and j < len(right):
            if left[i] < right[j]:
                arr[k] = left[i]
                i += 1
            else:
                arr[k] = right[j]
                j += 1
            k += 1

        while i < len(left):
            arr[k] = left[i]
            k += 1
            i += 1

        while j < len(right):
            arr[k] = right[j]
            k += 1
            j += 1

        print(f"left={left}", f"right={right}", f"arr={arr}")


def start_with_sorting():
    user_selection = 1
    arr = [38, 27, 43, 3, 9, 82, 10]
    while user_selection in [1]:
        user_selection = src.UserInput.get_user_selection(
            "Please select an option"
            "\n1. Insertion Sort"
            "\n2. Merge Sort"
        )
        if user_selection == 1:
            print(f"insertion_sort {insertion_sort(arr)}")
        elif user_selection == 2:
            merge_sort(arr)
            print(f"merge_sort {arr}")
