import zipfile
import shutil


def using_zipfile():
    f = open("data/fileone.txt", "w+")
    f.write("File One")
    f.close()

    f = open("data/filetwo.txt", "w+")
    f.write("File Two")
    f.close()

    f = open("data/filethree.txt", "w+")
    f.write("File Three")
    f.close()

    # zip
    comp_file = zipfile.ZipFile("data/comp_file.zip", "w")
    comp_file.write("data/fileone.txt", compress_type=zipfile.ZIP_DEFLATED)
    comp_file.write("data/filetwo.txt", compress_type=zipfile.ZIP_DEFLATED)
    comp_file.write("data/filethree.txt", compress_type=zipfile.ZIP_DEFLATED)
    comp_file.close()

    # unzip
    zip_object = zipfile.ZipFile("data/comp_file.zip", "r")
    zip_object.extractall(path="data/extracted_content")


def using_shutil():
    dir_to_zip = "data/extracted_content"
    output_file_name = "data/shutil/example"
    shutil.make_archive(output_file_name, "zip", dir_to_zip)

    shutil.unpack_archive("data/shutil/example.zip", "data/shutil/unzip", "zip")


def zip_unzip_files():
    """
    Let's see how to zip and unzip files in Python
    :return: None
    """

    using_zipfile()
    using_shutil()
