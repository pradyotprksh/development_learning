class SinglyLinkedListNode {
  SinglyLinkedListNode({
    this.data = -1,
    this.next,
  });

  final int data;
  SinglyLinkedListNode? next;

  @override
  String toString() => "$data->$next";
}

void singlyLinkedList() {
  print("* Singly Linked List *");
  final singlyLinkedList = SingleLinkedList();
  singlyLinkedList.insert(0, 29);
  singlyLinkedList.unShift(1);
  singlyLinkedList.remove(2);
  singlyLinkedList.push(3);
  singlyLinkedList.push(4);
  singlyLinkedList.pop();
  singlyLinkedList.push(5);
  singlyLinkedList.remove(6);
  singlyLinkedList.push(7);
  singlyLinkedList.pop();
  singlyLinkedList.push(8);
  singlyLinkedList.remove(9);
  singlyLinkedList.push(10);
  singlyLinkedList.remove(11);
  singlyLinkedList.remove(12);
  singlyLinkedList.push(13);
  singlyLinkedList.push(14);
  singlyLinkedList.shift();
  singlyLinkedList.pop();
  singlyLinkedList.push(15);
  singlyLinkedList.push(16);
  singlyLinkedList.pop();
  singlyLinkedList.unShift(17);
  singlyLinkedList.unShift(18);
  singlyLinkedList.pop();
  singlyLinkedList.unShift(19);
  singlyLinkedList.unShift(20);
  singlyLinkedList.remove(21);
  singlyLinkedList.remove(22);
  singlyLinkedList.get(100);
  singlyLinkedList.get(1);
  singlyLinkedList.get(0);
  singlyLinkedList.get(8);
  singlyLinkedList.set(8, 23);
  singlyLinkedList.set(9, 24);
  singlyLinkedList.push(25);
  singlyLinkedList.push(26);
  singlyLinkedList.push(27);
  singlyLinkedList.set(9, 28);
  singlyLinkedList.insert(5, 30);
  singlyLinkedList.insert(0, 31);
  singlyLinkedList.insert(1, 32);
  singlyLinkedList.insert(16, 33);
  singlyLinkedList.insert(16, 34);
  singlyLinkedList.insert(18, 35);
  singlyLinkedList.insert(20, 35);
  singlyLinkedList.removeAt(20);
  singlyLinkedList.insert(19, 36);
  singlyLinkedList.removeAt(0);
  singlyLinkedList.removeAt(19);
  singlyLinkedList.reverse();
  singlyLinkedList.pop();
  singlyLinkedList.shift();
  singlyLinkedList.pop();
  singlyLinkedList.shift();
  singlyLinkedList.pop();
  singlyLinkedList.shift();
  singlyLinkedList.pop();
  singlyLinkedList.shift();
  singlyLinkedList.pop();
  singlyLinkedList.shift();
  singlyLinkedList.pop();
  singlyLinkedList.shift();
  singlyLinkedList.pop();
  singlyLinkedList.shift();
  singlyLinkedList.reverse();
  singlyLinkedList.pop();
  singlyLinkedList.pop();
  singlyLinkedList.reverse();
  singlyLinkedList.pop();
  singlyLinkedList.reverse();
  print("* End Singly Linked List *");
}

class SingleLinkedList {
  SinglyLinkedListNode? head;

  void push(int data) {
    final newNode = SinglyLinkedListNode(
      data: data,
    );
    if (head == null) {
      head = newNode;
    } else {
      var temp = head;
      while (temp?.next != null) {
        temp = temp?.next;
      }
      temp?.next = newNode;
    }
    print("Pushed $newNode in $head");
  }

  void pop() {
    if (head == null) {
      print("Head is null.");
      return;
    }
    var temp = head;
    SinglyLinkedListNode? prev;
    while (temp?.next != null) {
      prev = temp;
      temp = temp?.next;
    }
    if (prev == null && temp?.next == null) {
      head = null;
    } else {
      prev?.next = null;
    }
    print("Popped last element $head");
  }

  void shift() {
    if (head == null) {
      print("Head is null.");
      return;
    }
    head = head?.next;
    print("Shifted head $head");
  }

  void unShift(int data) {
    final newNode = SinglyLinkedListNode(
      data: data,
    );
    if (head == null) {
      head = newNode;
    } else {
      newNode.next = head;
      head = newNode;
    }
    print("Inserted ${SinglyLinkedListNode(
      data: data,
    )} at the beginning in $head");
  }

  void remove(int data) {
    if (head == null) {
      print("Head is null.");
      return;
    }
    var temp = head;
    SinglyLinkedListNode? prev;
    while (temp != null && temp.data != data) {
      prev = temp;
      temp = temp.next;
    }
    if (temp != null) {
      if (prev == null) {
        head = temp.next;
      } else {
        prev.next = temp.next;
      }
      print("Found $data and removed. $head");
    } else {
      print("$data not found in $head");
    }
  }

  void get(int pos) {
    if (head == null) {
      print("Head is null.");
      return;
    }
    var tempPos = pos;
    var temp = head;
    while (temp != null && tempPos > 0) {
      --tempPos;
      temp = temp.next;
    }
    if (temp != null) {
      print("Found ${temp.data} at position $pos");
    } else {
      print("No element at position $pos");
    }
  }

  void set(int pos, int newData) {
    if (head == null) {
      print("Head is null.");
      return;
    }
    var tempPos = pos;
    var temp = head;
    while (temp != null && tempPos > 0) {
      --tempPos;
      temp = temp.next;
    }
    if (temp != null) {
      final oldData = temp.data;
      temp = SinglyLinkedListNode(data: newData);
      print("Found $oldData at position $pos and updated to $newData. $head");
    } else {
      print("No element at position $pos");
    }
  }

  void insert(int pos, int data) {
    final newNode = SinglyLinkedListNode(data: data);
    if (head == null) {
      head = newNode;
    } else {
      var temp = head;
      SinglyLinkedListNode? prev;
      var tempPos = pos;
      while ((temp != null || prev != null) && tempPos > 0) {
        --tempPos;
        prev = temp;
        temp = temp?.next;
      }
      if (prev == null && temp == null) {
        print("Position $pos is out of bounds");
        return;
      } else {
        if (prev == null) {
          head = newNode;
        } else {
          prev.next = newNode;
        }
        newNode.next = temp;
      }
    }
    print(
        "New node ${SinglyLinkedListNode(data: data)} inserted at position $pos in $head");
  }

  void removeAt(int pos) {
    if (head == null) {
      print("Head is null.");
      return;
    }
    var temp = head;
    SinglyLinkedListNode? prev;
    var tempPos = pos;
    while ((temp != null || prev != null) && tempPos > 0) {
      --tempPos;
      prev = temp;
      temp = temp?.next;
    }
    if (prev == null && temp == null) {
      print("Position $pos is out of bounds");
      return;
    } else {
      if (prev == null && temp != null) {
        head = temp.next;
      } else if (prev != null && temp == null) {
        pop();
      } else {
        prev?.next = temp?.next?.next;
      }
    }
    print("Item removed from position $pos in $head");
  }

  void reverse() {
    if (head == null) {
      print("Head is null.");
      return;
    }
    var first = head;
    SinglyLinkedListNode? second;
    SinglyLinkedListNode? third;
    while (first != null) {
      third = second;
      second = first;
      first = first.next;
      second.next = third;
    }
    if (second != null) {
      head = second;
    }
    print("Linked list reversed $head");
  }
}
