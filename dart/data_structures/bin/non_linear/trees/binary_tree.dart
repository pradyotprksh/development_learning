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
