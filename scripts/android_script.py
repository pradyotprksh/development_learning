import os
import shutil
import sys

with open("local.properties","w") as file:
    file.write("sdk.dir=/Users/pradyotprakash/Library/Android/sdk")

os.system("chmod +x ./gradlew")

current_dir = sys.argv[1]

# Google services file check
app_google_service_dir = f"{current_dir}/app"

parent_dir = ".."
slash_count = current_dir.count("/")
for _ in range(slash_count):
    parent_dir = f"../{parent_dir}"

google_services_dir = f"{parent_dir}/JenkinsSetup/{current_dir}/google-services.json"

print("+++++++++++++++++++")
print(f"google_services_dir {google_services_dir} app_google_service_dir {app_google_service_dir}")
print("+++++++++++++++++++")

if os.path.isdir(google_services_dir):
    print(f"Copying google-services.json from {google_services_dir} to {app_google_service_dir}")
    shutil.copy2(google_services_dir, app_google_service_dir)
