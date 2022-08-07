from collections import namedtuple
from src.utils.constants import Keys

UserDetails = namedtuple(
    "UserDetails",
    [
        Keys.User.user_id,
        Keys.User.first_name,
        Keys.User.last_name,
        Keys.User.permanent_address,
    ]
)
