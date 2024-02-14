import os
import shutil

if os.path.isdir('JenkinsSetup'):
    os.system("cd JenkinsSetup")
    jenkins_setup_directory = f"{os.getcwd()}/JenkinsSetup"
    print(f"Deleting JenkinsSetup")
    shutil.rmtree(jenkins_setup_directory)

os.system("git clone git@github.com:pradyotprksh/JenkinsSetup.git")