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
        "ip_address"
    ]
)
