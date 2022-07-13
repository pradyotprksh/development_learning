"""Let's implement singly linked list"""
import src


class SinglyLinkedList:
    def __init__(self, value):
        self.head = Node(
            data=value,
        )
        self.tail = self.head
        self.length = 1

    def append(self, value):
        new_node = Node(data=value)
        self.tail.next = new_node
        self.tail = new_node
        self.length += 1

    def prepend(self, value):
        new_node = Node(data=value)
        new_node.next = self.head
        self.head = new_node
        self.length += 1

    def insert(self, index, value):
        if index <= 0:
            self.prepend(value)
            return
        if index >= self.length:
            self.append(value)
            return
        new_node = Node(data=value)
        prev = self.get_node_by_index(index=index-1)
        temp = prev.next
        prev.next = new_node
        new_node.next = temp
        self.length += 1

    def remove(self, index):
        if index < 0:
            print("Please enter a valid index")
            return
        temp = self.get_node_by_index(index=index-1)
        if temp is not None:
            temp.next = temp.next.next
            self.length -= 1
        else:
            print(f"Node not available at index {index}")

    def get_node_by_index(self, index):
        counter = 0
        temp = self.head
        while counter != index:
            temp = temp.next
            counter += 1
        return temp

    def print_linked_list(self):
        print(f"\n{self.__str__()}\n")

    def __str__(self) -> str:
        return f"{self.head} length={self.length}"

    def reverse(self):
        prev_node = None
        self.tail = self.head
        next_node = self.head.next
        while self.head.next is not None:
            self.head.next = prev_node
            prev_node = self.head
            self.head = next_node
            next_node = self.head.next
        self.head.next = prev_node


class Node:
    def __init__(self, data, next=None):
        self.data = data
        self.next = next

    def __str__(self) -> str:
        return f"{self.data}-->{self.next}"


def start_with_singly_linked_list():
    """All programs related to singly linked list will start execution from here"""

    linked_list = None

    user_selection = 1
    while user_selection in [1, 2, 3, 4, 5]:
        user_selection = src.UserInput.get_user_selection(
            "Please select an option"
            "\n1. Append"
            "\n2. Prepend"
            "\n3. Insert"
            "\n4. Delete"
            "\n5. Reverse"
        )
        if user_selection == 1:
            value_input = src.UserInput.get_user_selection("Please enter the value")
            if linked_list is None:
                linked_list = SinglyLinkedList(value=value_input)
            else:
                linked_list.append(value_input)
        elif user_selection == 2:
            value_input = src.UserInput.get_user_selection("Please enter the value")
            if linked_list is None:
                linked_list = SinglyLinkedList(value=value_input)
            else:
                linked_list.prepend(value_input)
        elif user_selection == 3:
            value_input = src.UserInput.get_user_selection("Please enter the value")
            index_input = src.UserInput.get_user_selection("Please enter the index")
            if linked_list is None:
                print("Linked list is empty, creating one")
                linked_list = SinglyLinkedList(value=value_input)
            else:
                linked_list.insert(index=index_input, value=value_input)
        elif user_selection == 4:
            index_input = src.UserInput.get_user_selection("Please enter the index")
            if linked_list is None:
                print("Linked list is empty, create one first")
            else:
                linked_list.remove(index=index_input)
        elif user_selection == 5:
            if linked_list is None:
                print("Linked list is empty, create one first")
            else:
                linked_list.reverse()
        if linked_list is not None:
            linked_list.print_linked_list()
