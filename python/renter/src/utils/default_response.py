def response_creator(code, message, other_data=None):
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
