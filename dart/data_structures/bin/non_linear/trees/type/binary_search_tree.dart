class Node {
  Node? left;
  Node? right;
  int value;

  Node({
    required this.value,
    this.left,
    this.right,
  });

  @override
  String toString() {
    return '$value -> {$left $right}';
  }
}

class BinarySearchTree {
  Node? root;

  Node _newNode(int value) {
    return Node(value: value);
  }

  void insert(int value) {
    var newNode = _newNode(value);
    if (root == null) {
      root = newNode;
    } else {
      var temp = root;
      while (true) {
        if (temp == null) {
          break;
        }
        if (value > temp.value) {
          if (temp.right == null) {
            temp.right = newNode;
            break;
          } else {
            temp = temp.right;
          }
        } else if (value < temp.value) {
          if (temp.left == null) {
            temp.left = newNode;
            break;
          } else {
            temp = temp.left;
          }
        }
      }
    }
  }

  // TODO: Remove from a binary search tree
  void remove(int value) {}

  Node? lookUp(Node? node, int value) {
    if (node == null) {
      return null;
    }
    if (node.value == value) {
      return node;
    }
    if (value > node.value) {
      return lookUp(node.right, value);
    } else {
      return lookUp(node.left, value);
    }
  }

  void inorderTraversal(Node? node) {
    if (node == null) {
      return;
    }
    inorderTraversal(node.left);
    print(node.value);
    inorderTraversal(node.right);
  }
}
