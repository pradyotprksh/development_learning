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

def get_local_properties_content() -> str:

def update_local_properties_file():
    with open("local.properties", "w") as file:
        file.write("sdk.dir=/Users/pradyotprakash/Library/Android/sdk")

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

    

update_local_properties_file()
add_google_services_file()
update_release_notes_for_debug()
