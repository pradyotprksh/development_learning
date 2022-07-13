import src


class Stack:

    def __init__(self, size):
        self.arr = []
        self.size = size

    def peek(self):
        return self.arr[len(self.arr) - 1]

    def push(self, value):
        if len(self.arr) >= self.size:
            print("Stack is full")
            return
        self.arr.append(value)

    def pop(self):
        if len(self.arr) == 0:
            print("Stack is empty")
            return
        temp = self.arr.pop()
        return temp

    def print_stack(self):
        print(f"\n{self.__str__()}\n")

    def __str__(self) -> str:
        return f"{self.arr} length={len(self.arr)} size={self.size}"


def start_with_stack_arrays():
    """All programs related to stack implementation with arrays will start execution from here"""

    stack = None
    user_selection = 1
    while user_selection != 4:
        user_selection = src.UserInput.get_user_selection(
            "Please select an option"
            "\n1. Peek"
            "\n2. Push"
            "\n3. Pop"
            "\n4. Done"
        )
        if user_selection == 1:
            if stack is None:
                print("Stack is not created yet")
                continue
            print(stack.peek())
        elif user_selection == 2:
            if stack is None:
                stack = Stack(size=5)
            value_input = src.UserInput.get_user_selection("Please enter a value")
            stack.push(value_input)
        elif user_selection == 3:
            print(stack.pop())
        if stack is not None:
            stack.print_stack()
