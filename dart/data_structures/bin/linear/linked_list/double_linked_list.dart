class Node {
  Node({
    required this.data,
    this.prev,
    this.next,
  });

  int data;
  Node? prev;
  Node? next;
}

class DoubleLinkedList {}
