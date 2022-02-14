"""A collection of all the utility methods"""

import platform
import socket
from loguru import logger
from pyblog import SystemDetails
from .http_client import get_request
from .constants import Constants


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

    try:
        ip_address = get_request(
            Constants.URLs.IP_ADDRESS
        ).ip
    except Exception as e:
        logger.exception(e)
        host_name = socket.gethostname()
        ip_address = socket.gethostbyname(host_name)

    return SystemDetails(
        system=system,
        architecture=architecture,
        machine=machine,
        processor=processor,
        version=version,
        python_version=python_version,
        ip_address=ip_address
    )
