import 'linear/linked_list/double_linked_list.dart';
import 'linear/linked_list/single_linked_list.dart';
import 'linear/queue/queue.dart';
import 'linear/queue/types/circular_queue.dart';
import 'linear/stack/stack.dart';
import 'linear/stack/stack_linked_list_implementation.dart';
import 'linear/stack/stack_questions.dart';
import 'non_linear/graphs.dart';
import 'non_linear/trees/trees.dart' as tree;
import 'non_linear/trees/type/binary_search_tree.dart';
import 'non_linear/trees/type/binary_tree.dart';
import 'questions.dart';

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

  print('***** STACK USING LINKED LIST *****');
  var stackLinkedListImplementation = StackLinkedListImplementation();
  stackLinkedListImplementation.push(1);
  stackLinkedListImplementation.push(2);
  stackLinkedListImplementation.push(3);
  stackLinkedListImplementation.push(4);
  print(stackLinkedListImplementation.peek());
  print('Removed ${stackLinkedListImplementation.pop()}');
  print('Removed ${stackLinkedListImplementation.pop()}');
  print(stackLinkedListImplementation.peek());
  print('Removed ${stackLinkedListImplementation.pop()}');
  print('Removed ${stackLinkedListImplementation.pop()}');
  print(stackLinkedListImplementation.peek());
  stackLinkedListImplementation.push(1);
  print(stackLinkedListImplementation.peek());
  print('**********\n');

  print('***** STACK QUESTIONS *****');
  var stackQuestions = StackQuestions();
  stackQuestions.evaluatePostfixExpression('138*+');
  stackQuestions.findDuplicateParenthesis('(xy)((z))');
  print(
      'Decoded sequence : ${stackQuestions.decodeSequenceAndGetMinimum('IDIDII')}');
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
  singleLinkedList.deleteFromMiddle(4);

  singleLinkedList.searchElement(6);
  singleLinkedList.searchElement(7);

  singleLinkedList.sortList();
  singleLinkedList.reverseList();
  print('**********\n');

  print('***** CIRCULAR SINGLE LINKED LIST *****');
  var circularSingleLinkedList = CircularSingleLinkedList();

  circularSingleLinkedList.insertAtStart(1);
  circularSingleLinkedList.insertAtStart(2);
  circularSingleLinkedList.insertAtStart(3);

  circularSingleLinkedList.insertAtEnd(4);
  circularSingleLinkedList.insertAtEnd(5);

  circularSingleLinkedList.insertAtMiddle(6, 6);
  circularSingleLinkedList.insertAtMiddle(7, 3);
  circularSingleLinkedList.insertAtMiddle(8, 5);

  circularSingleLinkedList.deleteFromStart();
  circularSingleLinkedList.deleteFromEnd();
  circularSingleLinkedList.deleteAtMiddle(3);
  print('**********\n');

  print('***** DOUBLE LINKED LIST *****');
  var doubleLinkedList = DoubleLinkedList();

  doubleLinkedList.insertAtStart(1);
  doubleLinkedList.insertAtStart(2);
  doubleLinkedList.insertAtStart(3);

  doubleLinkedList.insertAtEnd(4);
  doubleLinkedList.insertAtEnd(5);

  doubleLinkedList.insertAtMiddle(6, 1);
  doubleLinkedList.insertAtMiddle(7, 7);

  doubleLinkedList.deleteFromStart();
  doubleLinkedList.deleteFromEnd();
  doubleLinkedList.deleteFromMiddle(4);

  doubleLinkedList.searchElement(6);
  doubleLinkedList.searchElement(7);

  doubleLinkedList.sortList();
  print('**********\n');

  print('***** CIRCULAR DOUBLE LINKED LIST *****');
  var circularDoubleLinkedList = CircularDoubleLinkedList();

  circularDoubleLinkedList.insertAtStart(1);
  circularDoubleLinkedList.insertAtStart(2);
  circularDoubleLinkedList.insertAtStart(3);

  circularDoubleLinkedList.insertAtEnd(4);
  circularDoubleLinkedList.insertAtEnd(5);

  circularDoubleLinkedList.insertAtMiddle(6, 1);
  circularDoubleLinkedList.insertAtMiddle(7, 7);

  circularDoubleLinkedList.deleteFromStart();
  circularDoubleLinkedList.deleteFromEnd();
  circularDoubleLinkedList.deleteAtMiddle(4);
  print('**********\n');

  print('***** TREES *****');
  var trees = tree.Trees();

  trees.insertNode(1);
  trees.insertNode(2);
  trees.insertNode(3);
  trees.insertNode(4);
  trees.insertNode(5);

  print('Inorder traversal');
  trees.inorderTraversal(trees.root);
  print('Preorder traversal');
  trees.preOrderTraversal(trees.root);
  print('Postorder traversal');
  trees.postOrderTraversal(trees.root);
  print('**********\n');

  print('***** BINARY TREE *****');
  print('***** FULL BINARY TREE *****');
  var fullBinaryTree = FullBinaryTree();
  fullBinaryTree.root = tree.Node(data: 1);
  fullBinaryTree.root?.left = tree.Node(data: 2);
  fullBinaryTree.root?.right = tree.Node(data: 3);
  fullBinaryTree.root?.left?.left = tree.Node(data: 4);
  fullBinaryTree.root?.left?.right = tree.Node(data: 5);
  fullBinaryTree.root?.right?.left = tree.Node(data: 6);
  fullBinaryTree.root?.right?.right = tree.Node(data: 7);

  print('Inorder traversal');
  trees.inorderTraversal(fullBinaryTree.root);
  print(
      'Is full binary tree? ${fullBinaryTree.isFullBinaryTree(fullBinaryTree.root)}');

  print('***** PERFECT BINARY TREE *****');
  var perfectBinaryTree = PerfectBinaryTree();
  perfectBinaryTree.root = tree.Node(data: 1);
  perfectBinaryTree.root?.left = tree.Node(data: 2);
  perfectBinaryTree.root?.right = tree.Node(data: 3);
  perfectBinaryTree.root?.left?.left = tree.Node(data: 4);
  perfectBinaryTree.root?.left?.right = tree.Node(data: 5);
  perfectBinaryTree.root?.right?.left = tree.Node(data: 6);
  perfectBinaryTree.root?.right?.right = tree.Node(data: 7);

  print('Inorder traversal');
  trees.inorderTraversal(perfectBinaryTree.root);
  print(
      'Is perfect binary tree? ${perfectBinaryTree.isPerfectBinaryTree(perfectBinaryTree.root)}');
  print('**********\n');

  print('***** COMPLETE BINARY TREE *****');
  var completeBinaryTree = CompleteBinaryTree();
  completeBinaryTree.root = tree.Node(data: 1);
  completeBinaryTree.root?.left = tree.Node(data: 12);
  completeBinaryTree.root?.left?.left = tree.Node(data: 5);
  completeBinaryTree.root?.right = tree.Node(data: 9);
  completeBinaryTree.root?.right?.left = tree.Node(data: 10);

  print('Inorder traversal');
  trees.inorderTraversal(completeBinaryTree.root);
  print(
      'Number of nodes ${completeBinaryTree.numberOfNodes(completeBinaryTree.root)}');
  print('Is complete binary tree? ${completeBinaryTree.isCompleteBinaryTree(
    completeBinaryTree.root,
    0,
    completeBinaryTree.numberOfNodes(
      completeBinaryTree.root,
    ),
  )}');
  print('**********\n');

  print('***** QUESTIONS *****');
  var questions = Questions();
  print(questions.reverseAString('Hi this is a test string'));
  print(questions.mergedSortedArray([1, 2, 4, 6], [4, 4, 6]));
  print(questions.twoSum([2, 7, 11, 15], 17));
  print(questions.maxSubArray([5, 4, -1, 7, 8]));
  print(questions.movesZero([0, 2, 0, 1]));
  print(questions.containsDuplicate([1, 1, 1, 3, 3, 4, 3, 2, 4, 2]));
  print(questions.rotateArray([-1, -100, 3, 99], 2));
  print(questions.longestWord('fun&!! time'));
  print(questions.recurringNumber([1, 2, 3, 4, 5, 2, 1]));
  print('**********\n');

  print('***** BINARY SEARCH TREE *****');
  var binarySearchTree = BinarySearchTree();
  binarySearchTree.insert(41);
  binarySearchTree.insert(20);
  binarySearchTree.insert(11);
  binarySearchTree.insert(29);
  binarySearchTree.insert(32);
  binarySearchTree.insert(65);
  binarySearchTree.insert(50);
  binarySearchTree.insert(91);
  binarySearchTree.insert(72);
  binarySearchTree.insert(99);
  binarySearchTree.remove(65);
  binarySearchTree.inorderTraversal(binarySearchTree.root);
  print('**********\n');

  print('***** GRAPHS *****');
  var graphs = Graphs();
  graphs.addVertex(0);
  graphs.addVertex(1);
  graphs.addVertex(2);
  graphs.addVertex(3);
  graphs.addVertex(4);
  graphs.addVertex(5);
  graphs.addVertex(6);
  graphs.addConnection(3, 1);
  graphs.addConnection(3, 4);
  graphs.addConnection(4, 2);
  graphs.addConnection(4, 5);
  graphs.addConnection(1, 2);
  graphs.addConnection(1, 0);
  graphs.addConnection(0, 2);
  graphs.addConnection(6, 5);
  graphs.printConnections();
  print('**********\n');
}
