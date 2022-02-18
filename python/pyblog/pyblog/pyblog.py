"""A pyblog package"""

from firebase import Firebase
from src import confirmation_question, get_user_email, get_user_phone_number, Constants

firebase = Firebase()


def _sign_up_user():
    """
    Signup the user
    :return: None
    """

    email_address = get_user_email()
    phone_number = get_user_phone_number()
    print(email_address)
    print(phone_number)


def _login_user():
    pass


def initiate_user_authentication():
    """
    Initiate the user authentication for PyBlog
    :return: None
    """

    auth_type = confirmation_question(message=Constants.Messages.AUTH_TYPE_QUESTION)
    if auth_type:
        _sign_up_user()
    else:
        _login_user()
