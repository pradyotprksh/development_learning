from .pyblog_exception import InvalidValueException, InvalidTypeException
from .constants import Constants


class ValueCheck:
    """
    A value checker class which will help in making some normal checks on variables
    """

    @staticmethod
    def is_empty_or_none(arg, argument_name=""):
        """
        Check if its empty or none
        :param arg: argument to be checked
        :param argument_name: name of the argument in string for better message (optional)
        :return: None
        :raise InvalidValueException if None or empty
        """
        if arg is None or arg == "":
            raise InvalidValueException(message=Constants.ExceptionMessages.INVALID_VARIABLE.format(argument_name))

    @staticmethod
    def is_not_confirmed_type(arg, arg_type, argument_name=""):
        """
        Check if the type of the argument is same as expected
        :param arg: argument to be checked
        :param arg_type: the type which argument need to be
        :param argument_name: name of the argument in string for better message (optional)
        :return: None
        :raise InvalidTypeException if not valid type
        """
        if type(arg) is not arg_type:
            raise InvalidTypeException(message=Constants.ExceptionMessages.INVALID_VARIABLE_TYPE.format(argument_name))
