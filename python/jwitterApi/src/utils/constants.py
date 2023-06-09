"""
Constant module

A module for constants which will be used to add all the constants needed for the project.

This will help in not hard coding the values needed at many places.
"""


class Endpoints:
    """An endpoint constant class which contains all the endpoints"""
    common_part = "/jwitter/v1"
    user = f"{common_part}/user"
