"""A collection of all inputs which will be asked to the user"""

from PyInquirer import prompt, style_from_dict, Token
from .constants import Constants
from .validators import EmailValidator, PhoneNumberValidator, NameValidator, \
    PasswordValidator, PhotoValidator, BlogTitleValidator, BlogSubTitleValidator, \
    BlogTagValidator


class UserInput:
    style = style_from_dict({
        Token.Separator: '#f94144',
        Token.QuestionMark: '#f3722c bold',
        Token.Selected: '#f8961e',
        Token.Pointer: '#f9c74f bold',
        Token.Instruction: '#90be6d',
        Token.Answer: '#43aa8b bold',
        Token.Question: '#577590',
    })

    def confirmation_question(self, message):
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
            style=self.style
        )["answer"]

    def get_photo_path(self):
        """
        Get the photo path
        :return: Photo path or None
        """
        get_photo = self.confirmation_question(message=Constants.Messages.PHOTO_CONFIRMATION)
        if get_photo is True:
            return prompt(
                questions=[{
                    "type": Constants.Variables.INPUT_QUESTION,
                    "name": "photo",
                    "message": Constants.Messages.PHOTO_QUESTION,
                    "validate": PhotoValidator,
                }],
                style=self.style,
            )["photo"]
        return None

    def get_user_name(self):
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
            style=self.style,
        )["name"]

    def get_user_email(self):
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
            style=self.style,
        )["email"]

    def get_password(self):
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
            style=self.style,
        )["password"]

    def get_user_phone_number(self):
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
            style=self.style
        )["phone"]

    def show_list_options(self, choices):
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
            style=self.style
        )["option"]

    @staticmethod
    def press_any_key_to_continue(message):
        """
        Show an option to user to press any key to continue
        :param message: Message to be shown
        :return: None
        """
        input(message)

    def start_blog_edit(self):
        """
        Start the blog edit process
        :return: Written blog
        """
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
            style=self.style
        )["blog"]

    def edit_written_blog(self, filename):
        """
        Edit the existing blog
        :param filename: File to be edited
        :return: Edited blog
        """
        return prompt(
            questions=[{
                "type": Constants.Variables.EDITOR_QUESTION,
                "name": "blog",
                "message": Constants.Messages.WRITE_BLOG,
                "eargs": {
                    "editor": "nano",
                    "ext": ".txt",
                    "filename": filename
                }
            }],
            style=self.style
        )["blog"]

    def ask_for_new_tags(self):
        """
        Ask for a new tag for blog
        :return: New tag
        """
        return prompt(
            questions=[{
                "type": Constants.Variables.INPUT_QUESTION,
                "name": "tag",
                "message": Constants.Messages.PROVIDE_BLOG_TAG,
                "validate": BlogTagValidator,
            }],
            style=self.style,
        )["tag"]

    def get_blog_title(self):
        """
        Get the blog title
        :return: blog title
        """
        return prompt(
            questions=[{
                "type": Constants.Variables.INPUT_QUESTION,
                "name": "title",
                "message": Constants.Messages.BLOG_TITLE_QUESTION,
                "validate": BlogTitleValidator,
            }],
            style=self.style,
        )["title"]

    def get_blog_subtitle(self):
        """
        Get the blog subtitle
        :return: blog subtitle
        """
        return prompt(
            questions=[{
                "type": Constants.Variables.INPUT_QUESTION,
                "name": "subtitle",
                "message": Constants.Messages.BLOG_SUBTITLE_QUESTION,
                "validate": BlogSubTitleValidator,
            }],
            style=self.style,
        )["subtitle"]

    def get_selected_tags(self, tags, checked_tags):
        tag_choices = []
        for tag in tags:
            tag_choices.append({
                "name": tag,
                "checked": tag in checked_tags
            })

        return prompt(
            questions=[{
                "type": Constants.Variables.CHECKBOX_QUESTION,
                "name": "selected_tags",
                "message": Constants.Messages.BLOG_TAGS_QUESTION,
                "choices": tag_choices,
                'validate': lambda answer: Constants.Messages.INVALID_CHECKBOX_SELECTION if len(answer) == 0 else True
            }],
            style=self.style,
        )["selected_tags"]

    def get_blog_initial_details(self, tags):
        """
        Fetch the initial details of the blog
        :parm tags: Tags for blog
        :return: tags, title, send email to subscriber and subtitle
        """

        title = self.get_blog_title()

        subtitle = self.get_blog_subtitle()

        selected_tags = self.get_selected_tags(tags=tags, checked_tags=["All"])

        if Constants.Messages.USE_A_NEW_TAG in selected_tags:
            new_tags = list(set(self.ask_for_new_tags().replace(", ", ",").replace(" ,", ",").split(",")))
            selected_tags = list(set(selected_tags + new_tags))

        email_subscriber = self.confirmation_question(Constants.Messages.BLOG_EMAIL_SUBSCRIBERS_QUESTION)

        return title, subtitle, selected_tags, email_subscriber
