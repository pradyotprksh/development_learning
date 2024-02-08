import os
import sys

with open("local.properties","w") as file:
    file.write("sdk.dir=/Users/pradyotprakash/Library/Android/sdk")

os.system("chmod +x ./gradlew")

current_dir = sys.argv[1]

# Google services file check
parent_dir = ""
slash_count = current_dir.count("/")
for _ in range(slash_count):
    parent_dir = f"../{parent_dir}"

google_services_dir = f"{parent_dir}/JenkinsSetup/{current_dir}"

print(google_services_dir)
