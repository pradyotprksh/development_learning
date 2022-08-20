"""
Default response module

A module which will create the response for any calls and send it back, since the structure of the response
is almost same for all, so it is better to have it this way.
"""


def response_creator(code, message, other_data=None):
    """A response creator which will create the response and sends it back.

    @:arg code Response code for the response
    @:arg message Human readable message to be shown when the response is received
    @:arg other_data Dictionary of extra data to be sent, if needed
    """
    if other_data is None:
        return {
            "code": code,
            "message": message
        }, code
    return {
        "code": code,
        "message": message,
        **other_data,
    }, code
