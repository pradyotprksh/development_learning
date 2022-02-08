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


def regular_expressions():
    """
    Let's take a look on regular expressions in Python
    :return: None
    """

    part_1()
    part_2()
