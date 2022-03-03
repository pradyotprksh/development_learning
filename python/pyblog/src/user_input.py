"""A collection of all inputs which will be asked to the user"""

from PyInquirer import prompt, style_from_dict, Token
from .constants import Constants
from .validators import EmailValidator, PhoneNumberValidator, NameValidator, \
    PasswordValidator, PhotoValidator, BlogTitleValidator, BlogSubTitleValidator

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


def show_list_options(choices):
    """
    Ask for a choice
    :return: Selected choice
    """
    return prompt(
        questions=[{
            "type": Constants.Variables.LIST_QUESTION,
            "name": "option",
            "message": Constants.Messages.OPTION_QUESTION,
            "choices": choices
        }],
        style=style
    )["option"]


def press_any_key_to_continue(message):
    """
    Show an option to user to press any key to continue
    :param message: Message to be shown
    :return: None
    """
    input(message)


def start_blog_edit(template_path=None):
    """
    Start the blog edit process
    :parm template_path: Path for the template blog, optional
    :return: Written blog
    """
    if template_path is not None:
        pass
    return prompt(
        questions=[{
            "type": Constants.Variables.EDITOR_QUESTION,
            "name": "blog",
            "message": Constants.Messages.WRITE_BLOG,
            "eargs": {
                "editor": "nano",
                "ext": ".txt"
            }
        }],
        style=style
    )["blog"]


def get_blog_initial_details(tags):
    """
    Fetch the initial details of the blog
    :parm tags: Tags for blog
    """
    tag_choices = []
    for tag in tags:
        tag_choices.append({
            "name": tag,
            "checked": tag == "All"
        })

    title = prompt(
        questions=[{
            "type": Constants.Variables.INPUT_QUESTION,
            "name": "title",
            "message": Constants.Messages.BLOG_TITLE_QUESTION,
            "validate": BlogTitleValidator,
        }],
        style=style,
    )["title"]

    subtitle = prompt(
        questions=[{
            "type": Constants.Variables.INPUT_QUESTION,
            "name": "subtitle",
            "message": Constants.Messages.BLOG_SUBTITLE_QUESTION,
            "validate": BlogSubTitleValidator,
        }],
        style=style,
    )["subtitle"]

    selected_tags = prompt(
        questions=[{
            "type": Constants.Variables.CHECKBOX_QUESTION,
            "name": "selected_tags",
            "message": Constants.Messages.BLOG_TAGS_QUESTION,
            "choices": tag_choices,
            'validate': lambda answer: Constants.Messages.INVALID_CHECKBOX_SELECTION if len(answer) == 0 else True
        }],
        style=style,
    )["selected_tags"]

    email_subscriber = confirmation_question(Constants.Messages.BLOG_EMAIL_SUBSCRIBERS_QUESTION)

    return title, subtitle, selected_tags, email_subscriber
