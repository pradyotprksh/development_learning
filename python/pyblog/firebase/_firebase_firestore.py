"""
Firestore implementation for PyBlog
"""

from firebase_admin import firestore
from src import Constants


class _FirebaseFirestore:
    """A collection for Firebase Firestore"""

    def __init__(self, firebase_app):
        """
        A firestore implementation for PyBlog. Will be used for get, set, update and delete
        details from Firebase Firestore
        :param firebase_app: App object for the firebase admin
        """
        self.firestore_db = firestore.client(app=firebase_app)

    def update_user_details(self, uid, user_details, platform_details):
        """
        Update the user details on the user collection
        :param uid: User id of the current user
        :param user_details: User details of the current user
        :param platform_details: Platform details of the current user
        :return: None
        """
        full_details = {
            Constants.Firebase.Keys.EMAIL: user_details.email,
            Constants.Firebase.Keys.PHONE_NUMBER: user_details.phone_number,
            Constants.Firebase.Keys.PHOTO_URL: user_details.photo_url,
            Constants.Firebase.Keys.EMAIL_VERIFIED: user_details.email_verified,
            Constants.Firebase.Keys.USER_ID: uid,
            Constants.Firebase.Keys.SYSTEM_NAME: platform_details.system,
            Constants.Firebase.Keys.SYSTEM_ARCHITECTURE: platform_details.architecture,
            Constants.Firebase.Keys.SYSTEM_MACHINE: platform_details.machine,
            Constants.Firebase.Keys.SYSTEM_PROCESSOR: platform_details.processor,
            Constants.Firebase.Keys.SYSTEM_VERSION: platform_details.version,
            Constants.Firebase.Keys.PYTHON_VERSION: platform_details.python_version,
            Constants.Firebase.Keys.SYSTEM_IP_ADDRESS: platform_details.ip_address,
            Constants.Firebase.Keys.LAST_LOGGED_IN: user_details.last_logged_in,
        }

        self.firestore_db\
            .collection(Constants.Firebase.Collections.USERS)\
            .document(uid).set(full_details)

    def get_current_user_details(self, uid):
        """
        Get the current user details from Firestore
        :param uid: User id of the current user
        :return: UserDetails
        """

        return self.firestore_db.collection(Constants.Firebase.Collections.USERS).document(uid).get().to_dict()
