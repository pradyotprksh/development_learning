import src


class Node:
    def __init__(self, data):
        self.left = None
        self.data = data
        self.right = None

    def __str__(self) -> str:
        if self.left is None and self.right is None:
            return f"{self.data}"
        elif self.left is None:
            return f"{self.data}-->[{self.right}]"
        elif self.right is None:
            return f"[{self.left}]<--{self.data}"
        else:
            return f"[{self.left}]<--{self.data}-->[{self.right}]"


class BinarySearchTree:

    def __init__(self):
        self.root = None

    def insert(self, value):
        new_node = Node(data=value)
        if self.root is None:
            self.root = new_node
            return
        temp = self.root
        while True:
            if temp.data > value:
                if temp.left is None:
                    temp.left = new_node
                    return
                temp = temp.left
            else:
                if temp.right is None:
                    temp.right = new_node
                    return
                temp = temp.right

    def lookup(self, value):
        if self.root is None:
            print("BST is empty")
            return
        found_node = self.root
        while found_node is not None:
            if found_node.data == value:
                return found_node
            else:
                if found_node.data < value:
                    found_node = found_node.right
                else:
                    found_node = found_node.left

    def remove(self, value):
        # Not able to understand
        # 😅
        pass
        # if self.root is None:
        #     print("BST is empty")
        #     return
        # found_node = self.root
        # parent_node = None
        # while found_node is not None:
        #     if found_node.data == value:
        #         # No right child for found_node
        #         if found_node.right is None:
        #             if parent_node is None:
        #                 self.root = found_node.left
        #             else:
        #                 # parent value is > found_node value
        #                 if parent_node.data > found_node.data:
        #                     parent_node.right = found_node.left
        #                 else:
        #                     parent_node.left = found_node.left
        #         # No left child for the found_node right child
        #         elif found_node.right.left is None:
        #             if parent_node is None:
        #                 self.root = found_node.left
        #             else:
        #                 # parent is > found_node value
        #                 if parent_node.data > found_node.data:
        #                     parent_node.right = found_node.left
        #                 else:
        #                     parent_node.left = found_node.left
        #         # Right child has a left child
        #         else:
        #     else:
        #         parent_node = found_node
        #         if found_node.data < value:
        #             found_node = found_node.right
        #         else:
        #             found_node = found_node.left

    def print_binary_search_tree(self):
        print(f"{self.root}")


def start_with_binary_search_tree():

    binary_search_tree = None
    user_selection = 1
    while user_selection in [1, 2]:
        user_selection = src.UserInput.get_user_selection(
            "Please select an option"
            "\n1. Insert"
            "\n2. Lookup"
            "\n3. Remove"
        )
        if user_selection == 1:
            value_input = src.UserInput.get_user_selection("Please enter the value")
            if binary_search_tree is None:
                binary_search_tree = BinarySearchTree()
            binary_search_tree.insert(value_input)
        elif user_selection == 2:
            value_input = src.UserInput.get_user_selection("Please enter the value")
            if binary_search_tree is None:
                print("Binary search tree is not created yet")
            else:
                node = binary_search_tree.lookup(value_input)
                if node is not None:
                    print(f"Found node\n{node}")
                else:
                    print(f"Couldn't found {value_input} in")
        elif user_selection == 3:
            value_input = src.UserInput.get_user_selection("Please enter the value")
            if binary_search_tree is None:
                print("Binary search tree is not created yet")
            else:
                binary_search_tree.remove(value_input)
        if binary_search_tree is not None:
            binary_search_tree.print_binary_search_tree()
