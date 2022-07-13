import src


class Node:
    def __init__(self, data, next=None):
        self.data = data
        self.next = next

    def __str__(self) -> str:
        return f"{self.data}|{self.next}"


class StackLinkedList:

    def __init__(self):
        self.top = None
        self.bottom = None
        self.length = 0

    def peek(self):
        return self.top

    def push(self, value):
        new_node = Node(data=value)
        if self.length == 0:
            self.bottom = new_node
            self.top = new_node
        else:
            temp = self.top
            self.top = new_node
            self.top.next = temp
        self.length += 1

    def pop(self):
        if self.bottom is None:
            print("Stack is empty")
            return None
        temp = self.top
        self.top = self.top.next
        self.length -= 1
        if self.top is None:
            self.bottom = None
        return temp.data

    def print_stack(self):
        print(f"\n{self.__str__()}\n")

    def __str__(self) -> str:
        return f"{self.top} length={self.length}"


def start_with_stack_linked_list():
    """All programs related to stack implementation with linked list will start execution from here"""

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
                stack = StackLinkedList()
            value_input = src.UserInput.get_user_selection("Please enter a value")
            stack.push(value_input)
        elif user_selection == 3:
            print(stack.pop())
        if stack is not None:
            stack.print_stack()
