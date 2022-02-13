""""""

import json
import urllib3
from collections import namedtuple


http = urllib3.PoolManager()


def _custom_decoder(result):
    return namedtuple('X', result.keys())(*result.values())


def get_request(url):
    """
    Make a get request for the given url
    :param url: Make a request to which place
    :return: JSON data
    """
    request = http.request('GET', url)
    return json.loads(request.data.decode('utf-8'), object_hook=_custom_decoder)
