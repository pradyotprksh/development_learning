"""
Search Module

A module for user which will contain all the basic calls made to Renter project like
get, update, etc.

This will help in making the api file cleaner and making the refactoring easy.
"""
from flask_restful import Resource
from flask import request
from src.core.services.db import get_collection, get_documents
from src.utils.constants import Keys, MESSAGES_LIST, DEFAULT_INVALID_REQUEST_PARAMS, USER_TYPE
from src.utils.response_mapper import response_creator


class Search:
    """A Search class which will initiate all the resources required for the Search module
    to execute the operations

    * [api] : An API instance of the flask_restful which will be used to add resources for search class.
    """

    def __init__(self, api):
        self.common_path = "/renter/search"
        api.add_resource(_Search, f"{self.common_path}/")


class _Search(Resource):
    """A Search class which will help in handling all the requests made on
    <path>/search/
    """

    def __init__(self):
        # Get the user collection to be used by the user resource
        self.user_collection = get_collection(Keys.User.collection_name)
        # Get the property collection to be used by the property resource
        self.property_collection = get_collection(Keys.Property.collection_name)

    def get(self):
        # headers
        # Check for app token to validate request
        app_check_token = request.headers[Keys.Rental.firebase_app_check_token]
        if app_check_token is None or app_check_token == "":
            return response_creator(
                code=401,
                message=MESSAGES_LIST[Keys.Messages.cannot_validate_request],
            )

        # Query parameters
        search_text = request.args.get(Keys.Search.search_text)
        user_type = request.args.get(Keys.Search.user_type)

        if (search_text is None or search_text == "") and user_type is None:
            return response_creator(
                code=404,
                message=DEFAULT_INVALID_REQUEST_PARAMS
            )

        search_text = search_text.lower()

        is_valid_user_type = user_type in USER_TYPE

        # Get all property details
        property_list = []
        if not is_valid_user_type:
            for doc in get_documents(self.property_collection):
                property_list.append(doc)

        # Get all user details
        users_list = []
        for doc in get_documents(self.user_collection):
            users_list.append(doc)

        property_search_result = list(filter(
            lambda p: p.get(Keys.Property.property_name).lower().find(search_text) > -1
                      or p.get(Keys.Property.address).get(Keys.Property.display_name).lower().find(search_text) > -1
                      or p.get(Keys.Property.monthly_rent).find(search_text) > -1
                      or p.get(Keys.Property.yearly_deposit).find(search_text) > -1,
            property_list
        ))

        if not is_valid_user_type:
            user_search_result = list(filter(
                lambda u: u.get(Keys.User.email_address).lower().find(search_text) > -1
                          or u.get(Keys.User.first_name).lower().find(search_text) > -1
                          or u.get(Keys.User.last_name).lower().find(search_text) > -1
                          or u.get(Keys.User.permanent_address).get(Keys.Property.display_name).lower().find(search_text) > -1
                          or u.get(Keys.User.phone_number).find(search_text) > -1
                          or u.get(Keys.User.profession).lower().find(search_text) > -1
                          or u.get(Keys.User.user_type).lower().find(search_text) > -1,
                users_list
            ))
        else:
            user_search_result = list(filter(
                lambda u: u.get(Keys.User.user_type).find(user_type) > -1,
                users_list
            ))

        return response_creator(
            code=200,
            message=MESSAGES_LIST.get(Keys.Messages.search_result),
            other_data={
                "count": property_search_result.__len__() + user_search_result.__len__(),
                "data": {
                    "properties": {
                        "count": property_search_result.__len__(),
                        "details": property_search_result
                    },
                    "users": {
                        "count": user_search_result.__len__(),
                        "details": user_search_result
                    },
                }
            }
        )
