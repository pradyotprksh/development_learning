def _sherlock_and_anagrams(s):
    s_dict = {}
    is_duplicate_available = False
    anagrams = []
    for i in range(0, len(s)):
        current_anagram = []
        if s[i] in s_dict:
            is_duplicate_available = True
            current_anagram.append(s[i])
        s_dict.setdefault(s[i], 0)
        s_dict[s[i]] += 1
        current_anagram.append(s[i])
        if len(current_anagram) > 1:
            anagrams.append(current_anagram)
        for j in range(i + 1, len(s) + 1):
            new_str = s[i + 1:j]
            if len(new_str) > 0:
                concat_str = s[i] + new_str

                for key in s_dict.keys():
                    if sorted(key) == sorted(concat_str):
                        anagrams.append([key, concat_str])

                s_dict.setdefault(concat_str, 0)
                s_dict[concat_str] += 1

    if not is_duplicate_available:
        return 0
    return len(anagrams)