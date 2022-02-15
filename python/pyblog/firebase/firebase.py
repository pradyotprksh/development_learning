"""
A single place to handle all the firebase work
"""

import firebase_admin
from firebase_admin import credentials, auth
from loguru import logger
from src import Constants
from ._firebase_firestore import _FirebaseFirestore
from ._firebase_storage import _FirebaseStorage


class Firebase:
    """A collection for Firebase"""

    def __init__(self):
        """
        Initialize the firebase admin and all the other packages for usage.
        :return: None
        """

        cred = credentials.Certificate(cert=Constants.Paths.FIREBASE_CRED_FILE_PATH)
        self._firebase_app = firebase_admin.initialize_app(
            credential=cred,
            name=Constants.Variables.PROJECT_NAME
        )
        self._pyblog_firestore = _FirebaseFirestore(firebase_app=self._firebase_app)
        self._pyblog_storage = _FirebaseStorage(firebase_app=self._firebase_app)
        self.current_user = None

    def create_user(self, user_details, platform_details):
        """
        Create a user after
        :param user_details: Details of the user
        :param platform_details: Details of the current platform
        :return: None
        """

        try:
            self.current_user = auth.create_user(
                email=user_details.email,
                email_verified=user_details.email_verified,
                phone_number=user_details.phone_number,
                password=user_details.password,
                display_name=user_details.display_name,
                photo_url=user_details.photo_url,
                disabled=False,
                app=self._firebase_app
            )

            self._pyblog_firestore.update_user_details(
                uid=self.current_user.uid,
                user_details=user_details,
                platform_details=platform_details
            )
        except Exception as e:
            logger.debug(e.__str__())
