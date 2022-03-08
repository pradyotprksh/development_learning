"""A pyblog package"""

from firebase import Firebase
from src import confirmation_question, get_user_email, get_user_phone_number, \
    Constants, get_user_name, get_password, get_platform_details, get_photo_path, show_list_options, \
    press_any_key_to_continue, start_blog_edit, get_blog_initial_details, get_current_date, \
    get_current_timestamp, ask_for_new_tags
from .models import UserDetails, BlogDetails

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
    if _photo_path is not None:
        photo_url = firebase.upload_file(path=_photo_path)
    else:
        photo_url = Constants.URLs.DEFAULT_IMAGE

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


def _get_final_blog_data(title, subtitle, tags, blog, created_by, created_on):
    with open(Constants.Paths.DEFAULT_BLOG_TEMPLATE, "r") as default_blog:
        default_blog_data = default_blog.read()

        tags_string = ""
        for tag in tags:
            tags_string = f"| {tag} | {tags_string}"

        blog = default_blog_data.replace(
            Constants.Variables.REPLACE_BLOG_TITLE, title
        ).replace(
            Constants.Variables.REPLACE_BLOG_SUBTITLE, subtitle
        ).replace(
            Constants.Variables.REPLACE_BLOG_DATA, blog
        ).replace(
            Constants.Variables.REPLACE_BLOG_CREATED_BY, created_by
        ).replace(
            Constants.Variables.REPLACE_BLOG_CREATED_ON, created_on
        ).replace(
            Constants.Variables.REPLACE_BLOG_TAGS, tags_string
        )

        default_blog.close()

    return blog


def _write_blog_option(start_type):
    """
    Start write blog option
    :parm start_type: Start type of the blog
    """
    tags = firebase.get_blog_tags()
    title, subtitle, selected_tags, email_subscriber = get_blog_initial_details(tags=tags)

    if Constants.Messages.USE_A_NEW_TAG in selected_tags:
        new_tags = list(set(ask_for_new_tags().replace(", ", ",").replace(" ,", ",").split(",")))
        selected_tags = list(set(selected_tags + new_tags))
        firebase.update_blog_tags(tags=selected_tags)

    user_details = firebase.get_user_details_firestore()

    created_by_uid = user_details[Constants.Firebase.Keys.USER_ID]
    created_by_name = user_details[Constants.Firebase.Keys.DISPLAY_NAME]
    created_on = get_current_date()
    created_on_timestamp = get_current_timestamp()

    if start_type == Constants.Variables.USER_BLOGS_WRITE_BLOG_DEFAULT_TEMPLATE:
        blog = start_blog_edit(template_path=Constants.Paths.DEFAULT_BLOG_TEMPLATE)
        final_blog = _get_final_blog_data(
            title=title,
            subtitle=subtitle,
            tags=selected_tags,
            blog=blog,
            created_by=created_by_name,
            created_on=created_on
        )
    else:
        blog = start_blog_edit()
        final_blog = blog

    blog_details = BlogDetails(
        title=title,
        subtitle=subtitle,
        tags=selected_tags,
        email_subscriber=email_subscriber,
        blog=final_blog,
        created_by_uid=created_by_uid,
        created_on=created_on_timestamp,
        views=0,
        likes=0,
        isDraft=False
    )

    upload_ask = True
    while upload_ask:
        blog_write_action = show_list_options(Constants.Variables.USER_BLOGS_WRITE_ACTIONS)
        if blog_write_action == Constants.Variables.UPLOAD_FIRESTORE:
            is_upload = confirmation_question(Constants.Messages.CONFIRM_UPLOAD)
            if is_upload:
                firebase.upload_blog(blog_details=blog_details)
                upload_ask = False
        elif blog_write_action == Constants.Variables.SAVE_AS_DRAFT:
            blog_details.isDraft = True
            firebase.upload_blog(blog_details=blog_details)
            upload_ask = False
        else:
            is_discard = confirmation_question(Constants.Messages.CONFIRM_DISCARD.format(title))
            if is_discard:
                print(Constants.Messages.BLOG_DISCARDED.format(title))
                upload_ask = False


