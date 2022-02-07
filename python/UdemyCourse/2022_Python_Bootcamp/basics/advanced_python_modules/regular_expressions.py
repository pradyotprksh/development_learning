from src import log_debug
import re


def regular_expressions():
    """
    Let's take a look on regular expressions in Python
    :return: None
    """

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
