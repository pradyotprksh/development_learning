"""A collection of all validators in the project"""

import os
import imghdr
import regex
from PyInquirer import Validator, ValidationError
from .constants import Constants


class EmailValidator(Validator):
    """Validate email address"""

    def validate(self, document):
        is_okay = regex.match(
            Constants.Variables.EMAIL_REGEX,
            document.text
        )
        if not is_okay:
            raise ValidationError(
                message=Constants.Messages.INVALID_EMAIL,
                cursor_position=len(document.text)
            )


class NameValidator(Validator):
    """Validate username"""

    def validate(self, document):
        is_okay = regex.match(
            Constants.Variables.USER_NAME_REGEX,
            document.text
        )
        if not is_okay:
            raise ValidationError(
                message=Constants.Messages.INVALID_NAME,
                cursor_position=len(document.text)
            )


class BlogTitleValidator(Validator):
    """Validate blog title"""
    def validate(self, document):
        is_okay = len(document.text) > 5
        if not is_okay:
            raise ValidationError(
                message=Constants.Messages.INVALID_BLOG_TITLE,
                cursor_position=len(document.text)
            )


class BlogTagValidator(Validator):
    """Validate blog title"""
    def validate(self, document):
        is_okay = len(document.text) > 3
        if not is_okay:
            raise ValidationError(
                message=Constants.Messages.INVALID_BLOG_TITLE,
                cursor_position=len(document.text)
            )


class BlogSubTitleValidator(Validator):
    """Validate blog subtitle"""
    def validate(self, document):
        is_okay = len(document.text) > 15
        if not is_okay:
            raise ValidationError(
                message=Constants.Messages.INVALID_BLOG_SUBTITLE,
                cursor_position=len(document.text)
            )


class PhotoValidator(Validator):
    """Validate photo"""

    def validate(self, document):
        is_okay = os.path.exists(document.text) and \
             imghdr.what(document.text) in Constants.Variables.IMAGES_TYPES
        if not is_okay:
            raise ValidationError(
                message=Constants.Messages.INVALID_PHOTO,
                cursor_position=len(document.text)
            )


class PhoneNumberValidator(Validator):
    """Validate phone number"""

    def validate(self, document):
        is_okay = regex.match(
            Constants.Variables.PHONE_NUMBER_REGEX,
            document.text
        )
        if not is_okay:
            raise ValidationError(
                message=Constants.Messages.INVALID_PHONE_NUMBER,
                cursor_position=len(document.text)
            )


class PasswordValidator(Validator):
    """Validate password"""

    def validate(self, document):
        is_okay = regex.match(
            Constants.Variables.PASSWORD_REGEX,
            document.text
        )
        if not is_okay:
            raise ValidationError(
                message=Constants.Messages.INVALID_PASSWORD,
                cursor_position=len(document.text)
            )
