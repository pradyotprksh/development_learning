"""A pyblog package"""

from firebase import Firebase
from src import confirmation_question, get_user_email, get_user_phone_number, \
    Constants, get_user_name, get_password, \
    get_platform_details, get_photo_path, ask_for_choices
from .models import UserDetails

firebase = Firebase()


def _sign_up_user():
    """
    Signup the user
    :return: None
    """

    name = get_user_name()
    email_address = get_user_email()
    phone_number = get_user_phone_number()
    password = get_password()
    _photo_path = get_photo_path()
    photo_url = Constants.Variables.DEFAULT_IMAGE
    if _photo_path is not None:
        photo_url = firebase.upload_file(path=_photo_path)

    user_details = UserDetails(
        display_name=name,
        email=email_address,
        email_verified=False,
        phone_number=phone_number,
        password=password,
        photo_url=photo_url
    )

    firebase.create_user(user_details=user_details, platform_details=get_platform_details())


def _login_user():
    """
    Login the user
    :return: None
    """

    email_address = None
    phone_number = None

    email_option = confirmation_question(message=Constants.Messages.EMAIL_OPTION)
    if email_option:
        email_address = get_user_email()

    if email_address is None:
        phone_option = confirmation_question(message=Constants.Messages.PHONE_OPTION)
        if phone_option:
            phone_number = get_user_phone_number()

    if email_address is not None or phone_number is not None:
        firebase.login_user(
            email=email_address,
            phone_number=phone_number,
            platform_details=get_platform_details()
        )


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

    if firebase.get_current_user_details() is not None:
        current_user = firebase.get_current_user_details()
        print(Constants.Messages.WELCOME_USER.format(
            current_user.display_name,
            Constants.Variables.PROJECT_NAME
        ))
        logout = False
        while not logout:
            choice = ask_for_choices()
            if choice == Constants.Variables.MY_PROFILE_CHOICE:
                pass
            if choice == Constants.Variables.BLOGS_CHOICE:
                pass
            if choice == Constants.Variables.SEARCH_CHOICE:
                pass
            if choice == Constants.Variables.LOG_OUT_CHOICE:
                print(Constants.Messages.THANK_YOU_USER.format(
                    current_user.display_name,
                    Constants.Variables.PROJECT_NAME
                ))
                print(Constants.Messages.SEE_YOU_AGAIN)
                firebase.logout_user()
                logout = True
