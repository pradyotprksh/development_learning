import src
import stack


class QueueStack(stack.Stack):

    def __init__(self, size):
        super().__init__(size)

    def peek(self):
        return self.arr[0]

    def enqueue(self, value):
        self.push(value)

    def dequeue(self):
        if len(self.arr) <= 0:
            print("Queue is empty")
            return
        temp_stack = []
        for _ in range(0, len(self.arr)):
            temp_stack.append(self.pop())
        item = temp_stack.pop()
        for _ in range(0, len(temp_stack)):
            self.push(temp_stack.pop())
        return item

    def print_queue(self):
        print(f"{super().__str__()}")


def start_with_queue_stack():
    """All programs related to queue implementation with stack will start execution from here"""

    queue = None
    user_selection = 1
    while user_selection != 4:
        user_selection = src.UserInput.get_user_selection(
            "**THIS IMPLEMENTATION IS DONE USING STACK AS A SUPER CLASS SO YOU MIGHT SEE LOGS FROM STACK CLASSES, "
            "BUT THE REQUIREMENT IS FULL FILLED FOR USING A STACK AS A QUEUE**"
            "\nPlease select an option"
            "\n1. Peek"
            "\n2. Enqueue"
            "\n3. Dequeue"
            "\n4. Done"
        )
        if user_selection == 1:
            if queue is None:
                print("Queue is not created yet")
                continue
            print(queue.peek())
        elif user_selection == 2:
            if queue is None:
                queue = QueueStack(size=5)
            value_input = src.UserInput.get_user_selection("Please enter a value")
            queue.enqueue(value_input)
        elif user_selection == 3:
            print(queue.dequeue())
        if queue is not None:
            queue.print_queue()
