class Node {
  Node({
    required this.data,
    this.left,
    this.right,
  });

  int data;
  Node? left;
  Node? right;
}

class Trees {
  Node? root;
  Node? temp;

  void inorderTraversal(Node? head) {
    if (head == null) {
      return;
    }
    inorderTraversal(head.left);
    print(head.data);
    inorderTraversal(head.right);
  }

  void preOrderTraversal(Node? head) {
    if (head == null) {
      return;
    }
    print(head.data);
    preOrderTraversal(head.left);
    preOrderTraversal(head.right);
  }

  void postOrderTraversal(Node? head) {
    if (head == null) {
      return;
    }
    postOrderTraversal(head.left);
    postOrderTraversal(head.right);
    print(head.data);
  }

  void insertNode(int value) {
    var node = Node(data: value);
    if (root == null) {
      root = node;
      return;
    }
    var temp = root;
    while (temp?.right != null) {
      if (temp?.left?.left == null || temp?.left?.right == null) {
        temp = temp?.left;
      } else {
        temp = temp?.right;
      }
    }
    if (temp?.left == null) {
      temp?.left = node;
      print('Added $value to left most');
    } else {
      temp?.right = node;
      print('Added $value to right most');
    }
  }
}
