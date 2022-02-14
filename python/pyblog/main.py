"""PyBlog"""


from src import get_platform_details
from pyblog import UserDetails
from firebase import Firebase


if __name__ == '__main__':
    firebase = Firebase()
    firebase.create_user(
        user_details=UserDetails(
            email="pradyotprksh4@gmail.com",
            email_verified=False,
            phone_number="+91 9663522579",
            password="password",
            display_name="Pradyot Prakash",
            photo_url="https://www.my_photo.com",
        ),
        platform_details=get_platform_details()
    )
