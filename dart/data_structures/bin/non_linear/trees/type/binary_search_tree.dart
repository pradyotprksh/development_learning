import 'dart:collection';

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

  void bfsTraversal(
    Node? node,
  ) {
    if (node == null) {
      return;
    }
    var currentNode = node;
    var list = <int>[];
    var queue = Queue<Node>();
    queue.add(currentNode);
    while (queue.isNotEmpty) {
      currentNode = queue.removeFirst();
      list.add(currentNode.value);
      if (currentNode.left != null) {
        queue.add(currentNode.left!);
      }
      if (currentNode.right != null) {
        queue.add(currentNode.right!);
      }
    }
    print(list);
  }

  bool isValidBST(
    Node? node,
  ) {
    if (node == null) {
      return true;
    }
    var currentNode = node;
    var queue = Queue<Node>();
    queue.add(currentNode);
    while (queue.isNotEmpty) {
      currentNode = queue.removeFirst();
      if (currentNode.left != null) {
        if (currentNode.value < currentNode.left!.value) {
          return false;
        }
        queue.add(currentNode.left!);
      }
      if (currentNode.right != null) {
        if (currentNode.value > currentNode.right!.value) {
          return false;
        }
        queue.add(currentNode.right!);
      }
    }
    return true;
  }
}
