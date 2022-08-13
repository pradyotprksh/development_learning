"""
Constant module

A module for constants which will be used to add all the constants needed for the project.

This will help in not hard coding the values needed at many places.
"""
# Type of the user
USER_TYPE = ["owner", "renter"]
# Path for the file which contains the mongo db
MONGO_DB_DETAILS_FILE = "./data/confidential/mongo_db_details"
# Path for information HTML file
INFORMATION_HTML_FILE = "information.html"
# Path for the file which contains the firebase admin account details
FIREBASE_SERVICE_ACCOUNT = "./data/confidential/renterServiceAccountKey.json"
# Default error message
DEFAULT_ERROR_MESSAGE = "Something went wrong with parsing the request. Please check and try again."
# Default error message for data not present
DEFAULT_FORM_DATA_NOT_AVAILABLE = "Missing your {} in the form"
# Default error message for data invalid
DEFAULT_INVALID_DATA = "Invalid {}, please provide a valid one."


class Endpoints:
    """An endpoint constant class which contains all the endpoints"""

    class Renter:
        """A renter endpoint lists"""
        home = "/"
        terms_and_condition = "/terms_condition/<string:user_type>"
        information = "/information"


class Keys:
    """A Keys class which contains all the keys used everywhere"""

    class Messages:
        """A set of keys related to messages"""
        invalid_input = "invalid_input"
        renter_details = "renter_details"
        invalid_user_type = "invalid_user_type"
        terms_condition_message = "terms_condition_message"
        information_message = "information_message"
        user_not_found = "user_not_found"
        user_found = "user_found"
        user_already_available = "user_already_available"
        user_created = "user_created"

    class User:
        """A set of keys related to the User section"""
        collection_name = "user"
        user_id = "user_id"
        first_name = "first_name"
        last_name = "last_name"
        permanent_address = "permanent_address"
        date_of_birth = "date_of_birth"
        email_address = "email_address"
        profession = "profession"
        phone_number = "phone_number"
        profile_pic_url = "profile_pic_url"


# Dictionary of messages
MESSAGES_LIST = {
    Keys.User.first_name: DEFAULT_FORM_DATA_NOT_AVAILABLE,
    Keys.User.email_address: DEFAULT_FORM_DATA_NOT_AVAILABLE,
    Keys.Messages.invalid_input: DEFAULT_INVALID_DATA,
    Keys.Messages.renter_details: "Hello User to Renter, this is a Python created RESTFUL API which is used by "
                                  "our Renter Android application. If you want to you can also use this and make "
                                  "requests.",
    Keys.Messages.invalid_user_type: "{} is not a valid user type, Please provide a valid one.",
    Keys.Messages.terms_condition_message: "Hello {}, this is to inform you that you will be virtually signing our "
                                           "terms and condition.",
    Keys.Messages.information_message: "Owner and Renter Details are as below.",
    Keys.Messages.user_not_found: "Asked user is not present. Please create one before asking for it.",
    Keys.Messages.user_found: "User found",
    Keys.Messages.user_already_available: "{} exists, you are trying to create one which is already there.",
    Keys.Messages.user_created: "User created",
}
