"""Google questions"""

from .challenge_nine import challenge_nine
from .speed_typing import speed_typing


def run_google_questions():
    """All google questions calls are added here"""
    print("****** Google KS ******")
    print("challenge_nine")
    challenge_nine(1)
    print("speed_typing")
    speed_typing(1)
