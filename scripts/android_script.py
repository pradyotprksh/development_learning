import os

with open("local.properties","w") as file:
    file.write("sdk.dir=/Users/pradyotprakash/Library/Android/sdk")

os.system("chmod +x ./gradlew")
