"""
App module

A module for app which holds the details related to Flask and restful APIs.

Also helps in start the application and make it usable by external applications.
"""
from flask import Flask
from flask_restful import Api
from src import Renter

# Create flask app object
app = Flask(__name__)
# Create the flask restful object
api = Api(app)

# Below are all the resources used by the application
# 1. Renter
Renter(api=api)

# Start the application
app.run(host="0.0.0.0", port=5000, debug=True)
