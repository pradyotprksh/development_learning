import 'linear/linked_list/double_linked_list.dart';
import 'linear/linked_list/single_linked_list.dart';
import 'linear/queue/queue.dart';
import 'linear/queue/types/circular_queue.dart';
import 'linear/stack.dart';

void main(List<String> arguments) {
  print('***** STACK *****');
  var _stack = Stack();
  _stack.push(10);
  _stack.push(9);
  _stack.push(8);
  _stack.push(7);

  _stack.pop();
  _stack.pop();
  _stack.pop();

  _stack.peek();
  _stack.push(5);
  _stack.peek();

  _stack.pop();
  _stack.push(9);
  _stack.push(8);
  _stack.push(7);
  _stack.push(6);

  _stack.push(5);
  print('**********\n');

  print('***** QUEUE *****');
  var queue = Queue();
  queue.enqueue(1);
  queue.enqueue(2);
  queue.enqueue(3);
  queue.enqueue(4);
  queue.enqueue(5);
  queue.enqueue(6);

  queue.peek();

  queue.dequeue();
  queue.dequeue();
  queue.dequeue();
  queue.dequeue();
  queue.dequeue();
  queue.dequeue();

  queue.enqueue(1);
  queue.enqueue(2);
  queue.enqueue(3);
  queue.enqueue(4);
  queue.enqueue(5);

  queue.dequeue();
  print('**********\n');

  print('***** CIRCULAR QUEUE *****');
  var circularQueue = CircularQueue();
  circularQueue.enqueue(1);
  circularQueue.enqueue(2);
  circularQueue.enqueue(3);
  circularQueue.enqueue(4);
  circularQueue.enqueue(5);
  circularQueue.enqueue(6);

  circularQueue.peek();

  circularQueue.dequeue();
  circularQueue.dequeue();

  circularQueue.enqueue(7);
  circularQueue.enqueue(8);
  circularQueue.enqueue(9);

  circularQueue.dequeue();
  circularQueue.enqueue(9);
  print('**********\n');

  print('***** SINGLE LINKED LIST *****');
  var singleLinkedList = SingleLinkedList();
  print('**********\n');

  print('***** DOUBLY LINKED LIST *****');
  var doublyLinkedList = DoubleLinkedList();
  print('**********\n');
}
