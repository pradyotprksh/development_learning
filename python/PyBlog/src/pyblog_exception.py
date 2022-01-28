class PyBlogException(Exception):

    def __init__(self, message=""):
        """
        Main exception class for PyBlog
        :param message: Message to be shown for exception
        """
        self.message = message


class InvalidValueException(PyBlogException):
    """
    Exception which will be raised for invalid value
    """
    pass


class InvalidTypeException(PyBlogException):
    """
    Exception which will be raised for invalid argument type
    """
    pass
