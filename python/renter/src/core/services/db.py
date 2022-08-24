"""
DB Module

A module related to database which creates the client for mongo db and also
provides other useful resources to be used.

Helps in getting the collection, creating the client, etc.
"""
from src.utils.util_calls import get_mongo_details
from pymongo import MongoClient

# Create mongo db
client = MongoClient(
    get_mongo_details(),
    connect=False,
)
db = client["renter"]


def get_collection(collection_name):
    """Get the collection from the database

    :arg collection_name: Name of the collection to be returned
    """
    return db[collection_name]


def get_document(collection, key, value):
    """Get the document from the collection

    :argument collection: Collection from which the document is needed
    :argument key: Key to be used to get the document
    :argument value: Value for the key
    """
    return collection.find_one({key: value})


def insert_document(collection, data):
    """Insert the data to the given collection

    :argument collection: Collection in which the data needs to be added
    :argument data: Data which needs to be added to the collection
    """
    collection.insert_one(data)


def update_a_document(collection, key, value, data):
    """Update the first document

    :argument collection: Collection in which the data needs to be updated
    :argument key: Key to be used to get the document
    :argument value: Value for the key
    :argument data: Data which needs to be updated to the collection
    """
    collection.update_one(
        {key: value},
        {"$set": data},
    )