def _user_write_blogs_flow():
    """
    Start current user write a blog flow
    :return: None
    """
    choice = show_list_options(choices=Constants.Variables.USER_BLOGS_WRITE_BLOG_OPTIONS)
    if choice == Constants.Variables.USER_BLOGS_WRITE_BLOG_DEFAULT_TEMPLATE:
        _write_blog_option(start_type=Constants.Variables.USER_BLOGS_WRITE_BLOG_DEFAULT_TEMPLATE)
        _user_blogs_flow()
    elif choice == Constants.Variables.USER_BLOGS_WRITE_BLOG_JUST_WRITE:
        _write_blog_option(start_type=Constants.Variables.USER_BLOGS_WRITE_BLOG_JUST_WRITE)
        _user_blogs_flow()
    elif choice == Constants.Variables.BACK:
        _user_blogs_flow()


def _user_blogs_flow():
    """
    Start current user blogs flow
    :return: None
    """
    choice = show_list_options(choices=Constants.Variables.USER_BLOGS_OPTIONS)
    if choice == Constants.Variables.USER_BLOGS_SHOW_BLOGS:
        _profile_flow()
    elif choice == Constants.Variables.USER_BLOGS_WRITE_BLOG:
        _user_write_blogs_flow()
    elif choice == Constants.Variables.USER_BLOGS_FAV_BLOG:
        _profile_flow()
    elif choice == Constants.Variables.BACK:
        _profile_flow()


def _user_actions_flow():
    """
    Start profile actions flow for current user
    :return: None
    """
    choice = show_list_options(choices=Constants.Variables.USER_ACTIONS_OPTIONS)
    if choice == Constants.Variables.USER_ACTIONS_VERIFY_EMAIL_ADDRESS:
        _profile_flow()
    elif choice == Constants.Variables.USER_ACTIONS_CHANGE_PASSWORD:
        _profile_flow()
    elif choice == Constants.Variables.USER_ACTIONS_DELETE_ACCOUNT:
        _profile_flow()
    elif choice == Constants.Variables.BACK:
        _profile_flow()


def _user_edit_option():
    """
    Start flow for edit profile for current user
    :return: None
    """
    choice = show_list_options(choices=Constants.Variables.USER_EDIT_OPTIONS)
    if choice == Constants.Variables.USER_EDIT_NAME:
        name = get_user_name()
        confirmation = confirmation_question(Constants.Messages.CONFIRM_CHANGE.format(
            Constants.Messages.NAME,
            name
        ))
        if confirmation:
            firebase.update_user(platform_details=get_platform_details(), name=name)
        _profile_flow()
    elif choice == Constants.Variables.USER_EDIT_DISPLAY_IMAGE:
        _photo_path = get_photo_path()
        if _photo_path is not None:
            photo_url = firebase.upload_file(path=_photo_path)
            firebase.update_user(platform_details=get_platform_details(), photo_url=photo_url)
        else:
            print(Constants.Messages.SOMETHING_WENT_WRONG)
        _profile_flow()
    elif choice == Constants.Variables.BACK:
        _profile_flow()


def _show_profile_details():
    """
    Show the current user profile details
    :return: None
    """
    user_details = firebase.get_user_details_firestore()
    string_user_details = Constants.Messages.USER_DETAILS.format(
        user_details[Constants.Firebase.Keys.DISPLAY_NAME],
        user_details[Constants.Firebase.Keys.EMAIL],
        user_details[Constants.Firebase.Keys.PHONE_NUMBER],
        user_details[Constants.Firebase.Keys.PHOTO_URL],
    )
    print(string_user_details)
    press_any_key_to_continue(message=Constants.Messages.PRESS_TO_CONTINUE)
    _profile_flow()


