"""
Constant module

A module for constants which will be used to add all the constants needed for the project.

This will help in not hard coding the values needed at many places.
"""
# Project name for Firebase
PROJECT_NAME = "Rental"
# Renter user type
RENTER_USER_TYPE = "Renter"
# Owner user type
OWNER_USER_TYPE = "Owner"
# Type of the user
USER_TYPE = [RENTER_USER_TYPE, OWNER_USER_TYPE]
# Path for the file which contains the mongo db
MONGO_DB_DETAILS_FILE = "./data/confidential/mongo_db_details"
# Path for information HTML file
INFORMATION_HTML_FILE = "information.html"
# Path for the file which contains the firebase admin account details
FIREBASE_SERVICE_ACCOUNT = "./data/confidential/renterServiceAccountKey.json"
# Default error message
DEFAULT_ERROR_MESSAGE = "Something went wrong with parsing the request. Please check and try again."
# Default error message for data not present
DEFAULT_FORM_DATA_NOT_AVAILABLE = "Missing your {} in the form"
# Default error message for data invalid
DEFAULT_INVALID_DATA = "Invalid {}, please provide a valid one."
# Default no issue message for data
DEFAULT_VALID_DATA = "Detail/s provided are correct."
# Invalid request
DEFAULT_INVALID_REQUEST_PARAMS = "Invalid request details were provided"


class Endpoints:
    """An endpoint constant class which contains all the endpoints"""

    class Renter:
        """A renter endpoint lists"""
        home = "/"
        terms_and_condition = "/terms_condition/<string:user_type>"
        information = "/information"
        email = "/email/<string:email_address>"

    class Users:
        """A user endpoint lists"""
        wishlist = "/wishlist"

    class Property:
        """A property endpoint lists"""
        proposals = "/proposals"


class Keys:
    """A Keys class which contains all the keys used everywhere"""

    class Rental:
        """A set of common keys used in the whole application"""
        firebase_app_check_token = "X-Firebase-AppCheck"

    class Messages:
        """A set of keys related to messages"""
        invalid_input = "invalid_input"
        invalid_request = "invalid_request"
        renter_details = "renter_details"
        invalid_user_type = "invalid_user_type"
        terms_condition_message = "terms_condition_message"
        information_message = "information_message"
        user_not_found = "user_not_found"
        property_not_found = "property_not_found"
        property_found = "property_found"
        properties_not_found = "properties_not_found"
        properties_found = "properties_found"
        location_found = "location_found"
        location_not_found = "location_not_found"
        user_found = "user_found"
        user_already_available = "user_already_available"
        property_already_available = "property_already_available"
        user_created = "user_created"
        property_created = "property_created"
        property_updated = "property_updated"
        valid = "valid"
        can_not_update_value = "can_not_update_value"
        cannot_validate_request = "cannot_validate_request"
        search_result = "search_result"
        search_error = "search_error"
        invalid_user_property_update = "invalid_user_property_update"
        wishlist_already_added = "wishlist_already_added"
        wishlist_created = "wishlist_created"
        wishlist_found = "wishlist_found"
        wishlist_not_found = "wishlist_not_found"
        wishlist_deleted = "wishlist_deleted"
        wishlist_deleted_error = "wishlist_deleted_error"
        proposal_deleted_error = "proposal_deleted_error"
        need_agreement_conformation = "need_agreement_conformation"
        proposal_already_added = "proposal_already_added"
        proposal_created = "proposal_created"
        proposal_updated = "proposal_updated"
        proposal_not_found = "proposal_not_found"
        proposal_deleted = "proposal_deleted"

    class User:
        """A set of keys related to the User section"""
        collection_name = "user"
        user_id = "user_id"
        first_name = "first_name"
        last_name = "last_name"
        permanent_address = "permanent_address"
        date_of_birth = "date_of_birth"
        email_address = "email_address"
        profession = "profession"
        phone_number = "phone_number"
        profile_pic_url = "profile_pic_url"
        user_type = "user_type"
        is_all_details_available = "is_all_details_available"
        account_created_on = "account_created_on"
        account_updated_on = "account_updated_on"
        properties = "properties"
        nearby_properties = "nearby_properties"
        other_properties = "other_properties"

    class Property:
        """A set of keys related to property section"""
        collection_name = "property"
        property_id = "property_id"
        property_name = "property_name"
        property_images = "property_images"
        is_rental_owner = "is_rental_owner"
        is_for_rental = "is_for_rental"
        property_for = "property_for"
        furnished_type = "furnished_type"
        property_type = "property_type"
        number_of_bathrooms = "number_of_bathrooms"
        where_it_is = "where_it_is"
        yearly_deposit = "yearly_deposit"
        monthly_rent = "monthly_rent"
        address = "address"
        display_name = "display_name"
        perks = "perks"
        agreement_rules = "agreement_rules"
        property_created_on = "property_created_on"
        property_updated_on = "property_updated_on"
        property_created_by = "property_created_by"
        property_created_by_details = "property_created_by_details"
        latitude = "lat"
        longitude = "lon"
        distance = "distance"
        is_in_wishlist = "is_in_wishlist"
        proposal_details = "proposal_details"
        proposals = "proposals"
        proposals_created_by_details = "proposals_created_by_details"

    class Search:
        """A set of keys related to search section"""
        search_text = "search_text"
        zip_code = "zip_code"
        latitude = "latitude"
        longitude = "longitude"
        exactly_one = "exactly_one"
        user_type = "user_type"

    class Wishlist:
        """A set of keys related to wishlist section"""
        collection_name = "wishlist"
        property_id = "property_id"
        created_by = "created_by"
        created_on = "created_on"
        wishlist_id = "wishlist_id"
        property_details = "property_details"

    class Proposals:
        """A set of keys related to proposals section"""
        collection_name = "proposal"
        proposal_id = "proposal_id"
        user_id = "user_id"
        property_id = "property_id"
        confirm_rent = "confirm_rent"
        rent_proposal = "rent_proposal"
        confirm_deposit = "confirm_deposit"
        deposit_proposal = "deposit_proposal"
        confirm_agreements = "confirm_agreements"
        created_on = "created_on"
        updated_on = "updated_on"
        proposal_status = "proposal_status"

    class Filter:
        """A set of keys related to filter section"""
        property_name = "property_name"
        owner_name = "owner_name"
        property_address = "property_address"
        listed_by_owner = "listed_by_owner"
        for_rental = "for_rental"
        property_for = "property_for"
        furnished_type = "furnished_type"
        property_type = "property_type"
        number_of_bathrooms = "number_of_bathrooms"
        where_it_is = "where_it_is"
        yearly_deposit_start = "yearly_deposit_start"
        yearly_deposit_end = "yearly_deposit_end"
        monthly_rent_start = "monthly_rent_start"
        monthly_rent_end = "monthly_rent_end"
        property_updated_on_start = "property_updated_on_start"
        property_updated_on_end = "property_updated_on_end"
        distance_start = "distance_start"
        distance_end = "distance_end"


