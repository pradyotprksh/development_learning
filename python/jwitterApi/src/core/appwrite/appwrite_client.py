from appwrite.client import Client
from appwrite.id import ID
from appwrite.services.users import Users

from data import ProjectDetails


def get_app_write_client() -> Client:
    return Client() \
        .set_endpoint(ProjectDetails.endpoint) \
        .set_project(ProjectDetails.project_id) \
        .set_key(ProjectDetails.api_key)


def get_users() -> Users:
    return Users(get_app_write_client())


def get_random_id() -> str:
    return ID.unique()