def _profile_flow():
    """
    Start profile flow of the user
    :return: None
    """
    choice = show_list_options(choices=Constants.Variables.USER_PROFILE_OPTION_CHOICES)
    if choice == Constants.Variables.USER_PROFILE_DETAILS:
        _show_profile_details()
    elif choice == Constants.Variables.USER_BLOGS:
        _user_blogs_flow()
    elif choice == Constants.Variables.USER_ACTIONS:
        _user_actions_flow()
    elif choice == Constants.Variables.USER_EDIT:
        _user_edit_option()
    elif choice == Constants.Variables.USER_FOLLOWERS:
        pass
    elif choice == Constants.Variables.USER_FOLLOWING:
        pass
    elif choice == Constants.Variables.USER_SUBSCRIBED:
        pass
    elif choice == Constants.Variables.USER_SUBSCRIBER:
        pass
    elif choice == Constants.Variables.BACK:
        pass


def _blogs_see_filtered_option():
    """
    See filtered option for blogs
    :return: None
    """
    choice = show_list_options(choices=Constants.Variables.BLOGS_SEE_FILTERED_BLOGS_OPTIONS)
    if choice == Constants.Variables.BLOGS_SEE_FILTERED_BLOGS_RECENT:
        _blogs_flow()
    elif choice == Constants.Variables.BLOGS_SEE_FILTERED_BLOGS_LIKED:
        _blogs_flow()
    elif choice == Constants.Variables.BLOGS_SEE_FILTERED_BLOGS_VIEWED:
        _blogs_flow()
    elif choice == Constants.Variables.BACK:
        _blogs_flow()


def _blogs_flow():
    """
    Start blogs flow
    :return: None
    """
    choice = show_list_options(choices=Constants.Variables.BLOGS_CHOICE_OPTIONS)
    if choice == Constants.Variables.BLOGS_SEE_ALL:
        pass
    elif choice == Constants.Variables.BLOGS_SEE_FOLLOWING_BLOGS:
        pass
    elif choice == Constants.Variables.BLOGS_SEE_SUBSCRIBED_BLOGS:
        pass
    elif choice == Constants.Variables.BLOGS_SEE_FILTERED_BLOGS:
        _blogs_see_filtered_option()
    elif choice == Constants.Variables.BACK:
        pass


def _search_flow():
    """
    Start blogs flow
    :return: None
    """
    choice = show_list_options(choices=Constants.Variables.SEARCH_CHOICE_OPTIONS)
    if choice == Constants.Variables.SEARCH_CHOICE_USERS:
        pass
    elif choice == Constants.Variables.SEARCH_CHOICE_BLOGS:
        pass
    elif choice == Constants.Variables.SEARCH_CHOICE_BLOG_TAGS:
        pass
    elif choice == Constants.Variables.BACK:
        pass


def _start_user_flow():
    """
    Start the user flow when the user is authenticated
    :return: None
    """
    current_user = firebase.get_current_user_details()
    print(Constants.Messages.WELCOME_USER.format(
        current_user.display_name,
        Constants.Variables.PROJECT_NAME
    ))
    logout = False
    while not logout:
        choice = show_list_options(choices=Constants.Variables.USER_OPTION_CHOICES)
        if choice == Constants.Variables.MY_PROFILE_CHOICE:
            _profile_flow()
        elif choice == Constants.Variables.BLOGS_CHOICE:
            _blogs_flow()
        elif choice == Constants.Variables.SEARCH_CHOICE:
            _search_flow()
        elif choice == Constants.Variables.LOG_OUT_CHOICE:
            confirm_log_out = confirmation_question(message=Constants.Messages.LOGOUT_CONFIRMATION)
            if confirm_log_out:
                current_user = firebase.get_current_user_details()
                print(Constants.Messages.THANK_YOU_USER.format(
                    current_user.display_name,
                    Constants.Variables.PROJECT_NAME
                ))
                print(Constants.Messages.SEE_YOU_AGAIN)
                firebase.logout_user()
                logout = True


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
        _start_user_flow()