# Dictionary of messages
MESSAGES_LIST = {
    Keys.User.first_name: DEFAULT_FORM_DATA_NOT_AVAILABLE,
    Keys.User.email_address: DEFAULT_FORM_DATA_NOT_AVAILABLE,
    Keys.Messages.invalid_input: DEFAULT_INVALID_DATA,
    Keys.Messages.invalid_request: DEFAULT_INVALID_REQUEST_PARAMS,
    Keys.Messages.renter_details: "Hello User to Renter, this is a Python created RESTFUL API which is used by "
                                  "our Renter Android application. If you want to you can also use this and make "
                                  "requests.",
    Keys.Messages.invalid_user_type: "{} is not a valid user type, Please provide a valid one.",
    Keys.Messages.terms_condition_message: "Hello {}, this is to inform you that you will be virtually signing our "
                                           "terms and condition.",
    Keys.Messages.information_message: "Owner and Renter Details are as below.",
    Keys.Messages.user_not_found: "Asked user is not present. Ask the user to create an account or try again.",
    Keys.Messages.user_found: "User found",
    Keys.Messages.user_already_available: "{} exists, you are trying to create one which is already there.",
    Keys.Messages.property_already_available: "{} exists, you are trying to create one which is already there.",
    Keys.Messages.user_created: "User created",
    Keys.Messages.valid: "Valid",
    Keys.Messages.can_not_update_value: "Cannot update {} to a new one.",
    Keys.Messages.cannot_validate_request: "We were not able to validate the given request. Seems like some suspicious "
                                           "activity is going on, or something wrong from our end to validate"
                                           " the request. Please try again.",
    Keys.Messages.property_created: "Property created",
    Keys.Messages.property_found: "Property found",
    Keys.Messages.properties_found: "Properties found",
    Keys.Messages.property_not_found: "Asked property is not present. Please create the property or try again.",
    Keys.Messages.properties_not_found: "Properties couldn't be found",
    Keys.Messages.search_result: "Search result",
    Keys.Messages.property_updated: "Property details updated",
    Keys.Messages.invalid_user_property_update: "Only the user who created the property can edit it. Please ask the "
                                                "respective property creator to edit it.",
    Keys.Messages.location_found: "Location found",
    Keys.Messages.location_not_found: "Sorry the requested location was not found. For better results use all available"
                                      " options. We are using a free location service for now.",
    Keys.Messages.wishlist_already_added: "{} property is already in your wishlist.",
    Keys.Messages.proposal_already_added: "{} property has a proposal already made by you.",
    Keys.Messages.wishlist_created: "Wishlist created",
    Keys.Messages.wishlist_found: "Wishlist found",
    Keys.Messages.proposal_created: "Proposal created",
    Keys.Messages.proposal_updated: "Proposal updated",
    Keys.Messages.wishlist_not_found: "{} not found in your wishlist.",
    Keys.Messages.proposal_not_found: "No proposal found for the {}",
    Keys.Messages.wishlist_deleted: "{} removed from your wishlist.",
    Keys.Messages.proposal_deleted: "Your proposal for {} was deleted.",
    Keys.Messages.wishlist_deleted_error: "We were not able to remove {} from your wishlist. Please try again.",
    Keys.Messages.proposal_deleted_error: "We were not able to remove the proposal for {}. Please try again.",
    Keys.Messages.need_agreement_conformation: "You need to confirm to the agreement. Stating you are okay with "
                                               "the points mentioned by the owner.",
}
