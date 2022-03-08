"""A src module which will contain the common functionalities"""

from .constants import Constants
from .utility import get_platform_details, get_current_date, get_current_timestamp
from .user_input import confirmation_question, get_user_email, get_user_phone_number,\
    get_user_name, get_password, get_photo_path, show_list_options, press_any_key_to_continue, \
    start_blog_edit, get_blog_initial_details, ask_for_new_tags
from .http_client import get_request
