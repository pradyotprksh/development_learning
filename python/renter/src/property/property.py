"""
Property Module

A module for property which will contain all the basic calls made to Renter project like
get, update, etc.

This will help in making the api file cleaner and making the refactoring easy.
"""
from flask_restful import Resource
from flask import request
from src.core.services.db import get_collection, get_document, get_documents, insert_document
from src.utils.constants import Keys, MESSAGES_LIST, DEFAULT_ERROR_MESSAGE
from src.utils.response_mapper import response_creator
from src.utils.util_calls import get_current_timestamp
from src.core.modals import PropertyDetails


class Property:
    """A Property class which will initiate all the resources required for the Property module
    to execute the operations

    * [api] : An API instance of the flask_restful which will be used to add resources for property class.
    """

    def __init__(self, api):
        self.common_path = "/renter/property"
        api.add_resource(_Property, f"{self.common_path}")


class _Property(Resource):
    """A User class which will help in handling all the requests made on
    <path>/property/

    headers: user_id
    """

    def find_user_by_user_id(self, user_id):
        # Get all user details
        users_list = []
        for doc in get_documents(self.user_collection):
            users_list.append(doc)

        for user in users_list:
            if user.get(Keys.User.user_id) == user_id:
                return user
        return {}

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
        property_id = request.args.get("property_id")
        user_id = request.args.get("user_id")

        # If user id is empty
        if user_id is None or user_id == "":
            # If property id is also empty
            if property_id is None or property_id == "":
                # Fetch all the properties
                property_cursor = get_documents(self.property_collection)
                property_list = []
                for doc in property_cursor:
                    user_id = doc.get(Keys.Property.property_created_by)
                    user_details = self.find_user_by_user_id(user_id)
                    doc[Keys.Property.property_created_by_details] = user_details
                    property_list.append(doc)
            # But property id is present
            else:
                # Get all the properties from the collection
                property_details = get_document(self.property_collection, Keys.Property.property_id, property_id)
                user_id = property_details.get(Keys.Property.property_created_by)
                print(user_id)
                user_details = self.find_user_by_user_id(user_id)
                print(user_details)
                property_details[Keys.Property.property_created_by_details] = user_details
                property_list = [
                    property_details
                ]
        # User id is available
        else:
            # find the user in db
            user = get_document(self.user_collection, Keys.User.user_id, user_id)
            if user is None:
                return response_creator(
                    code=404,
                    message=MESSAGES_LIST[Keys.Messages.user_not_found],
                )

            # But property id is not
            if property_id is None or property_id == "":
                # Get all the properties for the given user
                property_cursor = get_documents(self.property_collection, Keys.Property.property_created_by, user_id)
                property_list = []
                for doc in property_cursor:
                    user_id = doc.get(Keys.Property.property_created_by)
                    user_details = self.find_user_by_user_id(user_id)
                    doc[Keys.Property.property_created_by_details] = user_details
                    property_list.append(doc)
            # And property id is also present
            else:
                # Get the property details for the give property id which belongs to the given user id
                property_details = get_document(self.property_collection, Keys.Property.property_id, property_id)
                user_id = property_details.get(Keys.Property.property_created_by)
                user_details = self.find_user_by_user_id(user_id)
                property_details[Keys.Property.property_created_by_details] = user_details
                property_list = [
                    property_details
                ]

        if property_list is None:
            return response_creator(
                code=409,
                message=MESSAGES_LIST[Keys.Messages.invalid_request],
            )

        return response_creator(
            code=200,
            message=MESSAGES_LIST.get(Keys.Messages.properties_found),
            other_data={
                "count": property_list.__len__(),
                "data": property_list
            }
        )

    def post(self):
        # headers
        # Check for app token to validate request
        app_check_token = request.headers[Keys.Rental.firebase_app_check_token]
        if app_check_token is None or app_check_token == "":
            return response_creator(
                code=401,
                message=MESSAGES_LIST[Keys.Messages.cannot_validate_request],
            )
        user_id = request.headers[Keys.User.user_id]

        # check if the user already exists
        user = get_document(self.user_collection, Keys.User.user_id, user_id)
        property_created_by = user.get(Keys.User.user_id)
        if user is None or property_created_by is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST[Keys.Messages.user_not_found],
            )

        property_form = request.form.to_dict()

        property_id = property_form.get(Keys.Property.property_id)
        if property_id is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.Property.property_id, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.Property.property_id.split("_")))
            )
        else:
            property_details = get_document(self.property_collection, Keys.Property.property_id, property_id)
            if property_details is not None:
                property_name = property_details.get(Keys.Property.property_name)
                return response_creator(
                    code=409,
                    message=MESSAGES_LIST.get(Keys.Messages.property_already_available).format(property_name)
                )

        property_name = property_form.get(Keys.Property.property_name)
        is_rental_owner = property_form.get(Keys.Property.is_rental_owner)
        is_for_rental = property_form.get(Keys.Property.is_for_rental)
        property_for = property_form.get(Keys.Property.property_for)
        furnished_type = property_form.get(Keys.Property.furnished_type)
        property_type = property_form.get(Keys.Property.property_type)
        number_of_bathrooms = property_form.get(Keys.Property.number_of_bathrooms)
        where_it_is = property_form.get(Keys.Property.where_it_is)
        yearly_deposit = property_form.get(Keys.Property.yearly_deposit)
        monthly_rent = property_form.get(Keys.Property.monthly_rent)
        address = property_form.get(Keys.Property.address)
        perks = property_form.get(Keys.Property.perks)
        agreement_rules = property_form.get(Keys.Property.agreement_rules)
        property_images = property_form.get(Keys.Property.property_images)
        property_created_on = get_current_timestamp()
        property_updated_on = get_current_timestamp()

        if property_name is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.Property.property_name, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.Property.property_name.split("_")))
            )
        if is_rental_owner is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.Property.is_rental_owner, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.Property.is_rental_owner.split("_")))
            )
        if is_for_rental is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.Property.is_for_rental, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.Property.is_for_rental.split("_")))
            )
        if property_for is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.Property.property_for, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.Property.property_for.split("_")))
            )
        if furnished_type is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.Property.furnished_type, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.Property.furnished_type.split("_")))
            )
        if property_type is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.Property.property_type, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.Property.property_type.split("_")))
            )
        if number_of_bathrooms is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.Property.number_of_bathrooms, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.Property.number_of_bathrooms.split("_")))
            )
        if where_it_is is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.Property.where_it_is, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.Property.where_it_is.split("_")))
            )
        if yearly_deposit is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.Property.yearly_deposit, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.Property.yearly_deposit.split("_")))
            )
        if monthly_rent is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.Property.monthly_rent, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.Property.monthly_rent.split("_")))
            )
        if address is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.Property.address, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.Property.address.split("_")))
            )
        if perks is None:
            perks = ""
        if agreement_rules is None:
            agreement_rules = ""
        if property_images is None:
            property_images = []

        # Payload
        property_details = PropertyDetails(
            property_id=property_id,
            property_name=property_name,
            property_created_by=property_created_by,
            is_rental_owner=is_rental_owner,
            is_for_rental=is_for_rental,
            property_for=property_for,
            furnished_type=furnished_type,
            property_type=property_type,
            number_of_bathrooms=number_of_bathrooms,
            where_it_is=where_it_is,
            yearly_deposit=yearly_deposit,
            monthly_rent=monthly_rent,
            address=address,
            perks=perks,
            agreement_rules=agreement_rules,
            property_created_on=property_created_on,
            property_updated_on=property_updated_on,
            property_images=property_images,
        )

        # add user details to mongo db
        insert_document(
            self.property_collection,
            {
                "_id": property_details.property_id,
                **property_details._asdict(),
            }
        )

        # get the user details from db
        db_property_details = get_document(self.property_collection, Keys.Property.property_id, property_id)

        return response_creator(
            code=200,
            message=MESSAGES_LIST.get(Keys.Messages.property_created),
            other_data={
                "data": {
                    **db_property_details
                }
            }
        )
