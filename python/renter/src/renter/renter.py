"""
Renter Module

A module for renter which will contain all the basic calls made to Renter project like
details of the project, terms and condition, information etc.

This will help in making the api file cleaner and making the refactoring easy.
"""
from flask import render_template, request
from flask_restful import Resource
from src.utils.constants import USER_TYPE, Endpoints, MESSAGES_LIST, Keys, INFORMATION_HTML_FILE, \
    DEFAULT_ERROR_MESSAGE, DEFAULT_VALID_DATA
from src.utils.response_mapper import response_creator
from src.utils.util_calls import is_email_address_valid


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
        api.add_resource(_Email, f"{self.common_path}{Endpoints.Renter.email}")


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
        # Headers
        # Check for app token to validate request
        app_check_token = request.headers[Keys.Rental.firebase_app_check_token]
        if app_check_token is None or app_check_token == "":
            return response_creator(
                code=401,
                message=MESSAGES_LIST[Keys.Messages.cannot_validate_request],
            )

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
        # Headers
        # Check for app token to validate request
        app_check_token = request.headers[Keys.Rental.firebase_app_check_token]
        if app_check_token is None or app_check_token == "":
            return response_creator(
                code=401,
                message=MESSAGES_LIST[Keys.Messages.cannot_validate_request],
            )

        return render_template(INFORMATION_HTML_FILE)


class _Email(Resource):
    """An email class which will be performing any operation when
    <path>/email endpoint is called"""

    @staticmethod
    def get(email_address):
        # Headers
        # Check for app token to validate request
        app_check_token = request.headers[Keys.Rental.firebase_app_check_token]
        if app_check_token is None or app_check_token == "":
            return response_creator(
                code=401,
                message=MESSAGES_LIST[Keys.Messages.cannot_validate_request],
            )

        if not is_email_address_valid(email=email_address):
            return response_creator(
                code=400,
                message=MESSAGES_LIST.get(Keys.Messages.invalid_input, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.User.email_address.split("_")))
            )
        return response_creator(
            code=200,
            message=MESSAGES_LIST.get(Keys.Messages.valid, DEFAULT_VALID_DATA)
        )
