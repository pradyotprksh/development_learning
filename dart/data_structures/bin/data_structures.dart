import 'linear/linked_list/single_linked_list.dart';
import 'linear/queue/queue.dart';
import 'linear/queue/types/circular_queue.dart';
import 'linear/stack/stack.dart';
import 'linear/stack/stack_questions.dart';

void main(List<String> arguments) {
  print('***** STACK *****');
  var _stack = Stack();
  _stack.push(10);
  _stack.push(9);
  _stack.push(8);
  _stack.push(7);

  _stack.getMinimumValue();

  _stack.pop();
  _stack.pop();
  _stack.pop();

  _stack.getMinimumValue();

  _stack.peek();
  _stack.push(5);
  _stack.peek();

  _stack.pop();
  _stack.push(9);
  _stack.push(8);
  _stack.push(7);
  _stack.push(6);

  _stack.getMinimumValue();
  print('**********\n');

  print('***** DOUBLE STACK *****');
  var doubleStack = DoubleStack();

  doubleStack.push1(1);
  doubleStack.push1(2);
  doubleStack.push2(10);
  doubleStack.push2(20);
  doubleStack.push2(30);
  doubleStack.printStacks();

  doubleStack.pop1();
  doubleStack.printStacks();

  doubleStack.pop2();
  doubleStack.printStacks();

  doubleStack.push1(2);
  doubleStack.push2(30);
  doubleStack.push2(40);
  doubleStack.push1(3);
  doubleStack.printStacks();
  print('**********\n');

  print('***** STACK QUESTIONS *****');
  var stackQuestions = StackQuestions();
  stackQuestions.evaluatePostfixExpression('138*+');
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

  singleLinkedList.insertAtStart(1);
  singleLinkedList.insertAtStart(2);
  singleLinkedList.insertAtStart(3);

  singleLinkedList.insertAtEnd(4);
  singleLinkedList.insertAtEnd(5);

  singleLinkedList.insertAtMiddle(6, 1);

  singleLinkedList.insertAtMiddle(7, 7);

  singleLinkedList.deleteFromStart();

  singleLinkedList.deleteFromEnd();

  singleLinkedList.deleteFromMiddle(1);
  singleLinkedList.deleteFromMiddle(3);
  print('**********\n');
}
