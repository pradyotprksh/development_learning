def google_questions():
    print(f"_two_sum {_two_sum([2, 7, 11, 15], 9)}")
    print(f"_two_sum {_two_sum([3, 3], 6)}")
    print(f"_two_sum {_two_sum([3, 2, 4], 6)}")
    print(
        f"_add_two_numbers {_add_two_numbers(ListNode(2, ListNode(4, ListNode(3))), ListNode(5, ListNode(6, ListNode(4))))}")
    print(
        f"_add_two_numbers {_add_two_numbers(ListNode(9, ListNode(9, ListNode(9, ListNode(9, ListNode(9, ListNode(9, ListNode(9))))))), ListNode(9, ListNode(9, ListNode(9, ListNode(9)))))}")
    print(
        f"_add_two_numbers {_add_two_numbers(ListNode(7, ListNode(8, ListNode(4))), ListNode(5, ListNode(8)))}"
    )
    print(
        f"_two_sum_numbers_not_reverse {__add_two_numbers_not_reverse(ListNode(3, ListNode(4, ListNode(2))), ListNode(4, ListNode(6, ListNode(5))))}"
    )


# https://leetcode.com/problems/two-sum/
def _two_sum(nums, target):
    diff_dict = {}
    for i, item in enumerate(nums):
        if target - item in diff_dict:
            return [i, diff_dict[target - item]]
        else:
            diff_dict[item] = i


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

    def __str__(self):
        return f"{self.val}->{self.next}"


# https://leetcode.com/problems/add-two-numbers/
def _add_two_numbers(l1: ListNode, l2: ListNode):
    sum_ll = None
    last_node = None

    cary = 0
    while True:
        if l1 is None and l2 is None and cary <= 0:
            break
        total_sum = 0
        if l1 is not None and l2 is not None:
            total_sum = l1.val + l2.val + cary
        elif l1 is not None:
            total_sum = l1.val + cary
        elif l2 is not None:
            total_sum = l2.val + cary
        elif cary > 0:
            total_sum = cary
        if l1:
            l1 = l1.next
        if l2:
            l2 = l2.next
        if total_sum >= 10:
            total_sum, cary = total_sum % 10, total_sum // 10
        else:
            cary = 0
        new_node = ListNode(total_sum)
        if sum_ll is None:
            sum_ll = new_node
        else:
            last_node.next = new_node
        last_node = new_node

    return sum_ll


# Alternate questions for https://leetcode.com/problems/add-two-numbers/
# One issue with this approach:
# If the previous node also has a cary after adding the next node cary then it won't do it
# because after one alteration that cary is lost and the node loop move forward
# For example:
# 9 -> 8 -> 5 -> None and 8 -> 5 -> None will give the output 0 -> 7 -> 2 -> None
def __add_two_numbers_not_reverse(l1, l2):
    # Length of the 2 linked list
    l1, l2, l1_len, l2_len, length_dif = _get_l1_l2_l1_len_l2_len_length_difference(l1, l2)

    # Get the sum linked list without carry
    sum_ll = _get_l1_l2_sum_without_cary(l1, l2, l1_len, length_dif)

    # Get the sum linked list wit carry
    sum_ll = _get_sum_with_cary(sum_ll)

    # return the sum ll
    return sum_ll


def _get_sum_with_cary(sum_ll):
    temp = sum_ll
    prev_temp = None
    while temp:
        # Get the current value and see if cary is needed
        total_sum = temp.val
        if total_sum >= 10:
            total_sum, cary = total_sum % 10, total_sum // 10
        else:
            cary = 0
        temp.val = total_sum

        # If cary needed then add that cary to the previous node
        if cary > 0:
            prev_total_sum = prev_temp.val + cary
            if prev_total_sum >= 10:
                prev_total_sum, cary = prev_total_sum % 10, prev_total_sum // 10
            prev_temp.val = prev_total_sum

        # move the previous and temp node forward
        prev_temp = temp
        temp = temp.next

    return sum_ll


def _get_l1_l2_sum_without_cary(l1, l2, l1_len, length_dif):
    sum_ll = None
    last_node = None
    for i in range(0, l1_len):
        first_num = l1.val
        l1 = l1.next if l1 else None
        second_num = None
        if i >= length_dif:
            second_num = l2.val
            l2 = l2.next if l2 else None
        total_sum = first_num + (second_num if second_num else 0)
        new_node = ListNode(total_sum)
        if sum_ll is None:
            sum_ll = new_node
        else:
            last_node.next = new_node
        last_node = new_node

    # return the sum ll
    return sum_ll


def _get_l1_l2_l1_len_l2_len_length_difference(l1, l2):
    # Length of the 2 linked list
    l1_len = 0
    l2_len = 0

    temp_l1 = l1
    while temp_l1:
        l1_len += 1
        temp_l1 = temp_l1.next
    temp_l2 = l2
    while temp_l2:
        l2_len += 1
        temp_l2 = temp_l2.next

    # Get the longest linked list and the length difference
    if l2_len > l1_len:
        l1, l2 = l2, l1
        length_dif = l2_len - l1_len
        l1_len, l2_len = l2_len, l1_len
    else:
        length_dif = l1_len - l2_len

    return l1, l2, l1_len, l2_len, length_dif
