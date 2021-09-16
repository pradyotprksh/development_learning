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

  void deleteFromMiddle(int position) {
    var temp = head;
    for (var i = 2; i < position; i++) {
      temp = temp?.next;
    }
    temp?.next = temp.next?.next;
    traverse();
  }

  void searchElement(int value) {
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
    if (head == null) {
      return;
    }
    var current = head;
    while (current != null) {
      var next = current.next;
      while (next != null) {
        if (next.data > current.data) {
          current.data = next.data + current.data;
          next.data = current.data - next.data;
          current.data = current.data - next.data;
        }
        next = next.next;
      }
      current = current.next;
    }
    traverse();
  }
}

class CircularSingleLinkedList {
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
    head = node;
    last ??= head;
    last?.next = head;
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
      node.next = temp?.next;
      temp?.next = node;
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
      traverse();
    }
  }
}
