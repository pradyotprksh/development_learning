"""
A single place to handle all the firebase work
"""

import firebase_admin
from firebase_admin import credentials, auth
from loguru import logger
from src import Constants
from pyblog import UserDetails
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
        Create a user
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

            self._generate_email_for_verification()
        except Exception as e:
            logger.debug(e.__str__())

    def login_user(self, platform_details, email=None, phone_number=None):
        """
        Authenticate the user based on either email or password
        :param platform_details: Details of the current platform
        :param email: Email id of the user
        :param phone_number: Phone number of the user
        :return: None
        """

        try:
            if email is not None:
                self.current_user = auth.get_user_by_email(
                    email=email,
                    app=self._firebase_app
                )
            else:
                self.current_user = auth.get_user_by_phone_number(
                    phone_number=phone_number,
                    app=self._firebase_app
                )

            if self.current_user is not None:
                user_details = UserDetails(
                    display_name=self.current_user.display_name,
                    email=self.current_user.email,
                    phone_number=self.current_user.phone_number,
                    photo_url=self.current_user.photo_url,
                    password=None,
                    email_verified=self.current_user.email_verified,
                )

                self._pyblog_firestore.update_user_details(
                    uid=self.current_user.uid,
                    user_details=user_details,
                    platform_details=platform_details
                )
        except Exception as e:
            logger.debug(e.__str__())

    def logout_user(self):
        """
        Log out the current user
        :return: None
        """
        self.current_user = None

    def _generate_email_for_verification(self):
        """
        Generate an email for the current logged-in user for verification
        :return: None
        """
        if self.current_user is not None and self.current_user.email_verified is False:
            auth.generate_email_verification_link(email=self.current_user.email, app=self._firebase_app)
