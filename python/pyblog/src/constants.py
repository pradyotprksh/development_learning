"""A collection of all constants"""


class Constants:
    """A constant class for PyBlog"""

    class Messages:
        """A constant class for messages"""
        CONFIRM_DELETE_BLOG = "Are you sure you want to delete \"{}\"?"
        CONFIRM_EDIT_BLOG_IS_DRAFT = "Do you want to publish the blog?"
        CONFIRM_EDIT_BLOG_EMAIL_SUBSCRIBER = "Do you want to change the email the subscriber settings?"
        CONFIRM_EDIT_BLOG_BLOG = "Do you want to edit the content of blog?"
        CONFIRM_EDIT_BLOG_TAGS = "Do you want to change the blog tags?"
        CONFIRM_EDIT_BLOG_SUBTITLE = "Do you want to change blog subtitle?"
        CONFIRM_EDIT_BLOG_TITLE = "Do you want to change blog title?"
        DRAFT_BLOG_UPLOAD_SUCCESS = "Your blog \"{}\" is saved as a draft."
        FIRESTORE_BLOG_UPLOAD_SUCCESS = "Your blog \"{}\" is available online for others to read."
        BLOG_DISCARDED = "Blog \"{}\" is discarded. And not saved in our system."
        CONFIRM_DISCARD = "Are you sure you want to discard the blog \"{}\"?"
        CONFIRM_UPLOAD = "Are you sure you want to upload the blog?"
        NAME = "name"
        WELCOME = "Welcome to {}"
        AUTH_TYPE_QUESTION = "Are you a new user?"
        NAME_QUESTION = "What\'s your name?"
        EMAIL_QUESTION = "What\'s your email address?"
        PHONE_QUESTION = "What\'s your phone number?"
        PHOTO_CONFIRMATION = "Do you want to add a photo to your profile?"
        PHOTO_QUESTION = "Please provide the path for your photo"
        PASSWORD_QUESTION = "Add a password for your account"
        INVALID_EMAIL = "Please provide a valid email address. For example, abc@gog.com"
        INVALID_NAME = "Please provide a valid name"
        INVALID_PHOTO = "Please provide a valid photo"
        INVALID_PHONE_NUMBER = "Please provide a valid phone number. For example, +9194620XXXX4"
        INVALID_PASSWORD = "Must contain 1 number (0-9), 1 uppercase letters, " \
                           "1 lowercase letters," \
                           " 1 non-alpha numeric number and 8-16 characters with " \
                           "no space"
        EMAIL_OPTION = "Do you want to login with email address?"
        PHONE_OPTION = "Do you want to login with phone number?"
        OPTION_QUESTION = "Please select an option"
        WELCOME_USER = "Welcome {} to {}"
        THANK_YOU_USER = "Thank you {} for using {}"
        SEE_YOU_AGAIN = "We hope to see you again."
        LOGOUT_CONFIRMATION = "Are you sure you want to log out?"
        SHALL_CONTINUE = "Shall we continue?"
        PRESS_TO_CONTINUE = "Press any key to continue"
        USER_DETAILS = "Details are as below:" \
                       "\nNAME: {}" \
                       "\nEMAIL ADDRESS: {}" \
                       "\nPHONE NUMBER: {}" \
                       "\nPROFILE PICTURE: {}"
        WRITE_BLOG = "Please write your new blog, press enter when done"
        CONFIRM_CHANGE = "Are you sure you want to change your {} to {}"
        SOMETHING_WENT_WRONG = "Something went wrong. Please try again."
        BLOG_TITLE_QUESTION = "Please provide the blog title"
        BLOG_SUBTITLE_QUESTION = "Please provide the blog subtitle"
        BLOG_TAGS_QUESTION = "Please select the blog tags"
        BLOG_EMAIL_SUBSCRIBERS_QUESTION = "Do you want to email your subscribers about the blog?"
        INVALID_BLOG_TITLE = "Blog title must be grater than 5 characters"
        INVALID_BLOG_SUBTITLE = "Blog subtitle must be grater than 5 characters"
        INVALID_CHECKBOX_SELECTION = "You must choose at least one option."
        VIEWS = "views"
        LIKES = "likes"
        USE_A_NEW_TAG = "Add a new tag"
        PROVIDE_BLOG_TAG = "Please provide tag for your blog. <For multiple tags separate it with comma(,)>"
        VALID_BLOG_TAG = "Please provide a valid tag name. For multiple separate it with comma(,)"
        EDITING_DRAFT_BLOG = "You are going to edit your draft titled \"{}\""

    class Paths:
        """A constant class for paths"""
        FIREBASE_CRED_FILE_PATH = "data/confidential/serviceAccountKey.json"
        DEFAULT_BLOG_TEMPLATE = "data/BLOGS_DEFAULT_TEMPLATE.txt"
        EDIT_BLOG = "data/EDIT_BLOG.txt"

    class Variables:
        """A constant class for variables"""
        PROJECT_NAME = "PyBlog"
        FIREBASE_STORAGE_BUCKET_NAME = "pyblog-f1a61.appspot.com"
        YES_NO_CHOICE = ["Yes", "No"]
        INPUT_QUESTION = "input"
        LIST_QUESTION = "list"
        EDITOR_QUESTION = "editor"
        CONFIRM_QUESTION = "confirm"
        PASSWORD_QUESTION = "password"
        CHECKBOX_QUESTION = "checkbox"
        EMAIL_REGEX = r"(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$)"
        # This regex is only for Indian phone number
        # Taken from https://regexpattern.com/phone-number/
        PHONE_NUMBER_REGEX = r"((\+*)((0[ -]*)*|((91 )*))((\d{12})+|(\d{10})+))|\d{5}([- ]*)\d{6}"
        USER_NAME_REGEX = r"(^[a-zA-Z][a-zA-Z\s]{0,20}[a-zA-Z]$)"
        PASSWORD_REGEX = r"^(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\w\d\s:])([^\s]){8,16}$"
        IMAGES_TYPES = ["jpg", "jpeg", "jfif", "pjpeg", "pjp", "png"]

        # Default Blog
        REPLACE_BLOG_TITLE = "###TITLE"
        REPLACE_BLOG_SUBTITLE = "###SUBTITLE"
        REPLACE_BLOG_DATA = "###BLOG_DATA"
        REPLACE_BLOG_CREATED_BY = "###CREATED_BY"
        REPLACE_BLOG_CREATED_ON = "###CREATED_ON"
        REPLACE_BLOG_VIEWS = "###VIEWS"
        REPLACE_BLOG_LIKES = "###LIKES"
        REPLACE_BLOG_TAGS = "##TAGS"

        # Options
        BACK = "Back"
        MY_PROFILE_CHOICE = "Profile"
        BLOGS_CHOICE = "Blog"
        SEARCH_CHOICE = "Search"
        LOG_OUT_CHOICE = "Log Out"
        USER_PROFILE_DETAILS = "My Profile Details"
        USER_BLOGS = "My Blogs Options"
        USER_ACTIONS = "My Profile Actions"
        USER_EDIT = "Edit Profile Details"
        USER_FOLLOWERS = "My Followers"
        USER_FOLLOWING = "Me Following"
        USER_SUBSCRIBED = "Me Subscribed"
        USER_SUBSCRIBER = "My Subscribers"
        USER_BLOGS_SHOW_BLOGS = "Show All My Blogs"
        USER_BLOGS_WRITE_BLOG = "Write A New Blog"
        USER_BLOGS_EDIT_DRAFT_BLOG = "See Your Drafts"
        USER_BLOGS_FAV_BLOG = "My Favourite Blogs"
        USER_ACTIONS_VERIFY_EMAIL_ADDRESS = "Verify My Email Address"
        USER_ACTIONS_CHANGE_PASSWORD = "Change My Password"
        USER_ACTIONS_DELETE_ACCOUNT = "Delete My Account"
        USER_EDIT_NAME = "Change My Name"
        USER_EDIT_DISPLAY_IMAGE = "Change My Display Image"
        USER_BLOGS_WRITE_BLOG_DEFAULT_TEMPLATE = "Use Default Template"
        USER_BLOGS_WRITE_BLOG_JUST_WRITE = "Just Write A Blog"
        BLOGS_SEE_ALL = "See All Blogs"
        BLOGS_SEE_FOLLOWING_BLOGS = "See Blogs From Following Users"
        BLOGS_SEE_SUBSCRIBED_BLOGS = "See Blogs From Subscribed Users"
        BLOGS_SEE_FILTERED_BLOGS = "Filter Blogs"
        BLOGS_SEE_FILTERED_BLOGS_RECENT = "Most Recent Blogs"
        BLOGS_SEE_FILTERED_BLOGS_LIKED = "Most Liked Blogs"
        BLOGS_SEE_FILTERED_BLOGS_VIEWED = "Most Viewed Blogs"
        SEARCH_CHOICE_USERS = "Users"
        SEARCH_CHOICE_BLOG_TAGS = "By Blog Tags"
        SEARCH_CHOICE_BLOGS = "Blogs"
        UPLOAD_FIRESTORE = "Upload Online"
        SAVE_AS_DRAFT = "Save As A Draft"
        DISCARD = "Discard"
        EDIT = "Edit"

        # Main Options
        USER_OPTION_CHOICES = [MY_PROFILE_CHOICE, BLOGS_CHOICE, SEARCH_CHOICE, LOG_OUT_CHOICE]

        # MY_PROFILE_CHOICE Options
        USER_PROFILE_OPTION_CHOICES = [USER_PROFILE_DETAILS, USER_ACTIONS, USER_EDIT, USER_FOLLOWERS,
                                       USER_FOLLOWING, USER_SUBSCRIBED, USER_SUBSCRIBER, USER_BLOGS, BACK]
        # --USER_BLOGS Options
        USER_BLOGS_OPTIONS = [USER_BLOGS_SHOW_BLOGS, USER_BLOGS_WRITE_BLOG, USER_BLOGS_EDIT_DRAFT_BLOG,
                              USER_BLOGS_FAV_BLOG, BACK]
        # ----USER_BLOGS_WRITE_BLOG Options
        USER_BLOGS_WRITE_BLOG_OPTIONS = [USER_BLOGS_WRITE_BLOG_DEFAULT_TEMPLATE, USER_BLOGS_WRITE_BLOG_JUST_WRITE, BACK]
        # ------USER_BLOGS_WRITE_ACTIONS Options
        USER_BLOGS_WRITE_ACTIONS = [UPLOAD_FIRESTORE, SAVE_AS_DRAFT, DISCARD, BACK]
        # ----USER_BLOGS_EDIT_DRAFT_BLOG Options
        USER_BLOGS_EDIT_DRAFT_BLOG_OPTIONS = [EDIT, DISCARD, BACK]
        # --USER_ACTIONS Options
        USER_ACTIONS_OPTIONS = [USER_ACTIONS_VERIFY_EMAIL_ADDRESS, USER_ACTIONS_CHANGE_PASSWORD,
                                USER_ACTIONS_DELETE_ACCOUNT, BACK]
        # --USER_EDIT Options
        USER_EDIT_OPTIONS = [USER_EDIT_NAME, USER_EDIT_DISPLAY_IMAGE, BACK]

        # BLOGS_CHOICE Options
        BLOGS_CHOICE_OPTIONS = [BLOGS_SEE_ALL, BLOGS_SEE_FOLLOWING_BLOGS, BLOGS_SEE_SUBSCRIBED_BLOGS,
                                BLOGS_SEE_FILTERED_BLOGS, BACK]
        # --BLOGS_SEE_FILTERED_BLOGS Options
        BLOGS_SEE_FILTERED_BLOGS_OPTIONS = [BLOGS_SEE_FILTERED_BLOGS_RECENT, BLOGS_SEE_FILTERED_BLOGS_LIKED,
                                            BLOGS_SEE_FILTERED_BLOGS_VIEWED, BACK]

        # SEARCH_CHOICE Options
        SEARCH_CHOICE_OPTIONS = [SEARCH_CHOICE_USERS, SEARCH_CHOICE_BLOGS, SEARCH_CHOICE_BLOG_TAGS, BACK]

    class URLs:
        """A constant class for URLs"""
        IP_ADDRESS = "https://api64.ipify.org?format=json"
        DEFAULT_IMAGE = "https://firebasestorage.googleapis.com/v0/b/pyblog-f1a61." \
                        "appspot.com/o/default_image.png?alt" \
                        "=media&token=3512d6cf-af03-48cc-b9a7-c8cac855e257 "

    class Firebase:
        """A constant class for Firebase collections/documents/keys"""

        class Collections:
            """A constants for only collections"""
            USERS = "users"
            BLOGS = "blogs"
            TAGS = "tags"

        class Documents:
            """A constants for only documents"""
            TAGS = "tags"

        class Keys:
            """A constants for only keys"""
            DISPLAY_NAME = "display_name"
            EMAIL = "email"
            PHONE_NUMBER = "phone_number"
            PHOTO_URL = "photo_url"
            PASSWORD = "password"
            EMAIL_VERIFIED = "email_verified"
            SYSTEM_NAME = "system_name"
            USER_ID = "uid"
            SYSTEM_ARCHITECTURE = "system_architecture"
            SYSTEM_MACHINE = "system_machine"
            SYSTEM_PROCESSOR = "system_processor"
            SYSTEM_VERSION = "system_version"
            PYTHON_VERSION = "python_version"
            SYSTEM_IP_ADDRESS = "system_ip_address"
            LAST_LOGGED_IN = "last_logged_in"
            TITLE = "title"
            SUBTITLE = "subtitle"
            TAGS = "tags"
            EMAIL_SUBSCRIBER = "email_subscriber"
            BLOG = "blog_data"
            CREATED_BY = "created_by"
            CREATED_ON = "created_on"
            VIEWS = "views"
            LIKES = "likes"
            IS_DRAFT = "isDraft"

        class Operators:
            """A constants for operators"""
            IS_EQUAL = "=="
