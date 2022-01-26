from src import log_debug, log_exception


class Book:

    def __init__(self, title, author, pages):
        self.title = title
        self.author = author
        self.pages = pages

    # String representation of Book class
    def __str__(self):
        return f"{self.title} by {self.author}"

    # Length representation of Book class
    def __len__(self):
        return self.pages

    # Things to happen when the instance is deleted
    def __del__(self):
        log_debug(f"{self.title} book has been removed from the shelf")


class Sample:
    pass


def special_methods():
    """
    Let's take a look on special methods in oops for python
    :return: none
    """

    my_list = [1, 2, 3]
    log_debug(len(my_list))

    my_sample = Sample()

    try:
        log_debug(len(my_sample))
    except Exception as e:
        log_exception(e)

    log_debug(my_list)
    log_debug(my_sample)

    my_book = Book("A hen nest", "Henary", 200)
    log_debug(my_book)
    log_debug(len(my_book))

    # delete an object from memory
    del my_book
    try:
        log_debug(my_book)
    except Exception as e:
        log_exception(e)
