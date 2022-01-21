from src import log_debug, log_exception


def read_files():
    """
    Read files operation in Python
    :return:
    """
    try:
        my_file = open("data/testing.txt")
        log_debug(my_file)

        log_debug(my_file.read())

        # This will print nothing because when we read once from the file there is a cursor which is moved to
        # the end of the file. So when we read again it returns empty string.
        log_debug(my_file.read())
        # To fix the above issue use seek
        my_file.seek(0)
        log_debug(my_file.read())
        my_file.seek(0)

        log_debug(my_file.readlines())

        # Have to close the file after use
        my_file.close()
        # Another way to open file without explicitly closing it
        with open("data/testing.txt") as new_file:
            contents = new_file.read()
            log_debug(contents)
    except Exception as e:
        log_exception(e)


def write_files():
    """
    Write operation on files in Python
    :return:
    """

    try:
        with open("data/new_test.txt", mode="r") as my_file:
            content = my_file.read()
            log_debug(content)

        try:
            # w mode is for writing on a file. But the below code will throw error because
            # when we set write mode we can only write to a file. Not read from it.
            #
            with open("data/new_test.txt", mode="w") as my_file:
                content = my_file.read()
                log_debug(content)
        except Exception as e:
            log_exception(e)

        # we have to use different mode for the above issue
        # r = read
        # w = write only (overwrite existing file or create new)
        # a = append only
        # r+ = reading and writing
        # w+ = writing and reading (overwrite existing file or create new)

        with open("data/my_new_file.txt", mode="r") as f:
            log_debug(f.read())
        # TODO: Uncomment this when done
        # with open("data/my_new_file.txt", mode="a") as f:
        #     f.write("\nFOUR ON FOURTH")
        with open("data/my_new_file.txt", mode="r") as f:
            log_debug(f.read())
        with open("data/test_new_file.txt", mode="w") as f:
            f.write("I created this file")
        with open("data/test_new_file.txt", mode="r") as f:
            log_debug(f.read())
    except Exception as e:
        log_exception(e)


def io_with_files_basics():
    """
    Let's check on I/O with files in Python
    :return:
    """

    read_files()
    write_files()
