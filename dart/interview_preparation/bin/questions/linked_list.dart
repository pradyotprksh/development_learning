import '../learning/doubly_linked_list.dart';
import '../learning/singly_linked_list.dart';

void linkedListQuestions() {
  print("* Linked List Questions *");
  sortedList();
  findMergeNode();
  detectCycle();
}

// https://www.hackerrank.com/challenges/insert-a-node-into-a-sorted-doubly-linked-list/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=linked-lists
void sortedList() {
  print("* sortedList");
  final doublyLinkedList = DoublyLinkedList();
  doublyLinkedList.addInSortedManner(1);
  doublyLinkedList.addInSortedManner(2);
  doublyLinkedList.addInSortedManner(4);
  doublyLinkedList.addInSortedManner(3);
  doublyLinkedList.addInSortedManner(0);
}

// https://www.hackerrank.com/challenges/find-the-merge-point-of-two-joined-linked-lists/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=linked-lists
void findMergeNode() {
  print("* findMergeNode");
  final one_1 = SinglyLinkedListNode(data: 1);
  final one_2 = SinglyLinkedListNode(data: 2);
  final two = SinglyLinkedListNode(data: 3);
  final three = SinglyLinkedListNode(data: 4);

  one_1.next = two;
  two.next = three;
  one_2.next = three;

  print(one_1);
  print(one_2);

  final firstMap = <int>[];
  SinglyLinkedListNode? temp_1 = one_1;
  while (temp_1 != null) {
    firstMap.add(temp_1.data);
    temp_1 = temp_1.next;
  }
  SinglyLinkedListNode? temp_2 = one_2;
  int index = 0;
  while (temp_2 != null || index < firstMap.length) {
    if (index >= firstMap.length) {
      temp_2 = null;
      break;
    }
    if (temp_2?.data == firstMap[index++]) {
      break;
    }
    if (temp_2?.next != null) {
      temp_2 = temp_2?.next;
    }
  }
  if (temp_2 != null) {
    print("Found merge at ${temp_2.data}");
  } else {
    print("No merge found");
  }
}

// https://www.hackerrank.com/challenges/ctci-linked-list-cycle/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=linked-lists
void detectCycle() {
  print("* detectCycle");
  final one = SinglyLinkedListNode(data: 1);
  final two = SinglyLinkedListNode(data: 2);
  final three = SinglyLinkedListNode(data: 3);
  final four = SinglyLinkedListNode(data: 4);
  final five = SinglyLinkedListNode(data: 5);

  one.next = two;
  two.next = three;
  three.next = four;
  four.next = five;
  five.next = three;

  SinglyLinkedListNode? slow = one;
  SinglyLinkedListNode? fast = one.next?.next;

  while (slow != null && fast != null && slow.data != fast.data) {
    slow = slow.next;
    fast = fast.next?.next;
  }

  if (slow == null || fast == null) {
    print("No cycle");
  } else {
    print("Cycle found at ${slow.data} ${fast.data}");
  }
}
