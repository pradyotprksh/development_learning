from src.utils.util_calls import get_mongo_details
from pymongo import MongoClient


# Create mongo db
client = MongoClient(
    get_mongo_details(),
    connect=False,
)
db = client["renter"]


def get_collection(collection_name):
    return db[collection_name]
