class DoublyLinkedListNode {
  DoublyLinkedListNode({
    this.data = -1,
    this.next,
    this.prev,
  });

  final int data;
  DoublyLinkedListNode? next;
  DoublyLinkedListNode? prev;

  @override
  String toString() => "$data<->$next";
}

void doublyLinkedList() {
  print("* Doubly Linked List *");
  final doublyLinkedList = DoublyLinkedList();
  doublyLinkedList.push(1);
  doublyLinkedList.push(2);
  doublyLinkedList.push(3);
  doublyLinkedList.push(4);
  doublyLinkedList.push(5);
  doublyLinkedList.pop();
  doublyLinkedList.pop();
  doublyLinkedList.pop();
  doublyLinkedList.pop();
  doublyLinkedList.pop();
  doublyLinkedList.pop();
  doublyLinkedList.push(1);
  doublyLinkedList.push(2);
  doublyLinkedList.push(3);
  doublyLinkedList.push(4);
  doublyLinkedList.push(5);
  doublyLinkedList.shift();
  doublyLinkedList.shift();
  doublyLinkedList.shift();
  doublyLinkedList.shift();
  doublyLinkedList.shift();
  doublyLinkedList.shift();
  doublyLinkedList.unShift(1);
  doublyLinkedList.unShift(2);
  doublyLinkedList.unShift(3);
  doublyLinkedList.unShift(4);
  doublyLinkedList.unShift(5);
  doublyLinkedList.remove(5);
  doublyLinkedList.remove(3);
  doublyLinkedList.remove(1);
  doublyLinkedList.remove(6);
  doublyLinkedList.removeAt(0);
  doublyLinkedList.removeAt(1);
  doublyLinkedList.removeAt(0);
  doublyLinkedList.unShift(1);
  doublyLinkedList.unShift(2);
  doublyLinkedList.unShift(3);
  doublyLinkedList.unShift(4);
  doublyLinkedList.unShift(5);
  doublyLinkedList.get(1);
  doublyLinkedList.get(2);
  doublyLinkedList.get(3);
  doublyLinkedList.get(4);
  doublyLinkedList.get(5);
  doublyLinkedList.set(4, 6);
  doublyLinkedList.set(0, 7);
  doublyLinkedList.set(3, 8);
  doublyLinkedList.insert(4, 9);
  doublyLinkedList.insert(0, 10);
  doublyLinkedList.insert(3, 11);
  doublyLinkedList.reverse();
  doublyLinkedList.pop();
  doublyLinkedList.reverse();
  doublyLinkedList.pop();
  doublyLinkedList.reverse();
  doublyLinkedList.pop();
  doublyLinkedList.reverse();
  doublyLinkedList.pop();
  doublyLinkedList.reverse();
  doublyLinkedList.pop();
  doublyLinkedList.reverse();
  doublyLinkedList.pop();
  doublyLinkedList.reverse();
  doublyLinkedList.pop();
  doublyLinkedList.reverse();
  doublyLinkedList.pop();
  doublyLinkedList.reverse();
  print("* End Doubly Linked List *");
}

class DoublyLinkedList {
  DoublyLinkedListNode? head;

  void push(int data) {
    final newNode = DoublyLinkedListNode(data: data);
    if (head == null) {
      head = newNode;
    } else {
      var temp = head;
      while (temp?.next != null) {
        temp = temp?.next;
      }
      temp?.next = newNode;
      newNode.prev = temp;
    }
    print("Pushed $newNode in $head");
  }

  void pop() {
    if (head == null) {
      print("Head is null");
      return;
    } else {
      var temp = head;
      while (temp?.next != null) {
        temp = temp?.next;
      }
      if (temp?.prev == null) {
        head = null;
      } else {
        temp?.prev?.next = null;
      }
      print("Popped last element from $head");
    }
  }

  void shift() {
    if (head == null) {
      print("Head is null");
      return;
    } else {
      head = head?.next;
      head?.prev = null;
      print("Shifted head to $head");
    }
  }

  void unShift(int data) {
    final newNode = DoublyLinkedListNode(data: data);
    if (head == null) {
      head = newNode;
    } else {
      newNode.next = head;
      head?.prev = newNode;
      head = newNode;
    }
    print("Inserted $data at the beginning in $head");
  }

  void remove(int data) {
    if (head == null) {
      print("Head is null");
      return;
    } else {
      var temp = head;
      while (temp != null && temp.data != data) {
        temp = temp.next;
      }
      if (temp != null) {
        if (temp.prev == null) {
          shift();
        } else {
          temp.prev?.next = temp.next;
          temp.next?.prev = temp.prev;
        }
        print("Removed $data from $head");
      } else {
        print("$data not found");
      }
    }
  }

  void get(int pos) {
    if (head == null) {
      print("Head is null");
      return;
    } else {
      var temp = head;
      var tempPos = pos;
      while (temp != null && tempPos > 0) {
        temp = temp.next;
        --tempPos;
      }
      if (temp == null) {
        print("$pos index out of bound");
      } else {
        print("Found $temp at position $pos");
      }
    }
  }

  void set(int pos, int newData) {
    if (head == null) {
      print("Head is null");
      return;
    } else {
      var temp = head;
      var tempPos = pos;
      while (temp != null && tempPos > 0) {
        temp = temp.next;
        --tempPos;
      }
      if (temp == null) {
        print("$pos index out of bound");
      } else {
        final newNode = DoublyLinkedListNode(
          data: newData,
          prev: temp.prev,
          next: temp.next,
        );
        if (temp.prev == null) {
          head = newNode;
        } else {
          temp.prev?.next = newNode;
          temp.next?.prev = newNode;
        }
        print(
            "Set $newData at position $pos by replacing ${temp.data} in $head");
      }
    }
  }

  void insert(int pos, int data) {
    if (head == null) {
      print("Head is null");
      return;
    } else {
      var temp = head;
      var tempPos = pos;
      while (temp != null && tempPos > 0) {
        temp = temp.next;
        --tempPos;
      }
      if (temp == null) {
        print("$pos index out of bound");
      } else {
        final newNode = DoublyLinkedListNode(
          data: data,
          next: temp,
          prev: temp.prev,
        );
        if (temp.prev == null) {
          head = newNode;
        } else {
          temp.prev?.next = newNode;
          temp.prev = newNode;
        }
        print("Inserted $data added at position $pos in $head");
      }
    }
  }

  void removeAt(int pos) {
    if (head == null) {
      print("Head is null");
      return;
    } else {
      var temp = head;
      var tempPos = pos;
      while (temp != null && tempPos > 0) {
        temp = temp.next;
        --tempPos;
      }
      if (temp == null) {
        print("$pos index out of bound");
      } else {
        if (temp.prev == null) {
          shift();
        } else {
          temp.prev?.next = temp.next;
          temp.next?.prev = temp.prev;
        }
        print("Removed at $pos (${temp.data}) in $head");
      }
    }
  }

  void reverse() {
    if (head == null) {
      print("Head is null");
      return;
    } else {
      DoublyLinkedListNode? third;
      DoublyLinkedListNode? second;
      var first = head;
      while (first != null) {
        third = second;
        second = first;
        first = first.next;
        second.next = third;
        second.prev = first;
      }
      if (second != null) {
        head = second;
      }
      print("Linked list reversed $head");
    }
  }
}
