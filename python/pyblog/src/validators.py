"""A collection of all validators in the project"""

from whaaaaat import Validator


class EmailValidator(Validator):
    """Validate email address"""
    def validate(self, document):
        pass


class PhoneNumberValidator(Validator):
    """Validate phone number"""
    def validate(self, document):
        pass


class PasswordValidator(Validator):
    """Validate password"""
    def validate(self, document):
        pass
