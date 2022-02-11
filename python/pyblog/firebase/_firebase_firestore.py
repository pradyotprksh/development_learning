"""
Firestore implementation for PyBlog
"""

from firebase_admin import firestore


class _FirebaseFirestore:
    """A collection for Firebase Firestore"""

    def __init__(self, firebase_app):
        """
        A firestore implementation for PyBlog. Will be used for get, set, update and delete
        details from Firebase Firestore
        :param firebase_app: App object for the firebase admin
        """
        self.firestore_db = firestore.client(app=firebase_app)
