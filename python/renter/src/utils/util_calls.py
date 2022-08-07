"""
Util class module

A module which contains all the common calls required at most places, or keeping the calls at one place
which doesn't do much.

This helps in organising the stuff which is important and keeping normal things in one place.
"""
from .constants import MONGO_DB_DETAILS_FILE


def get_mongo_details():
    """Get the mongo db details from the file, since the detail is confidential it will not be added
    to the gitignore"""
    with open(MONGO_DB_DETAILS_FILE) as mongo_db:
        content = mongo_db.read()
        return content
