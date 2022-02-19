"""A collection of all inputs which will be asked to the user"""

from whaaaaat import prompt, style_from_dict, Token
from .constants import Constants
from .validators import EmailValidator, PhoneNumberValidator, NameValidator, \
    PasswordValidator, PhotoValidator

style = style_from_dict({
    Token.Separator: '#f94144',
    Token.QuestionMark: '#f3722c bold',
    Token.Selected: '#f8961e',
    Token.Pointer: '#f9c74f bold',
    Token.Instruction: '#90be6d',
    Token.Answer: '#43aa8b bold',
    Token.Question: '#577590',
})


def confirmation_question(message):
    """
    Ask for a confirmation from the user
    :param message: Message to be shown
    :return: Boolean
    """
    return prompt(
        questions=[{
            "type": Constants.Variables.CONFIRM_QUESTION,
            "name": "answer",
            "message": message,
        }],
        style=style
    )["answer"]


def get_photo_path():
    """
    Get the photo path
    :return: Photo path or None
    """
    get_photo = confirmation_question(message=Constants.Messages.PHOTO_CONFIRMATION)
    if get_photo is True:
        return prompt(
            questions=[{
                "type": Constants.Variables.INPUT_QUESTION,
                "name": "photo",
                "message": Constants.Messages.PHOTO_QUESTION,
                "validate": PhotoValidator,
            }],
            style=style,
        )["photo"]
    return None


def get_user_name():
    """
    Get username
    :return: Username
    """
    return prompt(
        questions=[{
            "type": Constants.Variables.INPUT_QUESTION,
            "name": "name",
            "message": Constants.Messages.NAME_QUESTION,
            "validate": NameValidator,
        }],
        style=style,
    )["name"]


def get_user_email():
    """
    Get user email address
    :return: Email address
    """
    return prompt(
        questions=[{
            "type": Constants.Variables.INPUT_QUESTION,
            "name": "email",
            "message": Constants.Messages.EMAIL_QUESTION,
            "validate": EmailValidator,
        }],
        style=style,
    )["email"]


def get_password():
    """
    Get password
    :return: Password
    """
    return prompt(
        questions=[{
            "type": Constants.Variables.PASSWORD_QUESTION,
            "name": "password",
            "message": Constants.Messages.PASSWORD_QUESTION,
            "validate": PasswordValidator,
        }],
        style=style,
    )["password"]


def get_user_phone_number():
    """
    Get user phone number
    :return: Phone number
    """
    return prompt(
        questions=[{
            "type": Constants.Variables.INPUT_QUESTION,
            "name": "phone",
            "message": Constants.Messages.PHONE_QUESTION,
            "validate": PhoneNumberValidator,
        }],
        style=style
    )["phone"]


def ask_for_choices():
    """
    Ask for choices
    :return: Selected choice
    """
    return prompt(
        questions=[{
            "type": Constants.Variables.LIST_QUESTION,
            "name": "option",
            "message": Constants.Messages.OPTION_QUESTION,
            "choices": Constants.Variables.USER_OPTION_CHOICES
        }],
        style=style
    )["option"]
