from src import log_debug


class Dog:
    # CLass Object Attribute
    species = "mammal"

    def __init__(self, breed, name="No name"):
        # String
        self.breed = breed
        # String
        self.name = name
        self.greet = f"Woof!! Woof!! My name is {name} and I am a {Dog.species}"

    # Methods are actions performed by the objects
    def bark(self, number):
        log_debug(f"{self.name} Woof!! x{number}")

    # Methods can return also
    def my_age(self):
        return 2 * len(self.breed)


def class_object_attributes_method():
    """
    Let's take a look on class object attributes and methods
    :return:
    """

    my_dog = Dog(breed="Lab", name="Luffy")
    log_debug(type(my_dog))
    log_debug(my_dog.breed)
    log_debug(my_dog.name)
    log_debug(my_dog.species)
    my_dog.bark(10)

    my_new_dog = Dog(breed="Lab")
    log_debug(type(my_new_dog))
    log_debug(my_new_dog.breed)
    log_debug(my_new_dog.name)
    log_debug(my_new_dog.species)
    my_new_dog.bark(10)
    log_debug(my_new_dog.my_age())
    log_debug(my_new_dog.greet)
