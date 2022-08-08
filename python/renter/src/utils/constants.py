"""
Constant module

A module for constants which will be used to add all the constants needed for the project.

This will help in not hard coding the values needed at many places.
"""
# Type of the user
USER_TYPE = ["owner", "renter"]
# Path for the file which contains the mongo db
MONGO_DB_DETAILS_FILE = "./data/confidential/mongo_db_details"
# Path for the file which contains the firebase admin account details
FIREBASE_SERVICE_ACCOUNT = "./data/confidential/renterServiceAccountKey.json"
# Default error message
DEFAULT_ERROR_MESSAGE = "Something went wrong with parsing the request. Please check and try again."
# Default error message for data not present
DEFAULT_FORM_DATA_NOT_AVAILABLE = "Missing your {} in the form"


class Keys:
    """A Keys class which contains all the keys used everywhere"""
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


# Error Message
ERROR_RESPONSE_MESSAGES = {
    Keys.User.first_name: DEFAULT_FORM_DATA_NOT_AVAILABLE,
    Keys.User.email_address: DEFAULT_FORM_DATA_NOT_AVAILABLE
}
