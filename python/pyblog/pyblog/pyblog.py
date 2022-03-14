"""A pyblog package"""

from firebase import Firebase
from src import Constants, UserInput, get_current_date, get_current_timestamp, \
    get_platform_details, get_date_from_timestamp
from .models import UserDetails, BlogDetails

_firebase = Firebase()
_user_input = UserInput()


def _sign_up_user():
    """
    Signup the user
    :return: None
    """

    # Get all user details
    name = _user_input.get_user_name()
    email_address = _user_input.get_user_email()
    phone_number = _user_input.get_user_phone_number()
    password = _user_input.get_password()
    _photo_path = _user_input.get_photo_path()
    if _photo_path is not None:
        photo_url = _firebase.upload_file(path=_photo_path)
    else:
        photo_url = Constants.URLs.DEFAULT_IMAGE

    # Create user details object
    user_details = UserDetails(
        display_name=name,
        email=email_address,
        email_verified=False,
        phone_number=phone_number,
        password=password,
        photo_url=photo_url
    )

    # Upload user details to firestore
    _firebase.create_user(user_details=user_details, platform_details=get_platform_details())


def _login_user():
    """
    Login the user
    :return: None
    """

    email_address = None
    phone_number = None

    email_option = _user_input.confirmation_question(message=Constants.Messages.EMAIL_OPTION)
    if email_option:
        email_address = _user_input.get_user_email()

    if email_address is None:
        phone_option = _user_input.confirmation_question(message=Constants.Messages.PHONE_OPTION)
        if phone_option:
            phone_number = _user_input.get_user_phone_number()

    if email_address is not None or phone_number is not None:
        _firebase.login_user(
            email=email_address,
            phone_number=phone_number,
            platform_details=get_platform_details()
        )


