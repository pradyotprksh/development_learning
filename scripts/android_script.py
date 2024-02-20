import configparser
import json
import os
import shutil
import subprocess
import sys

current_dir = sys.argv[1]
config = configparser.ConfigParser()

def convert_to_int_with_fallback(value: int, fallback_value=0) -> int:
    try:
        integer_value = int(value)
        return integer_value
    except ValueError:
        print(f"Error: '{value}' is not a valid integer. Using fallback value.")
        return fallback_value

def get_jenkins_setup_dir() -> str:
    android_path = f"/android/{current_dir}"
    parent_path = os.getcwd().replace(android_path, "")
    return f"{parent_path}/JenkinsSetup"

def is_directory_avaiable(dir_path) -> bool:
    return os.path.isdir(dir_path)

def is_file_available(file_path) -> bool:
    return os.path.isfile(file_path)

def get_confidential_content(key: str):
    confidential_file_path = f"{get_jenkins_setup_dir()}/confidential.json"
    try:
        with open(confidential_file_path, 'r') as file:
            data = json.load(file)
        value = data[key]
        return value
    except FileNotFoundError:
        print(f"Error: File '{confidential_file_path}' not found.")
    except json.JSONDecodeError:
        print(f"Error: Invalid JSON format in file '{confidential_file_path}'.")
    except KeyError:
        print(f"Error: Key '{key}' not found in the JSON data.")
    return "***"

def get_local_properties_content() -> str:
    pass

def update_local_properties_file():
    local_properties_value = get_confidential_content("ANDROID_SDK_PATH")
    with open("local.properties", "w") as file:
        file.write(f"sdk.dir={local_properties_value}")

def add_google_services_file():
    app_google_service_dir = f"{os.getcwd()}/app"

    parent_dir = ".."
    slash_count = current_dir.count("/")
    for _ in range(slash_count):
        parent_dir = f"../{parent_dir}"

    google_services_dir = f"{get_jenkins_setup_dir()}/{current_dir}"

    if is_directory_avaiable(google_services_dir):
        print(f"Copying google-services.json to {current_dir}")
        shutil.copy2(f"{google_services_dir}/google-services.json", app_google_service_dir)

def get_last_commit_changed_files() -> str:
    try:
        root_path = subprocess.run(['git', 'rev-parse', '--show-toplevel'], stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True, check=True).stdout.strip()
        status_output = subprocess.run(['git', 'status', '--short'], cwd=root_path, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True, check=True)
        changed_files = [line.split(' ', 1)[1] for line in status_output.stdout.split('\n') if line.startswith(' M') or line.startswith('A  ')]
        return '\n'.join(changed_files)
    except subprocess.CalledProcessError as e:
        print("Error get_last_commit_changed_files:", e.stderr)

def get_last_commit_message() -> str:
    try:
        root_path = subprocess.run(['git', 'rev-parse', '--show-toplevel'], stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True, check=True).stdout.strip()
        result = subprocess.run(['git', 'log', '-1', '--pretty=%B'], cwd=root_path, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True, check=True)
        return result.stdout.strip()
    except subprocess.CalledProcessError as e:
        print("Error get_last_commit_message:", e.stderr)
        return ""

def get_last_commit_author() -> str:
    try:
        result = subprocess.run(['git', 'log', '-1', '--pretty=%an <%ae>'], stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True, check=True)
        return result.stdout.strip()
    except subprocess.CalledProcessError as e:
        print("Error get_last_commit_author:", e.stderr)
        return ""

def get_next_version_name(version_name: str) -> str:
    version_split = version_name.split(".")
    last_version = convert_to_int_with_fallback(version_split[2], 0) + 1
    return f"{version_split[0]}.{version_split[1]}.{last_version}"

def update_version_name():
    application_details_file = f"{os.getcwd()}/application_details.properties"

    if is_file_available(application_details_file):
        config.read(application_details_file)
        version_code = config.get("version_code")
        version_name = config.get("version_name")

        new_version_code = convert_to_int_with_fallback(version_code, 1)
        new_version_name = get_next_version_name(version_name)

        with open(application_details_file, "w") as file:
            content = file.read()
            content.replace(f"version_code={version_code}", f"version_code={new_version_code}")
            content.replace(f"version_name={version_name}", f"version_name={new_version_name}")

        with open(application_details_file, "r") as file:
            print(file.read())

def update_release_notes_for_debug():
    commit_message = get_last_commit_message()
    author_name = get_last_commit_author()
    changed_files = get_last_commit_changed_files()

    notes_message = f"Author: {author_name}\n\nCommit Message:\n{commit_message}\n\nChanged Files:\n{changed_files}"

    release_notes_file = f"{os.getcwd()}/releasenotes_release.txt"
    debug_notes_file = f"{os.getcwd()}/releasenotes_debug.txt"

    if is_file_available(release_notes_file):
        with open(release_notes_file, "w") as file:
            file.write(notes_message)
    else:
        print("Release notes file not avaiable")

    if is_file_available(debug_notes_file):
        with open(debug_notes_file, "w") as file:
            file.write(notes_message)
    else:
        print("Debug notes file not avaiable")

# update_local_properties_file()
add_google_services_file()
update_release_notes_for_debug()
update_version_name()
