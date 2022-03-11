"""
A single place to handle all the firebase work
"""

import firebase_admin
from firebase_admin import credentials, auth
from loguru import logger
from src import Constants
from pyblog import UserDetails, BlogDetails
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
        self._current_user = None

    def create_user(self, user_details, platform_details):
        """
        Create a user
        :param user_details: Details of the user
        :param platform_details: Details of the current platform
        :return: None
        """

        try:
            self._current_user = auth.create_user(
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
                uid=self._current_user.uid,
                user_details=user_details,
                platform_details=platform_details
            )

            self._generate_email_for_verification()
        except Exception as exception:
            logger.debug(exception.__str__())

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
                self._current_user = auth.get_user_by_email(
                    email=email,
                    app=self._firebase_app
                )
            else:
                self._current_user = auth.get_user_by_phone_number(
                    phone_number=phone_number,
                    app=self._firebase_app
                )

            if self._current_user is not None:
                user_details = UserDetails(
                    display_name=self._current_user.display_name,
                    email=self._current_user.email,
                    phone_number=self._current_user.phone_number,
                    photo_url=self._current_user.photo_url,
                    password=None,
                    email_verified=self._current_user.email_verified,
                )

                self._pyblog_firestore.update_user_details(
                    uid=self._current_user.uid,
                    user_details=user_details,
                    platform_details=platform_details
                )

                if self._current_user.email_verified is False:
                    self._generate_email_for_verification()
        except Exception as exception:
            logger.debug(exception.__str__())

    def logout_user(self):
        """
        Log out the current user
        :return: None
        """
        self._current_user = None

    def _generate_email_for_verification(self):
        """
        Generate an email for the current logged-in user for verification
        :return: None
        """
        if self._current_user is not None and self._current_user.email_verified is False:
            auth.generate_email_verification_link(
                email=self._current_user.email,
                app=self._firebase_app
            )

    def get_current_user_details(self):
        """
        Get current user details
        :return: UserDetails
        """

        return self._current_user

    def upload_file(self, path):
        """
        Upload the file to firebase storage module
        :param path: File name
        :return: URL of file
        """

        return self._pyblog_storage.upload_file(file_path=path)

    def get_user_details_firestore(self):
        """
        Get user details from firestore
        :return: User details
        """

        return self._pyblog_firestore.get_current_user_details(
            uid=self._current_user.uid
        )

    def update_user(self, platform_details, name=None, photo_url=None):
        """
        Update the user details
        :param platform_details: Details of the current platform
        :param name: New name of the user
        :param photo_url: New display image of the user
        """
        if name is not None or photo_url is not None:
            if name is not None:
                self._current_user = auth.update_user(
                    uid=self._current_user.uid,
                    display_name=name,
                    app=self._firebase_app
                )
            if photo_url is not None:
                self._current_user = auth.update_user(
                    uid=self._current_user.uid,
                    photo_url=photo_url,
                    app=self._firebase_app
                )
            if self._current_user is not None:
                user_details = UserDetails(
                    display_name=self._current_user.display_name,
                    email=self._current_user.email,
                    phone_number=self._current_user.phone_number,
                    photo_url=self._current_user.photo_url,
                    password=None,
                    email_verified=self._current_user.email_verified,
                )

                self._pyblog_firestore.update_user_details(
                    uid=self._current_user.uid,
                    user_details=user_details,
                    platform_details=platform_details
                )

    def get_blog_tags(self):
        """
        Get the tags from firestore
        :return: Tags list
        """

        return self._pyblog_firestore.get_blog_tags()[Constants.Firebase.Keys.TAGS]

    def update_blog_tags(self, tags):
        """
        Ser the tags in the tags array
        :return: None
        """

        self._pyblog_firestore.update_blog_tags(tags=tags)

    def upload_blog(self, blog_details, document_id=None):
        """
        Upload blog on firestore
        :param blog_details: Blog details
        :param document_id: Blog document to be edited, None by default
        """

        if document_id is None:
            self._pyblog_firestore.update_blogs(blog_details=blog_details, document_id=document_id)
        else:
            self._pyblog_firestore.upload_blog(blog_details=blog_details)

    def get_current_user_blog_drafts(self):
        """
        Get current user blogs which are saved as draft
        :return: List of Blog details
        """

        drafts_doc = self._pyblog_firestore.get_blogs(
            is_draft=True, is_for_current_user=True, current_uid=self._current_user.uid
        )

        draft_list = []
        for draft in drafts_doc:
            blog_details = BlogDetails(
                title=draft.to_dict()[Constants.Firebase.Keys.TITLE],
                subtitle=draft.to_dict()[Constants.Firebase.Keys.SUBTITLE],
                tags=draft.to_dict()[Constants.Firebase.Keys.TAGS],
                blog=draft.to_dict()[Constants.Firebase.Keys.BLOG],
                email_subscriber=draft.to_dict()[Constants.Firebase.Keys.EMAIL_SUBSCRIBER],
                created_on=draft.to_dict()[Constants.Firebase.Keys.CREATED_ON],
                created_by_uid=draft.to_dict()[Constants.Firebase.Keys.CREATED_BY],
                views=draft.to_dict()[Constants.Firebase.Keys.VIEWS],
                likes=draft.to_dict()[Constants.Firebase.Keys.LIKES],
                isDraft=draft.to_dict()[Constants.Firebase.Keys.IS_DRAFT],
                blog_id=draft.id
            )
            draft_list.append(blog_details)
        return draft_list

    def delete_blog(self, document_id):
        """
        Delete the blog
        :param document_id: Id of the blog to be deleted
        """

        self._pyblog_firestore.delete_blog(document_id=document_id)
