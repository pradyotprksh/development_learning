"""
Storage implementation for PyBlog
"""

from firebase_admin import storage
from src import Constants


class _FirebaseStorage:
    """A collection for firebase storage"""

    def __init__(self, firebase_app):
        """
        A firebase storage implementation for PyBlog. Will be used for get, upload and delete
        details from Firebase Storage
        :param firebase_app: App object for the firebase admin
        """
        self.firestore_storage = storage.bucket(
            name=Constants.Variables.FIREBASE_STORAGE_BUCKET_NAME,
            app=firebase_app
        )
