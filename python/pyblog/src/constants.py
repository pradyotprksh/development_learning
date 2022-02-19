"""A collection of all constants"""


class Constants:
    """A constant class for PyBlog"""

    class Messages:
        """A constant class for messages"""
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

    class Paths:
        """A constant class for paths"""
        FIREBASE_CRED_FILE_PATH = "data/confidential/serviceAccountKey.json"
        MAIN_ICON = r"data/images/main_icon.ico"

    class Variables:
        """A constant class for variables"""
        PROJECT_NAME = "PyBlog"
        FIREBASE_STORAGE_BUCKET_NAME = "pyblog-f1a61.appspot.com"
        YES_NO_CHOICE = ["Yes", "No"]
        INPUT_QUESTION = "input"
        LIST_QUESTION = "list"
        CONFIRM_QUESTION = "confirm"
        PASSWORD_QUESTION = "password"
        EMAIL_REGEX = r"(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$)"
        # This regex is only for Indian phone number
        # Taken from https://regexpattern.com/phone-number/
        PHONE_NUMBER_REGEX = r"((\+*)((0[ -]*)*|((91 )*))((\d{12})+|(\d{10})+))|\d{5}([- ]*)\d{6}"
        USER_NAME_REGEX = r"(^[a-zA-Z][a-zA-Z\s]{0,20}[a-zA-Z]$)"
        PASSWORD_REGEX = r"^(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\w\d\s:])([^\s]){8,16}$"
        IMAGES_TYPES = ["jpg", "jpeg", "jfif", "pjpeg", "pjp", "png"]
        DEFAULT_IMAGE = "https://firebasestorage.googleapis.com/v0/b/pyblog-f1a61." \
                        "appspot.com/o/default_image.png?alt" \
                        "=media&token=3512d6cf-af03-48cc-b9a7-c8cac855e257 "
        MY_PROFILE_CHOICE = "My Profile"
        BLOGS_CHOICE = "Show All Blogs"
        SEARCH_CHOICE = "Search"
        LOG_OUT_CHOICE = "Log Out"
        USER_OPTION_CHOICES = [MY_PROFILE_CHOICE, BLOGS_CHOICE, SEARCH_CHOICE, LOG_OUT_CHOICE]

    class URLs:
        """A constant class for URLs"""
        IP_ADDRESS = "https://api64.ipify.org?format=json"

    class Firebase:
        """A constant class for Firebase collections/documents/keys"""

        class Collections:
            """A constants for only collections"""
            USERS = "users"

        class Documents:
            """A constants for only documents"""

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
