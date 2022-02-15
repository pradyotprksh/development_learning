"""A collection of all constants"""


class Constants:
    """A constant class for PyBlog"""

    class Messages:
        """A constant class for messages"""
        WELCOME = "Welcome to {}"

    class Paths:
        """A constant class for paths"""
        FIREBASE_CRED_FILE_PATH = "data/confidential/serviceAccountKey.json"
        MAIN_ICON = r"data/images/main_icon.ico"

    class Variables:
        """A constant class for variables"""
        PROJECT_NAME = "PyBlog"
        FIREBASE_STORAGE_BUCKET_NAME = "pyblog-f1a61.appspot.com"

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
