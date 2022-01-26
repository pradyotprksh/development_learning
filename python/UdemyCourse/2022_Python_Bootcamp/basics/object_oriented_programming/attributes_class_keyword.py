from src import log_debug


class Sample:
    pass


class Dog:

    def __init__(self, breed, name, spots):
        # String
        self.breed = breed
        # String
        self.name = name

        # Boolean - True/False
        self.spots = spots


def attributes_class_keyword():
    """
    Let's check on what is a class and how to create it1
    :return: none
    """

    my_sample = Sample()
    log_debug(type(my_sample))

    my_dog = Dog(breed="Lab", name="Luffy", spots=False)
    log_debug(type(my_dog))
    log_debug(my_dog.breed)
    log_debug(my_dog.name)
    log_debug(my_dog.spots)
