"""A collection of all inputs which will be asked to the user"""

from whaaaaat import prompt, style_from_dict, Token
from .constants import Constants

style = style_from_dict({
    Token.QuestionMark: '',
    Token.Instruction: '',
    Token.Answer: '',
    Token.Question: '',
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
        }],
        style=style)["email"]


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
        }],
        style=style
    )["phone"]
