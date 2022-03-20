# Result
# S WA 🚫

def challenge_nine(n):
    nine_divisible = set()
    for i in range(1, 10):
        num_1 = int(f"{i}{n}")
        num_2 = int(f"{n}{i}")

        if num_1 % 9 == 0:
            nine_divisible.add(num_1)
        if num_2 % 9 == 0:
            nine_divisible.add(num_2)

    list_nine_divisible = list(nine_divisible)
    list_nine_divisible.sort()
    return list_nine_divisible[0]


test_cases = int(input())
for i in range(test_cases):
    print(f"Case #{i + 1}: {challenge_nine(int(input()))}")