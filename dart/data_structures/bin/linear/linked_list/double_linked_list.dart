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

class DoubleLinkedList {
  Node? head;

  void traverse() {
    if (head == null) {
      print('List empty');
      return;
    }
    var temp = head;
    var statement = '';
    while (temp != null) {
      statement = '$statement${temp.data}<--->';
      temp = temp.next;
    }
    print(statement);
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
    temp?.next?.prev = node;
    node.next = temp?.next;
    node.prev = temp;
    temp?.next = node;
    print('Added $value at position $position');
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

  void deleteFromMiddle(int position) {
    var temp = head;
    for (var i = 2; i < position; i++) {
      temp = temp?.next;
    }
    temp?.next = temp.next?.next;
    temp?.next?.prev = temp;
    traverse();
  }

  void searchElement(int value) {
    if (head == null) {
      print('List empty');
      return;
    }
    var temp = head;
    while (temp != null) {
      if (temp.data == value) {
        print('$value found in the list');
        return;
      }
      temp = temp.next;
    }
    print('$value not found in the list');
  }

  void sortList() {
    var current = head;
    while (current != null) {
      var index = current.next;
      while (index != null) {
        if (index.data > current.data) {
          current.data = current.data + index.data;
          index.data = current.data - index.data;
          current.data = current.data - index.data;
        }
        index = index.next;
      }
      current = current.next;
    }
    traverse();
  }
}

class CircularDoubleLinkedList {
  Node? head;
  Node? last;

  void traverse() {
    var temp = head;
    var statement = '--';
    while (true) {
      statement = '$statement${temp?.data}--->';
      if (temp == last) {
        break;
      }
      temp = temp?.next;
    }
    print(statement);
  }

  void insertAtStart(int value) {
    var node = Node(data: value);
    node.next = head;
    head?.prev = node;
    head = node;
    last ??= head;
    last?.next = head;
    head?.prev = last;
    print('Added $value on start');
    traverse();
  }

  void insertAtEnd(int value) {
    var node = Node(data: value);
    head ??= node;
    last ??= node;
    last?.next = node;
    node.next = head;
    last = node;
    head?.prev = last;
    print('Added $value on end');
    traverse();
  }

  void insertAtMiddle(int value, int position) {
    if (head == null) {
      print('List empty');
      return;
    }
    var node = Node(data: value);
    var temp = head;
    var i = 2;
    for (i = 2; i < position; i++) {
      temp = temp?.next;
    }
    print('Added $value at position $position');
    if (temp?.next == head) {
      insertAtStart(value);
    } else {
      var next = temp?.next;
      node.next = next;
      next?.prev = node;

      temp?.next = node;
      node.prev = temp;
      traverse();
    }
  }

  void deleteFromStart() {
    if (head == null) {
      print('List empty');
      return;
    }
    if (head == last) {
      head = null;
      last = null;
    } else {
      head = head?.next;
      head?.prev = last;
      last?.next = head;
    }
    traverse();
  }

  void deleteFromEnd() {
    if (last == null) {
      print('List empty');
      return;
    }
    if (head == last) {
      head = null;
      last = null;
    } else {
      var temp = head;
      while (temp?.next != last) {
        temp = temp?.next;
      }
      temp?.next = head;
      last = temp;
      head?.prev = last;
    }
    traverse();
  }

  void deleteAtMiddle(int position) {
    if (head == null) {
      print('List empty');
      return;
    }
    if (head == last) {
      head = null;
      last = null;
    } else {
      var temp = head;
      for (var i = 2; i < position; i++) {
        temp = temp?.next;
      }
      temp?.next = temp.next?.next;
      temp?.next?.next?.prev = temp;
      traverse();
    }
  }
}
