"""
Modals Module

A module which contains all the modals/entity related to the project.
"""
from collections import namedtuple
from src.utils.constants import Keys

# A named tuple for the user details
UserDetails = namedtuple(
    "UserDetails",
    [
        Keys.User.user_id,
        Keys.User.first_name,
        Keys.User.last_name,
        Keys.User.permanent_address,
        Keys.User.date_of_birth,
        Keys.User.email_address,
        Keys.User.profession,
        Keys.User.phone_number,
        Keys.User.profile_pic_url,
        Keys.User.user_type,
        Keys.User.is_all_details_available,
        Keys.User.account_created_on,
        Keys.User.account_updated_on,
    ]
)

# A named tuple for the wishlist
WishlistDetails = namedtuple(
    "WishlistDetails",
    [
        Keys.Wishlist.wishlist_id,
        Keys.Wishlist.property_id,
        Keys.Wishlist.created_by,
        Keys.Wishlist.created_on,
    ]
)

# A named tuple for the property
PropertyDetails = namedtuple(
    "PropertyDetails",
    [
        Keys.Property.property_id,
        Keys.Property.property_name,
        Keys.Property.property_created_by,
        Keys.Property.is_rental_owner,
        Keys.Property.is_for_rental,
        Keys.Property.property_for,
        Keys.Property.furnished_type,
        Keys.Property.property_type,
        Keys.Property.number_of_bathrooms,
        Keys.Property.where_it_is,
        Keys.Property.yearly_deposit,
        Keys.Property.monthly_rent,
        Keys.Property.address,
        Keys.Property.perks,
        Keys.Property.agreement_rules,
        Keys.Property.property_created_on,
        Keys.Property.property_updated_on,
        Keys.Property.property_images,
    ]
)
