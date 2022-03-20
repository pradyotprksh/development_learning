# Taken from https://codingcompetitions.withgoogle.com/kickstart/round/00000000008cb33e/00000000009e7997

# TODO
def challenge_nine(n):
    """
    :param n: Number of test cases
    :return: None
    """

    for num in range(n):
        nine_divisible = set()

        n = input("Number")

        for i in range(1, 10):

            num_1 = int(f"{i}{n}")
            num_2 = int(f"{n}{i}")

            if num_1 % 9 == 0:
                nine_divisible.add(num_1)
            if num_2 % 9 == 0:
                nine_divisible.add(num_2)

        list_nine_divisible = list(nine_divisible)
        list_nine_divisible.sort()
        print(list_nine_divisible[0])
