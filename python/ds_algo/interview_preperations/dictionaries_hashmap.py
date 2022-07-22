# https://www.hackerrank.com/interview/interview-preparation-kit/dictionaries-hashmaps/challenges
def start_with_dictionaries_hashmap():
    print("Starting with dictionaries and hashmap")
    print("_check_magazine")
    _check_magazine(['give', 'me', 'one', 'grand', 'today', 'night'], ['give', 'one', 'grand', 'today'])
    print("_check_magazine")
    _check_magazine(['two', 'times', 'three', 'is', 'not', 'four'], ['two', 'times', 'two', 'is', 'four'])
    print("_check_magazine")
    _check_magazine(["ive", "got", "a", "lovely", "bunch", "of", "coconuts"], ["ive", "got", "some", "coconuts"])
    print(f"_two_strings {_two_strings('hello', 'world')}")
    print(f"_two_strings {_two_strings('hi', 'world')}")
    print(f"_sherlock_and_anagrams {_sherlock_and_anagrams('abba')}")
    print(f"_sherlock_and_anagrams {_sherlock_and_anagrams('ifailuhkqq')}")
    print(f"_sherlock_and_anagrams {_sherlock_and_anagrams('kkkk')}")
    print(f"_sherlock_and_anagrams {_sherlock_and_anagrams('abcd')}")


# https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem?h_l=interview&isFullScreen=true&playlist_slugs%5B%5D%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D%5B%5D=dictionaries-hashmaps&h_r=next-challenge&h_v=zen
def _sherlock_and_anagrams(s):
    pass


# https://www.hackerrank.com/challenges/two-strings/problem?h_l=interview&isFullScreen=true&playlist_slugs%5B%5D%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D%5B%5D=dictionaries-hashmaps
def _two_strings(s1, s2):
    if len(s1) > len(s2):
        long_str = s1
        small_str = s2
    else:
        long_str = s2
        small_str = s1

    char_dict = {}

    for s in long_str:
        char_dict.setdefault(s, 1)
        char_dict[s] += 1

    for s in small_str:
        if s in char_dict:
            return "YES"

    return "NO"


# https://www.hackerrank.com/challenges/ctci-ransom-note/problem?h_l=interview&isFullScreen=true&playlist_slugs%5B%5D%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D%5B%5D=dictionaries-hashmaps
def _check_magazine(magazine, note):
    msg_str_dict = {}
    for item in magazine:
        msg_str_dict.setdefault(item, 0)
        msg_str_dict[item] += 1

    can_decoded = True
    for item in note:
        try:
            if msg_str_dict[item] > 0:
                msg_str_dict[item] -= 1
            else:
                can_decoded = False
        except KeyError:
            can_decoded = False
            break

    if can_decoded:
        print("Yes")
    else:
        print("No")
