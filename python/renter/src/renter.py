"""
Renter Module

A module for renter which will contain all the basic calls made to Renter project like
details of the project, terms and condition, information etc.

This will help in making the api file cleaner and making the refactoring easy.
"""
from flask_restful import Resource
from .utils import constants
from .utils import default_response


class Renter:
    """A Renter class which will initiate all the resources required for the Renter module
    to execute the operations

    * [api] : An API instance of the flask_restful which will be used to add resources for renter class.
    """
    def __init__(self, api):
        self.common_path = "/renter"
        api.add_resource(_Details, f"{self.common_path}/")
        api.add_resource(_TermsAndCondition, f"{self.common_path}/terms_condition/<string:user_type>")
        api.add_resource(_Information, f"{self.common_path}/information")


class _Details(Resource):
    """A Details class which will be performing any operation when <path>/ endpoint is called"""
    @staticmethod
    def get():
        return default_response.response_creator(
            code=200,
            message="Hello User to Renter, this is a Python created RESTFUL API which is used by our Renter "
                    "Android application. If you want to you can also use this and make requests."
        )


class _TermsAndCondition(Resource):
    """A Terns and Condition class which will be performing any operation when
    <path>/terms_condition/<user_type> endpoint is called

    * <user_type> : is required and not all type is accepted, it should be in USER_TYPE
    """
    @staticmethod
    def get(user_type):
        if user_type not in constants.USER_TYPE:
            return default_response.response_creator(
                code=404,
                message=f"{user_type} is not a valid user type, Please provide a valid one.",
            )
        return default_response.response_creator(
            code=200,
            message=f"Hello {user_type}, this is to inform you that you will be "
                    f"virtually signing our terms and condition.",
        )


class _Information(Resource):
    """An Information class which will be performing any operation when
    <path>/information endpoint is called"""
    @staticmethod
    def get():
        return default_response.response_creator(
            code=200,
            message="Owner and Renter Details are as below."
        )