def _get_final_blog_data(title, subtitle, tags, blog, created_by, created_on):
    """
    Get the final blog details by updating the blog to the default template
    :param title: Title of the blog
    :param subtitle: Subtitle of the blog
    :param tags: Tags selected for the blog
    :param blog: Main blog string
    :param created_by: Created by username
    :param created_on: Created on
    :return: Updated blog: Updated the blog on
    """

    with open(Constants.Paths.DEFAULT_BLOG_TEMPLATE, "r") as default_blog:
        default_blog_data = default_blog.read()

        tags_string = ""
        for tag in tags:
            tags_string = f"{tag} | {tags_string}"
        tags_string = f"| {tags_string}"

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
    :return: None
    """
    tags = _firebase.get_blog_tags()
    title, subtitle, selected_tags, email_subscriber = _user_input.get_blog_initial_details(tags=tags)

    if Constants.Messages.USE_A_NEW_TAG in selected_tags:
        updated_tags = list(set(tags + selected_tags))
        _firebase.update_blog_tags(tags=updated_tags)
        selected_tags.remove(Constants.Messages.USE_A_NEW_TAG)

    user_details = _firebase.get_user_details_firestore()

    created_by_uid = user_details[Constants.Firebase.Keys.USER_ID]
    created_by_name = user_details[Constants.Firebase.Keys.DISPLAY_NAME]
    created_on = get_current_date()
    created_on_timestamp = get_current_timestamp()

    if start_type == Constants.Variables.USER_BLOGS_WRITE_BLOG_DEFAULT_TEMPLATE:
        blog = _user_input.start_blog_edit()
        final_blog = _get_final_blog_data(
            title=title,
            subtitle=subtitle,
            tags=selected_tags,
            blog=blog,
            created_by=created_by_name,
            created_on=created_on
        )
    else:
        blog = _user_input.start_blog_edit()
        final_blog = blog

    upload_ask = True
    while upload_ask:
        blog_write_action = _user_input.show_list_options(Constants.Variables.USER_BLOGS_WRITE_ACTIONS)
        if blog_write_action == Constants.Variables.UPLOAD_FIRESTORE:
            is_upload = _user_input.confirmation_question(Constants.Messages.CONFIRM_UPLOAD)
            if is_upload:
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
                    isDraft=False,
                    blog_id=""
                )
                _firebase.upload_blog(blog_details=blog_details)
                print(Constants.Messages.FIRESTORE_BLOG_UPLOAD_SUCCESS.format(title))
                upload_ask = False
        elif blog_write_action == Constants.Variables.SAVE_AS_DRAFT:
            blog_details = BlogDetails(
                title=title,
                subtitle=subtitle,
                tags=selected_tags,
                email_subscriber=email_subscriber,
                blog=blog,
                created_by_uid=created_by_uid,
                created_on=created_on_timestamp,
                views=0,
                likes=0,
                isDraft=True,
                blog_id=""
            )
            _firebase.upload_blog(blog_details=blog_details)
            print(Constants.Messages.DRAFT_BLOG_UPLOAD_SUCCESS.format(title))
            upload_ask = False
        else:
            is_discard = _user_input.confirmation_question(Constants.Messages.CONFIRM_DISCARD.format(title))
            if is_discard:
                print(Constants.Messages.BLOG_DISCARDED.format(title))
                upload_ask = False


def _user_write_blogs_flow():
    """
    Start current user write a blog flow
    :return: None
    """
    choice = _user_input.show_list_options(choices=Constants.Variables.USER_BLOGS_WRITE_BLOG_OPTIONS)
    if choice == Constants.Variables.USER_BLOGS_WRITE_BLOG_DEFAULT_TEMPLATE:
        _write_blog_option(start_type=Constants.Variables.USER_BLOGS_WRITE_BLOG_DEFAULT_TEMPLATE)
        _user_blogs_flow()
    elif choice == Constants.Variables.USER_BLOGS_WRITE_BLOG_JUST_WRITE:
        _write_blog_option(start_type=Constants.Variables.USER_BLOGS_WRITE_BLOG_JUST_WRITE)
        _user_blogs_flow()
    elif choice == Constants.Variables.BACK:
        _user_blogs_flow()


def _edit_selected_draft(draft):
    """
    Edit the selected draft
    :param draft: Draft to be edited
    """
    print(Constants.Messages.EDITING_DRAFT_BLOG.format(draft.title))

    new_title = draft.title
    new_subtitle = draft.subtitle
    new_tags = draft.tags
    new_email_subscriber = draft.email_subscriber
    new_edit_blog = draft.blog
    new_is_draft = draft.isDraft

    edit_title = _user_input.confirmation_question(Constants.Messages.CONFIRM_EDIT_BLOG_TITLE)
    if edit_title:
        new_title = _user_input.get_blog_title()

    edit_subtitle = _user_input.confirmation_question(Constants.Messages.CONFIRM_EDIT_BLOG_SUBTITLE)
    if edit_subtitle:
        new_subtitle = _user_input.get_blog_subtitle()

    edit_tags = _user_input.confirmation_question(Constants.Messages.CONFIRM_EDIT_BLOG_TAGS)
    if edit_tags:
        tags = _firebase.get_blog_tags()
        new_tags = _user_input.get_selected_tags(
            tags=_firebase.get_blog_tags(),
            checked_tags=draft.tags
        )

        if Constants.Messages.USE_A_NEW_TAG in new_tags:
            updated_tags = list(set(tags + new_tags))
            _firebase.update_blog_tags(tags=updated_tags)
            new_tags.remove(Constants.Messages.USE_A_NEW_TAG)

    edit_email_subscriber = _user_input.confirmation_question(Constants.Messages.CONFIRM_EDIT_BLOG_EMAIL_SUBSCRIBER)
    if edit_email_subscriber:
        new_email_subscriber = _user_input.confirmation_question(
            Constants.Messages.BLOG_EMAIL_SUBSCRIBERS_QUESTION
        )

    edit_blog = _user_input.confirmation_question(Constants.Messages.CONFIRM_EDIT_BLOG_BLOG)
    if edit_blog:
        with open(Constants.Paths.EDIT_BLOG, "w") as new_blog:
            new_blog.write(draft.blog)
        _user_input.edit_written_blog(filename=Constants.Paths.EDIT_BLOG)
        with open(Constants.Paths.EDIT_BLOG, "r") as new_blog:
            new_edit_blog = new_blog.read()

    edit_is_draft = _user_input.confirmation_question(Constants.Messages.CONFIRM_EDIT_BLOG_IS_DRAFT)
    if edit_is_draft:
        new_is_draft = edit_is_draft

    if edit_title or edit_subtitle or edit_tags or edit_email_subscriber or edit_blog or edit_is_draft:
        user_details = _firebase.get_user_details_firestore()

        created_by_uid = user_details[Constants.Firebase.Keys.USER_ID]
        created_on_timestamp = get_current_timestamp()

        if edit_is_draft:
            created_by_name = user_details[Constants.Firebase.Keys.DISPLAY_NAME]
            created_on = get_current_date()

            choice = _user_input.show_list_options(choices=Constants.Variables.USER_BLOGS_WRITE_BLOG_OPTIONS)

            if choice == Constants.Variables.USER_BLOGS_WRITE_BLOG_DEFAULT_TEMPLATE:
                new_edit_blog = _get_final_blog_data(
                    title=new_title,
                    subtitle=new_subtitle,
                    tags=new_tags,
                    blog=new_edit_blog,
                    created_by=created_by_name,
                    created_on=created_on
                )

        blog_details = BlogDetails(
            title=new_title,
            subtitle=new_subtitle,
            tags=new_tags,
            email_subscriber=new_email_subscriber,
            blog=new_edit_blog,
            created_by_uid=created_by_uid,
            created_on=created_on_timestamp,
            views=0,
            likes=0,
            isDraft=new_is_draft,
            blog_id=draft.blog_id
        )

        _firebase.upload_blog(blog_details=blog_details, document_id=draft.blog_id)

    _show_user_blog_drafts()


def _show_user_blog_drafts():
    """
    Shows the list of all the blogs for the current user which are saved as draft.
    :return: None
    """
    drafts = _firebase.get_current_user_blog_drafts()
    only_titles = []
    for draft in drafts:
        only_titles.append(draft.title)
    only_titles.append(Constants.Variables.BACK)
    choice = _user_input.show_list_options(choices=only_titles)
    if choice is Constants.Variables.BACK:
        _user_blogs_flow()
    else:
        selected_title_index = only_titles.index(choice)
        selected_draft = drafts[selected_title_index]
        draft_choice = _user_input.show_list_options(choices=Constants.Variables.USER_BLOGS_EDIT_DRAFT_BLOG_OPTIONS)

        if draft_choice is Constants.Variables.EDIT:
            _edit_selected_draft(draft=selected_draft)
        elif draft_choice is Constants.Variables.DISCARD:
            confirm_delete = _user_input.confirmation_question(
                Constants.Messages.CONFIRM_DELETE_BLOG.format(selected_draft.title)
            )
            if confirm_delete:
                _firebase.delete_blog(document_id=selected_draft.blog_id)
            _user_blogs_flow()
        else:
            _show_user_blog_drafts()


def _get_final_blog_to_show(blog_details):
    """
    Get final blog details to be shown
    :param blog_details: Details of the blog
    :return: Blog
    """

    final_blog = blog_details.blog

    if Constants.Variables.REPLACE_BLOG_VIEWS not in final_blog or \
            Constants.Variables.REPLACE_BLOG_LIKES not in final_blog:
        current_user = _firebase.get_current_user_details()
        final_blog = _get_final_blog_data(
            title=blog_details.title, subtitle=blog_details.subtitle, tags=blog_details.tags,
            blog=final_blog, created_by=current_user.display_name,
            created_on=get_date_from_timestamp(blog_details.created_on)
        )

    final_blog = final_blog.replace(
        Constants.Variables.REPLACE_BLOG_VIEWS,
        f"{blog_details.views}"
    ).replace(
        Constants.Variables.REPLACE_BLOG_LIKES,
        f"{blog_details.likes}"
    )

    return final_blog


def _show_user_blog_all():
    """
    Get all blog for the current user
    :return: None
    """

    current_blogs = _firebase.get_current_user_blogs()
    only_titles = []
    for blog in current_blogs:
        only_titles.append(blog.title)
    only_titles.append(Constants.Variables.BACK)
    choice = _user_input.show_list_options(choices=only_titles)
    if choice is Constants.Variables.BACK:
        _user_blogs_flow()
    else:
        selected_title_index = only_titles.index(choice)
        selected_blog = current_blogs[selected_title_index]

        final_blog = _get_final_blog_to_show(blog_details=selected_blog)
        print(final_blog.__str__())

        _user_input.press_any_key_to_continue(message=Constants.Messages.PRESS_TO_CONTINUE)


def _user_blogs_flow():
    """
    Start current user blogs flow
    :return: None
    """
    choice = _user_input.show_list_options(choices=Constants.Variables.USER_BLOGS_OPTIONS)
    if choice == Constants.Variables.USER_BLOGS_SHOW_BLOGS:
        _show_user_blog_all()
        _user_blogs_flow()
    elif choice == Constants.Variables.USER_BLOGS_WRITE_BLOG:
        _user_write_blogs_flow()
    elif choice == Constants.Variables.USER_BLOGS_EDIT_DRAFT_BLOG:
        _show_user_blog_drafts()
    elif choice == Constants.Variables.USER_BLOGS_FAV_BLOG:
        _profile_flow()
    elif choice == Constants.Variables.BACK:
        _profile_flow()


def _user_actions_flow():
    """
    Start profile actions flow for current user
    :return: None
    """
    choice = _user_input.show_list_options(choices=Constants.Variables.USER_ACTIONS_OPTIONS)
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
    choice = _user_input.show_list_options(choices=Constants.Variables.USER_EDIT_OPTIONS)
    if choice == Constants.Variables.USER_EDIT_NAME:
        name = _user_input.get_user_name()
        confirmation = _user_input.confirmation_question(Constants.Messages.CONFIRM_CHANGE.format(
            Constants.Messages.NAME,
            name
        ))
        if confirmation:
            _firebase.update_user(platform_details=get_platform_details(), name=name)
        _profile_flow()
    elif choice == Constants.Variables.USER_EDIT_DISPLAY_IMAGE:
        _photo_path = _user_input.get_photo_path()
        if _photo_path is not None:
            photo_url = _firebase.upload_file(path=_photo_path)
            _firebase.update_user(platform_details=get_platform_details(), photo_url=photo_url)
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
    user_details = _firebase.get_user_details_firestore()
    string_user_details = Constants.Messages.USER_DETAILS.format(
        user_details[Constants.Firebase.Keys.DISPLAY_NAME],
        user_details[Constants.Firebase.Keys.EMAIL],
        user_details[Constants.Firebase.Keys.PHONE_NUMBER],
        user_details[Constants.Firebase.Keys.PHOTO_URL],
    )
    print(string_user_details)
    _user_input.press_any_key_to_continue(message=Constants.Messages.PRESS_TO_CONTINUE)
    _profile_flow()


def _profile_flow():
    """
    Start profile flow of the user
    :return: None
    """
    choice = _user_input.show_list_options(choices=Constants.Variables.USER_PROFILE_OPTION_CHOICES)
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
    choice = _user_input.show_list_options(choices=Constants.Variables.BLOGS_SEE_FILTERED_BLOGS_OPTIONS)
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
    choice = _user_input.show_list_options(choices=Constants.Variables.BLOGS_CHOICE_OPTIONS)
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
    choice = _user_input.show_list_options(choices=Constants.Variables.SEARCH_CHOICE_OPTIONS)
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
    current_user = _firebase.get_current_user_details()
    print(Constants.Messages.WELCOME_USER.format(
        current_user.display_name,
        Constants.Variables.PROJECT_NAME
    ))
    logout = False
    while not logout:
        choice = _user_input.show_list_options(choices=Constants.Variables.USER_OPTION_CHOICES)
        if choice == Constants.Variables.MY_PROFILE_CHOICE:
            _profile_flow()
        elif choice == Constants.Variables.BLOGS_CHOICE:
            _blogs_flow()
        elif choice == Constants.Variables.SEARCH_CHOICE:
            _search_flow()
        elif choice == Constants.Variables.LOG_OUT_CHOICE:
            confirm_log_out = _user_input.confirmation_question(message=Constants.Messages.LOGOUT_CONFIRMATION)
            if confirm_log_out:
                current_user = _firebase.get_current_user_details()
                print(Constants.Messages.THANK_YOU_USER.format(
                    current_user.display_name,
                    Constants.Variables.PROJECT_NAME
                ))
                print(Constants.Messages.SEE_YOU_AGAIN)
                _firebase.logout_user()
                logout = True


def initiate_user_authentication():
    """
    Initiate the user authentication for PyBlog
    :return: None
    """

    # Ask for authentication type
    auth_type = _user_input.confirmation_question(message=Constants.Messages.AUTH_TYPE_QUESTION)
    if auth_type:
        _sign_up_user()
    else:
        _login_user()

    # Check if user details is available after authentication
    if _firebase.get_current_user_details() is not None:
        _start_user_flow()
