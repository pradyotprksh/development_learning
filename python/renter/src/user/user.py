"""
User Module

A module for user which will contain all the basic calls made to Renter project like
get, update, etc.

This will help in making the api file cleaner and making the refactoring easy.
"""
from flask_restful import Resource
from flask import request
from src.core.services.db import get_collection
from src.utils.response_mapper import response_creator
from src.utils.constants import Keys, ERROR_RESPONSE_MESSAGES, DEFAULT_ERROR_MESSAGE
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
    """A User class which will help in handling all the requests made on
    <path>/user/

    The user_id will be coming in the headers
    """
    def __init__(self):
        # Get the user collection to be used by the user resource
        self.user_collection = get_collection(Keys.User.collection_name)

    def get(self):
        # headers
        user_id = request.headers[Keys.User.user_id]

        # find the user in db
        user = self.user_collection.find_one({Keys.User.user_id: user_id})
        if user is None:
            return response_creator(
                code=404,
                message="Asked user is not present. Please create one before asking for it.",
            )

        return response_creator(
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

            return response_creator(
                code=409,
                message=f"{username} exists, you are trying to create one which is already there."
            )

        user_form = request.form.to_dict()

        first_name = user_form.get(Keys.User.first_name)
        last_name = user_form.get(Keys.User.last_name, "")
        permanent_address = user_form.get(Keys.User.permanent_address, "")
        date_of_birth = user_form.get(Keys.User.date_of_birth, -1)
        email_address = user_form.get(Keys.User.email_address)
        profession = user_form.get(Keys.User.profession, "")
        phone_number = user_form.get(Keys.User.phone_number, "")
        profile_pic_url = user_form.get(Keys.User.profile_pic_url, "")

        if first_name is None:
            return response_creator(
                code=404,
                message=ERROR_RESPONSE_MESSAGES.get(Keys.User.first_name, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.User.first_name.split("_")))
            )
        if email_address is None:
            return response_creator(
                code=404,
                message=ERROR_RESPONSE_MESSAGES.get(Keys.User.email_address, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.User.email_address.split("_")))
            )

        # payload
        user_details = UserDetails(
            user_id=user_id,
            first_name=first_name,
            last_name=last_name,
            permanent_address=permanent_address,
            date_of_birth=date_of_birth,
            email_address=email_address,
            profession=profession,
            phone_number=phone_number,
            profile_pic_url=profile_pic_url
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

        return response_creator(
            code=200,
            message="User created",
            other_data={
                "data": {
                    **db_user_details
                }
            }
        )
