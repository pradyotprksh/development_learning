import firebase_admin
from firebase_admin import credentials, firestore, storage


class Firebase:
    """
    A firebase class which will hold all the details and functionality related to Firebase and its extensions
    like firestore, storage, etc.

    :param configuration_path will contain the configuration path from were all the details related to firebase
        project will be taken
    :type configuration_path str
    :param storage_bucket_name is an optional field which will contain the bucket name for firebase storage
    :type storage_bucket_name str
    """

    def __init__(self, configuration_path, storage_bucket_name=None):
        if configuration_path is None or configuration_path == "":
            raise ValueError("Configuration path is required")

        cred = credentials.Certificate(configuration_path)
        self.firebase_app = firebase_admin.initialize_app(cred)
        self.firestore = Firestore(firebase_app=self.firebase_app)
        self.storage = Storage(firebase_app=self.firebase_app, storage_bucket_name=storage_bucket_name)


class Firestore:

    def __init__(self, firebase_app):
        self.firestore_db = firestore.client(firebase_app)


class Storage:

    def __init__(self, firebase_app, storage_bucket_name=None):
        self.firestore_storage = storage.bucket(storage_bucket_name, firebase_app)
