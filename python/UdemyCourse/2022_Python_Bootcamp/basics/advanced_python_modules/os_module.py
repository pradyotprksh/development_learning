from src import log_debug
import os
import shutil


def os_module():
    """
    Let's take a look on the concepts related to opening and reading files and folder in python
    :return: None
    """
    # delete a file
    # this check is required to delete the file if already exist otherwise it will throw exception
    try:
        os.mkdir("data/move")
    except FileExistsError:
        pass
    try:
        os.unlink("data/move/practice.txt")
    except FileNotFoundError:
        pass

    f = open("data/practice.txt", "w+")
    f.write("This is for practice")
    f.close()

    # Get current working directory
    log_debug(os.getcwd())
    # List all files in the current directory
    log_debug(os.listdir())
    # List files for a specific directory
    log_debug(os.listdir("data"))

    # move a file
    shutil.move("data/practice.txt", "data/move/")

    # Get directory tree
    for folder, subFolders, files in os.walk(os.getcwd()):
        log_debug(f"{folder.title()}")
        for subFolder in subFolders:
            log_debug(f"----/{subFolder.title()}/")
        for file in files:
            log_debug(f"--/{file.title()}")
        log_debug("------------------------")
