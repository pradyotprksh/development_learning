"""
User Module

A module for user which will contain all the basic calls made to Renter project like
get, update, etc.

This will help in making the api file cleaner and making the refactoring easy.
"""
from flask_restful import Resource
from flask import request
from src.core.modals import UserDetails
from src.core.services.db import get_collection, get_document, insert_document, update_a_document, get_documents
from src.utils.constants import Keys, MESSAGES_LIST, DEFAULT_ERROR_MESSAGE, USER_TYPE
from src.utils.response_mapper import response_creator
from src.utils.util_calls import is_email_address_valid, get_current_timestamp


class User:
    """A User class which will initiate all the resources required for the User module
    to execute the operations

    * [api] : An API instance of the flask_restful which will be used to add resources for renter class.
    """

    def __init__(self, api):
        self.common_path = "/renter/user"
        api.add_resource(_User, f"{self.common_path}/")


class _User(Resource):
    """A User class which will help in handling all the requests made on
    <path>/user/

    headers: user_id
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
        user_id = request.headers[Keys.User.user_id]

        # find the user in db
        user = get_document(self.user_collection, Keys.User.user_id, user_id)
        if user is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST[Keys.Messages.user_not_found],
            )

        # Get properties added by the user
        property_cursor = get_documents(self.property_collection, Keys.Property.property_created_by, user_id)
        property_list = []
        for doc in property_cursor:
            property_list.append(doc)
        user[Keys.User.properties] = property_list

        return response_creator(
            code=200,
            message=MESSAGES_LIST.get(Keys.Messages.user_found),
            other_data={
                "data": {
                    **user
                }
            }
        )

    def patch(self):
        # TODO: Fix update call getting <lambda>() got an unexpected keyword argument 'account_updated_on' error

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

        if user is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST[Keys.Messages.user_not_found],
            )

        user_form = request.form.to_dict()

        first_name = user_form.get(Keys.User.first_name, user.get(Keys.User.first_name))
        last_name = user_form.get(Keys.User.last_name, user.get(Keys.User.last_name))
        permanent_address = user_form.get(Keys.User.permanent_address, user.get(Keys.User.permanent_address))
        date_of_birth = user_form.get(Keys.User.date_of_birth, user.get(Keys.User.date_of_birth))
        email_address = user_form.get(Keys.User.email_address)
        profession = user_form.get(Keys.User.profession, user.get(Keys.User.profession))
        phone_number = user_form.get(Keys.User.phone_number, user.get(Keys.User.phone_number))
        profile_pic_url = user_form.get(Keys.User.profile_pic_url, user.get(Keys.User.profile_pic_url))
        user_type = user_form.get(Keys.User.user_type, user.get(Keys.User.user_type))
        account_created_on = user.get(Keys.User.account_created_on)
        is_all_details_available = user_form.get(Keys.User.is_all_details_available,
                                                 user.get(Keys.User.is_all_details_available, False))
        account_updated_on = get_current_timestamp()

        if first_name is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.User.first_name, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.User.first_name.split("_")))
            )
        if account_created_on is None:
            account_created_on = get_current_timestamp()
        if email_address is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.User.email_address, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.User.email_address.split("_")))
            )
        if not is_email_address_valid(email=email_address):
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.Messages.invalid_input, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.User.email_address.split("_")))
            )
        if email_address != user.get(Keys.User.email_address):
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.Messages.can_not_update_value)
                .format(" ".join(Keys.User.email_address.split("_")))
            )
        if user_type not in USER_TYPE:
            return response_creator(
                code=404,
                message=MESSAGES_LIST[Keys.Messages.invalid_user_type].format(user_type)
            )

        # payload
        user_details = UserDetails(
            user_id=user_id,
            first_name=first_name,
            last_name=last_name,
            permanent_address=permanent_address,
            date_of_birth=date_of_birth,
            email_address=email_address,
            profession=profession,
            phone_number=phone_number,
            profile_pic_url=profile_pic_url,
            user_type=user_type,
            is_all_details_available=is_all_details_available,
            account_created_on=account_created_on,
            account_updated_on=account_updated_on,
        )

        # add user details to mongo db
        update_a_document(
            self.user_collection,
            Keys.User.user_id,
            user_id,
            user_details._asdict(),
        )

        # get the user details from db
        db_user_details = get_document(self.user_collection, Keys.User.user_id, user_id)

        return response_creator(
            code=200,
            message=MESSAGES_LIST.get(Keys.Messages.user_created),
            other_data={
                "data": {
                    **db_user_details
                }
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
        if user is not None:
            username = f"{user.get(Keys.User.first_name)} {user.get(Keys.User.last_name)}"

            return response_creator(
                code=409,
                message=MESSAGES_LIST.get(Keys.Messages.user_already_available).format(username)
            )

        user_form = request.form.to_dict()

        first_name = user_form.get(Keys.User.first_name)
        last_name = user_form.get(Keys.User.last_name, "")
        permanent_address = user_form.get(Keys.User.permanent_address, "")
        date_of_birth = user_form.get(Keys.User.date_of_birth, "")
        email_address = user_form.get(Keys.User.email_address)
        profession = user_form.get(Keys.User.profession, "")
        phone_number = user_form.get(Keys.User.phone_number, "")
        profile_pic_url = user_form.get(Keys.User.profile_pic_url, "")
        user_type = user_form.get(Keys.User.user_type)
        account_created_on = user_form.get(Keys.User.account_created_on)
        account_updated_on = user_form.get(Keys.User.account_updated_on)

        if first_name is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.User.first_name, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.User.first_name.split("_")))
            )
        if account_created_on is None:
            account_created_on = get_current_timestamp()
        if account_updated_on is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.User.account_updated_on, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.User.account_updated_on.split("_")))
            )
        if email_address is None:
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.User.email_address, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.User.email_address.split("_")))
            )
        if not is_email_address_valid(email=email_address):
            return response_creator(
                code=404,
                message=MESSAGES_LIST.get(Keys.Messages.invalid_input, DEFAULT_ERROR_MESSAGE)
                .format(" ".join(Keys.User.email_address.split("_")))
            )
        if user_type not in USER_TYPE:
            return response_creator(
                code=404,
                message=MESSAGES_LIST[Keys.Messages.invalid_user_type].format(user_type)
            )

        # payload
        user_details = UserDetails(
            user_id=user_id,
            first_name=first_name,
            last_name=last_name,
            permanent_address=permanent_address,
            date_of_birth=date_of_birth,
            email_address=email_address,
            profession=profession,
            phone_number=phone_number,
            profile_pic_url=profile_pic_url,
            user_type=user_type,
            account_created_on=account_created_on,
            account_updated_on=account_updated_on,
        )

        # add user details to mongo db
        insert_document(
            self.user_collection,
            {
                "_id": user_details.user_id,
                **user_details._asdict(),
            }
        )

        # get the user details from db
        db_user_details = get_document(self.user_collection, Keys.User.user_id, user_id)

        return response_creator(
            code=200,
            message=MESSAGES_LIST.get(Keys.Messages.user_created),
            other_data={
                "data": {
                    **db_user_details
                }
            }
        )
