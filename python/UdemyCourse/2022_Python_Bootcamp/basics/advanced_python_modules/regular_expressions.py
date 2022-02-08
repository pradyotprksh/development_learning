from src import log_debug
import re


def part_1():
    text = "Contact me using the phone number 123-123-123. Call soon!!"
    log_debug("phone" in text)

    pattern = "phone"
    log_debug(re.search(pattern, text))

    pattern = "not"
    log_debug(re.search(pattern, text))

    pattern = "phone"
    match = re.search(pattern, text)
    log_debug(match.span())
    log_debug(match.start())
    log_debug(match.end())

    text = "my phone once. my phone twice"
    match = re.search("phone", text)
    log_debug(match.span())
    matches = re.findall("phone", text)
    log_debug(matches)
    for match in re.finditer("phone", text):
        log_debug(match)


def part_2():
    text = "My phone number is 123-456-7890"
    phone = re.search(r'\d\d\d-\d\d\d-\d\d\d\d', text)
    log_debug(phone.group())

    phone = re.search(r'\d{3}-\d{3}-\d{4}', text)
    log_debug(phone.group())

    phone_pattern = re.compile(r'(\d{3})-(\d{3})-(\d{4})')
    results = re.search(phone_pattern, text)
    log_debug(results.group())
    # Group order starts from one
    log_debug(results.group(1))
    log_debug(results.group(2))
    log_debug(results.group(3))


def part_3():
    log_debug(re.search(r'cat', 'The cat is here'))
    log_debug(re.search(r'dog', 'The cat is here'))
    log_debug(re.search(r'cat | dog', 'The cat is here'))
    for match in re.finditer(r'cat|dog', 'The cat & dog is here'):
        log_debug(match)

    log_debug(re.findall(r'.{3}at', 'The cat in the hat when splat'))

    log_debug(re.findall(r'^\d', '1 is a number'))
    log_debug(re.findall(r'^\d', 'The 1 is a number'))

    log_debug(re.findall(r'\d$', '1 is a number'))
    log_debug(re.findall(r'\d$', 'The number is 2'))

    phrase = 'There are 3 numbers 34 inside 5 this sentence'
    pattern = r'[^\d]'
    log_debug(re.findall(pattern, phrase))
    pattern = r'[^\d]+'
    log_debug(re.findall(pattern, phrase))
    phrase = 'This a string. But how can we remove punctuations? Please help!!'
    pattern = r'[^!.? ]+'
    clean = re.findall(pattern, phrase)
    log_debug(' '.join(clean))

    text = 'Only find the hyphen-words ih this sentence. But you do not know how long-ish they are'
    pattern = r'[\w]+-[\w]+'
    log_debug(re.findall(pattern, text))

    test1 = "This is a catfish"
    test2 = "This is a catnap"
    test3 = "This is a catwork"
    log_debug(re.search(r'cat(fish|nap|work)', test1))
    log_debug(re.search(r'cat(fish|nap|work)', test2))
    log_debug(re.search(r'cat(fish|nap|claw)', test3))
    log_debug(re.search(r'cat(fish|nap|work)', test3))


def regular_expressions():
    """
    Let's take a look on regular expressions in Python
    :return: None
    """

    part_1()
    part_2()
    part_3()
