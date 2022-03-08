"""A collection of all the utility methods"""

import platform
import socket
import calendar
import time
from loguru import logger
from pyblog import SystemDetails
from .http_client import get_request
from .constants import Constants
from datetime import date


def get_platform_details():
    """
    Get all the details of the platform on the which the project is running on
    :return: SystemDetails Contains all the details related to the system which are required
    """
    system = platform.system()
    architecture = platform.architecture()[0]
    machine = platform.machine()
    processor = platform.processor()
    version = platform.version()
    python_version = platform.python_version()
    time_stamp = get_current_timestamp()

    try:
        ip_address = get_request(
            Constants.URLs.IP_ADDRESS
        ).ip
    except Exception as exception:
        logger.exception(exception.__str__())
        host_name = socket.gethostname()
        ip_address = socket.gethostbyname(host_name)

    return SystemDetails(
        system=system,
        architecture=architecture,
        machine=machine,
        processor=processor,
        version=version,
        python_version=python_version,
        ip_address=ip_address,
        timestamp=time_stamp
    )


def get_current_date():
    """
    Get the current date of format %B %d, %Y
    :return: Current data
    """
    today = date.today()
    return today.strftime("%B %d, %Y")


def get_current_timestamp():
    """
    Get current timestamp
    :return: Current Timestamp
    """
    return calendar.timegm(time.gmtime())
