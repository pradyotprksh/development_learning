class Node {
  Node({
    required this.data,
    this.next,
  });

  int data = 0;
  Node? next;
}

class SingleLinkedList {
  Node? head;

  void traverse() {
    if (head == null) {
      print('List empty');
    } else {
      var statement = '';
      var temp = head;
      while (temp != null) {
        statement = '$statement${temp.data}--->';
        temp = temp.next;
      }
      print(statement);
    }
  }

  void insertAtStart(int value) {
    var node = Node(data: value);
    node.next = head;
    head = node;
    print('Added $value at start');
    traverse();
  }

  void insertAtEnd(int value) {
    var node = Node(data: value);
    var temp = head;
    while (temp?.next != null) {
      temp = temp?.next;
    }
    temp?.next = node;
    print('Added $value at end');
    traverse();
  }

  void insertAtMiddle(int value, int position) {
    var node = Node(data: value);
    var temp = head;
    for (var i = 0; i < position - 1; i++) {
      if (temp?.next != null) {
        temp = temp?.next;
      } else {
        print(
            'Position $position can\'t be reached. So adding $value in the end');
        break;
      }
    }
    node.next = temp?.next?.next;
    temp?.next = node;
    traverse();
  }

  void deleteFromStart() {
    head = head?.next;
    traverse();
  }

  void deleteFromEnd() {
    var temp = head;
    while (temp?.next?.next != null) {
      temp = temp?.next;
    }
    temp?.next = null;
    traverse();
  }

  void deleteFromMiddle(int position) {}
}
