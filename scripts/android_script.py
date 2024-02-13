import os
import shutil
import sys

def update_local_properties_file():
    with open("local.properties","w") as file:
        file.write("sdk.dir=/Users/pradyotprakash/Library/Android/sdk")

def add_google_services_file():
    current_dir = sys.argv[1]

    app_google_service_dir = f"/android/{current_dir}"
    current_wd = os.getcwd().replace(app_google_service_dir, "")
    app_google_service_dir = f"{current_wd}{app_google_service_dir}/app"

    parent_dir = ".."
    slash_count = current_dir.count("/")
    for _ in range(slash_count):
        parent_dir = f"../{parent_dir}"

    google_services_dir = f"{current_wd}/JenkinsSetup/{current_dir}"

    if os.path.isdir(google_services_dir):
        print(f"Copying google-services.json to {current_dir}")
        shutil.copy2(f"{google_services_dir}/google-services.json", app_google_service_dir)

def update_release_notes_for_debug():
    pass

update_local_properties_file()
add_google_services_file()
update_release_notes_for_debug()
