import src


class Node:
    def __init__(self, data, next=None):
        self.data = data
        self.next = next

    def __str__(self) -> str:
        return f"{self.data}|{self.next}"


class QueueLinkedList:

    def __init__(self):
        self.first = None
        self.last = None
        self.length = 0

    def peek(self):
        return self.first

    def enqueue(self, value):
        temp = Node(data=value)
        if self.length <= 0:
            self.first = temp
            self.last = temp
        else:
            self.last.next = temp
            self.last = temp
        self.length += 1

    def dequeue(self):
        if self.length <= 0:
            print("Queue is empty")
            return None
        if self.length == 1:
            self.last = None
        temp = self.first
        self.first = self.first.next
        self.length -= 1
        return temp.data

    def print_queue(self):
        print(f"\n{self.__str__()}\n")

    def __str__(self) -> str:
        return f"{self.first} length={self.length}"


def start_with_queue_linked_list():
    """All programs related to queue implementation with linked list will start execution from here"""

    queue = None
    user_selection = 1
    while user_selection in [1, 2, 3]:
        user_selection = src.UserInput.get_user_selection(
            "Please select an option"
            "\n1. Peek"
            "\n2. Enqueue"
            "\n3. Dequeue"
        )
        if user_selection == 1:
            if queue is None:
                print("Queue is not created yet")
                continue
            print(queue.peek())
        elif user_selection == 2:
            if queue is None:
                queue = QueueLinkedList()
            value_input = src.UserInput.get_user_selection("Please enter a value")
            queue.enqueue(value_input)
        elif user_selection == 3:
            print(queue.dequeue())
        if queue is not None:
            queue.print_queue()
