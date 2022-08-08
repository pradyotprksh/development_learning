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
    ]
)
