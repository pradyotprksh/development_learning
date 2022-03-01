"""A src module which will contain the common functionalities"""

from .constants import Constants
from .utility import get_platform_details
from .user_input import confirmation_question, get_user_email, get_user_phone_number,\
    get_user_name, get_password, get_photo_path, show_list_options, press_any_key_to_continue, \
    open_editor_for_blog
from .http_client import get_request
