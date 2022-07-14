import math


class DocumentDistanceAlgorithm:

    def __init__(self, file1, file2):
        self.file1 = file1
        self.file2 = file2

    def get_document_distance(self):
        words_1 = self._get_words(self.file1)
        words_2 = self._get_words(self.file2)

        frequency_1 = self._compute_frequency(words_1)
        frequency_2 = self._compute_frequency(words_2)

        distance = self._compute_distance(frequency_1, frequency_2)
        return distance

    @staticmethod
    def _compute_inner_product(hash_file1, hash_file2):
        dot_product = 0
        for key, value in hash_file1.items():
            if key in hash_file2.keys():
                dot_product += value * hash_file2[key]
        return dot_product

    def _compute_distance(self, hash_file1, hash_file2):
        # Copied the formula from the lecture
        #     --    N1N2     --
        # cos | ------------- |
        #     | |N1N1|*|N2N2| |
        #     --             --
        # where N is the inner product for a given dictionary
        # and 1 and 2 denotes the files
        numerator = self._compute_inner_product(hash_file1, hash_file2)
        denominator = math.sqrt(
            self._compute_inner_product(hash_file1, hash_file1) *
            self._compute_inner_product(hash_file2, hash_file2)
        )
        return math.acos(numerator/denominator)

    @staticmethod
    def _compute_frequency(str_input):
        hash_doc = {}
        for item in str_input:
            if item.lower() in hash_doc.keys():
                hash_doc[item.lower()] += 1
            else:
                hash_doc[item.lower()] = 1
        return hash_doc

    @staticmethod
    def _get_words(str_input):
        words = []
        characters = []
        for word in str_input.split(" "):
            for char in word:
                if char.isalnum():
                    characters.append(char)
            if len(characters) > 0:
                words.append(''.join(characters).lower())
                characters = []
        return words
