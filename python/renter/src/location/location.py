"""
Location Module

A module for user which will contain all the basic calls made to Renter project like
get, update, etc.

This will help in making the api file cleaner and making the refactoring easy.
"""
from flask_restful import Resource
from flask import request
from geopy.geocoders import Nominatim
from src.utils.constants import Keys, MESSAGES_LIST, DEFAULT_INVALID_REQUEST_PARAMS
from src.utils.response_mapper import response_creator


class Location:
    """A Location class which will initiate all the resources required for the Location module
    to execute the operations

    * [api] : An API instance of the flask_restful which will be used to add resources for location class.
    """

    def __init__(self, api):
        self.common_path = "/renter/location"
        api.add_resource(_Location, f"{self.common_path}/")


class _Location(Resource):
    """A Location class which will help in handling all the requests made on
    <path>/location/
    """

    def __init__(self):
        self.loc = Nominatim(user_agent="rental")

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
        zip_code = request.args.get(Keys.Search.zip_code)
        latitude = request.args.get(Keys.Search.latitude)
        longitude = request.args.get(Keys.Search.longitude)
        exactly_one = request.args.get(Keys.Search.exactly_one) == "True"

        # Find which parameters are present
        is_zip_code_available = not (zip_code is None or zip_code == "")
        is_search_query_available = not (search_text is None or search_text == "")
        is_coordinates_available = not ((latitude is None or latitude == "") and (longitude is None or longitude == ""))

        # Return 404 if no one is available
        if not is_zip_code_available and not is_search_query_available and not is_coordinates_available:
            return response_creator(
                code=404,
                message=DEFAULT_INVALID_REQUEST_PARAMS
            )

        search_text_location_details = []
        if is_search_query_available:
            result = self.loc.geocode(
                search_text,
                addressdetails=True,
                exactly_one=False,
            )
            if not (result is None):
                search_text_location_details = result
        zip_code_location_details = []
        if is_zip_code_available:
            result = self.loc.geocode(
                zip_code,
                addressdetails=True,
                exactly_one=False,
            )
            if not (result is None):
                zip_code_location_details = result
        coordinates_location_details = []
        if is_coordinates_available:
            result = self.loc.reverse(
                f"{latitude}, {longitude}",
                addressdetails=True,
                exactly_one=False,
            )
            if not (result is None):
                coordinates_location_details = result

        location_details = search_text_location_details + zip_code_location_details + coordinates_location_details

        if location_details is None or len(location_details) == 0:
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.Messages.location_not_found),
            )

        location_details_list = []
        for location in location_details:
            if exactly_one and len(location_details_list) == 1:
                break
            location_details_list.append(location.raw)

        return response_creator(
            code=200,
            message=MESSAGES_LIST.get(Keys.Messages.location_found),
            other_data={
                "count": len(location_details_list),
                "data": location_details_list,
            }
        )
