"""A collection of all the models used in the whole application"""

from collections import namedtuple

SystemDetails = namedtuple(
    "SystemDetails",
    [
        "system",
        "architecture",
        "machine",
        "processor",
        "version",
        "python_version",
        "ip_address",
        "timestamp"
    ]
)

UserDetails = namedtuple(
    "UserDetails",
    [
        "display_name",
        "email",
        "phone_number",
        "photo_url",
        "password",
        "email_verified"
    ]
)

BlogDetails = namedtuple(
    "BlogDetails",
    [
        "title",
        "subtitle",
        "tags",
        "blog",
        "email_subscriber",
        "created_on",
        "created_by_uid",
        "views",
        "likes",
        "isDraft",
        "blog_id"
    ]
)
