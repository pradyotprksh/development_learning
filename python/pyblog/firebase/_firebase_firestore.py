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
        self._firestore_db = firestore.client(app=firebase_app)

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
            Constants.Firebase.Keys.DISPLAY_NAME: user_details.display_name,
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
            Constants.Firebase.Keys.LAST_LOGGED_IN: platform_details.timestamp,
        }

        self._firestore_db \
            .collection(Constants.Firebase.Collections.USERS) \
            .document(uid).set(full_details)

    def get_current_user_details(self, uid):
        """
        Get the current user details from Firestore
        :param uid: User id of the current user
        :return: UserDetails
        """

        return self._firestore_db.collection(Constants.Firebase.Collections.USERS) \
            .document(uid).get().to_dict()

    def get_blog_tags(self):
        """
        Get the tags from firestore
        :return: Tags
        """

        return self._firestore_db.collection(Constants.Firebase.Collections.TAGS) \
            .document(Constants.Firebase.Documents.TAGS) \
            .get().to_dict()

    def update_blog_tags(self, tags):
        """
        Ser the tags in the tags array
        :return: None
        """

        self._firestore_db.collection(Constants.Firebase.Collections.TAGS) \
            .document(Constants.Firebase.Documents.TAGS) \
            .set({
                Constants.Firebase.Keys.TAGS: tags
            })

    def upload_blog(self, blog_details):
        """
        Upload blog on firestore
        :param blog_details: Blog details
        :return: None
        """

        full_details = {
            Constants.Firebase.Keys.TITLE: blog_details.title,
            Constants.Firebase.Keys.SUBTITLE: blog_details.subtitle,
            Constants.Firebase.Keys.TAGS: blog_details.tags,
            Constants.Firebase.Keys.EMAIL_SUBSCRIBER: blog_details.email_subscriber,
            Constants.Firebase.Keys.BLOG: blog_details.blog,
            Constants.Firebase.Keys.CREATED_BY: blog_details.created_by_uid,
            Constants.Firebase.Keys.CREATED_ON: blog_details.created_on,
            Constants.Firebase.Keys.VIEWS: blog_details.views,
            Constants.Firebase.Keys.LIKES: blog_details.likes,
            Constants.Firebase.Keys.IS_DRAFT: blog_details.isDraft,
        }

        self._firestore_db \
            .collection(Constants.Firebase.Collections.BLOGS) \
            .document().set(full_details)

    def get_blogs(self, is_draft=False, is_for_current_user=False, current_uid=None):
        """
        Get blogs from firestore
        :param is_draft: Get only drafts. Default is False
        :param is_for_current_user: Get only current user blogs. Default is False
        :param current_uid: current user id. Default is None.
        """

        if is_draft and is_for_current_user:
            blog_collection = self._firestore_db.collection(Constants.Firebase.Collections.BLOGS) \
                .where(
                    Constants.Firebase.Keys.IS_DRAFT,
                    Constants.Firebase.Operators.IS_EQUAL,
                    is_draft
                )\
                .where(
                    Constants.Firebase.Keys.CREATED_BY,
                    Constants.Firebase.Operators.IS_EQUAL,
                    current_uid
                )
        elif is_for_current_user:
            blog_collection = self._firestore_db.collection(Constants.Firebase.Collections.BLOGS) \
                .where(
                    Constants.Firebase.Keys.CREATED_BY,
                    Constants.Firebase.Operators.IS_EQUAL,
                    current_uid
                )
        else:
            blog_collection = self._firestore_db.collection(Constants.Firebase.Collections.BLOGS) \
                .where(
                    Constants.Firebase.Keys.IS_DRAFT,
                    Constants.Firebase.Operators.IS_EQUAL,
                    is_draft
                )

        return blog_collection.stream()

    def update_blogs(self, blog_details, document_id):
        """
        Update the blog
        :param blog_details: Updated blog details
        :param document_id: Id of the blog to be updated
        """

        full_details = {
            Constants.Firebase.Keys.TITLE: blog_details.title,
            Constants.Firebase.Keys.SUBTITLE: blog_details.subtitle,
            Constants.Firebase.Keys.TAGS: blog_details.tags,
            Constants.Firebase.Keys.EMAIL_SUBSCRIBER: blog_details.email_subscriber,
            Constants.Firebase.Keys.BLOG: blog_details.blog,
            Constants.Firebase.Keys.CREATED_BY: blog_details.created_by_uid,
            Constants.Firebase.Keys.CREATED_ON: blog_details.created_on,
            Constants.Firebase.Keys.VIEWS: blog_details.views,
            Constants.Firebase.Keys.LIKES: blog_details.likes,
            Constants.Firebase.Keys.IS_DRAFT: blog_details.isDraft,
        }

        self._firestore_db \
            .collection(Constants.Firebase.Collections.BLOGS) \
            .document(document_id).set(full_details)
