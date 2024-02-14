import json
import os
import shutil
import subprocess
import sys

current_dir = sys.argv[1]

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
        changed_files = subprocess.run(['git', 'diff', '--name-only'], cwd=root_path, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True, check=True)
        return changed_files.stdout.strip()
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

def update_release_notes_for_debug():
    commit_message = get_last_commit_message()
    author_name = get_last_commit_author()
    changed_files = get_last_commit_changed_files()

    notes_message = f"Author: {author_name}\n\nCommit Message:\n{commit_message}\n\nChanged Files:\n{changed_files}"

    print(notes_message)

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

update_local_properties_file()
add_google_services_file()
update_release_notes_for_debug()
