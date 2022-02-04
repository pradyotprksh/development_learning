import math
import random
from src import log_debug


def math_random_module():
    """
    Let's take a look on math and random module in Python
    :return: None
    """

    value = 2.45
    log_debug(math.floor(value))

    log_debug(round(4.35))
    log_debug(round(4.5))
    log_debug(round(5.5))

    log_debug(math.pi)
    log_debug(math.inf)
    log_debug(math.nan)
    log_debug(math.e)
    log_debug(math.log(math.e))

    log_debug(random.randint(0, 100))

    my_list = list(range(0, 20))
    log_debug(my_list)
    log_debug(random.choice(my_list))
    log_debug(random.choice(my_list))
    log_debug(random.choices(population=my_list, k=10))
    log_debug(random.sample(population=my_list, k=10))
    random.shuffle(my_list)
    log_debug(my_list)

    log_debug(random.uniform(0, 100))
    log_debug(random.gauss(0, 1))
