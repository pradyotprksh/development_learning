class Queue {
  final _queue = List.generate(5, (index) => -1);
  var _rear = -1;
  var _front = -1;
  final _maxSize = 5;

  void enqueue(int value) {
    if (isFull()) {
      print('Queue full. Can\'t add $value');
    } else {
      ++_rear;
      if (_front == -1) {
        ++_front;
      }
      _queue[_rear] = value;
      print('Added $value');
      printQueue();
    }
  }

  void dequeue() {
    if (isEmpty()) {
      print('Queue empty');
    } else {
      _queue[_front] = -1;
      ++_front;
      if (_front > _rear) {
        _front = -1;
        _rear = -1;
      }
      print('Removed last element');
      printQueue();
    }
  }

  bool isEmpty() {
    return _front == -1 && _rear == -1;
  }

  bool isFull() {
    return _rear == _maxSize - 1;
  }

  void peek() {
    print('Top - ${_queue[_front]}');
  }

  void printQueue() {
    var elements = '';
    for (var i in _queue) {
      if (i == -1) {
        elements = '$elements#--->';
      } else {
        elements = '$elements$i--->';
      }
    }
    print(elements);
  }
}
