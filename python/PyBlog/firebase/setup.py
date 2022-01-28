import firebase_admin
from firebase_admin import credentials, firestore, storage, App
from src import ValueCheck


_DEFAULT_APP_NAME = "[DEFAULT_PYBLOG]"
_DEFAULT_STORAGE = "pyblog_storage"


class Firebase:

    def __init__(self, configuration_path, storage_bucket_name=_DEFAULT_STORAGE, app_name=_DEFAULT_APP_NAME):
        """
        A firebase class which will hold all the details and functionality related to Firebase and its extensions
        like firestore, storage, etc.

        :param configuration_path: will contain the configuration path from were all the details related to firebase
        project will be taken
        :type configuration_path: str
        :param storage_bucket_name: is an optional field which will contain the bucket name for firebase storage
        :type storage_bucket_name: str
        """

        # Check if arguments of valid type
        ValueCheck.is_not_confirmed_type(arg=configuration_path, arg_type=str, argument_name="configuration_path")
        ValueCheck.is_not_confirmed_type(arg=app_name, arg_type=str, argument_name="app_name")
        ValueCheck.is_not_confirmed_type(arg=storage_bucket_name, arg_type=str, argument_name="storage_bucket_name")

        # Check if arguments of valid input
        ValueCheck.is_empty_or_none(arg=configuration_path, argument_name="configuration_path")
        ValueCheck.is_empty_or_none(arg=app_name, argument_name="app_name")
        ValueCheck.is_empty_or_none(arg=storage_bucket_name, argument_name="storage_bucket_name")

        cred = credentials.Certificate(cert=configuration_path)
        self.firebase_app = firebase_admin.initialize_app(
            credential=cred,
            name=app_name,
            options={
                'storageBucket': storage_bucket_name
            }
        )
        self.firestore = Firestore(firebase_app=self.firebase_app)
        self.storage = Storage(firebase_app=self.firebase_app, storage_bucket_name=storage_bucket_name)


class Firestore:

    def __init__(self, firebase_app):
        """
        A firestore class for the firebase which will handle the firestore functionality.
        :param firebase_app: firebase app of the admin
        """

        # Check if arguments of valid type
        ValueCheck.is_not_confirmed_type(arg=firebase_app, arg_type=App, argument_name="firebase_app")

        self.firestore_db = firestore.client(app=firebase_app)


class Storage:

    def __init__(self, firebase_app, storage_bucket_name=_DEFAULT_STORAGE):
        """
        A storage class for the firebase which will handle the storage functionality
        :param firebase_app: firebase app of the admin
        :param storage_bucket_name: bucket name for the storage
        """

        # Check if arguments of valid type
        ValueCheck.is_not_confirmed_type(arg=firebase_app, arg_type=App, argument_name="firebase_app")
        ValueCheck.is_not_confirmed_type(arg=storage_bucket_name, arg_type=str, argument_name="storage_bucket_name")

        # Check if arguments of valid input
        ValueCheck.is_empty_or_none(arg=storage_bucket_name, argument_name="storage_bucket_name")

        self.firestore_storage = storage.bucket(name=storage_bucket_name, app=firebase_app)
