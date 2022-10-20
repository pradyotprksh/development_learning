"""
App module

A module for app which holds the details related to Flask and restful APIs.

Also helps in start the application and make it usable by external applications.
"""
from flask import Flask
from flask_restful import Api
from src import Renter, User, Property, Search
from src.utils.util_calls import get_mongo_details
from pymongo import MongoClient

# Create flask app object
app = Flask(__name__)
# Create the flask restful object
api = Api(app)

# Create mongo db
client = MongoClient(
    get_mongo_details(),
    connect=False,
)
db = client["renter"]

# Below are all the resources used by the application
# 1. Renter
Renter(api=api)
# 2. User
User(api=api)
# 3. Property
Property(api=api)
# 4. Search
Search(api=api)

# Start the application
app.run(host="0.0.0.0", port=5000, debug=True)
