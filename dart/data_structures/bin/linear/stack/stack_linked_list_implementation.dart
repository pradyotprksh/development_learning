import '../linked_list/single_linked_list.dart';

class StackLinkedListImplementation {
  Node? top;
  Node? bottom;
  int length = 0;

  Node _newNode(int value) {
    return Node(data: value);
  }

  void push(int value) {
    var node = _newNode(value);
    bottom ??= node;
    if (top == null) {
      top = node;
    } else {
      node.next = top;
      top = node;
    }
    ++length;
  }

  int pop() {
    var value = top?.data ?? -1;
    if (top == bottom) {
      bottom = null;
    }
    top = top?.next;
    --length;
    return value;
  }

  int peek() {
    return top?.data ?? -1;
  }
}
