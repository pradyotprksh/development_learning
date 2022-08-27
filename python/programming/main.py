"""Main file to be run"""
import practice
from ds import start_with_ds
from algo import start_with_algo
from interview_preperations import start_with_interview_preparation
from google import google_questions
import src

if __name__ == '__main__':
    print("At any given point you can give a invalid option to go back in the selection process")
    user_selection = 1
    while user_selection in [1, 2, 3, 4]:
        user_selection = src.UserInput.get_user_selection(
            "Please select an option"
            "\n1. DS"
            "\n2. Algorithm"
            "\n3. Practice Problems"
            "\n4. Interview Preparations"
            "\n5. Google Questions"
        )
        if user_selection == 1:
            start_with_ds()
        elif user_selection == 2:
            start_with_algo()
        elif user_selection == 3:
            practice.run_practice_problems()
        elif user_selection == 4:
            start_with_interview_preparation()
        elif user_selection == 5:
            google_questions()
