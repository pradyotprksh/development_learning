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
        # If BST is null
        if self.root is None:
            print("BST is empty")
            return
        # for traversal
        current_node = self.root
        # for tracking the to be removed node parent
        parent_node = None
        while current_node is not None:
            # When value is found
            if current_node.data == value:
                # if the node doesn't have a right child
                if current_node.right is None:
                    # if the parent is null then we are removing the root node only
                    if parent_node is None:
                        # make the root node as node's left child
                        self.root = current_node.left
                    # if parent is not null then we pull the node's left child to parent's left or right
                    # based on the BST rule
                    else:
                        if current_node.data > parent_node.data:
                            parent_node.right = current_node.left
                        else:
                            parent_node.left = current_node.left
                # if the node has a right child but right child not have a left child
                elif current_node.right.left is None:
                    # make the node's right's left child point to the node left's child
                    current_node.right.left = current_node.left
                    # if the parent is null then we are removing the root node only
                    if parent_node is None:
                        # make the root node as node's left child
                        self.root = current_node.left
                    else:
                        # if parent is not null then we pull the node's right child to parent's left or right
                        # based on the BST rule
                        if current_node.data > parent_node.data:
                            parent_node.right = current_node.right
                        else:
                            parent_node.left = current_node.right
                # if node's right child has a left child
                else:
                    # we traverse to the leftmost child of the node's right child
                    # by keeping track of the node's right's left's parent
                    left_most_node = current_node.right.left
                    left_most_node_parent = current_node.right
                    # find the leftmost most child
                    while left_most_node.left is not None:
                        left_most_node_parent = left_most_node
                        left_most_node = left_most_node.left
                    # whatever is there is in the left most right we move it to left most parent's left
                    left_most_node_parent.left = left_most_node.right
                    # we make the node's left and right to left most node's left and right
                    left_most_node.left = current_node.left
                    left_most_node.right = current_node.right
                    # if the parent is null then we are removing the root node only
                    if parent_node is None:
                        # we move the root to the left most node
                        self.root = left_most_node
                    else:
                        # if parent is not null then we pull the left most node to parent's left or right
                        # based on the BST rule (but checking the value of current node not the
                        # left most node)
                        if current_node.data > parent_node.data:
                            parent_node.right = left_most_node
                        else:
                            parent_node.left = left_most_node
                break
            # if not found then move forward with the BST logic
            else:
                # keep the parent to the current before traversing
                parent_node = current_node
                if current_node.data > value:
                    current_node = current_node.left
                else:
                    current_node = current_node.right

    def print_binary_search_tree(self):
        print(f"{self.root}")


def start_with_binary_search_tree():

    binary_search_tree = None
    user_selection = 1
    while user_selection in [1, 2, 3]:
        user_selection = src.UserInput.get_user_selection(
            "Please select an option"
            "\n1. Insert"
            "\n2. Lookup"
            "\n3. Remove"
        )
        if user_selection == 1:
            for value_input in [34, 24, 86, 10, 44, 96, 43, 71, 91, 49]:
                # value_input = src.UserInput.get_user_selection("Please enter the value")
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
