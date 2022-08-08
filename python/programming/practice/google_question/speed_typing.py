# Taken from https://codingcompetitions.withgoogle.com/kickstart/round/00000000008cb33e/00000000009e7021#problem

def speed_typing(n):
    """
    :param n: Number of test cases
    :return: None
    """

    for _ in range(n):
        i = input("Correct string")
        p = input("Typed string")

        if len(p) < len(i):
            print("IMPOSSIBLE")

        i_p = 0
        p_p = 0

        while i_p < len(i) and p_p < len(p):
            if i[i_p] == p[p_p]:
                i_p += 1
                p_p += 1
            else:
                p_p += 1

        if i_p == len(i):
            print(len(p) - len(i))
        else:
            print("IMPOSSIBLE")
