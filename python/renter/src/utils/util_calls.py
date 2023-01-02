"""
Util class module

A module which contains all the common calls required at most places, or keeping the calls at one place
which doesn't do much.

This helps in organising the stuff which is important and keeping normal things in one place.
"""
from .constants import MONGO_DB_DETAILS_FILE
from validate_email import validate_email
from geopy.distance import geodesic
import calendar
import time
import datetime
import json


def get_mongo_details():
    """Get the mongo db details from the file, since the detail is confidential it will not be added
    to the gitignore"""
    with open(MONGO_DB_DETAILS_FILE) as mongo_db:
        content = mongo_db.read()
        return content


def is_email_address_valid(email):
    """Returns True is the email address is valid"""
    result = validate_email(email=email)
    if result is None:
        return False
    return result


def get_current_timestamp():
    """Returns the current timestamp"""
    current_gmt = time.gmtime()
    return calendar.timegm(current_gmt)


def convert_date_to_timestamp(date):
    """Returns the given date into timestamp format"""
    return int(time.mktime(datetime.datetime.strptime(date, "%d/%m/%Y").timetuple()))


def convert_string_to_json(value):
    """Returns the JSON format for the given string"""
    return json.loads(value)


def distance_between(coordinate_1, coordinate_2):
    """Returns the distance between 2 coordinates"""
    return round(geodesic(coordinate_1, coordinate_2).km, 2)
