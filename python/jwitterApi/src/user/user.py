"""
User Module

A module for user which will contain all the basic calls made to Jwitter project like
get, update, etc.

This will help in making the api file cleaner and making the refactoring easy.
"""
from flask_restful import Resource
from flask import request

from src.utils.constants import Endpoints
from src.core.appwrite.appwrite_client import get_users


class User:
    """A User class which will initiate all the resources required for the User module
    to execute the operations

    * [api] : An API instance of the flask_restful which will be used to add resources for renter class.
    """

    def __init__(self, api):
        self.common_path = Endpoints.user
        api.add_resource(_User, f"{self.common_path}")


class _User(Resource):
    """A User class which will help in handling all the requests made on
    <path>/user/
    """

    def post(self):
        appwrite_users = get_users()

        user_form = request.form
        user_dict = user_form.to_dict()

        print(user_dict)
