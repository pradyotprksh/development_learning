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

    :argument collection_name Name of the collection to be returned
    """
    return db[collection_name]
