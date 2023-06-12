"""
App module

A module for app which holds the details related to Flask and restful APIs.

Also helps in start the application and make it usable by external applications.
"""
from flask import Flask
from flask_restful import Api
from src import Renter, User, Property, Search, Location, Filter

# Create flask app object
app = Flask(__name__)
# Create the flask restful object
api = Api(app)

# Below are all the resources used by the application
# 1. Renter
Renter(api=api)
# 2. User
User(api=api)
# 3. Property
Property(api=api)
# 4. Search
Search(api=api)
# 4. Location
Location(api=api)
# 5. Filter
Filter(api=api)

# Start the application
app.run(host="0.0.0.0", port=5000, debug=True)
