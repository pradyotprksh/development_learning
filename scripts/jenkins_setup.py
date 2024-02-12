import os
import shutil

if os.path.isdir('JenkinsSetup'):
    os.system("cd JenkinsSetup")
    current_directory = os.getcwd()
    shutil.rmtree(current_directory)

os.system("git clone git@github.com:pradyotprksh/JenkinsSetup.git")