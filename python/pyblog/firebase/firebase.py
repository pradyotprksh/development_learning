"""
A single place to handle all the firebase work
"""

import firebase_admin
from firebase_admin import credentials
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

        cred = credentials.Certificate(cert=Constants.Variables.FIREBASE_CRED_FILE_PATH)
        firebase_app = firebase_admin.initialize_app(
            credential=cred,
            name=Constants.Variables.PROJECT_NAME
        )
        self._pyblog_firestore = _FirebaseFirestore(firebase_app=firebase_app)
        self._pyblog_storage = _FirebaseStorage(firebase_app=firebase_app)
