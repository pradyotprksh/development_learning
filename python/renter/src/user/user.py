"""
User Module

A module for user which will contain all the basic calls made to Renter project like
get, update, etc.

This will help in making the api file cleaner and making the refactoring easy.
"""
from flask_restful import Resource
from flask import request
from src.db import get_collection
from src.utils import default_response
from src.utils.constants import Keys
from src.core.modals import UserDetails


class User:
    """A User class which will initiate all the resources required for the User module
    to execute the operations

    * [api] : An API instance of the flask_restful which will be used to add resources for renter class.
    """

    def __init__(self, api):
        self.common_path = "/renter"
        api.add_resource(_User, f"{self.common_path}/user/")


class _User(Resource):
    def __init__(self):
        self.user_collection = get_collection(Keys.User.collection_name)

    """A User class which will help in handling all the requests made on
    <path>/user/
    
    The user_id will be coming in the headers
    """

    def get(self):
        # headers
        user_id = request.headers[Keys.User.user_id]

        # find the user in db
        user = self.user_collection.find_one({Keys.User.user_id: user_id})
        if user is None:
            return default_response.response_creator(
                code=404,
                message=f"Asked user is not present. Please create one before asking for it.",
            )

        return default_response.response_creator(
            code=200,
            message="User Found",
            other_data={
                "data": {
                    **user
                }
            }
        )

    def post(self):
        # headers
        user_id = request.headers[Keys.User.user_id]

        # check if the user already exists
        user = self.user_collection.find_one({Keys.User.user_id: user_id})
        if user is not None:
            username = f"{user.get(Keys.User.first_name)} {user.get(Keys.User.last_name)}"

            return default_response.response_creator(
                code=409,
                message=f"{username} exists, you are trying to create one which is already there."
            )

        # payload
        user_details = UserDetails(
            user_id=user_id,
            first_name=request.json[Keys.User.first_name],
            last_name=request.json[Keys.User.last_name],
            permanent_address=request.json[Keys.User.permanent_address],
        )

        # add user details to mongo db
        self.user_collection.insert_one(
            {
                "_id": user_details.user_id,
                **user_details._asdict(),
            }
        )

        # get the user details from db
        db_user_details = self.user_collection.find_one({Keys.User.user_id: user_id})

        return default_response.response_creator(
            code=200,
            message="User created",
            other_data={
                "data": {
                    **db_user_details
                }
            }
        )
