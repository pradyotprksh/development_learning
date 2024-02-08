import os
import sys

with open("local.properties","w") as file:
    file.write("sdk.dir=/Users/pradyotprakash/Library/Android/sdk")

os.system("chmod +x ./gradlew")

current_dir = sys.argv[0]

# Google services file check
google_services_file = ""

os.system("pwd")
print(current_dir)
