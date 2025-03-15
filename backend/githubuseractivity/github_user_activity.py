import http.client
import json
import shlex
import sys
import subprocess

GITHUB_ACTIVITY_URL = "api.github.com/users"

def check_input():
    if len(sys.argv) > 1:
        return sys.argv[1]
    else:
        return None
    
def get_request(path):
    connection = http.client.HTTPSConnection("api.github.com")

    try:
        cmd = "curl -L " \
        "-H \"Accept: application/vnd.github+json\" " \
        "-H \"Authorization: Bearer <USER_TOKEN>\" " \
        "-H \"X-GitHub-Api-Version: 2022-11-28\" " \
        "https://api.github.com/users/pradyotprksh/events"
        args = shlex.split(cmd)
        process = subprocess.Popen(args, shell=False, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        stdout, stderr = process.communicate()        
        events = json.loads(stdout)
        return events
    except Exception as e:
        print(f'Exception occurred: {e}')
    finally:
        connection.close()
    
def get_user_activity(username):
    user_activity = get_request(f"/users/{username}/events")

    if user_activity is None:
        print("Not able to get user activity. Check the username or try again.")
        return
    
    for activity in user_activity:
        type = activity["type"]
        repo_name = activity["repo"]["name"]
        print(f"{type} {repo_name}")

if __name__ == '__main__':
    username = check_input()

    if username is None:
        print("Please provide an username")
    else:
        get_user_activity(username)
