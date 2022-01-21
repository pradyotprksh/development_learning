import unittest
from basics.testing.testing import cap_text


class TestCao(unittest.TestCase):

    def test_one_word(self):
        text = 'python'
        result = cap_text(text)
        self.assertEqual(result, 'Python')

    def test_multiple_word(self):
        text = "hello there"
        result = cap_text(text)
        self.assertEqual(result, 'Hello there')


if __name__ == 'main':
    unittest.main()
