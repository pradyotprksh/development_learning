"""
Storage implementation for PyBlog
"""

from firebase_admin import storage
from loguru import logger
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

    def upload_file(self, file_path):
        """
        Upload the file on firebase storage
        :param file_path: Name of the file
        :return: File URL
        """

        try:
            blob = self.firestore_storage.blob(file_path)
            blob.upload_from_filename(filename=file_path)
            blob.make_public()
            return blob.public_url
        except Exception as exception:
            logger.exception(exception.__str__())
            return Constants.Variables.DEFAULT_IMAGE
