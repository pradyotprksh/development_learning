## NOTES

### Known Issues
    - If user details are already available still the form page is opening after authentication
    - Need to find a way to save data in firestore for chats so that the each user details can be fetched without storing the actual data. Same issue with status page. Need to find a better way to get user details from the id rather than saving each details in every documents.
        - Fixed by adding user list listener top of the status and chat listener. So whenever there is a change in user details then the chat/status details will also change like the image and name. Might not impact the performance much. (Need to check even though)
    - Home chat list need to have nested grid view and list view. Right now it's not working because compose doesn't support nested scrolling. Need to find a work around.

### Enhancement
    - Need to make changes for firestore update. Currently I am fetching the data then updating it. Since I am using data class then it is needed to update the previous values before updating the new one.
    - Need to give option for user to see the status. Right now only the all status list is being shown