"""
Filter Module

A module for filters which will contain all the basic calls made to Renter project like
get, update, etc.

This will help in making the api file cleaner and making the refactoring easy.
"""
from flask_restful import Resource
from flask import request
from src.core.services.db import get_collection, get_documents
from src.utils.constants import Keys, MESSAGES_LIST, DEFAULT_INVALID_REQUEST_PARAMS
from src.utils.response_mapper import response_creator
from src.utils.util_calls import convert_date_to_timestamp, get_current_timestamp, distance_between
import sys


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
        user_latitude = request.args.get(Keys.Search.latitude)
        user_longitude = request.args.get(Keys.Search.longitude)

        # Request query conversion
        if not number_of_bathrooms is None:
            number_of_bathrooms = int(number_of_bathrooms)

        if not yearly_deposit_start is None or not yearly_deposit_end is None:
            if not yearly_deposit_start is None:
                yearly_deposit_start = int(yearly_deposit_start)
            else:
                yearly_deposit_start = 0
            if not yearly_deposit_end is None:
                yearly_deposit_end = int(yearly_deposit_end)
            else:
                yearly_deposit_end = sys.maxsize - 1

        if not monthly_rent_start is None or not monthly_rent_end is None:
            if not monthly_rent_start is None:
                monthly_rent_start = int(monthly_rent_start)
            else:
                monthly_rent_start = 0
            if not monthly_rent_end is None:
                monthly_rent_end = int(monthly_rent_end)
            else:
                monthly_rent_end = sys.maxsize - 1

        if not distance_start is None or not distance_end is None:
            if not distance_start is None:
                distance_start = int(distance_start)
            else:
                distance_start = 0
            if not distance_end is None:
                distance_end = int(distance_end)
            else:
                distance_end = sys.maxsize - 1

        if not property_updated_on_start is None or not property_updated_on_end is None:
            if not property_updated_on_start is None:
                property_updated_on_start = convert_date_to_timestamp(property_updated_on_start)
            else:
                property_updated_on_start = 0
            if not property_updated_on_end is None:
                property_updated_on_end = convert_date_to_timestamp(property_updated_on_end)
            else:
                property_updated_on_end = get_current_timestamp()

        yearly_deposit_range = None
        monthly_rent_range = None
        distance_range = None
        property_updated_on_range = None

        if not yearly_deposit_start is None and not yearly_deposit_end is None:
            if yearly_deposit_start > yearly_deposit_end:
                return response_creator(
                    code=404,
                    message=DEFAULT_INVALID_REQUEST_PARAMS
                )
            yearly_deposit_range = range(yearly_deposit_start, yearly_deposit_end + 1)
        if not monthly_rent_start is None and not monthly_rent_end is None:
            if monthly_rent_start > monthly_rent_end:
                return response_creator(
                    code=404,
                    message=DEFAULT_INVALID_REQUEST_PARAMS
                )
            monthly_rent_range = range(monthly_rent_start, monthly_rent_end + 1)
        if not distance_start is None and not distance_end is None:
            if distance_start > distance_end:
                return response_creator(
                    code=404,
                    message=DEFAULT_INVALID_REQUEST_PARAMS
                )
            distance_range = range(distance_start, distance_end + 1)
        if not property_updated_on_start is None and not property_updated_on_end is None:
            if property_updated_on_start > property_updated_on_end:
                return response_creator(
                    code=404,
                    message=DEFAULT_INVALID_REQUEST_PARAMS
                )
            property_updated_on_range = range(property_updated_on_start, property_updated_on_end)

        users_list = get_documents(self.user_collection)
        properties_list = []
        for doc in get_documents(self.property_collection):
            property_created_by_user_id = doc.get(Keys.Property.property_created_by)
            doc[Keys.Property.property_created_by_details] = self.get_user_details(
                property_created_by_user_id, users_list
            )

            if user_latitude is not None and user_longitude is not None:
                property_latitude = doc.get(Keys.Property.address).get(Keys.Property.latitude)
                property_longitude = doc.get(Keys.Property.address).get(Keys.Property.longitude)
                distance = distance_between((property_latitude, property_longitude), (user_latitude, user_longitude))
                doc[Keys.Property.distance] = distance

            properties_list.append(doc)

        filtered_properties_list = list(
            filter(
                lambda p:
                number_of_bathrooms is not None and int(p.get(Keys.Property.number_of_bathrooms)) <=
                int(number_of_bathrooms) or
                property_name is not None and p.get(Keys.Property.property_name).lower()
                .find(property_name.lower()) > -1 or
                owner_name is not None and (p.get(Keys.Property.property_created_by_details)
                                            .get(Keys.User.first_name).lower().find(owner_name.lower()) > -1 or
                                            p.get(Keys.Property.property_created_by_details)
                                            .get(Keys.User.last_name).lower().find(owner_name.lower()) > -1) or
                property_address is not None and p.get(Keys.Property.address)
                .get(Keys.Property.display_name).lower().find(property_address.lower()) > -1 or
                listed_by_owner is not None and p.get(Keys.Property.is_rental_owner) == listed_by_owner or
                for_rental is not None and p.get(Keys.Property.is_for_rental) == for_rental or
                property_for is not None and p.get(Keys.Property.property_for) == property_for or
                furnished_type is not None and p.get(Keys.Property.furnished_type) == furnished_type or
                property_type is not None and p.get(Keys.Property.property_type) == property_type or
                where_it_is is not None and p.get(Keys.Property.where_it_is) == where_it_is or
                yearly_deposit_range is not None and int(p.get(Keys.Property.yearly_deposit)) in yearly_deposit_range or
                monthly_rent_range is not None and int(p.get(Keys.Property.monthly_rent)) in monthly_rent_range or
                property_updated_on_range is not None and int(
                    p.get(Keys.Property.property_updated_on)) in property_updated_on_range or
                distance_range is not None and
                p.get(Keys.Property.distance) is not None and p.get(Keys.Property.distance) in distance_range,
                properties_list
            )
        )

        return response_creator(
            code=200,
            message=MESSAGES_LIST.get(Keys.Messages.properties_found),
            other_data={
                "count": filtered_properties_list.__len__(),
                "data": filtered_properties_list
            }
        )
