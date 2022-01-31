from collections import Counter, defaultdict, namedtuple
from src import log_debug, log_exception


def collection_module():
    """
    Let's take a look on collection in python
    :return: None
    """

    my_list = [1, 1, 1, 1, 3, 3, 3, 3, 4, 4, 4, 4, 4]
    log_debug(Counter(my_list))

    my_list = ['a', 'a', 'b', 'c', 'a']
    log_debug(Counter(my_list))

    log_debug(Counter('aabcbasdfasdf'))

    sentence = "How many times does each word shows up in this word lists"
    log_debug(Counter(sentence.split()))

    c = Counter('aabcbasdfasdf')
    log_debug(c)
    log_debug(c.most_common())
    log_debug(list(c))
    log_debug(set(c))

    # Normal dictionary
    my_dict = {'a': 10}
    log_debug(my_dict)
    log_debug(my_dict['a'])
    try:
        log_debug(my_dict['b'])
    except Exception as e:
        log_exception(e)

    # Default dictionary
    d = defaultdict(lambda: 0)
    d['a'] = 10
    log_debug(d['a'])
    log_debug(d['b'])
    log_debug(d)

    # Normal tuple
    my_tuple = (10, 20, 30)
    log_debug(my_tuple)
    log_debug(my_tuple[0])

    Dog = namedtuple('Dog', ['age', 'breed', 'name'])
    log_debug(Dog)
    luffy = Dog(age=5, breed='Husky', name='Luffy')
    log_debug(luffy)
    log_debug(luffy.name)
    log_debug(luffy[2])
