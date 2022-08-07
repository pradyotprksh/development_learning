USER_TYPE = ["owner", "renter"]
MONGO_DB_DETAILS_FILE = "./data/confidential/mongo_db_details"


class Keys:
    class User:
        collection_name = "user"
        user_id = "user_id"
        first_name = "first_name"
        last_name = "last_name"
        permanent_address = "permanent_address"
