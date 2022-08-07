from .constants import MONGO_DB_DETAILS_FILE


def get_mongo_details():
    """Get the mongo db details from the file, since the detail is confidential it will not be added
    to the gitignore"""
    with open(MONGO_DB_DETAILS_FILE) as mongo_db:
        content = mongo_db.read()
        return content
