"""
Property Module

A module for property which will contain all the basic calls made to Renter project like
get, update, etc.

This will help in making the api file cleaner and making the refactoring easy.
"""
from flask_restful import Resource
from flask import request
from src.core.services.db import get_collection, get_document
from src.utils.constants import Keys, MESSAGES_LIST
from src.utils.response_mapper import response_creator


class Property:
    """A Property class which will initiate all the resources required for the Property module
    to execute the operations

    * [api] : An API instance of the flask_restful which will be used to add resources for property class.
    """

    def __init__(self, api):
        self.common_path = "/renter/property"
        api.add_resource(_Property, f"{self.common_path}")


class _Property(Resource):
    """A User class which will help in handling all the requests made on
    <path>/property/

    headers: user_id
    """

    def __init__(self):
        # Get the user collection to be used by the user resource
        self.user_collection = get_collection(Keys.User.collection_name)
        # Get the property collection to be used by the property resource
        self.property_collection = get_collection(Keys.Property.collection_name)

    def post(self):
        # headers
        user_id = request.headers[Keys.User.user_id]

        # check if the user already exists
        user = get_document(self.user_collection, Keys.User.user_id, user_id)
        if user is not None:
            username = f"{user.get(Keys.User.first_name)} {user.get(Keys.User.last_name)}"

            return response_creator(
                code=409,
                message=MESSAGES_LIST.get(Keys.Messages.user_already_available).format(username)
            )
