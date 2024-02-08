import os

if os.path.isdir('JenkinsSetup'):
    print("JenkinsSetup already cloned")
    print("Updating code")
    os.system("git fetch origin")
else:
    os.system("git clone git@github.com:pradyotprksh/JenkinsSetup.git")