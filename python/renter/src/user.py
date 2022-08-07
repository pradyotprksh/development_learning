"""
User Module

A module for user which will contain all the basic calls made to Renter project like
get, update, etc.

This will help in making the api file cleaner and making the refactoring easy.
"""
from flask_restful import Resource
from flask import request


class User:
    """A User class which will initiate all the resources required for the User module
    to execute the operations

    * [api] : An API instance of the flask_restful which will be used to add resources for renter class.
    """
    def __init__(self, api):
        self.common_path = "/renter"
        api.add_resource(_User, f"{self.common_path}/user/")


class _User(Resource):
    user_id = "user_id"

    """A User class which will help in handling all the requests made on
    <path>/user/
    
    The user_id will be coming in the headers
    """
    def get(self):
        user_id = request.headers[self.user_id]
        print(user_id)
