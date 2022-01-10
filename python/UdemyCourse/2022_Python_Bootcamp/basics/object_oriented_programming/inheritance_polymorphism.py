from src import log_debug, log_exception


class Animal:

    def __init__(self):
        log_debug("Animal Created")

    def who_am_i(self):
        log_debug("I am an animal")

    def eat(self):
        log_debug("I am eating")


class Dog(Animal):

    def __init__(self):
        Animal.__init__(self)
        log_debug("Dog created")

    def who_am_i(self):
        log_debug("I am a dog")

    def bark(self):
        log_debug("Woof!!")


def inheritance():
    my_animal = Animal()
    my_animal.eat()
    my_animal.who_am_i()

    my_dog = Dog()
    my_dog.who_am_i()
    my_dog.eat()
    my_dog.bark()


class PolyDog:

    def __init__(self, name):
        self.name = name

    def speak(self):
        return self.name + " says Woof!!"


class PolyCat:

    def __init__(self, name):
        self.name = name

    def speak(self):
        return self.name + " says Meow!!"


def pet_speak(pet):
    log_debug(pet.speak())


class PolyAnimal:

    def __init__(self, name):
        self.name = name

    def speak(self):
        raise NotImplementedError("Subclass must implement this abstract method")


class AbsPolyDog(PolyAnimal):
    def speak(self):
        return self.name + " says Woof!!"


class AbsPolyCat(PolyAnimal):
    def speak(self):
        return self.name + " says Meow!!"


def polymorphism():
    shawk = PolyDog("Shawk")
    griff = PolyCat("griff")

    log_debug(shawk.speak())
    log_debug(griff.speak())

    for pet_class in [shawk, griff]:
        log_debug(type(pet_class))
        log_debug(pet_class.speak())

    pet_speak(shawk)
    pet_speak(griff)

    # Abstraction
    my_animal = PolyAnimal("ErrorAnimal")
    try:
        my_animal.speak()
    except Exception as e:
        log_exception(e)

    fito = AbsPolyDog("Fido")
    tom = AbsPolyCat("Tom")
    log_debug(fito.speak())
    log_debug(tom.speak())


def inheritance_polymorphism():
    """
    Let's take a look on inheritance and polymorphism
    :return:
    """

    inheritance()
    polymorphism()
