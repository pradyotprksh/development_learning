import 'trees.dart';

class FullBinaryTree {
  Node? root;

  bool isFullBinaryTree(Node? node) {
    if (node == null) {
      return true;
    }
    if (node.left == null && node.right == null) {
      return true;
    }
    if (node.left != null && node.right != null) {
      return (isFullBinaryTree(node.left) && isFullBinaryTree(node.right));
    }
    return false;
  }
}

class PerfectBinaryTree {
  Node? root;

  int depth(Node? node) {
    var depth = 0;
    while (node?.left != null) {
      ++depth;
      node = node?.left;
    }
    return depth;
  }

  bool isPerfect(int d, int l, Node? node) {
    if (node == null) {
      return true;
    }
    if (node.left == null && node.right == null) {
      return (d == l);
    }
    if (node.left == null || node.right == null) {
      return false;
    }
    return isPerfect(d, l + 1, node.left) && isPerfect(d, l + 1, node.right);
  }

  bool isPerfectBinaryTree(Node? node) {
    int d = depth(node);
    return isPerfect(d, 0, node);
  }
}

class CompleteBinaryTree {
  Node? root;

  int numberOfNodes(Node? node) {
    if (node == null) return 0;
    return 1 + numberOfNodes(node.left) + numberOfNodes(node.right);
  }

  bool isCompleteBinaryTree(Node? node, int index, int nodeCount) {
    if (node == null) {
      return true;
    }
    if (index >= nodeCount) {
      return false;
    }
    return isCompleteBinaryTree(node.left, 2 * index + 1, nodeCount) &&
        isCompleteBinaryTree(node.right, 2 * index + 2, nodeCount);
  }
}
