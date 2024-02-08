import os
import sys

with open("local.properties","w") as file:
    file.write("sdk.dir=/Users/pradyotprakash/Library/Android/sdk")

os.system("chmod +x ./gradlew")

current_dir = sys.argv[0]

# Google services file check
google_services_file = ""

print(os.system("pwd"))
print(os.system(f"{current_dir}"))
