import random

def print_message():
    print("Welcome to the Number Guessing Game!")
    print("I'm thinking of a number between 1 and 100.")
    print("You have 5 chances to guess the correct number.")
    print("")
    print("Please select the difficulty level:")
    print("1. Easy (10 chances)")
    print("2. Medium (5 chances)")
    print("3. Hard (3 chances)")
    print("")

def check_input():
    option = 0
    try:
        option = int(input("Enter your choice: "))
    except:
        print("Please provide a valid input")
        return

    if option not in range(1, 4):
        print("Please select the correct option")
        return None
    
    difficulty = ""
    chances = 0
    if option == 1:
        difficulty = "Easy"
        chances = 10
    elif option == 2:
        chances = 5
        difficulty = "Medium"
    else:
        chances = 3
        difficulty = "Hard"

    print(f"Great! You have selected the {difficulty} difficulty level.")
    print(f"Let's start the game!")

    return chances

def start_game(chances):
    restart = "Y"
    while restart == "Y":
        random_number = random.randint(1, 100)
        attempts = 0
    
        while attempts < chances:
            try:
                guess = int(input("Enter your guess: "))
                attempts += 1

                if guess > random_number:
                    print(f"Incorrect! The number is less than {guess}.")
                elif guess < random_number:
                    print(f"Incorrect! The number is greater than {guess}.")
                else:
                    print(f"Congratulations! You guessed the correct number in {attempts} attempts.")
                    return
            except:
                print("Please provide valid input.")
    
        print("You are out of attempts.")
        restart = input("If you want to continue press Y: ")

    print("Thank you for playing.")


if __name__ == '__main__':
    print_message()

    chances = check_input()

    if chances is not None:
        start_game(chances)
