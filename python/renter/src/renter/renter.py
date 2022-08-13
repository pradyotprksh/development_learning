"""
Renter Module

A module for renter which will contain all the basic calls made to Renter project like
details of the project, terms and condition, information etc.

This will help in making the api file cleaner and making the refactoring easy.
"""
from flask import render_template
from flask_restful import Resource
from src.utils.constants import USER_TYPE, Endpoints, MESSAGES_LIST, Keys, INFORMATION_HTML_FILE
from src.utils.response_mapper import response_creator


class Renter:
    """A Renter class which will initiate all the resources required for the Renter module
    to execute the operations

    * [api] : An API instance of the flask_restful which will be used to add resources for renter class.
    """

    def __init__(self, api):
        self.common_path = "/renter"
        api.add_resource(_Details, f"{self.common_path}{Endpoints.Renter.home}")
        api.add_resource(_TermsAndCondition, f"{self.common_path}{Endpoints.Renter.terms_and_condition}")
        api.add_resource(_Information, f"{self.common_path}{Endpoints.Renter.information}")


class _Details(Resource):
    """A Details class which will be performing any operation when <path>/ endpoint is called"""

    @staticmethod
    def get():
        return response_creator(
            code=200,
            message=MESSAGES_LIST[Keys.Messages.renter_details]
        )


class _TermsAndCondition(Resource):
    """A Terns and Condition class which will be performing any operation when
    <path>/terms_condition/<user_type> endpoint is called

    * <user_type> : is required and not all type is accepted, it should be in USER_TYPE
    """

    @staticmethod
    def get(user_type):
        if user_type not in USER_TYPE:
            return response_creator(
                code=404,
                message=MESSAGES_LIST[Keys.Messages.invalid_user_type].format(user_type)
            )
        return response_creator(
            code=200,
            message=MESSAGES_LIST[Keys.Messages.terms_condition_message].format(user_type),
        )


class _Information(Resource):
    """An Information class which will be performing any operation when
    <path>/information endpoint is called"""

    @staticmethod
    def get():
        return render_template(INFORMATION_HTML_FILE)
