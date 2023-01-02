"""
Filter Module

A module for filters which will contain all the basic calls made to Renter project like
get, update, etc.

This will help in making the api file cleaner and making the refactoring easy.
"""
from flask_restful import Resource
from flask import request
from src.core.services.db import get_collection, get_documents
from src.utils.constants import Keys, MESSAGES_LIST, DEFAULT_INVALID_REQUEST_PARAMS, USER_TYPE
from src.utils.response_mapper import response_creator
from src.utils.util_calls import convert_date_to_timestamp


class Filter:
    """A Filter class which will initiate all the resources required for the Filter module
    to execute the operations

    * [api] : An API instance of the flask_restful which will be used to add resources for filter class.
    """

    def __init__(self, api):
        self.common_path = "/renter/filter"
        api.add_resource(_Filter, f"{self.common_path}/")


class _Filter(Resource):
    """A Filter class which will help in handling all the requests made on
    <path>/filter/
    """

    @staticmethod
    def find_user_by_user_id(user_id, users_list):
        for user in users_list:
            if user.get(Keys.User.user_id) == user_id:
                return user
        return {}

    def get_user_details(self, user_id, users_list):
        return self.find_user_by_user_id(user_id, users_list)

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
        property_name = request.args.get(Keys.Filter.property_name)
        owner_name = request.args.get(Keys.Filter.owner_name)
        property_address = request.args.get(Keys.Filter.property_address)
        listed_by_owner = request.args.get(Keys.Filter.listed_by_owner)
        for_rental = request.args.get(Keys.Filter.for_rental)
        property_for = request.args.get(Keys.Filter.property_for)
        furnished_type = request.args.get(Keys.Filter.furnished_type)
        property_type = request.args.get(Keys.Filter.property_type)
        where_it_is = request.args.get(Keys.Filter.where_it_is)
        number_of_bathrooms = request.args.get(Keys.Filter.number_of_bathrooms)
        yearly_deposit_start = request.args.get(Keys.Filter.yearly_deposit_start)
        yearly_deposit_end = request.args.get(Keys.Filter.yearly_deposit_end)
        monthly_rent_start = request.args.get(Keys.Filter.monthly_rent_start)
        monthly_rent_end = request.args.get(Keys.Filter.monthly_rent_end)
        property_updated_on_start = request.args.get(Keys.Filter.property_updated_on_start)
        property_updated_on_end = request.args.get(Keys.Filter.property_updated_on_end)
        distance_start = request.args.get(Keys.Filter.distance_start)
        distance_end = request.args.get(Keys.Filter.distance_end)

        # Request query conversion
        if not number_of_bathrooms is None:
            number_of_bathrooms = int(number_of_bathrooms)
        if not yearly_deposit_start is None:
            yearly_deposit_start = int(yearly_deposit_start)
        if not yearly_deposit_end is None:
            yearly_deposit_end = int(yearly_deposit_end)
        if not monthly_rent_start is None:
            monthly_rent_start = int(monthly_rent_start)
        if not monthly_rent_end is None:
            monthly_rent_end = int(monthly_rent_end)
        if not distance_start is None:
            distance_start = int(distance_start)
        if not distance_end is None:
            distance_end = int(distance_end)
        if not property_updated_on_start is None:
            property_updated_on_start = convert_date_to_timestamp(property_updated_on_start)
        if not property_updated_on_end is None:
            property_updated_on_end = convert_date_to_timestamp(property_updated_on_end)

        if number_of_bathrooms < 0 \
                or yearly_deposit_start > yearly_deposit_end \
                or monthly_rent_start > monthly_rent_end or distance_start > distance_end \
                or property_updated_on_start > property_updated_on_end:
            return response_creator(
                code=404,
                message=DEFAULT_INVALID_REQUEST_PARAMS
            )

        yearly_deposit_range = range(yearly_deposit_start, yearly_deposit_end + 1)
        monthly_rent_range = range(monthly_rent_start, monthly_rent_end + 1)
        distance_range = range(distance_start, distance_end + 1)
        property_updated_on_range = range(property_updated_on_start, property_updated_on_end)

        users_list = get_documents(self.user_collection)
        properties_list = []
        for doc in get_documents(self.property_collection):
            property_created_by_user_id = doc.get(Keys.Property.property_created_by)
            doc[Keys.Property.property_created_by_details] = self.get_user_details(
                property_created_by_user_id, users_list
            )
            properties_list.append(doc)

